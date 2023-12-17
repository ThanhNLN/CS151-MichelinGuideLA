# CS151-Project: Michelin Restaurant GUI
Team #7: Le Ngoc Thanh Nguyen, Annie Luu, Irene Chen
---
__Project Proposal Contributions:__
- Le Ngoc Thanh Nguyen: 
  - Idea 
  - Problem/Issue 
  - Functionality
- Annie Luu: 
  - High Level description
  - Assumptions/Operating Environments/Intended Usage
- Irene Chen: 
  - Problem/Issue
  - Functionality
  - Operations
---
__Project Presentation Contributions:__
- Le Ngoc Thanh Nguyen: 
  - create all slides (problem, solution, potential)
  - present to class (also presented for Irene because her throat hurt)
    - problem slide
    - 2 UML slides
    - design patterns and OOP ideas explanations 
    - Challenges and Potential
- Annie Luu: 
  - create all slides (problem, solution, challenges, UML diagrams)
  - present to class (also presented for Irene because her throat hurt)
    - Solution slide
    - 2 UML slides
    - Database explanation
    - Challenges and Potential
- Irene Chen: 
  - create all slides (problem, solution, design patterns/OOP ideas, UML diagrams)
  - assisted with code demo/operating computer during presentation
---
__Detailed Project Contributions:__
- Le Ngoc Thanh Nguyen: 
  - Restaurant.java
  - RestaurantDetailsController.java (hyperlink)
  - RestaurantDetailsScreen.fxml (hyperlink)
  - webScrape folder and json file
  - MySQL (ensure unique, no dupes)
  - UML diagram (Class diagram)
- Annie Luu: 
  - MySQL server (JDBC.java)
  - databaseDisplayControls (database code)
  - webScrape and json file
  - UML diagrams (Sequence, Class, Use Case, State)
  - Final Project Report
- Irene Chen: 
  - JavaFX (App, fxml files, Controllers)
  - databaseDisplayControls package
  - RememberedSelection.java
  - webScrape and json file (fix issues/bugs)
  - UML diagrams (Use Case, State)
  - Final Project Report
---
__Problem/issue to resolve:__
- The Michelin Restaurant Guide website can be overwhelming and confusing. 
- Users do not always have internet connection.

