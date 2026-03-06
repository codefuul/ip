/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    /**
     * The constructor takes in the description of the todo task and initializes the isDone status to false by calling the superclass constructor
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * The toString method returns a string representation of the todo task, which includes the type indicator "[T]", the status icon, and the description.
     * This method is used when displaying the todo task in the user interface (e.g., when listing tasks), allowing users to see that it is a todo task along with its completion status and description.
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * The toFileFormat method returns a string representation of the todo task in a format suitable for saving to a file. The format includes the type indicator "T", the done status (1 for done, 0 for not done), and the description, separated by " | ".
     * This method is used when saving tasks to a file (e.g., by the Storage class), allowing the application to store the necessary information about the todo task in a consistent format that can be easily read back when loading tasks from the file.
     * @return A string representation of the todo task in file format.
     */
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }
}