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

## Features 


### Logging Calorie Intake : `log`
- Adds a new meal to the log along with the number of servings.
- Alerts if the calories consumed exceed the goal.

Format: `log m/<MEAL_NAME> s/<SERVINGS>`

* The `<MEAL_NAME>` can be from any food from the food list.
* The `<SERVINGS>` can be any integer value.  

Example of usage: 

`log m/chicken rice s/1`

![Main](https://github.com/SuveenE/tp/blob/master/images/Logging%20meal%20name.png?raw=true)

### Adding new food item to database and logs meal to list: `add`
- Adds a new food item to the existing list of food items.
- Ensures user can input any kind of food to keep track their calories.

Format: `add m/<MEAL_NAME> c/CALORIES_PER_SERVING s/<NUMBER_OF_SERVINGS>`

* The `<MEAL_NAME>` can be from any food item.
* The `CALORIES_PER_SERVING` can be a rough guage of calorie amount of the food item
* The `<NUMBER_OF_SERVINGS>` can be any integer value.

Example of usage:

`add m/carrot cake c/500 s/2`

### Logging Water Intake : `log`
- Logs the quantity of water consumed.
- Tracks progress towards a customizable water intake goal.

Format: `log w/<AMOUNT_OF_WATER>`

* The `<AMOUNT_OF_WATER>` can be volume of water intake in ml.

Example of usage:

`log w/100`

![Main](https://github.com/SuveenE/tp/blob/master/images/Logging%20water%20intake.png?raw=true)

### Viewing Water Intake: `show`
-Displays the total water intake and the percentage of water 
that has been consumed in comparison to the water intake goal.

Format: `show w`

Example of usage: `show w`

### Goal Setting : `set goal` / `show g`
- Sets the daily water intake and calorie intake goal.
- Displays current calorie and water intake goals.

Format: `set goal c/<CALORIE_GOAL>`/ `set goal w/<WATER_GOAL>`/ `show goals`

* The `<CALORIE_GOAL>` can be calorie goal for the day.
* The `<WATER_GOAL>` can be water goal for the day

Example of usage:

`set goal c/1000`
`set goal w/2000`
`show g`

![Main](https://github.com/SuveenE/tp/blob/master/images/Goal%20Setting.png?raw=true)

### Delete meals/water/exercises: `delete`

-Deletes a meal/quantity of water(water intake) from the task list.
-Supports deletion of tasks based on their descriptions.

Format: `delete <QUANTITY_OF_WATER> ml` / `delete <MEAL_NAME>` / `delete <EXERCISE_NAME>`

* The `QUANTITY_OF_WATER` can be the quantity of water the 
user logged previously and wishes to delete.
* The `MEAL_NAME` can be the name of the meal the user 
previously logged in and wishes to delete.
* The `EXERCISE_NAME` can be the name of the exercise.

Example of usage:

`delete 100 ml`
`delete sushi`
`delete calisthenics`

![Main](https://github.com/SuveenE/tp/blob/master/images/Deleting%20meals.png?raw=true)

### Log exercises: `log`
- Logs the exercises performed.
- Track how many calories have burnt.

Format: `log e/<EXERCISE_NAME> d/<DURATION>`

* The `EXERCISE_NAME` can any form of exercise done.
* The `DURATION` can be the number of minutes the exercise has been done.

Example of usage:

`log e/running d/10`

![Main](https://github.com/SuveenE/tp/blob/master/images/Logging-exercises.png?raw=true)

### Adding new exercise item to database and logs exercise to list: `add`
- Adds a new exercise item to the existing list of exercises.
- Ensures user can input any kind of exercise to keep track of their calories.

Format: `add e/<EXERCISE> c/<CALORIES_BURNT_PER_MIN> d/<DURATION_IN_MIN>`

* The `<EXERCISE>` can be any form.
* The `<CALORIES_BURNT_PER_MIN>` can be a rough guage of calories being burnt.
* The `<DURATION_IN_MIN>` can be any integer value.

Example of usage:

`add e/calisthenics c/10 d/5`

### Testing data for food items 

-Users can use these data to test out the application

| Food Item      | Calorie Intake |
|----------------|----------------|
| fried chicken  | 543            |
| chicken burger | 357            |
| beef burger    | 500            |
| fish burger    | 379            |
| sambar rice    | 187            |
| medu vadai     | 108            |
| udon           | 400            |
| edamame        | 120            |
| sambal belacan | 75             |
| telur masin    | 85             |

### Testing data for list of exercises

-Users can use these data to test out the application

| Exercise Item | Calories Burnt Per minute |
|---------------|---------------------------|
| running       | 10                        |
| cycling       | 8                         |
| swimming      | 8                         |
| jumping jacks | 10                        |
| walking       | 4                         |
| yoga          | 3                         |
| aerobics      | 10                        |
| basketball    | 9                         |
| tennis        | 7                         |
| squash        | 8                         |

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

Here's a quick summary of commands available in ActiveEdge:


1. set goal c/<CALORIE_GOAL> | Sets the daily calorie goal.
2. set goal w/<WATER_GOAL> | Sets the daily water goal.
3. show g | Displays the set goals.
4. log m/<MEAL_NAME> s/<SERVINGS> | Logs consumed meals and their servings.
5. show c | Displays the daily calorie intake.
6. log w/<AMOUNT_OF_WATER> | Logs consumed water amount.
7. show w | Displays the water intake.
8. log e/<EXERCISE_NAME> d/<DURATION> | Logs performed exercises and their duration.
9. list | Shows all logged entries.
10. help | Provides assistance.
11. find <KEYWORD> | Searches for entries with a specified keyword.
12. delete <AMOUNT_OF_WATER>/<MEAL_NAME> | Deletes specified entries from the list.
13. summary | Shows a daily summary of food, water intake, and goals.
14. add m/<MEAL_NAME> c/<CALORIES_PER_SERVING(kCal)> s/<NUMBER_OF_SERVINGS> | Adds a new food item to the database and logs a meal.
15. add e/<EXERCISE> c/<CALORIES_BURNT_PER_MIN(kCal)> d/<DURATION_PER_MIN> | Adds a new exercise to the database and logs it.
16. clear | Clears the interface.
17. show food | Prints all food data.
18. show exercises | Prints all exercise data.
