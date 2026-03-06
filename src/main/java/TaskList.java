import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    // Constructor for starting with an empty list
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Constructor for when you load tasks from Storage
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    // Storage needs the raw ArrayList to save to the hard drive
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}