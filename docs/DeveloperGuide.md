# Developer Guide

## Design & implementation

### AddCommand
AddCommand() is shared between all 3 task types; to-do, events and deadlines

<img src="images/AddCCommandSequenceDiagram.png" alt="add-command-uml"/>




## Product scope
### Target user profile
Students and working professional who needs to manage multiple timelines.

### Value proposition
Duke aims to improve time management of users and provide a seamless and intuitive experience.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|user|be able to add a to-do|keep track memorising them|
|v1.0|user|be able to add an event|keep track without memorising them|
|v1.0|user|be able to add a deadline|keep track without memorising them|
|v1.0|user|view my entire task list|refer to the details whenever|
|v1.0|user|delete tasks|remove redundant ones|
|v1.0|user|mark tasks as completed|keep track of the progress without memorising them|
|v2.0|user|view my task list by status|review the incomplete tasks|
|v2.0|user|be reminded of my upcoming events and deadlines|will not forget them and work on them immediately, respectively|
|v2.0|user|filter my list by event dates and/or deadlines|review the tasks for the specified day|
|v3.0|user|associate a task to another|finish them sequentially|
|v3.0|user|add notes to a task|keep track of the details|
|v3.0|user|delete notes of a task|remove unnecessary ones|
|v3.0|user|update existing notes|it is up-to-date|
|v4.0|user|view tasks that has notes|review these tasks|
|v4.0|user|search tasks by keywords in notes|review tasks based on the keywords|

## Non-Functional Requirements
Reliability - Application should save any updates/work done by the user without fail.

## Glossary

* *tasks* - is a parent term used to group to-do, event and deadline.

