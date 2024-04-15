# Active Edge User Guide 💪

## Introduction

Welcome to ActiveEdge! **ActiveEdge** is a productivity tool 
designed to help you track your health and fitness goals
effectively. Whether you want to manage your calorie 
intake, track your water consumption, set daily goals,
or keep a record of your exercises, **ActiveEdge** has got
you covered.

## Quick Start

To get started quickly with ActiveEdge, 
follow these simple steps:

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ActiveEdge` from [here](https://github.com/AY2324S2-CS2113-W12-2/tp/releases).
3. Copy the file into the folder you want to use as the home folder for your `ActiveEdge`.
4. Open a command terminal, `cd` into the home folder you put the jar file in, and run the following command: `java -jar ActiveEdge.jar`. You should see a welcome screen.
```
Welcome to ACTIVE EDGE!
Take the next step in your Healthy Lifestyle!
```
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
    - [Change height input: `change`](#change-user-height-change)
    - [Change weight input: `change`](#change-user-weight-change)
    - [Change daily calorie intake goal: `change`](#change-daily-calorie-intake-goal-change)
    - [Change daily water intake goal: `change`](#change-daily-water-intake-goal-change)  
    - [Show daily summary: `summary`](#show-daily-summary-summary)
    - [Search for entries: `find`](#search-for-entries-find)
    - [Get help: `help`](#get-help-help)
    - [Clear all logged entries: `clear`](#clear-all-logged-entries-clear)
    - [Exit the program: `bye`](#exit-the-program-bye)
    
- [Frequently Asked Questions](#faq-)
- [Command Summary](#command-summary)
    

## Features 

---

At the start of the application, new users are prompted to enter the following information.
1. **Height** in centimeters (cm)
2. **Weight** in Kilograms (kg)
3. **Daily calorie goal** in calories
4. **Daily water intake goal** in millimeters (ml)

```
Since you are new here or your details are missing,
let's start with a few questions to set things up!
Please input your height (in cm): 
```
---

### Log meal : `log`
Logs the meals consumed along with the number of servings.

Format: `log m/<MEAL_NAME> s/<NUMBER_OF_SERVINGS>`

* The `<MEAL_NAME>` is a meal name that exists in our food database.  
  To see the meals in our database - [Click here](#view-all-food-items-in-the-database-fooddata)  
  To log a new meal that's not in the database - [Click here](#log-a-meal-not-in-the-database-add)
* The `<NUMBER_OF_SERVINGS>` can be any positive integer value.  

Sample input: `log m/chicken rice s/1`  
Expected output: 
```
You've logged 1 servings of chicken rice.
Estimated calories consumed: 450 kcal
```

---

### Log water intake : `log`
Logs the quantity of water consumed.

Format: `log w/<AMOUNT_OF_WATER>`

* The `<AMOUNT_OF_WATER>` is the volume of water intake in milliliters(**ml**).

Sample input: `log w/1000`   
Expected output:
```
You've logged 1000 ml of water.
```

---
### Log exercises: `log`
Logs the exercises performed.

Format: `log e/<EXERCISE_NAME> d/<DURATION>`

* The `EXERCISE_NAME` is the form of exercise done.   
  To see the exercises in our database - [Click here](#view-all-exercises-in-the-database-exercisedata)  
  To log a new exercise that's not in the database - [Click here](#log-an-exercise-not-in-the-database-add)
* The `DURATION` is the number of **minutes** the exercise has been done.

Sample input: `log e/running d/10`  
Expected output:
```
You've logged 10 minutes of running.
Estimated calories burnt: 100 kcal
```
---

### View daily goals: `show`
Displays the current daily calorie goal, and the water intake target.

Sample input: `show g`  
Expected output:
```
Current goals 
Daily calories intake goal: 2800 kcal
Daily water intake goal: 3000 ml
```
---

### View daily calorie intake: `show`
Displays the total calorie intake, the total amount of calories burnt and where you stand in regard to your goals.

Sample input: `show c`  
Expected output:
```
You have burned 100 kcal today!
You have consumed 450 kcal out of 2800 kcal
You're doing an excellent job of managing your calorie intake!
Calorie deficit at the moment --> 2450
```
---
### View daily water intake: `show`
Displays the total water intake, and the percentage of water
that has been consumed in comparison to the daily water intake goal.

Sample input: `show w`  
Expected output:
```
Total water consumed today: 1000 ml (33% of 3000ml goal).
```
---

### View all logged entries: `list`
Displays entries logged throughout the application usage period.

Sample input: `list`    
Expected output:
```
Logged data for today:
Food: 
____________________________________________________________
1. chicken rice | 1 servings | 450 cal (Recorded on: 2024-04-13 15:17)
____________________________________________________________

