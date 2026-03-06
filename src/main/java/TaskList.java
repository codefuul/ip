import java.util.ArrayList;
/**
 * The TaskList class manages a list of Task objects. It provides methods to add, delete, and retrieve tasks, as well
 * as to get the size of the list.
 * It also has a method to return the raw ArrayList of tasks, which is used by the Storage class to save tasks to a file.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * The TaskList class has two constructors: one for starting with an empty list, and another for when you load tasks
     * from Storage. The first constructor initializes an empty ArrayList, while the second constructor takes an
     * existing ArrayList of tasks and uses it to initialize the TaskList.
     */
    // Constructor for starting with an empty list
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * The second constructor takes an existing ArrayList of tasks and uses it to initialize the TaskList. This is used when
     * loading tasks from Storage, allowing the TaskList to be populated with the tasks that were previously saved to the file.
     * @param tasks
     */
    // Constructor for when you load tasks from Storage
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * The addTask method takes a Task object as a parameter and adds it to the list of tasks. This method is used when the user
     * creates a new task (e.g., by entering "todo", "deadline", or "event" commands), allowing the new task to be added to the TaskList.
     * @param task The Task object to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     * The deleteTask method takes an index as a parameter and removes the task at that index from the list of tasks. It returns the removed Task object.
     * This method is used when the user deletes a task (e.g., by entering "delete 2"), allowing the specified task to be removed from the TaskList.
     * @param index The index of the task to be removed from the list.
     * @return The Task object that was removed from the list.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }
    /**
     * The getTask method takes an index as a parameter and returns the Task object at that index in the list of tasks. This method is used when the user
     * marks a task as done (e.g., by entering "done 3"), allowing the specified task to be retrieved from the TaskList so that it can be marked as done.
     * @param index The index of the task to be retrieved from the list.
     * @return The Task object at the specified index in the list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * The getSize method returns the number of tasks currently in the list. This method is used when the user deletes a task, allowing the application to display the updated number of tasks in the list (e.g., "Now you have 4 tasks in the list.").
     * @return
     */
    public int getSize() {
        return tasks.size();
    }
    /**
     * The getTasks method returns the raw ArrayList of Task objects. This method is used by the Storage class when saving tasks to a file, allowing the Storage class to access the list of tasks and write them to the file in the appropriate format.
     * @return The raw ArrayList of Task objects.
     */
    // Storage needs the raw ArrayList to save to the hard drive
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}