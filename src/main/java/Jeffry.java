import java.util.Scanner;

public class Jeffry {
    // Coding Standard: Constants should be named in UPPER_CASE
    private static final int MAX_TASKS = 100;
    private static final String DIVIDER = "____________________________________________________________";

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

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

        while (true) {
            userInput = scanner.nextLine();
            System.out.println(DIVIDER);

            try {
                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i].toString());
                    }

                } else if (userInput.startsWith("mark")) {
                    // ERROR HANDLING: Handle if user types "mark" without number or "mark xyz"
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new JeffryException("Please specify which task number to mark.");
                    }

                    int index = Integer.parseInt(parts[1]) - 1;

                    // ERROR HANDLING: Check if index is valid
                    if (index < 0 || index >= taskCount) {
                        throw new JeffryException("That task number does not exist.");
                    }

                    tasks[index].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[index]);

                } else if (userInput.startsWith("unmark")) {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new JeffryException("Please specify which task number to unmark.");
                    }

                    int index = Integer.parseInt(parts[1]) - 1;

                    if (index < 0 || index >= taskCount) {
                        throw new JeffryException("That task number does not exist.");
                    }

                    tasks[index].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[index]);

                } else if (userInput.startsWith("todo")) {
                    // ERROR HANDLING: Check if description is empty
                    // "todo" length is 4. If input is just "todo", throw error.
                    if (userInput.trim().length() <= 4) {
                        throw new JeffryException("The description of a todo cannot be empty.");
                    }

                    String description = userInput.substring(5);
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    printAddedTask(tasks[taskCount - 1], taskCount);

                } else if (userInput.startsWith("deadline")) {
                    if (userInput.trim().length() <= 8) {
                        throw new JeffryException("The description of a deadline cannot be empty.");
                    }

                    // ERROR HANDLING: Check for /by
                    if (!userInput.contains(" /by ")) {
                        throw new JeffryException("A deadline must include '/by' to define the time.");
                    }

                    String[] parts = userInput.substring(9).split(" /by ");
                    tasks[taskCount] = new Deadline(parts[0], parts[1]);
                    taskCount++;
                    printAddedTask(tasks[taskCount - 1], taskCount);

                } else if (userInput.startsWith("event")) {
                    if (userInput.trim().length() <= 5) {
                        throw new JeffryException("The description of an event cannot be empty.");
                    }

                    // ERROR HANDLING: Check for /from and /to
                    if (!userInput.contains(" /from ") || !userInput.contains(" /to ")) {
                        throw new JeffryException("An event must include '/from' and '/to' to define the time.");
                    }

                    String[] parts = userInput.substring(6).split(" /from ");
                    String description = parts[0];
                    String[] times = parts[1].split(" /to ");
                    tasks[taskCount] = new Event(description, times[0], times[1]);
                    taskCount++;
                    printAddedTask(tasks[taskCount - 1], taskCount);

                } else {
                    // ERROR HANDLING: Unknown command
                    throw new JeffryException("What talking you? :-(");
                }

            } catch (JeffryException e) {
                System.out.println("Bruh. " + e.getMessage());
            } catch (NumberFormatException e) {
                // Catches if user types "mark abc"
                System.out.println("Enter a valid number dummy.");
            }

            System.out.println(DIVIDER);
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void printAddedTask(Task task, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}