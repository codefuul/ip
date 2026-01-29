import java.util.Scanner;

public class Jeffry {
    public static void main(String[] args) {
        // Change array type from String[] to Task[]
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String logo = "   _           __    __          _   _ \n"
                + "  (_)   ___   / _|  / _|  _ __  | | | |\n"
                + "  | |  / _ \\ | |_  | |_  | '__| | |_| |\n"
                + " _/ | |  __/ |  _| |  _| | |     \\__, |\n"
                + "|__/   \\___| |_|   |_|   |_|     |___/ \n";

        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Jeffry.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (userInput.equals("bye")) {
                break;

            } else if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    // Uses the toString() method we defined in Task class
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }

            } else if (userInput.startsWith("mark")) {
                // Parse "mark 2" -> get integer 2
                String[] parts = userInput.split(" ");
                int taskIndex = Integer.parseInt(parts[1]) - 1; // Convert 1-based to 0-based index

                tasks[taskIndex].markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex].toString());

            } else if (userInput.startsWith("unmark")) {
                // Parse "unmark 2" -> get integer 2
                String[] parts = userInput.split(" ");
                int taskIndex = Integer.parseInt(parts[1]) - 1;

                tasks[taskIndex].markAsNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex].toString());

            } else {
                // Default: Add a new task
                tasks[taskCount] = new Task(userInput);
                taskCount++;

                System.out.println("added: " + userInput);
            }

            System.out.println("____________________________________________________________");
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}