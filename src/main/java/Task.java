/**
 * Represents a task with a description and completion status.
 */

public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * The constructor takes in the description of the task and initializes the isDone status to false.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * The getStatusIcon method returns "X" if the task is done, and a space " " if it is not done.
     * This is used to visually indicate the completion status of the task in the user interface.
     * @return A string representing the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * The markAsDone method sets the isDone status of the task to true, indicating that the task has been completed.
     * This method is called when the user marks a task as done (e.g., by entering "done 2"), allowing the application to update the completion status of the specified task.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * The markAsNotDone method sets the isDone status of the task to false, indicating that the task has not been completed.
     * This method can be used if you want to allow users to mark a task as not done after it has been marked as done (e.g., by entering "undone 2"), allowing the application to update the completion status of the specified task accordingly.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
    /**
     * The toString method returns a string representation of the task, which includes the status icon and the description.
     * This method is used when displaying the task in the user interface (e.g., when listing tasks), allowing users to see both the completion status and the description of each task.
     * @return A string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    /**
     * The toFileFormat method returns a string representation of the task in a format suitable for saving to a file.
     * The format includes the done status (1 for done, 0 for not done) and the description, separated by " | ".
     * This method is used when saving tasks to a file (e.g., by the Storage class), allowing the application to store the necessary information about each task in a consistent format that can be easily read back when loading tasks from the file.
     * @return A string representation of the task in file format.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}