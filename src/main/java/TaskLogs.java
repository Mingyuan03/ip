import java.util.ArrayList;

public class TaskLogs {
    private final ArrayList<Task> taskLogs;

    public TaskLogs() {
        this.taskLogs = new ArrayList<>();
    }

    /**
     * Low-level method reliant on DS of taskLogs field encapsulated from higher-level methods.
     * @param index 1-indexed task location.
     * @return Task at 1-indexed location.
     */
    public Task getTask(int index) {
        return this.taskLogs.get(index - 1);
    }

    /**
     * Low-level method reliant on DS of taskLogs field encapsulated from higher-level methods.
     * @return 1-indexed total task count.
     */
    public int getTaskCount() {
        return this.taskLogs.size();
    }

    /**
     * Append task to tail of taskLogs field.
     * @param task New task.
     */
    public void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        this.taskLogs.add(task);
        System.out.printf("[%c][ ] %s\n", task.getTypeIcon(), task.getDescription());
        if (this.taskLogs.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", this.getTaskCount());
        }
    }

    /**
     * Toggle status of task in taskLogs field from Undone to Done.
     * @param index 1-indexed task location.
     */
    public void markTask(int index) {
        Task task = this.getTask(index);
        if (task.isDone()) {
            System.out.println("Oops! This task is already marked as done.");return;
        }
        System.out.println("Nice! I've marked this task as done:");
        task.toggle();
        System.out.printf("[%c][X] %s\n", task.getTypeIcon(), task.getDescription());
    }

    /**
     * Re-toggle status of task in taskLogs field from Done to Undone, possibly due to human error.
     * @param index 1-index task location.
     */
    public void unmarkTask(int index) {
        Task task = this.getTask(index);
        if (!task.isDone()) {
            System.out.println("Oops! This task is already marked as undone.");return;
        }
        System.out.println("OK, I've marked this task as not done yet:");
        task.toggle();
        System.out.printf("[%c][ ] %s\n", task.getTypeIcon(), task.getDescription());
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int index = 1; index <= this.getTaskCount(); index++) {
            Task task = this.getTask(index);
            char taskType = task.getTypeIcon();
            char crossIfDone = task.getStatusIcon();
            System.out.printf("%d.[%c][%c] %s\n", index,
                    taskType, crossIfDone, task.getDescription());
        }
    }
}
