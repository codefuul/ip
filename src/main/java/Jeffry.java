import java.util.Scanner;

public class Jeffry {
    public static void main(String[] args) {
        // POLYMORPHISM: We create an array of the parent class (Task)
        // This array can hold Todos, Deadlines, and Events!
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
                    // Polymorphism in action: Java calls the toString()
                    // of the specific child class (Todo, Deadline, or Event)
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }

            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index]);

            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[index]);

            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5);
                tasks[taskCount] = new Todo(description);
                taskCount++;
                printAddedTask(tasks[taskCount - 1], taskCount);

            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.substring(9).split(" /by ");
                tasks[taskCount] = new Deadline(parts[0], parts[1]);
                taskCount++;
                printAddedTask(tasks[taskCount - 1], taskCount);

            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.substring(6).split(" /from ");
                String description = parts[0];
                String[] times = parts[1].split(" /to ");
                tasks[taskCount] = new Event(description, times[0], times[1]);
                taskCount++;
                printAddedTask(tasks[taskCount - 1], taskCount);
            }

            System.out.println("____________________________________________________________");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void printAddedTask(Task task, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}