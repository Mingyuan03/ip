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
     * Retrieve custom greeting message at the start of task scheduler
     * @return Custom greeting message dependent on UI mode being either CLI xor GUI.
     */
    public String getGreeting(boolean isGUI) {
        return isGUI ? Ui.GREETING : Ui.GREETING + "\n" + Ui.DELIMITER;
    }

    /**
     * Retrieve custom goodbye message at the end of normal termination of task scheduler.
     * It is a designer choice that abnormal termination doesn't call this method.
     * @return Custom goodbye message dependent on UI mode being either CLI xor GUI.
     */
    public String getGoodbye(boolean isGUI) {
        return isGUI ? Ui.GOODBYE : Ui.GOODBYE + "\n" + Ui.DELIMITER;
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
