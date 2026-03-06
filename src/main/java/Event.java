import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * represents an Event task, which has a description, a start date/time, and an end date/time.
 * The date/time is stored as a LocalDateTime object for easy manipulation and formatting.
 */
public class Event extends Task {
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    /**
     * The constructor takes in the description, the start date/time, and the end date/time as strings.
     * It tries to parse the date/time using the specified format.
     * If parsing fails, it throws a JeffryException with a user-friendly message.
     */
    public Event(String description, String from, String to) throws JeffryException {
        super(description);
        try {
            this.fromDate = LocalDateTime.parse(from.trim(), INPUT_FORMAT);
            this.toDate = LocalDateTime.parse(to.trim(), INPUT_FORMAT);

            // The Bulletproof Check!
            if (this.toDate.isBefore(this.fromDate)) {
                throw new JeffryException("The 'to' time cannot be before the 'from' time! No time traveling allowed.");
            }

        } catch (DateTimeParseException e) {
            throw new JeffryException("Please enter both event dates/times in yyyy-MM-dd HHmm format (e.g., 2019-10-15 1800).");
        }
    }

    /**
     * The toString method returns a string representation of the Event task, which includes the type indicator "[E]", the status icon, the description, and the start and end date/time formatted in a more user-friendly way. This method is used when displaying the task in the user interface (e.g., when listing tasks), allowing users to see all relevant information about the Event task at a glance.
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate.format(OUTPUT_FORMAT) + " to: " + toDate.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * The toFileFormat method returns a string representation of the Event task in a format suitable for saving to a file. The format includes the type indicator "E", the done status (1 for done, 0 for not done), the description, and the start and end date/time formatted in the same way as the input format. This method is used when saving tasks to a file (e.g., by the Storage class), allowing the application to store all necessary information about the Event task in a consistent format that can be easily read back when loading tasks from the file.
     * @return
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + fromDate.format(INPUT_FORMAT) + " | " + toDate.format(INPUT_FORMAT);
    }
}