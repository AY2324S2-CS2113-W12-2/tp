# Active Edge User Guide 💪

## Introduction

Welcome to ActiveEdge! ActiveEdge is a productivity tool 
designed to help you track your health and fitness goals
effectively. Whether you want to manage your calorie 
intake, track your water consumption, set daily goals,
or keep a record of your exercises, ActiveEdge has got
you covered.

## Quick Start

To get started quickly with ActiveEdge, 
follow these simple steps:

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `ActiveEdge` from [here](https://github.com/AY2324S2-CS2113-W12-2/tp/releases).

## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
    - [Log meal: `log`](#log-meal--log)
    - [Log water intake : `log`](#log-water-intake--log)
    - [Log exercises: `log`](#log-exercises-log)
    - [View daily goals: `show`](#view-daily-goals-show)
    - [View daily calorie intake: `show`](#view-daily-calorie-intake-show)
    - [View daily water intake: `show`](#view-daily-water-intake-show)
    - [View all logged entries: `list`](#view-all-logged-entries-list)
    - [View all food items in the database: `foodData`](#view-all-food-items-in-the-database-fooddata)
    - [View all exercises in the database: `exerciseData`](#view-all-exercises-in-the-database-exercisedata)  
    - [Delete meal logs: `delete`](#delete-meal-logs-delete)
    - [Delete water logs: `delete`](#delete-water-logs-delete)
    - [Delete exercise logs: `delete`](#delete-exercise-logs-delete)
    - [Log a meal not in the database: `add`](#log-a-meal-not-in-the-database-add)
    - [Log an exercise not in the database: `add`](#log-an-exercise-not-in-the-database-add)
    - [Show daily summary: `summary`](#show-daily-summary-summary)
    - [Search for entries: `find`](#search-for-entries-find)
    - [Get help: `help`](#get-help-help)
    - [Clear all logged entries: `clear`](#clear-all-logged-entries-clear)
    - [Exit the program: `bye`](#exit-the-program-bye)
    
- [Frequently Asked Questions](#faq-)
- [Command Summary](#command-summary)
    

## Features 

---

At the start of the application, new users are prompted to enter their **weight**, **height**,
**daily calorie goal** and **daily water intake goal**.

---

### Log meal : `log`
Adds a new meal to the logs list along with the number of servings.


Format: `log m/<MEAL_NAME> s/<NUMBER_OF_SERVINGS>`

* The `<MEAL_NAME>` is a meal name that exists in our food database. Check here to add a new meal that
* The `<NUMBER_OF_SERVINGS>` can be any positive integer value.  

Sample input: `log m/chicken rice s/1`  
Expected output: 


---

### Log water intake : `log`
Logs the quantity of water consumed.

Format: `log w/<AMOUNT_OF_WATER>`

* The `<AMOUNT_OF_WATER>` is the volume of water intake in milliliters(**ml**).

Sample input: `log w/100`   
Expected output:

---
### Log exercises: `log`
Logs the exercises performed.

Format: `log e/<EXERCISE_NAME> d/<DURATION>`

* The `EXERCISE_NAME` is the form of exercise done.
* The `DURATION` is the number of **minutes** the exercise has been done.

Sample input: `log e/running d/10`  
Expected output:

---

### View daily goals: `show`
Displays the current daily calorie goal, and the water intake target.

Sample input: `show g`  
Expected output:

---

### View daily calorie intake: `show`
Displays the total calorie intake, the total amount of calories burnt and where you stand in regard to your goals.

Sample input: `show c`  
Expected output:

---
### View daily water intake: `show`
Displays the total water intake, and the percentage of water
that has been consumed in comparison to the daily water intake goal.

Sample input: `show w`  
Expected output:

---

### View all logged entries: `list`
Displays entries logged throughout the application usage period.

Sample input: `list`    
Expected output:
---

### View all food items in the database: `foodData`
Displays all the food items in the application database.

Sample input: `foodData`    
Expected output:
---

### View all exercises in the database: `exerciseData`
Displays all forms of exercises in the application database.

Sample input: `exerciseData`    
Expected output:
---

### Delete meal logs: `delete`

Deletes a meal from the logs list. By default, it deletes the oldest log.

Format:  `delete <MEAL_NAME>`

If you have **multiple logs with the same meal name**, use the additional parameter N to specify which entry to delete.

Format: `delete <MEAL_NAME> i/<N>`

* The `MEAL_NAME` is the name of the meal the user
  previously logged in and wishes to delete.
* The `N` is the N<sup>th</sup> log from the same meal that the user wishes to delete. Note: `N` starts with 1.

If you are unsure about the index, try `list` command and figure out the index first.

Sample input 1: `delete chicken rice`   
Expected output 1:


Sample input 2: `delete chicken rice i/2`   
Expected output 2:

---

### Delete water logs: `delete`

Deletes a water log from the logs list. By default, it deletes the oldest log.

Format:  `delete <QUANTITY_OF_WATER> ml`

If you have **multiple logs with the same water quantity**, use the additional parameter N to specify which entry to delete.

Format: `delete <QUANTITY_OF_WATER> ml i/2`



* The `QUANTITY_OF_WATER` can be the quantity of water the
  user logged previously and wishes to delete.
* The `N` is the N<sup>th</sup> log from the same meal that the user wishes to delete. Note: `N` starts with 1. 
  
If you are unsure about the index, try `list` command and figure out the index first.

Sample input 1: `delete 1000 ml`    
Expected output 1:  

Sample input 2:`delete 1000 ml i/2`     
Expected output 2:

---

### Delete exercise logs: `delete`

Deletes an exercise log from the logs list.  By default, it deletes the oldest log.

Format:  `delete <EXERCISE_NAME>`

If you have **multiple logs with the same exercise**, use the additional parameter N to specify which entry to delete.

Format: `delete <EXERCISE_NAME> i/<N>`

* The `EXERCISE_NAME` is the form of exercise
  previously logged in and the user wishes to delete.
* The `N` is the N<sup>th</sup> log from the exercise that the user wishes to delete. Note: `N` starts with 1.

If you are unsure about the index, try `list` command and figure out the index first.

Sample input 1: `delete running`   
Expected output 1:


Sample input 2: `delete running i/2`   
Expected output 2:

---

### Log a meal not in the database: `add`
Logs a new meal that is not available in the food database.

Format: `add m/<MEAL_NAME> c/CALORIES_PER_SERVING s/<NUMBER_OF_SERVINGS>`

* The `<MEAL_NAME>` is any food item.
* The `CALORIES_PER_SERVING` is the calorie amount of the food item
* The `<NUMBER_OF_SERVINGS>` can be any positive integer value.

Sample input: `add m/carrot cake c/500 s/2`     
Expected output:

---
### Log an exercise not in the database: `add`
Adds a new exercise item to the existing list of exercises.

Format: `add e/<EXERCISE> c/<CALORIES_BURNT_PER_MIN> d/<DURATION_IN_MIN>`

* The `<EXERCISE>` is the form of exercise.
* The `<CALORIES_BURNT_PER_MIN>` can be a rough gauge of calories being burnt.
* The `<DURATION_IN_MIN>` can be any positive integer value.

Sample input: `add e/calisthenics c/10 d/5`     
Expected output:

---

### Show daily summary: `summary`
Shows a daily summary of food, water intake, and goals.

Sample input: `summary`  
Expected output:
---

### Search for entries: `find`
Searches for entries with a specified keyword.

Format:  `find <KEY_WORD>`


Sample input: `find sushi`  
Expected output:

---

### Get help: `help`
Provides all the possible commands inside ActiveEdge application.

Format: `help`  
Expected output:

---

### Clear all logged entries: `clear`
Deletes all the logs entered throughout the usage time period.

Sample input: `clear`       
Expected output:

---

### Exit the program: `bye`
Exits the ActiveEdge application.

Sample input: `bye`     
Expected output:

---

## FAQ ❓

**Q**: How do I transfer my data to another computer? 

**A**:
Create a "data" folder within the ActiveEdge application directory, 
and then copy the "data.txt" file into this newly created folder.

**Q**: I accidentally deleted an entry. Is there a way to recover it?

**A**: Unfortunately, deleted entries are not recoverable, so it's 
important to be careful when using the delete command.

**Q**: I accidentally cleared the data. Is there a way to recover it?

**A**: Unfortunately, cleared data are not recoverable, so it's
important to be careful when using the clear command.


## Command Summary

Here's a quick summary of commands available in **ActiveEdge**:

1. Log meal: `log m/<MEAL_NAME> s/<NUMBER_OF_SERVINGS>`    
2. Log water intake : `log w/<AMOUNT_OF_WATER>`    
3. Log exercises: `log e/<EXERCISE_NAME> d/<DURATION>`     
4. View daily goals: `show g`  
5. View daily calorie intake: `show c`     
6. View daily water intake: `show w`      
7. View all logged entries: `list`     
8. View all food items in the database: `foodData`     
9. View all exercises in the database: `exerciseData`  
10. Delete meal logs: `delete <MEAL_NAME>`. [Click here](#delete-meal-logs-delete) if you have multiple meal logs from the same meal.
11. Delete water logs: `delete <QUANTITY_OF_WATER> ml` [Click here](#delete-water-logs-delete) if you have multiple water intake logs with the same amount.    
12. Delete exercise logs: `delete <EXERCISE_NAME>` [Click here](#delete-exercise-logs-delete) if you have multiple exercise logs from the same exercise.    
13. Log a meal not in the database: `add m/<MEAL_NAME> c/CALORIES_PER_SERVING s/<NUMBER_OF_SERVINGS>`   
14. Log an exercise not in the database: `add e/<EXERCISE> c/<CALORIES_BURNT_PER_MIN> d/<DURATION_IN_MIN>`  
15. Show daily summary: `summary`   
16. Search for entries: `find`  
17. Get help: `help`    
18. Clear all logged entries: `clear`   
19. Exit the program: `bye`
