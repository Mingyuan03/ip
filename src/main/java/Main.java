import java.util.concurrent.TimeUnit;
public class Main {
    private static final String name = "SteadyBot";

    /** Simple method to greet human user, deliberately waiting 5 seconds
     *  before greeting and saying goodbye for a more natural user experience.
     *  */
    public static void greet() throws InterruptedException {
        System.out.println("Hello! I'm " + Main.name);
        System.out.println("What can I do for you?");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) throws InterruptedException {
        greet();
    }
}
