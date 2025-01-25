import java.util.Scanner;

public class SteadyBot {
    private static final String NAME = "SteadyBot";
    private static final TaskLogs TASKLOGS = new TaskLogs();
    private static final String GREETING = "Hello! I'm " + NAME + "\nWhat can I do for you?";
    private static final String GOODBYE = "Bye! Hope to see you again soon!";
    private static final String delimiter = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(GOODBYE);
                System.out.println(delimiter);
                break;
            }
            String[] words = input.split(" ");
            String header = words[0].toLowerCase();
            if (header.equals("deadline")) {
                try {
                    Deadline deadline = new Deadline(input.substring(header.length() + 1));
                    TASKLOGS.addTask(deadline);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (header.equals("event")) {
                try {
                    Event event = new Event(input.substring(header.length() + 1));
                    TASKLOGS.addTask(event);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (header.equals("todo")) {
                ToDo todo = new ToDo(input.substring(header.length() + 1));
                TASKLOGS.addTask(todo);
            } else if (header.equals("list")) {
                TASKLOGS.printTasks();
            } else if (header.equals("mark")) {
                TASKLOGS.markTask(Integer.parseInt(words[1]));
            } else if (header.equals("unmark")) {
                TASKLOGS.unmarkTask(Integer.parseInt(words[1]));
            } else {
                System.out.println(input); // echo
            }
            System.out.println(delimiter);
        }
    }
}