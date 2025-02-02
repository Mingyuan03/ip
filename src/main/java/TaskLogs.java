import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskLogs {
    private static final Pattern CACHE_TASK_PATTERN = Pattern.compile("^\\[([DET])]\\[([X ])] (.+)$");
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

    public void deleteTask(int index) {
        if (index < 1 || index > this.getTaskCount()) {
            throw new IndexOutOfBoundsException("Index must be from 1 to " + this.getTaskCount());
        }
        System.out.println("Noted. I've removed this task:");
        Task task = this.getTask(index);
        System.out.printf("[%c][%c] %s\n",
                task.getTypeIcon(), task.getStatusIcon(), task.getDescription());
        this.taskLogs.remove(index - 1); // Implicitly shift all subsequent tasks forward
        if (this.taskLogs.size() > 1) {
            System.out.printf("Now you have %d tasks in the list.\n", this.getTaskCount());
        } else if (this.taskLogs.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have no tasks in the list.");
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

    public void printSingleTask(int index) {
        Task task = this.getTask(index);
        System.out.printf("Here is the specific task %d in your list:\n", index);
        System.out.printf("%d.[%c][%c] %s\n",
                index, task.getTypeIcon(), task.getStatusIcon(), task.getDescription());
    }

    public void printTasks() {
        if (this.getTaskCount() > 1) {
            System.out.println("Here are the tasks in your list:");
        } else if (this.getTaskCount() == 1) {
            System.out.println("Here is the task in your list:");
        } else {
            System.out.println("You have no tasks in your list.");
            return;
        }
        System.out.print(this);
    }

    /**
     * Add lines parsed from cache as Task instances into TaskLogs collection.
     * @param lines List of String data to be split into params for instantiating each Task.
     */
    public void fromLines(List<String> lines) {
        this.taskLogs.clear();
        for (String line : lines) {
            Matcher matcher = CACHE_TASK_PATTERN.matcher(line);
            if (!matcher.matches()) {
                System.out.printf("Invalid log format: " + line);
                continue;
            }
            Task newTask = getTask(matcher);
            this.taskLogs.add(newTask);
        }
    }

    private static Task getTask(Matcher matcher) {
        char taskType = matcher.group(1).charAt(0);
        char statusIcon = matcher.group(2).charAt(0);
        String description = matcher.group(3).trim();
        Task newTask = switch (taskType) {
            case 'D' -> new Deadline(Deadline.getRawDescription(description));
            case 'E' -> new Event(Event.getRawDescription(description));
            case 'T' -> new ToDo(description);
            default -> throw new IllegalStateException("Task type not in 'DET': " + taskType);
        };
        if (statusIcon == 'X') {
            newTask.toggle();
        }
        return newTask;
    }

    /**
     * Extract from Task instances in TaskLogs collection the Strings to update cache.
     * @return List of String data to overwrite cache.
     */
    public List<String> toLines() {
        ArrayList<String> cacheLines = new ArrayList<>();
        for (Task task : this.taskLogs) {
            cacheLines.add(String.format("[%c][%c] %s",
                    task.getTypeIcon(), task.getStatusIcon(), task.getDescription()));
        }
        return cacheLines;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int index = 1; index <= this.getTaskCount(); index++) {
            Task task = this.getTask(index);
            char taskType = task.getTypeIcon();
            char crossIfDone = task.getStatusIcon();
            str.append(String.format("%d.[%c][%c] %s\n",
                    index, taskType, crossIfDone, task.getDescription()));
        }
        return str.toString();
    }
}
