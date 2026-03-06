import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The Storage class is responsible for loading tasks from a file and saving tasks to a file.
 * It uses a simple text format where each line represents a task, with fields separated by " | ".
 * The first field indicates the type of task (T for Todo, D for Deadline, E for Event), followed by the done status, description, and any additional fields needed for Deadline and Event.
 */
public class Storage {
    private String filePath;
/**
     * The constructor takes in the file path where tasks will be saved and loaded from.
     * @param filePath The path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
/**
     * The load method reads tasks from the specified file and returns them as an ArrayList of Task objects.
     * If the file does not exist, it returns an empty list.
     * If there is an error reading the file, it throws a JeffryException with a user-friendly message.
     * @return An ArrayList of Task objects loaded from the file.
     * @throws JeffryException If there is an error reading the file.
     */
    public ArrayList<Task> load() throws JeffryException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, parts[3]);
                    break;
                case "E":
                    task = new Event(description, parts[3], parts[4]);
                    break;
                default:
                    continue;
                }

                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new JeffryException("Bruh, I couldn't read the file!");
        }
        return tasks;
    }
    /**
     * The save method takes in an ArrayList of Task objects and writes them to the specified file in the correct format.
     * If there is an error writing to the file, it prints a user-friendly message to the console.
     * @param tasks The list of tasks to be saved to the file.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(file);
            for (Task t : tasks) {
                fw.write(t.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving: " + e.getMessage());
        }
    }
}