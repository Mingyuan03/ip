package steadylah.ui;

import java.util.Scanner;

public class Ui {
    private static final String NAME = "SteadyLah";
    private static final String GREETING = "Hello! I'm " + NAME + ", your personal study planner!\n"
            + "What can I do for you?";
    private static final String GOODBYE = "Bye! Hope to see you again soon!";
    private static final String DELIMITER = "____________________________________________________________";

    // Scanner is best instantiated. Direct initialisation below omits class constructor solely for it.
    private final Scanner scanner = new Scanner(System.in);

    public void printGreeting() {
        System.out.println(GREETING);
        System.out.println(DELIMITER);
    }

    public void printGoodbye() {
        System.out.println(GOODBYE);
        System.out.println(DELIMITER);
    }

    public void printDelimiter() {
        System.out.println(DELIMITER);
    }

    /**
     * Encapsulate scanner as there exists many Scanner implementations.
     * Professor Halim's CS2040S and CS3230 Scanner implementations in NUS are poignant examples.
     * @return String input from end-user for further task processing.
     */
    public String readInput() {
        return scanner.nextLine();
    }
}
