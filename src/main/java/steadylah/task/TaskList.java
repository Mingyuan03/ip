package steadylah.task;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import steadylah.exception.EmptyKeywordException;
import steadylah.exception.EmptyTaskListException;
import steadylah.exception.InvalidIndexException;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class TaskList {
    private static final Pattern CACHE_TASK_PATTERN = Pattern.compile("^\\[([DET])]\\[([X ])] (.+)$");
    private final ArrayList<Task> taskLogs;

    public TaskList() {
        this.taskLogs = new ArrayList<>();
    }

    /**
     * Low-level method reliant on DS of taskLogs field encapsulated from higher-level methods.
     * @param index 1-indexed task location.
     * @return task at 1-indexed location.
     */
    public Task getTask(int index) {
        try {
            return this.taskLogs.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(index);
        }
    }

    /**
     * Low-level method reliant on DS of taskLogs field encapsulated from higher-level methods.
     * @return 1-indexed total task count.
     */
    public int getTaskCount() {
        return this.taskLogs.size();
    }

    /**
     * Append task to tail of taskList.
     * @param task New task.
     */
    public String addTask(Task task) {
        assert task != null : "Task cannot be null for meaningful task insertion.";
        StringBuilder addResponse = new StringBuilder();
        addResponse.append("Got it. I've added this task:\n");
        this.taskLogs.add(task);
        addResponse.append(new Formatter().format("[%c][ ] %s\n", task.getTypeIcon(), task.getDescription()));
        if (this.taskLogs.size() == 1) {
            addResponse.append("Now you have 1 task in the list.");
        } else {
            addResponse.append(new Formatter().format(
                    "Now you have %d tasks in the list.", this.getTaskCount()));
        }
        return addResponse.toString();
    }

    /**
     * Remove the task at location index from taskList.
     * @param index 1-indexed task location.
     */
    public String deleteTask(int index) {
        StringBuilder deleteResponse = new StringBuilder();
        if (this.taskLogs.isEmpty()) {
            throw new EmptyTaskListException();
        } else if (index < 1 || index > this.getTaskCount()) {
            throw new IndexOutOfBoundsException("Index must be from 1 to " + this.getTaskCount());
        }
        deleteResponse.append("Noted. I've removed this task:\n");
        Task task = this.getTask(index);
        deleteResponse.append(new Formatter().format(
                "[%c][%c] %s\n", task.getTypeIcon(), task.getStatusIcon(), task.getDescription()));
        this.taskLogs.remove(index - 1); // Implicitly shift all subsequent tasks forward
        if (this.taskLogs.size() > 1) {
            deleteResponse.append(new Formatter().format(
                    "Now you have %d tasks in the list.", this.getTaskCount()));
        } else if (this.taskLogs.size() == 1) {
            deleteResponse.append("Now you have 1 task in the list.");
        } else {
            deleteResponse.append("Now you have no tasks in the list.");
        }
        return deleteResponse.toString();
    }

    /**
     * Toggle status of task in taskList field from Undone to Done.
     * @param index 1-indexed task location.
     */
    public String markTask(int index) {
        StringBuilder markResponse = new StringBuilder();
        if (this.taskLogs.isEmpty()) {
            throw new EmptyTaskListException();
        }
        Task task = this.getTask(index);
        if (task.isDone()) {
            markResponse.append("Oops! This task is already marked as done.");
        } else {
            markResponse.append("Nice! I've marked this task as done:\n");
            task.toggle();
            markResponse.append(new Formatter().format(
                    "[%c][X] %s", task.getTypeIcon(), task.getDescription()));
        }
        return markResponse.toString();
    }

    /**
     * Re-toggle status of task in taskList field from Done to Undone, possibly due to human error.
     * The author envisages expanding on its functionality with a gentle reminder on the counterintuitive
     * nature of unmarking a Done task.
     * @param index 1-index task location.
     */
    public String unmarkTask(int index) {
        StringBuilder unmarkResponse = new StringBuilder();
        if (this.taskLogs.isEmpty()) {
            throw new EmptyTaskListException();
        }
        Task task = this.getTask(index);
        if (!task.isDone()) {
            unmarkResponse.append("Oops! This task is already marked as undone.");
        } else {
            unmarkResponse.append("OK, I've marked this task as not done yet:\n");
            task.toggle();
            unmarkResponse.append(new Formatter().format(
                    "[%c][ ] %s", task.getTypeIcon(), task.getDescription()));
        }
        return unmarkResponse.toString();
    }

    /**
     * Print to console the index, task type, task status and content of the task at index location in taskList.
     * @param index 1-indexed task location.
     */
    public String printSingleTask(int index) {
        StringBuilder printSingleResponse = new StringBuilder();
        if (this.taskLogs.isEmpty()) {
            throw new EmptyTaskListException();
        }
        Task task = this.getTask(index);
        printSingleResponse.append("Here is the task in your list:\n");
        printSingleResponse.append(new Formatter().format(
                "%d.[%c][%c] %s", index, task.getTypeIcon(), task.getStatusIcon(), task.getDescription()));
        return printSingleResponse.toString();
    }

    /**
     * Print to console the index, task type, task status and content of all task(s) containing the keyword in content.
     * It is a deliberate designer choice to not search by whole keyword, since search functions in real-life products
     * default to having this level of flexibility.
     * @param keywords array of character sequences of consecutive alphabets to search by, vararg for more flexibility.
     */
    public String printRelevantTasks(String... keywords) {
        StringBuilder printRelevantResponse = new StringBuilder();
        if (keywords.length == 0) {
            throw new EmptyKeywordException();
        }
        HashSet<Integer> matchingTaskIndices = new HashSet<>();
        for (int index = 1; index <= this.getTaskCount(); index++) {
            Task task = this.getTask(index);
            for (String keyword : keywords) {
                if (task.isFoundKeyword(keyword)) {
                    matchingTaskIndices.add(index);
                    break; // Adaptive search
                }
            }
        }
        if (matchingTaskIndices.isEmpty()) {
            if (keywords.length == 1) {
                printRelevantResponse.append("Oops! There are no matching tasks for keyword: ");
            } else {
                printRelevantResponse.append("Oops! There are no matching tasks for keywords: ");
            }
            for (int index = 0; index < keywords.length - 1; index++) {
                printRelevantResponse.append(keywords[index]).append(",");
            }
            printRelevantResponse.append(keywords[keywords.length - 1]);
        } else if (matchingTaskIndices.size() == 1) {
            printRelevantResponse.append("Here is the unique matching task in your list:\n");
        } else {
            printRelevantResponse.append("Here are the matching tasks in your list:\n");
        }
        for (Integer index : matchingTaskIndices) {
            Task task = this.getTask(index);
            printRelevantResponse.append(new Formatter().format(
                    "%d.[%c][%c] %s",
                    index, task.getTypeIcon(), task.getStatusIcon(), task.getDescription()));
            if (index < matchingTaskIndices.size()) {
                printRelevantResponse.append("\n");
            }
        }
        return printRelevantResponse.toString();
    }

    /**
     * Print index, task type, task status and content of all tasks present in taskList in insertion order sequentially.
     */
    public String printTasks() {
        StringBuilder printAllResponse = new StringBuilder();
        if (this.getTaskCount() > 1) {
            printAllResponse.append("Here are the tasks in your list:\n");
        } else if (this.getTaskCount() == 1) {
            printAllResponse.append("Here is the task in your list:\n");
        } else {
            return "You have no tasks in your list.";
        }
        printAllResponse.append(this);
        return printAllResponse.toString();
    }

    /**
     * Add lines parsed from cache as task instances into taskList collection.
     * @param lines List of String data to be split into params for instantiating each task.
     */
    public void fromLines(List<String> lines) {
        this.taskLogs.clear();
        for (String line : lines) {
            Matcher matcher = CACHE_TASK_PATTERN.matcher(line);
            if (!matcher.matches()) {
                System.out.printf("Invalid log format: " + line);
                continue;
            }
            Task newTask = TaskList.getMatch(matcher);
            this.taskLogs.add(newTask);
        }
    }

    /**
     * Auxiliary (helper) utility split from TaskList::fromLines in line with Single Responsibility Principle (SRP).
     * @param matcher Encapsulation of intended Task components after passing it through implicit predicate.
     * @return Processed Task to load from cache.
     */
    private static Task getMatch(Matcher matcher) {
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
     * Extract from Task instances in TaskList collection the Strings to update cache.
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
            str.append(String.format("%d.[%c][%c] %s",
                    index, taskType, crossIfDone, task.getDescription()));
            if (index < this.getTaskCount()) {
                str.append("\n");
            }
        }
        return str.toString();
    }
}
