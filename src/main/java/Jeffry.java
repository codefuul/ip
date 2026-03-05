import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Jeffry {
    // Coding Standard: Constants should be named in UPPER_CASE
    private static final String DIVIDER = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void saveTasks() {
        try {
            // Ensure the directory exists
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Write tasks to the file
            FileWriter fw = new FileWriter("./data/jeffry.txt");
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        try {
            File file = new File("./data/jeffry.txt");
            if (!file.exists()) {
                return; // File doesn't exist yet, just start with an empty list
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| "); // Split using the delimiter

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;

                if (type.equals("T")) {
                    task = new Todo(description);
                } else if (type.equals("D")) {
                    task = new Deadline(description, parts[3]);
                } else if (type.equals("E")) {
                    task = new Event(description, parts[3], parts[4]);
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found.");
        } catch (Exception e) {
            System.out.println("Data file is corrupted or formatted incorrectly.");
        }
    }

    public static void main(String[] args) {

        String logo = """
                   _           __    __          _   _\s
                  (_)   ___   / _|  / _|  _ __  | | | |
                  | |  / _ \\ | |_  | |_  | '__| | |_| |
                 _/ | |  __/ |  _| |  _| | |     \\__, |
                |__/   \\___| |_|   |_|   |_|     |___/\s
                """;

        System.out.println("Hello from\n" + logo);
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Jeffry.");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);

        Scanner scanner = new Scanner(System.in);
        String userInput;

        loadTasks();

        while (true) {
            userInput = scanner.nextLine();
            System.out.println(DIVIDER);

            try {
                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }

                } else if (userInput.startsWith("mark")) {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new JeffryException("Please specify which task number to mark.");
                    }

                    int index = Integer.parseInt(parts[1]) - 1;

                    if (index < 0 || index >= tasks.size()) {
                        throw new JeffryException("That task number does not exist.");
                    }

                    tasks.get(index).markAsDone();
                    saveTasks();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(index));

                } else if (userInput.startsWith("unmark")) {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new JeffryException("Please specify which task number to unmark.");
                    }

                    int index = Integer.parseInt(parts[1]) - 1;

                    if (index < 0 || index >= tasks.size()) {
                        throw new JeffryException("That task number does not exist.");
                    }

                    tasks.get(index).markAsNotDone();
                    saveTasks();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(index));

                } else if (userInput.startsWith("delete")) {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new JeffryException("Please specify which task number to delete.");
                    }

                    int index = Integer.parseInt(parts[1]) - 1;

                    if (index < 0 || index >= tasks.size()) {
                        throw new JeffryException("That task number does not exist.");
                    }

                    Task removedTask = tasks.remove(index);
                    saveTasks();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (userInput.startsWith("todo")) {
                    if (userInput.trim().length() <= 4) {
                        throw new JeffryException("The description of a todo cannot be empty.");
                    }

                    String description = userInput.substring(5);
                    Task newTodo = new Todo(description);
                    tasks.add(newTodo);
                    printAddedTask(newTodo, tasks.size());

                } else if (userInput.startsWith("deadline")) {
                    if (userInput.trim().length() <= 8) {
                        throw new JeffryException("The description of a deadline cannot be empty.");
                    }

                    if (!userInput.contains(" /by ")) {
                        throw new JeffryException("A deadline must include '/by' to define the time.");
                    }

                    String[] parts = userInput.substring(9).split(" /by ");
                    Task newDeadline = new Deadline(parts[0], parts[1]);
                    tasks.add(newDeadline);
                    printAddedTask(newDeadline, tasks.size());

                } else if (userInput.startsWith("event")) {
                    if (userInput.trim().length() <= 5) {
                        throw new JeffryException("The description of an event cannot be empty.");
                    }

                    if (!userInput.contains(" /from ") || !userInput.contains(" /to ")) {
                        throw new JeffryException("An event must include '/from' and '/to' to define the time.");
                    }

                    String[] parts = userInput.substring(6).split(" /from ");
                    String description = parts[0];
                    String[] times = parts[1].split(" /to ");

                    Task newEvent = new Event(description, times[0], times[1]);
                    tasks.add(newEvent);
                    printAddedTask(newEvent, tasks.size());

                } else {
                    throw new JeffryException("What talking you? :-(");
                }

            } catch (JeffryException e) {
                System.out.println("Bruh. " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number dummy.");
            }

            System.out.println(DIVIDER);
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void printAddedTask(Task task, int count) {
        saveTasks();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}