package steadylah.ui;

import java.util.Scanner;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class Ui {
    private static final String NAME = "SteadyLah";
    private static final String GREETING = "Hello! I'm " + NAME + ", your personal study planner!\n"
            + "What can I do for you?";
    private static final String GOODBYE = "Bye! Hope to see you again soon!";
    private static final String DELIMITER = "____________________________________________________________";

    // Scanner is best instantiated. Direct initialisation below omits class constructor solely for it.
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Print to console custom greeting message at the start of execution of task scheduler.
     */
    public void printGreeting() {
        System.out.println(GREETING);
        System.out.println(DELIMITER);
    }

    /**
     * Print to console custom goodbye message at the end of normal termination of task scheduler.
     * It is a designer choice that abnormal termination doesn't call this method.
     */
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
