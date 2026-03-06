import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime byDate;

    // We create a formatter for reading user input and saving to the file
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    // We create a formatter for making it look pretty when printing to the user
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    public Deadline(String description, String by) throws JeffryException {
        super(description);
        try {
            // Parse using the specific date+time format
            this.byDate = LocalDateTime.parse(by.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new JeffryException("Please enter the date and time in yyyy-MM-dd HHmm format (e.g., 2019-10-15 1800).");
        }
    }

    @Override
    public String toString() {
        // Example output: Oct 15 2019, 6:00 PM
        return "[D]" + super.toString() + " (by: " + byDate.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileFormat() {
        // We MUST save it in the exact INPUT_FORMAT so Storage can read it back successfully next time!
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDate.format(INPUT_FORMAT);
    }
}