public class Jeffry {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

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

    public static void main(String[] args) {
        new Jeffry("./data/jeffry.txt").run();
    }
}