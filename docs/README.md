# User Guide

## Features

### Create a task list that you can review and modify anytime
When you create your task list in Duke, it remembers your task so that you can review it again at any point of time.

### Different ways to view your list
Duke allows you to view your task list in different ways; by date, status or everything.


## Usage

### todo - creates a to-do task
Example of usage:
`todo <name of task>`

Expected outcome: A to-do task will be added to your task list

### event - creates an event 
Example of usage: `event <name of event> /<yyyy-mm-dd>`

Expected outcome: An event and it's date will be added to your task list


### deadline - creates a deadline
Example of usage: `deadline <name of event> /<yyyy-mm-dd>`

Expected outcome: A deadline and it's date will be added to your task list

### done - marks a task as completed
Example of usage: `done <task index>`

Expected outcome: The corresponding task will be marked as completed

### delete - deletes a task
Example of usage: `delete <task index>`

Expected outcome: The corresponding task will be deleted

### list - display the entire task list
Example of usage: `list`

Expected outcome: Full task list will be displayed

### list pending - display incomplete task
Example of usage: `list pending`

Expected outcome: Only tasks that have not been marked as "done" will be displayed

### list date - view tasks that falls on the specified date
Example of usage: `list date <yyyy-mm-dd>`

Expected outcome: Only events and deadlines that coincides with the input date will be displayed

### find - search for a task based on keywords 
Example of usage: `find <keyword>`

Expected outcome: Only tasks with keyword in it's name will be displayed


### bye - closes the app
Example of usage: `bye`

Expected outcome: Exits Duke