Water:
____________________________________________________________
1. 1000 ml (Recorded on: 2024-04-13 15:18)
____________________________________________________________

Exercises:
____________________________________________________________
1. running | 10 mins | 100 cal (Recorded on: 2024-04-13 15:18)
____________________________________________________________

```
---

### View all food items in the database: `foodData`
Displays all the food items in the application database.

Sample input: `foodData`    
Expected output: (Only the first 5 items are shown)
```
Meal: fried chicken | Calories per serving: 543 kcal
Meal: chicken burger | Calories per serving: 357 kcal
Meal: beef burger | Calories per serving: 500 kcal
Meal: fish burger | Calories per serving: 379 kcal
Meal: cheese pizza | Calories per serving: 220 kcal
...
```
---

### View all exercises in the database: `exerciseData`
Displays all forms of exercises in the application database.

Sample input: `exerciseData`    
Expected output: (Only the first 5 items are shown)
```
Exercise: running | Calories burnt per minute: 10 kcal
Exercise: cycling | Calories burnt per minute: 8 kcal
Exercise: swimming | Calories burnt per minute: 8 kcal
Exercise: jumping jacks | Calories burnt per minute: 10 kcal
Exercise: walking | Calories burnt per minute: 4 kcal
...
```
---

### Delete meal logs: `delete`

Deletes a meal from the logs list. By default, it deletes the oldest log.

Format:  `delete <MEAL_NAME>`

If you have **multiple logs with the same meal name**, use the additional parameter N to specify which log to delete.

Format: `delete <MEAL_NAME> i/<N>`

* The `MEAL_NAME` is the name of the meal the user
  previously logged in and wishes to delete.
* The `N` is the N<sup>th</sup> log from the same meal that the user wishes to delete. Note: `N` starts with 1.

If you are unsure about the index, try `list` command and figure out the index first.

Sample input: `delete chicken rice i/2`   
Expected output:
```
Log deleted: Meal | chicken rice | 4 servings | 1800 kcal (Recorded on: 2024-04-13 15:22)
```

---

### Delete water logs: `delete`

Deletes a water log from the logs list. By default, it deletes the oldest log.

Format:  `delete <QUANTITY_OF_WATER> ml`

If you have **multiple logs with the same water quantity**, use the additional parameter N to specify which log to 
delete.

Format: `delete <QUANTITY_OF_WATER> ml i/2`



* The `QUANTITY_OF_WATER` can be the quantity of water the
  user logged previously and wishes to delete.
* The `N` is the N<sup>th</sup> log from the same meal that the user wishes to delete. Note: `N` starts with 1. 
  
If you are unsure about the index, try `list` command and figure out the index first.

Sample input: `delete 1000 ml`    
Expected output:  
```
Log deleted: Water | 1000 ml (Recorded on: 2024-04-13 15:18)
```

---

### Delete exercise logs: `delete`

Deletes an exercise log from the logs list.  By default, it deletes the oldest log.

Format:  `delete <EXERCISE_NAME>`

If you have **multiple logs with the same exercise**, use the additional parameter N to specify which log to delete.

Format: `delete <EXERCISE_NAME> i/<N>`

* The `EXERCISE_NAME` is the form of exercise
  previously logged in, and the user wishes to delete.
* The `N` is the N<sup>th</sup> log from the exercise that the user wishes to delete. Note: `N` starts with 1.

If you are unsure about the index, try `list` command and figure out the index first.

Sample input: `delete running i/1`   
Expected output:
```
Log deleted: Exercise | running | 10 mins | 100 kcal (Recorded on: 2024-04-13 15:18)
```

---

### Log a meal not in the database: `add`
Logs a new meal that is not available in the food database.

Format: `add m/<MEAL_NAME> c/CALORIES_PER_SERVING s/<NUMBER_OF_SERVINGS>`

* The `<MEAL_NAME>` is any food item.
* The `CALORIES_PER_SERVING` is the calorie amount of the food item
* The `<NUMBER_OF_SERVINGS>` can be any positive integer value.

Sample input: `add m/carrot cake c/500 s/2`     
Expected output:
```
carrot cake has been added to the food database.
logging your meal.......

