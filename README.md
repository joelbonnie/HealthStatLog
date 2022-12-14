# Health Statistics Logger

### Log Daily Health and Analyse Progress Graphically

This application aims to be a tool that is used to ***log daily health statistics***
such as **body mass, body muscle, body fat percentage, body water percentage, and the number 
of glasses of water per day.**
This can then be used to track BMI progress and changes in these statistics using graphs.


#### **This application is intended for:**
- users checking progress while **bulking/cutting at the gym**
- users ***trying various diets*** and monitoring changes
- users ***casually monitoring body health*** statistics


#### Why this project is of interest to me:
This project is of interest to me because when I started going to the gym 
and started monitoring my daily diet, I would have greatly benefited from 
an application which would perform the tasks that this aims to achieve.

I believe this application would be a great asset to anyone just casually starting to 
monitor health statistics or even already working out and want to measure
their progress with ease.


### User Stories 

- As a user, I want to be able to log my daily health statistics.
- As a user, I want to be able to delete a previous log by inputting date.
- As a user, I want to be able to input current height and calculate BMI.
- As a user, I want to be able to input current height and calculate BMI class.
- As a user, I want to be able to check how the amount of water I drank has varied over the past 7 days.
- As a user, I want to be able to view graphs on how my body mass, body fat percentage,
body muscle and body water percentage has varied over the past 30 days. 
- As a user, I want to be able to save the currently inputted health statistics to file.
- As a user, I want to be able to load previously inputted health statistics from file.

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by inputting values in the corresponding 
text-boxes and clicking the "Add Log" button to add the new HealthLog.
- You can generate the second required event related to adding Xs to a Y by inputting a date value and clicking the 
"Delete Log" button to delete a particular log.
- You can locate my visual component by clicking the "Create Graph" button and selecting which graph
 to display.
- You can save the state of my application by clicking the "Save" button.
- You can reload the state of my application by clicking the "Load" button


### Example Output Log

LOGS: <br>
Wed Nov 23 17:19:56 PST 2022 <br>
New Health Log Added! <br>
Wed Nov 23 17:19:57 PST 2022 <br>
Health Logs Viewed! <br>
Wed Nov 23 17:20:04 PST 2022 <br>
Specified Health Log Removed!

This is the log produced when the following actions have occurred:
- A Health Log is added
- Then, Health Logs are viewed
- Then, a Health Log is removed

The three actions being logged are when we **add a new Health Log**,
when we **remove an existing Health Log** or when we **view all the existing Health Logs**. 
Since the logging action is done entirely in the model package, 
if no logs are printed on exiting the program this indicates that 
none of the three actions stated above has occurred.

## Project Design

In the current design, there are three main packages excluding the test package:

- ***model***
- ***persistence***
- ***ui***

there is also a fourth package ***graphs*** in ui.

Currently, the **model** package (consisting of HealthLog, HealthProgress, Event and EventLog)
performs the function of providing classes for representing Health Logs and a collection of 
Health Logs, ie, HealthProgress. 
It also provides the functionality for logging events. 

The **persistence** package (consisting of JsonRead, JsonWrite and the interface Writeable) 
provides functionality for saving and loading the current state of the application from a JSON file
JsonRead parses the JSON file and creates a HealthProgress object whereas JsonWrite takes a HealthProgress
object and writes it into the JSON file. Writeable is an Interface implemented by HealthLog and HealthProgress
specifying a method to convert to JSON.

The **ui** package (consisting of CalculateBodyMassIndexInterface, GraphicalUserInterface,GraphInterface,
ViewHealthLogWindow, Main and the sub-package graphs) provides functionality for the GUI. **graphs** is a package 
within ui which has classes for each type of graph. Main calls the GraphicalUserInterface, which in turns calls the 
other classes in ui depending on what the user's input is. In the current implementation, each class represents a 
different window or JFrame. GraphInterface, in particular, calls the different classes for graphs.

### Potential refactoring 
We can potentially perform the following refactoring tasks to improve our program

- We can refactor out the different components in the GraphicalUserInterface class corresponding to different
functionalities to maintain the single responsibility principle and improve cohesion. For example, refactoring out
the frame to add a new HealthLog and the frame to remove an existing HealthLog.
- In order to make the above refactoring work, we would create a class to take the functionality of storing 
the current HealthProgress and program status messages. We need this to have only one instance that can be accessible 
from all the ui classes. Hence, we would use a singleton design pattern. 
- We can extract a superclass from the different types of graphs and make each graph class extend the superclass. 
Through this, we are reducing duplicated code, and the chances of unexpected errors while making a change in 
another class. 
