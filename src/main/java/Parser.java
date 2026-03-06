public class Parser {

    // Returns true if the program should exit, false otherwise
    public static boolean parse(String userInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            if (userInput.equals("bye")) {
                return true; // Time to exit!

            } else if (userInput.equals("list")) {
                ui.showMessage("Here are the tasks in your list:");
                for (int i = 0; i < tasks.getSize(); i++) {
                    ui.showMessage((i + 1) + "." + tasks.getTask(i).toString());
                }

            } else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" ");
                if (parts.length < 2) {
                    throw new JeffryException("Please specify which task number to mark.");
                }
                int index = Integer.parseInt(parts[1]) - 1;
                if (index < 0 || index >= tasks.getSize()) {
                    throw new JeffryException("That task number does not exist.");
                }
                tasks.getTask(index).markAsDone();
                storage.save(tasks.getTasks());
                ui.showMessage("Nice! I've marked this task as done:");
                ui.showMessage("  " + tasks.getTask(index));

            } else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" ");
                if (parts.length < 2) {
                    throw new JeffryException("Please specify which task number to unmark.");
                }
                int index = Integer.parseInt(parts[1]) - 1;
                if (index < 0 || index >= tasks.getSize()) {
                    throw new JeffryException("That task number does not exist.");
                }
                tasks.getTask(index).markAsNotDone();
                storage.save(tasks.getTasks());
                ui.showMessage("OK, I've marked this task as not done yet:");
                ui.showMessage("  " + tasks.getTask(index));

            } else if (userInput.startsWith("delete")) {
                String[] parts = userInput.split(" ");
                if (parts.length < 2) {
                    throw new JeffryException("Please specify which task number to delete.");
                }
                int index = Integer.parseInt(parts[1]) - 1;
                if (index < 0 || index >= tasks.getSize()) {
                    throw new JeffryException("That task number does not exist.");
                }
                Task removedTask = tasks.deleteTask(index);
                storage.save(tasks.getTasks());
                ui.showMessage("Noted. I've removed this task:");
                ui.showMessage("  " + removedTask);
                ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");

            } else if (userInput.startsWith("todo")) {
                if (userInput.trim().length() <= 4) {
                    throw new JeffryException("The description of a todo cannot be empty.");
                }
                String description = userInput.substring(5);
                Task newTodo = new Todo(description);
                tasks.addTask(newTodo);
                printAddedTask(newTodo, tasks.getSize(), ui, storage, tasks);

            } else if (userInput.startsWith("deadline")) {
                if (userInput.trim().length() <= 8) {
                    throw new JeffryException("The description of a deadline cannot be empty.");
                }
                if (!userInput.contains(" /by ")) {
                    throw new JeffryException("A deadline must include '/by' to define the time.");
                }
                String[] parts = userInput.substring(9).split(" /by ");
                Task newDeadline = new Deadline(parts[0], parts[1]);
                tasks.addTask(newDeadline);
                printAddedTask(newDeadline, tasks.getSize(), ui, storage, tasks);

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
                tasks.addTask(newEvent);
                printAddedTask(newEvent, tasks.getSize(), ui, storage, tasks);

            } else if (userInput.startsWith("find")) {
                if (userInput.trim().length() <= 4) {
                    throw new JeffryException("Please provide a keyword to search for (e.g., find book).");
                }

                String keyword = userInput.substring(5).trim();
                ui.showMessage("Here are the matching tasks in your list:");

                int matchCount = 0;
                for (int i = 0; i < tasks.getSize(); i++) {
                    Task currentTask = tasks.getTask(i);
                    // We check if the task's string representation contains the keyword
                    if (currentTask.toString().contains(keyword)) {
                        matchCount++;
                        ui.showMessage(matchCount + "." + currentTask.toString());
                    }
                }

                if (matchCount == 0) {
                    ui.showMessage("No matching tasks found for: " + keyword);
                }

            } else {
                throw new JeffryException("What talking you? :-(");
            }

        } catch (JeffryException e) {
            ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            ui.showError("Enter a valid number dummy.");
        }

        return false; // Keep the loop running
    }

    // Moved the helper method here since Parser handles adding tasks
    private static void printAddedTask(Task task, int count, Ui ui, Storage storage, TaskList tasks) {
        storage.save(tasks.getTasks());
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + task.toString());
        ui.showMessage("Now you have " + count + " tasks in the list.");
    }
}