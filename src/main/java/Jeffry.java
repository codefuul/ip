import java.util.Scanner;

public class Jeffry {
    public static void main(String[] args) {
        // 1. Define the maximum number of tasks (e.g., 100)
        String[] tasks = new String[100];
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

            if (userInput.equals("bye")) {
                break;

            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");

            } else {
                // ADD command: Store the input in the array
                tasks[taskCount] = userInput;
                taskCount++;

                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}