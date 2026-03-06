# Jeffry User Guide

Jeffry is your brutally honest, highly efficient desktop task manager. He helps you keep track of your daily todos, impending deadlines, and upcoming events so you never miss a beat.

## Features

### 1. Adding a Todo: `todo`
Adds a basic task to your list without any date or time attached.
* **Format:** `todo <description>`
* **Example:** `todo read book`

### 2. Adding a Deadline: `deadline`
Adds a task that needs to be done before a specific date and time.
* **Format:** `deadline <description> /by yyyy-MM-dd HHmm`
* **Example:** `deadline return library book /by 2026-12-25 2359`

### 3. Adding an Event: `event`
Adds a task that starts and ends at specific times.
* **Format:** `event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`
* **Example:** `event project meeting /from 2026-08-15 1400 /to 2026-08-15 1600`

### 4. Viewing all tasks: `list`
Shows you a numbered list of all the tasks you have currently saved.
* **Format:** `list`

### 5. Marking a task as done: `mark`
Marks a specific task in your list as completed.
* **Format:** `mark <task_number>`
* **Example:** `mark 2`

### 6. Unmarking a task: `unmark`
Marks a completed task as not done yet.
* **Format:** `unmark <task_number>`
* **Example:** `unmark 2`

### 7. Deleting a task: `delete`
Permanently removes a task from your list.
* **Format:** `delete <task_number>`
* **Example:** `delete 3`

### 8. Finding a task: `find`
Searches for tasks that contain a specific keyword.
* **Format:** `find <keyword>`
* **Example:** `find book`

### 9. Exiting the program: `bye`
Saves your data and closes Jeffry.
* **Format:** `bye`