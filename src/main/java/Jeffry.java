
/**
 * The main class for the Jeffry application.
 * It initializes the necessary components and runs the main loop.
 */
public class Jeffry {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * The constructor initializes the Ui, Storage, and TaskList components. It attempts to load tasks from the specified file path using the Storage class. If loading fails (e.g., due to a missing file or corrupted data), it catches the JeffryException, shows a loading error message to the user, and initializes an empty TaskList instead.
     * @param filePath
     */
    public Jeffry(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JeffryException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * The main loop of the application. It shows a welcome message, then continuously reads user input,
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        // This is the entire core loop now!
        while (!isExit) {
            String userInput = ui.readCommand();
            ui.showLine();
            isExit = Parser.parse(userInput, tasks, ui, storage);
            ui.showLine();
        }

        ui.showGoodbye();
    }

    /**
     * The main method creates a new instance of Jeffry with the specified file path and runs it.
     * @param args
     */
    public static void main(String[] args) {
        new Jeffry("./data/jeffry.txt").run();
    }
}