import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * represents a Deadline task, which has a description and a due date/time.
 * The due date/time is stored as a LocalDateTime object for easy manipulation and formatting.
 */

public class Deadline extends Task {
    protected LocalDateTime byDate;

    // We create a formatter for reading user input and saving to the file
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    // We create a formatter for making it look pretty when printing to the user
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    /**
     * The constructor takes in the description and the due date/time as a string.
     * It tries to parse the due date/time using the specified format.
     * If parsing fails, it throws a JeffryException with a user-friendly message.
     */
    public Deadline(String description, String by) throws JeffryException {
        super(description);
        try {
            // Parse using the specific date+time format
            this.byDate = LocalDateTime.parse(by.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new JeffryException("Please enter the date and time in yyyy-MM-dd HHmm format (e.g., 2019-10-15 1800).");
        }
    }

    /**
     * The toString method returns a string representation of the Deadline task, which includes the type indicator "[D]", the status icon, the description, and the due date/time formatted in a more user-friendly way. This method is used when displaying the task in the user interface (e.g., when listing tasks), allowing users to see all relevant information about the Deadline task at a glance.
     * @return
     */
    @Override
    public String toString() {
        // Example output: Oct 15 2019, 6:00 PM
        return "[D]" + super.toString() + " (by: " + byDate.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * The toFileFormat method returns a string representation of the Deadline task in a format suitable for saving to a file. The format includes the type indicator "D", the done status (1 for done, 0 for not done), the description, and the due date/time formatted in the same way as the input format. This method is used when saving tasks to a file (e.g., by the Storage class), allowing the application to store all necessary information about the Deadline task in a consistent format that can be easily read back when loading tasks from the file.
     * @return
     */
    @Override
    public String toFileFormat() {
        // We MUST save it in the exact INPUT_FORMAT so Storage can read it back successfully next time!
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDate.format(INPUT_FORMAT);
    }
}