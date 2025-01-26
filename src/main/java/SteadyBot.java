import java.util.Scanner;

public class SteadyBot {
    private static final String NAME = "SteadyBot";
    private static final TaskLogs TASKLOGS = new TaskLogs();
    private static final String GREETING = "Hello! I'm " + NAME + "\nWhat can I do for you?";
    private static final String GOODBYE = "Bye! Hope to see you again soon!";
    private static final String DELIMITER = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);
        System.out.println(DELIMITER);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(GOODBYE);
                System.out.println(DELIMITER);
                break;
            }
            String[] words = input.split(" ");
            String header = words[0].toLowerCase();
            Command command = Command.parseHeader(header);
            command.execute(input.substring(header.length()), TASKLOGS);
            System.out.println(DELIMITER);
        }
    }
}