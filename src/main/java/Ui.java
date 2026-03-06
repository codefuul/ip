import java.util.Scanner;


/**
 * The Ui class is responsible for all interactions with the user. It handles displaying messages, reading user input, and showing errors.
 * It provides methods for showing the welcome message, reading commands, showing goodbye messages, and displaying errors.
 * It also has a helper method for printing a divider line to make the output look cleaner.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private Scanner scanner;
    /** The Ui class has a Scanner object as an instance variable, which is initialized in the constructor. This Scanner is used to read user input from the console. The constructor simply creates a new Scanner that reads from System.in, allowing the Ui class to read user input whenever needed. */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * The showWelcome method displays a welcome message to the user when the application starts. It includes an ASCII art logo of "Jeffry" and a greeting message. The method also calls showLine() to print a divider line before and after the welcome message for better readability.
     */
    public void showWelcome() {
        String logo = """
                   _           __    __          _   _\s
                  (_)   ___   / _|  / _|  _ __  | | | |
                  | |  / _ \\ | |_  | |_  | '__| | |_| |
                 _/ | |  __/ |  _| |  _| | |     \\__, |
                |__/   \\___| |_|   |_|   |_|     |___/\s
                """;

        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("Hello! I'm Jeffry.");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * The showLine method is a helper method that prints a divider line to the console. This is used to separate different sections of the output and make it look cleaner. The divider line is defined as a constant string (DIVIDER) at the top of the class, and the showLine method simply prints this string when called.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }
    /** The readCommand method reads a line of input from the user using the Scanner object. It waits for the user to enter a command and then returns that command as a string. This method is called in the main loop of the application to get user input for processing. */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * The showGoodbye method displays a goodbye message to the user when they exit the application. It thanks the user and expresses hope to see them again soon. The method also calls showLine() to print a divider line before and after the goodbye message for better readability.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * The showError method takes a string message as a parameter and displays it to the user as an error message. It prefixes the message with "Bruh." to add a bit of humor to the error output. This method is called whenever there is an error that needs to be communicated to the user, such as invalid input or issues with loading/saving tasks. The method simply prints the error message to the console for the user to see.
     * @param message
     */
    public void showError(String message) {
        System.out.println("Bruh. " + message);
    }

    /**
     * The showLoadingError method is a specific error message that is displayed when there is an issue loading tasks from the file. It informs the user that there was an error loading the file and that the application will start with an empty list of tasks. This method is called in the constructor of the Jeffry class if there is an exception thrown while trying to load tasks from the file, allowing the application to handle the error gracefully and continue running with an empty task list instead of crashing.
     */
    public void showLoadingError() {
        System.out.println("Error loading file. Starting with an empty list.");
    }

    /**
     * The showMessage method is a general-purpose method for displaying any message to the user. It takes a string message as a parameter and simply prints it to the console. This method can be used throughout the application whenever there is a need to display information to the user that doesn't fit into the specific categories of welcome messages, goodbye messages, or error messages. By having this method, it allows for consistent formatting and makes it easier to display messages without having to repeat the System.out.println() code each time.
     * @param message
     */
    // A handy helper method for printing general text
    public void showMessage(String message) {
        System.out.println(message);
    }
}