__Solution:__
- We aim to simplify the user interface to allow for efficient searching both online and offline. 
- We accomplished this by web scraping to create a database that can be loaded offline and creating a simple JavaFX GUI.
---
__If applicable, describe assumptions / operating environments / intended usage__
- Assumption: User wants to search for Michelin star restaurants in Los Angeles area. 
- Operating environments: Computer, GUI interface 
- Intended usage: Simplify the way users searching for Michelin restaurants around the LA area can access information, both online and offline.  
---
__Diagrams:__
- [Class Diagram](https://github.com/ThanhNLN/CS151-MichelinGuideLA/blob/32d8ce6282dc87a5e8b076a5f9fdf6ce01546f01/diagrams/CS151_UML_Diagrams-Class.jpg) 
- [State Diagram](https://github.com/ThanhNLN/CS151-MichelinGuideLA/blob/e5fd8acc79a15f2d4d1a5118fc762f063d82789c/diagrams/CS151_UML_Diagrams-State.jpg)
- [Use Case Diagram](https://github.com/ThanhNLN/CS151-MichelinGuideLA/blob/e5fd8acc79a15f2d4d1a5118fc762f063d82789c/diagrams/CS151_UML_Diagrams-Use_Case.jpg)
- [Sequence Diagram](https://github.com/ThanhNLN/CS151-MichelinGuideLA/blob/32d8ce6282dc87a5e8b076a5f9fdf6ce01546f01/diagrams/CS151_UML_Diagrams-Sequence.jpg) 
---
__High-level description of your solution which may include (but is not limited to), your plan and approach. Be as specific as possible.__
- We use Python Web Scrapping to get restaurant data around LA area from the Michelin Restaurant Guideline, save it as a .json file, and use it as our database. 
  - The database will use a List of HashMaps. 
- We use OOP to build the program back-end from the .json file 
  - Then, we use JavaFX to build a GUI that allows users to lookup restaurants based on location, price, and cuisine type. 
    - Once an option is selected, it will list the restaurants that fall into the respective category.  
      - Once a restaurant is selected, it will provide the basic information, which includes name of restaurant, address, price range, and cuisine. There will also be a link to the restaurant’s Michelin Guide website page if the user wishes for more information. 
---
__Functionality: describe how your solution tackles the issues__
- Our solution simplifies how users access Michelin restaurants data based on the users’ choices. 
- We tackle this issue by implementing the functionalities of the Michelin restaurant website in GUI where users can access restaurant information in the area around Los Angeles. 
- Our GUI will be simple with fewer options. Each option will guide the user to the next step, leaving less room for ambiguity. 
---
__Operations: List operations for each intended user (in list format). Be precise and specific.__  
- Run program - main interface shows 3 options to look up: 
  - Location: selection of this option displays list of cities  
    - [city] - choose a city from the provided list, and the program will display the list of restaurants in that city
      - [restaurant] - select a restaurant from the list, and the program will display the restaurant information  
        - [restaurant link] - click on link to take you to the restaurant webpage for more information 

  - Price Range: selection of this option displays buttons represented price ranges 
    - [$] - choose a price range from the provided options, and the program will display the list of restaurants in that price range 
      - [restaurant] - select a restaurant from the list, and the program will display the restaurant information 
        - [restaurant link] - click on link to take you to the restaurant webpage for more information 

  - Cuisine: selection of this option displays list of cuisines 
    - [cuisine] - choose a cuisine from the provided list, and the program will display the list of restaurants in that category of cuisine 
      - [restaurant] - select a restaurant from the list, and the program will display the restaurant information 
        - [restaurant link] - click on link to take you to the restaurant webpage for more information 
---
__Steps to Run Code:__
- May need to install MySQL and mysql-connector-j-8.2.0.jar
- May need to create server using information in JDBC.java file
  - database = "jdbc:mysql://localhost:3306/"
  - user = "root"
  - password = "password"
- Run App.java
- Then, click on the buttons/selections that you want in order to filter and find your desired Michelin Restaurant
---
__Snapshot of Running Program:__
- [Snapshot 1: Initial screen when first run](https://github.com/ThanhNLN/CS151-MichelinGuideLA/blob/375194ad130bc9f63867bd1c77d609a12f13dbea/programSnapshots/snapshot_1.png)
- [Snapshot 2: Selected a button (Location)](https://github.com/ThanhNLN/CS151-MichelinGuideLA/blob/375194ad130bc9f63867bd1c77d609a12f13dbea/programSnapshots/snapshot_2.png) 
- [Snapshot 3: Selected a location (Alhambra)](https://github.com/ThanhNLN/CS151-MichelinGuideLA/blob/375194ad130bc9f63867bd1c77d609a12f13dbea/programSnapshots/snapshot_3.png) 
- [Snapshot 4: Selected a restaurant (Henry's Cuisine)](https://github.com/ThanhNLN/CS151-MichelinGuideLA/blob/375194ad130bc9f63867bd1c77d609a12f13dbea/programSnapshots/snapshot_4.png) 
---
__OOP Concepts:__
- MVC Pattern: used fxml and controllers
- Strategy Pattern: the databaseDisplayControls package
- SOLID: 
  - S: the databaseDisplayControls package
  - O: using the databaseDisplayControls package, don't need to modify existing code to add new handlers/buttons
- Reduced Duplicate code: selection and the back button call the same code to load the screen
- 4 Pillars (Inheritance, Encapsulation, Abstraction, Polymorphism): databaseDisplayControls package (interface, method overriding)

  

