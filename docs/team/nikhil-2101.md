## Nikhil Babu  - Project Portfolio Page

## Overview
This chatbot, known as ActiveEdge, simplifies students' ability to track their calorie and water intake by giving them 
the ability to input the food and water they consumed and the exercises they engaged themselves in. Through this, we aim
to incentivise and promote a healthy lifestyle amid their busy and rigid schedules. 

### Summary of Contributions
To view the code that I have contributed: https://shorturl.at/owEU5

#### Enhancements Implemented
1. Created `DeleteLogCommand` class to delete specific logs.   
2. Created `LogWaterCommand` class to log water entries.   
3. Created `ViewWaterIntakeCommand` class to view all water entries.
4. Created `LogWater` class.
5. Refactored the whole `Parser` class to make it more OOP compared to our initial version.
6. Implemented the Date-Time feature throughout the program, allowing users to view the date and time during which they
logged or added in their meals, exercise or water intake.
7. Created test cases for `LogWaterCommand` which can be found in `LogWaterCommandTest`.
8. Created test cases for `ViewWaterIntakeCommand` which can be found in `ViewWaterIntakeCommandTest`
9. Implemented the warning feature for exceeding calorie intake as the user is logging or adding in their meals.
10. Created `ActiveEdgeException`.
11. Created `InvalidCommand`.
12. Created `PrintExercisesCommand`.
13. Created `PrintFoodCommand`.
14. Created `Command`.
15. Fixed bugs for the `list` function in which human errors were not taken into account for, such as extra spaces.
16. Implemented realistic limits for calories, duration, exercise and food inputs.
17. Fixed bugs that caused the program to crash when logging exercises.
18. Included more exception handling throughout to ensure that the program does not crash when incorrect inputs 
are given.
19. Implemented the feature of printing out calorie deficit or calorie surplus according to the 
number of calories consumed and burned when the user views their calorie intake.

#### Contributions to the UG
1. Identified errors in the UG such as wrong outputs given for certain commands and made the appropriate changes.
2. Provided my feedback on the formatting of the UG to make it more concise.

#### Contributions to the DG
1. Created the Generic Sequence Diagram and the description for it.
2. Created the Command Diagram and the description for it, in which I specifically explored how the log commands work.

#### Contributions to Team-Based Tasks
1. Initiated extra team meetings on top of our weekly ones to ensure that we meet our deadlines.
2. Heavily involved in testing the code for bugs while working on v2.0 and v2.1.
3. Helped to rectify issues faced by my other teammates in their codes.
4. Helped in the assignment of issues and bugs we faced while working on v2.0 and v2.1.
5. Provided the necessary guidance and help to my teammates when they were stuck.