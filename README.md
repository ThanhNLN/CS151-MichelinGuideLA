# CS151-Project: Michelin Restaurant GUI 

• Team #7: 
Le Ngoc Thanh Nguyen 
Annie Luu 
Irene Chen 

• Team members working on the proposal: Le Ngoc Thanh Nguyen, Annie Luu, Irene Chen 
• Problem/issue to resolve:  
The Michelin Restaurant Guide website can be overwhelming and confusing. We aim to simplify the user interface to allow for efficient searching. 

• If applicable, describe assumptions / operating environments / intended usage  
Assumption: User wants to search for Michelin star restaurants in Los Angeles area. 
Operating environments: Computer, GUI interface 
Intended usage: Simplify the way users searching for Michelin restaurants around the LA area can access information.  

• High-level description of your solution which may include (but is not limited to), your plan and approach. Be as specific as possible.  
We use Python Web Scrapping to get restaurant data around LA area from the Michelin Restaurant Guideline, save it as a .json file, and use it as our database. 
The database will use a List of HashMaps. 

We use OOP to build the program back-end from the .json file 
Then, we use JavaFX to build a GUI that allows users to lookup restaurants based on location, price, and cuisine type. 
- Once an option is selected, it will list the restaurants that fall into the respective category.  
- Once a restaurant is selected, it will provide the basic information, which includes name of restaurant, address, price range, and cuisine. There will also be a link to the restaurant’s Michelin Guide website page if the user wishes for more information. 

• Functionality: describe how your solution tackles the issues  
Our solution simplifies how users access Michelin restaurants data based on the users’ choices. 
We tackle this issue by implementing the functionalities of the Michelin restaurant website in GUI where users can access restaurant information in the area around Los Angeles. 
Our GUI will be simple with fewer options. Each option will guide the user to the next step, leaving less room for ambiguity. 

• Operations: List operations for each intended user (in list format). Be precise and specific.  
Run program - main interface shows 3 options to look up: 
- Location: selection of this option displays list of cities 
[city] - choose a city from the provided list, and the program will display the list of restaurants in that city 
[restaurant] - select a restaurant from the list, and the program will display the restaurant information 
[restaurant link] - click on link to take you to the restaurant webpage for more information 

- Price Range: selection of this option displays buttons represented price ranges 
[$] - choose a price range from the provided options, and the program will display the list of restaurants in that price range 
[restaurant] - select a restaurant from the list, and the program will display the restaurant information 
[restaurant link] - click on link to take you to the restaurant webpage for more information 

- Cuisine: selection of this option displays list of cuisines 
[cuisine] - choose a cuisine from the provided list, and the program will display the list of restaurants in that category of cuisine 
[restaurant] - select a restaurant from the list, and the program will display the restaurant information 
[restaurant link] - click on link to take you to the restaurant webpage for more information 
  

