# User Guide

## Features

### Create a task list that you can review anytime
To do, deadline or event tasks! Up to your choosing!

### Add notes to your tasks
Add notes to specific task and review them later!

### Different ways to view your list
View your task list in different ways; by date, status, notes, keywords or everything!

### Smarter search functionality
Duke returns tasks that contains keywords that you searched for! You do not need to input the exact description or notes anymore!

### Automatically saves your progress
There's no need to manually save your work, Duke does it automatically for you everytime you enter a command!

### Link tasks that are dependent on one another
Need to complete tasks in sequential order? No problem! You can associate tasks with one another!

## Usage

### todo - creates a to-do task
Adds a to-do task to your list!

Format:`todo <desc>`

Example of Usage: `todo Say Hello to World`

>Expected outcome: A to-do task, named *"Say Hello to World"*, will be added to your task list

### event - creates an event
Adds an event task to your list! You can add a date to the event, if you'd like to!

Format: `event <desc> /<date>`

Example of Usage:`event Say Hello to World /2022-02-20`

> Expected outcome: An event, named *"Say Hello to World"*, with a date of 20 Feb 2022 will be added to your task list
> > Note: Date has strict format of "yyyy-mm-dd" 

### deadline - creates a deadline
Adds a deadline task to your list! Like event, you can add a due date to it, if you'd like to!

Format: `deadline <desc> /<yyyy-mm-dd>`

Example of Usage: `deadline Say Hello to World /2022-02-20`

> Expected outcome: An deadline, named *"Say Hello to World"*, with a due date of 20 Feb 2022 will be added to your task list
> > Note: Date has strict format of "yyyy-mm-dd"

### done - marks a task as completed

Format: `done <task index>`

Example of Usage: `done 1`

> Expected outcome: The first task in your task list will be marked as completed!

### delete - deletes a task

Format: `delete <task index>`

Example of Usage: `delete 1`

> Expected outcome: The first task in your task list will be deleted!
> > WARNING: Deletion is IRREVERSIBLE! Tread carefully!!!

### list - display all task(s)
View every single tasks in your tasks list!

Format: `list`

> Expected outcome: A list with every tasks will be displayed!

### list pending - display incomplete task(s)
View a list of incomplete tasks!

Format: `list pending`

> Expected outcome: Only tasks that have not been marked as "done" will be displayed

### list completed - display completed task(s)
View a list of completed tasks!

Format: `list completed`

> Expected outcome: Only tasks that have been marked as "done" will be displayed

### list date - filter task list by date
View a list of events and deadlines that falls on the specified date

Format: `list date /<yyyy-mm-dd>`

Example of Usage: `list date /2022-02-02`

>Expected outcome: Only events and deadlines that coincides with the input date will be displayed

### find - search for a task based on keywords in the description
Filter the list by task description that matches your keyword(s)!

Format: `find <keyword>`

Example of Usage: `find Hello World`

> Expected outcome: Only tasks with keyword (partial or full match) in its name will be displayed.

### do after - assign a task to another
Complete your task in sequential order by associating one with another!

Format: `do after <task index> /<task index>`

Example of Usage: `do after <parent task index> /<child task index>`

> Expected outcome: Child task will be associated to parent task! 
> > Note: You cannot complete a child task before the parent task!

### note add - appends new notes to existing notes

Format: `note add <task index> <notes>`

Example of Usage: `note add 1 Bye World!`

> Expected outcome: *"Bye World!"* will be appended to the existing note of the first task

### note update - replace existing note with new note

Format: `note update <task index> <notes>`

Example of Usage: `note update 1 Goodbye World!`

> Expected outcome: *"Goodbye World!"* will replace any existing note in the first task

### note delete - delete any existing note

Format: `note delete <task index>`

Example of Usage: `note delete 1`

> Expected outcome: No more notes in the first task!

### find note: - search for a task based on keywords in the notes
Filter the list by notes that matches your keyword(s)!

Format: `find note: <keyword>`

Example of Usage: `find note: Hello World!`

> Expected outcome: Tasks with notes that contains *"Hello World!"* will be displayed!

### list note - view list of tasks with notes
Filter for tasks with notes only

Format: `list note`

> Expected outcome: Tasks with notes will be displayed
 
## Command Summary
* Add todo `todo <desc>`
* Add event `event <desc> /<date>`
* Add deadline `deadline <desc> /<yyyy-mm-dd>`
* Marks task as completed `done <task index>`
* Delete task `delete <task index>`
* View all task(s) `list`
* View incomplete task(s) `list pending`
* View completed task(s) `list completed`
* View task(s) by date `list date /<yyyy-mm-dd>`
* Find tasks by description `find <keyword>`
* Set do after tasks `do after <task index> /<task index>`
* Add new notes `note add <task index> <notes>`
* Append to existing notes `note add <task index> <notes>`
* Replace existing notes `note update <task index> <notes>`
* Delete existing notes `note delete <task index>`
* Find tasks by notes `find note: <keyword>`
* View tasks with notes `list note`