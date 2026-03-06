import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate.format(OUTPUT_FORMAT) + " to: " + toDate.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + fromDate.format(INPUT_FORMAT) + " | " + toDate.format(INPUT_FORMAT);
    }
}