You've logged 2 serving(s) of carrot cake.
Estimated calories: 1000 kcal
```
---
### Log an exercise not in the database: `add`
Adds a new exercise item to the existing list of exercises.

Format: `add e/<EXERCISE> c/<CALORIES_BURNT_PER_MIN> d/<DURATION_IN_MIN>`

* The `<EXERCISE>` is the form of exercise.
* The `<CALORIES_BURNT_PER_MIN>` can be a rough gauge of calories being burnt.
* The `<DURATION_IN_MIN>` can be any positive integer value.

Sample input: `add e/calisthenics c/10 d/30`     
Expected output:
```
calisthenics has been added to the exercise database.
logging your exercise.......

You've logged 30 minutes of calisthenics.
Estimated calories burnt: 300 kcal
```
---
### Change user height: `change`
Changes user height and updates the BMI value.

Format: `change h`
```
user: change h
Please input your height (in cm):
user: 174
You have successfully changed your height!
Your BMI is 23.
You are in the healthy weight range.
```
---

### Change user weight: `change`
Changes user weight and updates the BMI value

Format: `change w`
```
user: change w
Please input your weight (in kg): 
user: 85
You have successfully changed your height!
Your BMI is 28.
You are in the overweight range.
```
---

### Change daily calorie intake goal: `change`
Changes the daily calorie intake goal.

Format: `change cg`
```
user: change cg
Please input your new daily calorie goal (in kcal): 
user: 3200
You have successfully changed your calorie goal! 
```
---

### Change daily water intake goal: `change`
Changes user weight and updates the BMI value

Format: `change wg`
```
user: change wg
Please set your new daily water goal (in ml): 
user: 3500
You have successfully changed your water goal!
```
---

### Show daily summary: `summary`
Shows a daily summary of food, water intake, and goals.

Sample input: `summary`  
Expected output:
```
Daily Summary:
Height: 174 cm
Weight: 85 kg
BMI: 28
Total calories consumed: 1000 kcal
Total water consumed: 0 ml
Total calories burnt: 300 kcal
Calorie goal: 3200 kcal
Water goal: 3500 ml
Net calories: 700 kcal
Calorie status: Calories Deficit
```
---

### Search for entries: `find`
Searches for entries with a specified keyword.

Format:  `find <KEY_WORD>`


Sample input: `find sushi`  
Expected output:
```
____________________________________________________________
Here are the matching logs in your list:
1. sushi | 4 serving(s) | 1400 kcal (Recorded on: 2024-04-13 15:35)
____________________________________________________________
```
---

### Get help: `help`
Provides all the possible commands inside ActiveEdge application.

Format: `help`  
Expected output: (Only a part of the output is shown here.)
```
Welcome to Active Edge! Here are the available commands:
1. Log meal: `log m/<MEAL_NAME> s/<NUMBER_OF_SERVINGS>`
2. Log water intake : `log w/<AMOUNT_OF_WATER>`
3. Log exercises: `log e/<EXERCISE_NAME> d/<DURATION>`
4. View daily goals: `show g`
5. View daily calorie intake: `show c`
.
.
.
Start tracking your health goals now!
```
---

### Clear all logged entries: `clear`
Deletes all the logs entered throughout the usage time period.

Sample input: `clear`       
Expected output:
```
All logged data has been cleared.

Since you are new here or your details are missing,
let's start with a few questions to set things up!
Please input your height (in cm): 
```
---

### Exit the program: `bye`
Exits the ActiveEdge application whenever you want.

Sample input: `bye`     
Expected output:
```
Bye. Hope to see you again soon!
```
---

## FAQ ❓

**Q**: How do I transfer my data to another computer? 

**A**:
Create a "data" folder within the ActiveEdge application directory, 
and then copy the "data.txt" file into this newly created folder.

**Q**: I accidentally deleted a log. Is there a way to recover it?

**A**: Unfortunately, deleted logs are not recoverable, so it's 
important to be careful when using the delete command.




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
15. Change user height: `change h`  
16. Change user weight: `change w`  
17. Change daily calorie intake goal: `change cg`
18. Change daily water intake goal: `change wg`    
19. Show daily summary: `summary`   
20. Search for entries: `find`  
21. Get help: `help`    
22. Clear all logged entries: `clear`   
23. Exit the program: `bye`
