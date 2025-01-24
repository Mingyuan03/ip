import java.util.Scanner;
public class Main {
    private static final String name = "SteadyBot";
    /**
     * Simple method to greet human user.
     */
    public static void greet() {
        System.out.println("Hello! I'm " + Main.name);
        System.out.println("What can I do for you?");
    }

    /**
     * Simple method to say goodbye to human user.
     */
    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Main method of class Main.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                goodbye();
                break;
            }
            System.out.println(input);
        }
    }
}
