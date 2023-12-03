import requests
from bs4 import BeautifulSoup
import collections
import json


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
        # collect all the pages
        nextPages = self.find_all_pages(subLink)

        # print(nextPages)  #test

        # get the data of all the restaurants on each page
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

    def find_all_pages(self, sub_link):
        #check if initial page exists, then find other pages
        pages = set()  # set to contain all the pages (no dupes)
        link = self._WebLink + sub_link  # full link

        # try to open the full link
        try:
            requests.get(link)
            pages.add(sub_link)  # add to set if link is validdd
        except requests.exceptions.RequestException as error:
            print("Requests exception", error)
            return sorted(pages)

        # check for next available page
        self.find_all_pages_helper(sub_link, pages, len(pages))  # should this go in the try?
        return sorted(pages)

    def find_all_pages_helper(self, sub_link, pages, size):
        link = self._WebLink + sub_link
        initialSize = size  # to keep track if an subLink was added to the set

        # try to open the full link
        try:
            page = requests.get(link)
        except requests.exceptions.RequestException as error:
            print("Requests exception", error)
            return sorted(pages)

        # search the link for more links - finding the location
        soup = BeautifulSoup(page.content, "lxml")
        tag = soup.find('ul', class_="pagination")

        for link in tag.find_all('a'):
            if not pages.__contains__(link['href']):
                sub_link = link['href']
                pages.add(sub_link)
        if initialSize != len(pages):  # if no new links were added
            self.find_all_pages_helper(sub_link, pages, len(pages))  # go to "the last" (new) subLink in pages
        return sorted(pages)


obj = BackEnd()
