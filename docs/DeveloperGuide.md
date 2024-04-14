# ActiveEdge Developer Guide 🥦🏃‍♂️

## Table of Contents
- [Introduction](#introduction)
    - [Purpose](#purpose)
    - [How to use the Developer Guide](#how-to-use-the-developer-guide)
- [Quick Start](#quick-start)
- [Design & Implementation](#design--implementation)
    - [System Architecture](#system-architecture)
    - [Main Component](#main-component)
    - [UI Package](#ui-package)
    - [Parser Package](#parser-package)
    - [Command Package](#command-package)
    - [LogList Package](#entrylist-package)
    - [Storage Package](#storage-package)
- [Product Scope](#product-scope)
    - [Target User Profile](#target-user-profile)
    - [Value Proposition](#value-proposition)
- [User Stories](#user-stories)
- [Non-Functional Requirements](#non-functional-requirements)
- [Manual Testing](#instructions-for-manual-testing)
- [Command Summary](#command-summary)
- [Glossary](#glossary)
- [Acknowledgements](#acknowledgements)

## Introduction
**ActiveEdge** is a program for a university students to manage their food intake, water intake and exercises via the Command Line Interface (CLI).
### Purpose
This document specifies the architectural and software design decisions in the implementation of **ActiveEdge**.


### How to Use a Developer Guide

Are you a newcomer?
No problem, navigate to the Quick Start section.
Feeling disoriented within the content?
Refer to the Table of Contents to find the appropriate pages.
Require assistance with the features?
Consult the Features section in the User Guide for thorough guidance.
Have inquiries for us?
Visit the FAQ section in the User Guide.
Seeking a brief overview of all features?
Check out the Command Summary page for a summary of all commands.
Uncertain about a specific term's meaning?
Refer to the Glossary page for clarification.

## Quick Start

To get started quickly with ActiveEdge,
follow these simple steps:

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `ActiveEdge` from [here](https://github.com/AY2324S2-CS2113-W12-2/tp/releases). 
3. Copy the file into the folder you want to use as the home folder for your ActiveEdge.
4. Open a command terminal, cd into the home folder you put the jar file in, and run the following command: java -jar ActiveEdge.jar. You should see a welcome screen.
5. Type commands beside >>> and press Enter to execute it.
(e.g. typing ```help``` and pressing Enter will show the help page).
## Design & implementation 🏹  
### System Architecture

![System Architecture](https://github.com/SuveenE/tp/blob/master/images/System-Architecture.png?raw=true)

The Architecture Diagram above shows a high-level overview of the architectural design of ActiveEdge.

Active Edge is comprised of 8 major components:

```Main```: The entry point of the application.\
```UI```: A package responsible for handling user interactions, both capturing input and displaying output.\
```Parser```: A package that analyzes and interprets the user's input commands.\
```Command```: A package tasked with executing specific actions based on the user's commands.\
```Storage```: A package focused on persisting and retrieving all relevant data from the application's local storage system, ensuring data continuity and integrity.\
```LogList```: Maintains logged data throughout the application's runtime.\
```FoodData```: Active Edge's food database\
```ExerciseData```: Active Edge's exercise database\
### Generic Sequence Diagram
The generic sequence diagram provides a visual representation of the interactions between various components within the
ActiveEdge application. It illustrates how user commands are parsed, executed, and interact with different modules such
as user interface, data storage, and task management.

![Generic Sequence Diagram](https://github.com/AY2324S2-CS2113-W12-2/tp/blob/master/images/GenericSequenceDiagram.png?raw=true)

### Parser
The Parser components can be found within the Parser package.
It is responsible for parsing the input String of the user, and returning an appropriate XYZCommand class.
If the input is invalid, it throws exceptions to the Error package for error handling.
The Parser Class Diagram below shows how Execute, Parser, Error, Command classes of their respective packages work together.

![Parser](https://github.com/SuveenE/tp/blob/master/images/Parser.png?raw=true)

### Command

In the provided code, commands are represented as individual classes, each encapsulating a specific action or operation 
within the ActiveEdge application, promoting modularity and separation of concerns. The graph below depicts the 
interactions between the different components of  ActiveEdge  when water, a meal or an exercise is logged in.

![Command](https://github.com/AY2324S2-CS2113-W12-2/tp/blob/master/images/LogCommand.png?raw=true)

### Storage
Storage is the main class responsible for file operations and data management.
UserDetailsList, LogHeight, and LogWeight handle user details such as height and weight logs.
TaskList manages various types of tasks including goals, meals, water intake, and exercises.
GoalTask, MealTask, WaterTask, and ExerciseTask are specific task types with their attributes.
AddBMICommand, AddHeightCommand, and AddWeightCommand are commands for adding BMI, height, and weight respectively.
The relationships depicted in the diagram are primarily composition and inheritance:


![Storage](https://github.com/SuveenE/tp/blob/master/images/Storage.png?raw=true)

Storage has composition relationships with UserDetailsList and TaskList as it manages instances of these classes.
UserDetailsList and TaskList have a composition relationship with their respective contained classes (LogHeight, LogWeight, GoalTask, MealTask, WaterTask, ExerciseTask) 
as they hold lists of instances of these classes. AddBMICommand, AddHeightCommand, and AddWeightCommand are standalone commands 
used within the Storage class but don't directly interact with the other classes in the diagram.

### Main Component
The ActiveEdge class serves as the main entry point for the ActiveEdge application, handling user input parsing and interaction through a command-line interface.
![Main Component](https://github.com/AY2324S2-CS2113-W12-2/tp/blob/master/images/Main-component2%20UML.png?raw=true)


### UI Package
The activeedge.ui package encapsulates the user interface components of the ActiveEdge application, facilitating interactions such as printing welcome messages, 
logging meal and exercise data, displaying goal information, providing help instructions, and managing user queries and commands for health tracking and goal setting.
![Ui Package](https://github.com/AY2324S2-CS2113-W12-2/tp/blob/master/images/Ui.png?raw=true)

Under commands, there are 17 sub-components:
```AddBMICommand```: A command responsible for calculating and adding Body Mass Index (BMI) information.
```AddGoalsCommand```: A command responsible for users to set and track their fitness goals.
```AddHeightCommand```: A command responsible for users to input and track their height information for comprehensive health tracking.
```AddWeightCommand```: A command responsible for users to input and track their weight measurements to monitor their progress and achievements.
```ClearCommand```: A command responsible for users to clear or delete entries, such as meals, from their tracking history.
```DeleteTaskCommand```: A command responsible for users to delete or remove exercise tasks.
```FindCommand```: A command responsible for users to search specific keywords.
```ShowSummaryCommand```: A command responsible for users to view a summary or overview of their calorie intake,calorie burnt during exercise .
```ViewWaterIntakeCommand```: A command responsible for users to view and track their water intake levels.

### Task Package
This package is structured to manage and represent various types of tasks within an application, potentially for goal tracking, 
exercise logging, meal recording, and water intake monitoring. There are 3 main components of this package.
![Ui](https://github.com/SuveenE/tp/blob/master/images/Task_package.png?raw=true)

**Base class:`Task`**
+ **Purpose**: Serves as the foundational class for all types of tasks. It encapsulates the common
attribute all tasks share, which is a description
+ **Attributes**: `description`: A `String` that provides a brief description of the task.
+ **Methods**: 
  + `getDescription()`
  + `toString()`

**Derived class:`GoalTask`**
+ **Attributes**: Stores information about goals
+ **Functionality**: Allows users to log their goals

**Derived class:`LogExercise`**
+ **Attributes**: Stores information about exercises, such as the name of food, duration, and calories burnt 
+ **Functionality**: Allows users to log their exercsies

**Derived class:`MealTask`**
+ **Attributes**: Stores information about meals, such as the name of food, number of servings, and calories
+ **Functionality**: Allows users to log their meals 

**Derived class:`WaterTask`**
+ **Attributes**: Stores information about water intake, such as the amount of water
+ **Functionality**: Allows users to log their water intake

**Utility class:`TaskList`**
+ **Purpose**: Manages a collection of `Task` objects (including all derived types).
+ **Attributes**: `tasksList`: A static ArrayList that stores instances of `Task` and its subclasses.
+ **Methods**:
  + `add(Task task)`
  + `delete(int index)`
  + `get()`
  + `clearTasks()`
  
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile
Our target users are NUS university students, including fitness enthusiasts and those striving for a healthier
lifestyle. They struggle with time constraints due to academic and social commitments, making it difficult to 
manage nutrition and exercise. Our app aims to provide convenient calorie tracking and personalized exercise routines 
tailored to their individual needs, helping them achieve their fitness goals amidst the challenges of university life.

### Value proposition

**Comprehensive Health Tracking:** Users can log various aspects of their health, including meals, water intake, exercise activities, height, and weight. This comprehensive tracking enables users to maintain a holistic view of their health behaviors.

**Goal Setting and Monitoring:** The application allows users to set personalized goals for calorie intake, water consumption, and other health-related metrics. Users can track their progress towards these goals over time, fostering motivation and accountability.

**Customized Logging:** Users can log specific details about their meals, exercise routines, and water intake, providing granular insights into their health habits. This customization allows for tailored tracking based on individual preferences and requirements.

**Error Handling and User Guidance:** The application includes error handling mechanisms and user guidance to ensure smooth interaction and data input. This feature enhances user experience by reducing frustration and confusion during usage.

**Data Persistence and Accessibility:** The application stores user data securely, allowing for easy access and retrieval across multiple sessions. This ensures that users can review their health history and progress over time, facilitating informed decision-making and goal adjustment.

**Clear User Interface:** The user interface of the application is designed to be intuitive and user-friendly, enabling users to navigate and interact with the app effortlessly. This simplicity enhances user engagement and satisfaction with the overall experience.



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
* *wcg* - Calorie goal (`change cg` used to change calorie goal)
## Instructions for manual testing

1. Launching the App 
    - Refer to the [Quick Start](#quick-start) to get ActiveEdge set up and running.
    - Enter your height, weight, calorie and water goals when prompted.
   
2. Getting help
    - Run the `help` command by typing `help` and hitting `Enter` key on your keyboard.
   
3. With the help guide, you can `log` or `add` meal, exercises and water to your tracker.
    - Run the following commands `log m/chicken rice s/1`/ `log e/running d/1` /
      `add m/[FOOD] c/[CALORIES_PER_ERVING] s/[SERVINGS]`/ 
      `add e/[EXERCISES] c/[CALORIES_BURNT_PER _MIN] d/[DURATION_IN_MINUTES]`
   
4. Run `list` to see your logged data.

5. Use `delete` command with help of User Guide to delete any logged entries.
    - Run the following commands `delete chicken rice` / `delete running`
   
6. Run `summary` to track your calorie and water intake.

7. Run the `bye` command to exit app

## Command Summary

| Action                              | Format                                                            |
|-------------------------------------|-------------------------------------------------------------------|
| Log meal                            | `log m/<MEAL_NAME> s/<NUMBER_OF_SERVINGS>`                        |
| Log water intake                    | `log w/<AMOUNT_OF_WATER>`                                         |
| Log exercises                       | `log e/<EXERCISE_NAME> d/<DURATION>`                              |
| View daily goals                    | `show g`                                                          |
| View daily calorie intake           | `show c`                                                          |
| View daily water intake             | `show w`                                                          |
| View all logged entries             | `list`                                                            |
| View all food items in the database | `foodData`                                                        |
| View all exercises in the database  | `exerciseData`                                                    |
| Delete meal logs                    | `delete <MEAL_NAME>`                                              |
| Delete water logs                   | `delete <QUANTITY_OF_WATER>`                                      |
| Delete exercise logs                | `delete <EXERCISE_NAME>`                                          |  
| Log a meal not in the database      | `add m/<MEAL_NAME> c/CALORIES_PER_SERVING s/<NUMBER_OF_SERVINGS>` | 
| Log an exercise not in the database | `add e/<EXERCISE> c/<CALORIES_BURNT_PER_MIN> d/<DURATION_IN_MIN>` |
| Change height input                 | `change h`                                                        |
| Change weight input                 | `change w`                                                        |
| Change daily calorie intake goal                | `change cg`                                                        |
| Change daily water intake goal                | `change wg`                                                        |
| Show daily summary                  | `summary`                                                         |
| Search for entries                  | `find`                                                            |
| Get help                            | `help`                                                            |
| Clear all logged entries            | `clear`                                                           |
| Exit the program                    | `bye`                                                             |


## Acknowledgements


