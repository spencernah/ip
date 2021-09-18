# User Guide

DukePro is a **desktop app for managing tasks, optimized for use via a Command Line Interface (CLI)**.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## Quick Start
1. Ensure you have Java ```11 ``` or above installed in your computer.
2. Download the latest ```ip.jar ``` from [here](https://github.com/e0373972/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your DukePro.
4. Create an empty folder name  ```data ``` (refer to the image below).

![Image of  filepaths](filepaths.png)

5. Open Command Prompt and type ```java -jar ip.jar``` to start the app.

![Image of example](example.png)

6. Type the command in the command box and press Enter to execute it.

Some example commands you can try:
* ```todo borrow book ```: Add todo task ```borrow book ``` to the task list.
* ```event project meeting /at 2/12/2021 1700 ```: Add event  ```project meeting (at Dec 2 2021 1700) ``` to the task list.
* ```bye ```: Exits the app.

## Features

### Add todo: ```todo ```

Adds a todo task to the DukePro task list.

Format: ```todo DESCRIPTION```
* The ```DESCRIPTION``` is the detail of the task.
* The ```DESCRIPTION``` cannot be empty.

Example:
* ```todo borrow book ```

### Adding an event: ```event ```

Adds an event to the DukePro task list.

Format: ```event DESCRIPTION /at date/month/year time ```
* The ```DESCRIPTION``` is the detail of the event.
* Use ```/at ``` between ```DESCRIPTION``` and ```datetime ```
* The ```DESCRIPTION``` and ```date/month/year time ``` cannot be empty.

Example: ```event project meeting /at 2/12/2021 1700 ```

### Adding a deadline: ```deadline ```

Adds a deadline to the DukePro task list.

Format: ```deadline DESCRIPTION /by date/month/year time ```
* The ```DESCRIPTION``` is the detail of the deadline.
* Use ```/by ``` between ```DESCRIPTION``` and ```datetime ```
* The ```DESCRIPTION``` and ```date/month/year time ``` cannot be empty.

Example: ```deadline return book /by 2/12/2021 1800 ```

### Show tasks: ```list ```

To show all the tasks in the list.

Format: ```list ```

Example: ```list ```

### Mark as done: ```done ```

To mark a task as done.

Format: ```done INDEX ```
* Mark the task as done at the specified ```INDEX ```. The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...
* The index cannot be empty.

Example: ```done 2 ```

### Delete a task: ```delete ```

To delete a task in the list.

Format: ```delete INDEX ```
* Delete the task at the specified ```INDEX ```. The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...
* The index cannot be empty.

Example: ```delete 2 ```

### Exiting the program: ```bye ```

Exits the program.

Format: ```bye ```

Example: ```bye ```

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Without running the program, just copy the ```tasks.txt``` in the ```data``` folder.
To run and show the data in another computer, you need to follow the ```Quick Start``` above, and copy the ```tasks.txt``` to the ```data``` folder.

## Command Summary

**Action** | **Format, Examples**
------------ | -------------
**Todo** | ```todo DESCRIPTION``` e.g.,```todo borrow book ```
**Event** | ```event DESCRIPTION /at date/month/year time ``` e.g., ```event project meeting /at 2/12/2021 1700 ```
**Deadline** | ```deadline DESCRIPTION /by date/month/year time ``` e.g., ```deadline return book /by 2/12/2021 1800 ```
**List** | ```list ``` e.g., ```list ```
**Done** | ```done INDEX ``` e.g., ```done 2 ```
**Delete** | ```delete INDEX ``` e.g., ```delete 2 ```
**Bye** | ```bye ``` e.g., ```bye ```

