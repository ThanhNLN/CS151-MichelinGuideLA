import urllib.request as ur
import requests
from bs4 import BeautifulSoup
import collections
import json
import lxml

class BackEnd:
    _WebLink = "https://guide.michelin.com"
    _dataFile = "LosAngelesData.json"
    def __init__(self):
        self.__resDict = collections.defaultdict(dict)
        self.__resList = []

        LA = "/us/en/california/us-los-angeles/restaurants"

        self.getAndSaveData(LA)


    def getAndSaveData(self, subLink):
        '''Get all restaurants' data from web and save into a json file'''
        self.dataFromWeb(subLink)
        # Update all restaurant if next page exist
        nextPages = self.checkForNextPage(subLink)
        for page in nextPages:
            self.dataFromWeb(page)
        self.updateAddress()

        try:
            with open(self._dataFile, 'w') as outFile:
                json.dump(self.__resList, outFile, indent=3)
        except FileNotFoundError as error:
            print("Can not open", self._dataFile, error)
    def updateAddress(self):
        '''Add the restaurant address to its info list'''

        for restaurant in self.__resList:
            link = restaurant['url']
            try:
                page = requests.get(link)
            except requests.exceptions.RequestException as error:
                print("Requests exception", error)
            soup = BeautifulSoup(page.content, "lxml")
            for tag in soup.find('li', class_="restaurant-details__heading--address"):
                restaurant['address'] = tag.text

    def dataFromWeb(self, subLink):
        '''Extract all restaurant data from the given website'''
        link = self._WebLink + subLink
        try:
            page = requests.get(link)
        except requests.exceptions.RequestException as error:
            print("Requests exception", error)
        soup = BeautifulSoup(page.content, "lxml")
        div = soup.find_all('div', class_="card__menu box-placeholder js-restaurant__list_item js-match-height js-map")
        for tag in div:
            for restaurant in tag.find_all('h3'):
                for link in restaurant.find_all('a'):
                    resName = link.text.strip().split('\n')[0]
                    restSubLink = link['href']
                    resURL = self._WebLink + restSubLink
            for cities in tag.find('div', class_="card__menu-footer--location flex-fill pl-text"):
                city = cities.text.strip().split(',')[0]
            for prices in tag.find('div', class_="card__menu-footer--price pl-text"):
                p = prices.text.strip().split("·")
                price = p[0].strip()
                cuisine = p[-1].strip()
            self.__resList.append({'name': resName, 'url':resURL,'location':city,'cost':price,'cuisine':cuisine})

    def checkForNextPage(self, subLink):
        '''Check if more than 1 page exist'''
        pages = set()
        link = self._WebLink + subLink
        try:
            page = requests.get(link)
        except requests.exceptions.RequestException as error:
            print("Requests exception", error)
        soup = BeautifulSoup(page.content, "lxml")
        for tag in soup.find('ul', class_="pagination"):
            for link in tag.find_all('a'):
                pages.add(link['href'])
        return sorted(pages)

obj = BackEnd()