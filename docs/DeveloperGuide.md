# ActiveEdge Developer Guide 🥦🏃‍♂️

## Table of Contents
- [Introduction](#introduction)
    - [Purpose](#purpose)
- [Quick Start](#quick-start)
- [Design & Implementation](#design--implementation)
    - [System Architecture](#system-architecture)
    - [Generic Sequence Diagram](#generic-sequence-diagram)
    - [Main Component](#main-component)
    - [Command Package](#command-package)
    - [UI Package](#ui-package)
    - [Parser Package](#parser-package)
    - [Storage Class](#storage-class)
    - [Log Package](#log-package)
    - [User Details Package](#userdetails-package)
- [Product Scope](#product-scope)
    - [Target User Profile](#target-user-profile)
    - [Value Proposition](#value-proposition)
- [User Stories](#user-stories)
- [Non-Functional Requirements](#non-functional-requirements)
- [Glossary](#glossary)
- [Manual Testing](#instructions-for-manual-testing)
- [Command Summary](#command-summary)
- [Acknowledgements](#acknowledgements)

## Introduction
Welcome to ActiveEdge! **ActiveEdge** is a productivity tool
designed to help you track your health and fitness goals
effectively via the **Command Line Interface (CLI)**. Whether you want to manage your calorie
intake, track your water consumption, set daily goals,
or keep a record of your exercises, **ActiveEdge** has got
you covered.
### Purpose
This document specifies the architectural and software design decisions in the implementation of **ActiveEdge**.    
For an in-depth understanding of how to use the application and explanations into each specific command, 
feel free to visit our [Active Edge User Guide](UserGuide.md).

## Quick Start

To get started quickly with ActiveEdge,
follow these simple steps:

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `ActiveEdge` from [here](https://github.com/AY2324S2-CS2113-W12-2/tp/releases). 
3. Copy the file into the folder you want to use as the home folder for your ActiveEdge.
4. Open a command terminal, cd into the home folder you put the jar file in, and run the following command: 
`java -jar ActiveEdge.jar`. You should see a welcome screen.
```
Welcome to ACTIVE EDGE!
Take the next step in your Healthy Lifestyle!
```
5. Type commands beside >>> and press Enter to execute it.
(e.g. typing ```help``` and pressing `Enter` will give you the list of available commands).

## Design & implementation 
### System Architecture

![System Architecture](https://github.com/SuveenE/tp/blob/master/images/System-Architecture.png?raw=true)

The Architecture Diagram above shows a high-level overview of the architectural design of ActiveEdge.

Active Edge comprises the following major components:

```Main```: A component for the entry point of the application.     
```UI```: A package responsible for handling user interactions, both capturing input and displaying output.     
```Parser```: A package that analyzes and interprets the user's input commands.     
```Command```: A package tasked with executing specific actions based on the user's commands.   
```Storage```: A package focused on persisting and retrieving all relevant data from the application's local storage system, ensuring data continuity and integrity.    
```Log```: Maintains logged data throughout the application's runtime.  
```UserDetails```: Maintains user details throughout the application's runtime. 
```FoodData```: Active Edge's food database.    
```ExerciseData```: Active Edge's exercise database.


### Generic Sequence Diagram
The generic sequence diagram provides a visual representation of the interactions between various components within the
ActiveEdge application. It illustrates how user commands are parsed, executed, and interact with different modules such
as user interface, data storage, and task management.

![Generic Sequence Diagram](https://github.com/AY2324S2-CS2113-W12-2/tp/blob/master/images/GenericSequenceDiagram.png?raw=true)

### Main Component
The `Main` component, residing as a method in the `ActiveEdge` class,serves as the main entry point for the ActiveEdge application, handling user input and
interaction through a command-line interface(CLI).

![Main Component](https://github.com/AY2324S2-CS2113-W12-2/tp/blob/master/images/Main-component2%20UML.png?raw=true)

### Command Package
The `commands` are represented as individual classes inside the `Command Package`, each encapsulating a specific action or operation
within the ActiveEdge application.  
The graph below depicts the
interactions between the different components of  ActiveEdge  when water, a meal or an exercise is logged in.

![Command](https://github.com/AY2324S2-CS2113-W12-2/tp/blob/master/images/LogCommand.png?raw=true)

[The command summary](#command-summary) section will give you a brief on all the available `command` classes.
### UI Package
The `activeedge.ui` package encapsulates the user interface components of the ActiveEdge application, facilitating
interactions such as printing welcome messages,
logging meal and exercise data, displaying goal information, and providing help instructions.

![Ui Package](https://github.com/AY2324S2-CS2113-W12-2/tp/blob/master/images/Ui.png?raw=true)

### Parser Package
The diagram outlines the structure of the ActiveEdge application's `Parser` package and its connections with
various Command classes. The `Parser` class is responsible for parsing user input and generating appropriate commands, 
utilizing `DateTimeFormatter` instances for date and time parsing.  
It interacts with Command classes such as logging 
commands (e.g., `LogWaterCommand`, `LogMealCommand`), listing commands (`ListFullCommand`, `DeleteLogCommand`), and other 
functionalities. This setup promotes modular design and efficient handling 
of user actions within the `ActiveEdge` application.

![Parser](https://github.com/Praneet-25/tp/blob/master/images/Parser.png?raw=true)

### Storage Class
The `Storage` class is responsible for handling file operations within the ActiveEdge application. 
It includes static methods which 
manage file creation, saving logs to files, and fetching data from files.

This class directly deals with the `LogList` and `UserDetailsList` classes to
make sure the data is being saved properly during runtime and locally.

![Storage](https://github.com/Praneet-25/tp/blob/master/images/Storage.png?raw=true)

### Log Package

The `Log` package comprises a base class and multiple derived classes structured to 
manage various types of user logs.  
The derived classes—`LogGoals`, `LogExercise`, `LogMeals`, and `LogWater`—extend the base 
class to cater to specific logging needs such as tracking calorie and water goals, 
exercises, meals, and water each equipped with relevant attributes and functionalities to log
different types of data.    

Additionally, a utility class, `LogList`, handles the organization
and management of all log types using methods to add, delete, and retrieve log entries, 
ensuring structured data handling within the program.

![Log](https://github.com/AY2324S2-CS2113-W12-2/tp/blob/master/images/Log.png?raw=true)




### User Details Package


The `UserDetails` package is designed to capture and manage various user metrics 
such as height, weight, and BMI within an application. This class is extended by three specific subclasses: `LogHeight`,
`LogWeight`, and `LogBMI`, each storing respective metrics along with the date and 
time of entry, and providing functionalities for users to log these details.    

Additionally, the package includes a utility class, `UserDetailsList`, which
operates a static ArrayList to manage collections of `UserDetails` objects. 
This class offers methods for adding, retrieving, deleting, and clearing
entries from the list.

![Userdetails](https://github.com/AY2324S2-CS2113-W12-2/tp/blob/master/images/Userdetails.png?raw=true)



## Product scope
### Target user profile
Our app targets NUS university students, including fitness enthusiasts and those striving for healthier lifestyles 
amidst busy schedules.  
It provides convenient features like **calorie tracking** and **exercise routines tracking**
tailored to individual needs.       

Given their time constraints, our users prefer a **command-line interface** for efficiency, 
favoring typing over mouse input. This design choice ensures seamless integration into their daily routines, helping 
them achieve fitness goals while managing university life.


### Value proposition

- Comprehensive health tracking and goal setting for various metrics such as meals, water intake, and exercise, promoting overall health management.
- Customizable logging and user-friendly error handling enhance personalization and ease of use.
- Secure data storage and intuitive interface ensure easy access to health history and straightforward app navigation.
- Extensive database of food and exercise data supports informed decision-making and effective progress tracking.



## User Stories

| Version | As a ... | I want to ... | So that I can ...|
|---------|----------|---------------|------------------|
| v1.0    |new user|see usage instructions of the app|refer to them when I forget how to use the application|
| v1.0    |returning user|see my previous logs|see my daily logs and progress|
| v1.0    |user|log calorie intake|record daily consumption to monitor deitary habits|
| v1.0    | user|Log water intake|Track hydration levels throughout the day|
| v2.0    |user|Search for keywords|Quickly find specific information or entries within logs|
| v2.0    |user|	Delete entries from log|	Remove incorrect or unnecessary data from records|
| v2.0    |user|log exercises|keep track of my physical activities|
| v2.0    |user|View daily summary|Access a summary of exercise, nutrition, and hydration for the day|
| v2.0    |new user|log in my height and weight|see what range my weight is in|




## Non-Functional Requirements
1. Should be portable and working on any mainstream OS as long as it has Java 11 or above installed.
2. Should be able to hold up to 100 food items or list of exercises without any lag in the performance of the app  for typical usage.
3. The average keyboard user should complete tasks more quickly using ActiveEdge compared to a standard GUI application.

## Glossary

* *Log* - Entering your food, water and exercise data into the tracker.
* *Add* - Adding new exercises and food items to existing food and exercise database.
* *List* - Listing down the users' logged entries.
* *Summary* - Summarises the logged data of total water, calorie intake comparing it to the goals of the users.
* *wg* - Water goal (`change wg` used to change water goal)
* *cg* - Calorie goal (`change cg` used to change calorie goal)
## Instructions for manual testing

1. Launching the App 
    - Refer to the [Quick Start](#quick-start) to get ActiveEdge set up and running.
    - Enter your height, weight, calorie and water goals when prompted.
   
2. Getting help
    - Run the `help` command by typing `help` and hitting `Enter` key on your keyboard.
   
3. With the help guide, you can `log` or `add` meal, exercises and water to your tracker.
    - Run the following commands `log m/chicken rice s/1`, `log e/running d/1`, 
      `add m/[FOOD] c/[CALORIES_PER_ERVING] s/[SERVINGS]`, 
      `add e/[EXERCISES] c/[CALORIES_BURNT_PER _MIN] d/[DURATION_IN_MINUTES]`
   
4. Run `list` to see your logged data.

5. Use `delete` command with help of User Guide to delete any logged entries.
    - Run the following commands `delete chicken rice` / `delete running`
   
6. Run `summary` to track your calorie and water intake.

7. Run the `bye` command to exit app.

## Command Summary

| Action                                | Format                                                             |
|---------------------------------------|--------------------------------------------------------------------|
| Log meal                              | `log m/<MEAL_NAME> s/<NUMBER_OF_SERVINGS>`                         |
| Log water intake                      | `log w/<AMOUNT_OF_WATER>`                                          |
| Log exercises                         | `log e/<EXERCISE_NAME> d/<DURATION>`                               |
| View daily goals                      | `show g`                                                           |
| View daily calorie intake             | `show c`                                                           |
| View daily water intake               | `show w`                                                           |
| View all logged entries               | `list`                                                             |
| View all food items in the database   | `foodData`                                                         |
| View all exercises in the database    | `exerciseData`                                                     |
| Delete meal logs                      | `delete <MEAL_NAME>`                                               |
| Delete water logs                     | `delete <QUANTITY_OF_WATER>`                                       |
| Delete exercise logs                  | `delete <EXERCISE_NAME>`                                           |  
| Log a meal not in the database        | `add m/<MEAL_NAME> c/CALORIES_PER_SERVING s/<NUMBER_OF_SERVINGS>`  | 
| Log an exercise not in the database   | `add e/<EXERCISE> c/<CALORIES_BURNT_PER_MIN> d/<DURATION_IN_MIN>`  |
| Change height input                   | `change h`                                                         |
| Change weight input                   | `change w`                                                         |
| Change daily calorie intake goal      | `change cg`                                                        |
| Change daily water intake goal        | `change wg`                                                        |
| Show daily summary                    | `summary`                                                          |
| Search for entries                    | `find`                                                             |
| Get help                              | `help`                                                             |
| Clear all logged entries              | `clear`                                                            |
| Exit the program                      | `bye`                                                              |


## Acknowledgements
Reference - [AB3 Developer Guide](https://se-education.org/addressbook-level3/DeveloperGuide.html)  
Meet the [people](https://ay2324s2-cs2113-w12-2.github.io/tp/AboutUs.html) behind ActiveEdge!  

