import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

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

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showError(String message) {
        System.out.println("Bruh. " + message);
    }

    public void showLoadingError() {
        System.out.println("Error loading file. Starting with an empty list.");
    }

    // A handy helper method for printing general text
    public void showMessage(String message) {
        System.out.println(message);
    }
}