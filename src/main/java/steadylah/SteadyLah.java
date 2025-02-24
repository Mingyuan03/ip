package steadylah;

import java.lang.reflect.InvocationTargetException;

import steadylah.command.Command;
import steadylah.command.CommandProcessor;
import steadylah.command.HelpCommand;
import steadylah.exception.InvalidCommandException;
import steadylah.exception.SteadyLahException;
import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

/**
 * Define an academic task scheduler for students who are more accustomed to CLI over GUI.
 * The author envisages expanding this bot to incorporate more Singlish elements as a means of
 * improving its user-friendliness and uniqueness on the UI aspect.
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class SteadyLah {
    private final Storage storage = new Storage();
    private final TaskList taskList = new TaskList();
    private final Ui ui = new Ui();

    /**
     * Handle command-processing logic for both CLI and GUI modes as an auxiliary class of execute(),
     * separated from it so that its String return value qualifies as input for getResponse linking to Gui.java.
     * @param rawInput Human-user descriptionString input.
     * @return Response String to be displayed directly in GUI mode.
     */
    public String[] processCommandByMap(String rawInput) {
        try {
            Command command = CommandProcessor.processCommandByMap(rawInput);
            // command::execute needs to return String always for GUI-mode compatibility.
            return new String[]{
                    command.execute(this.taskList, this.ui, this.storage),
                    String.valueOf(command instanceof HelpCommand)
            };
        } catch (InvalidCommandException e) {
            // Separation of concern for this exception vs SteadyLahException subclasses for more scalability lest
            // future project increments customise exception-handling to no longer respond with a help message.
            return new String[]{e.getMessage(), "true"};
        } catch (SteadyLahException e) {
            return new String[]{e.getMessage(), "false"};
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException
                 | IllegalAccessException e) {
            return new String[]{"Error in processing command: " + e.getMessage(), "false"};
        } catch (RuntimeException e) {
            return new String[]{"A wild runtime exception occurred: " + e.getMessage(), "false"};
        }
    }

    /**
     * Simulate CLI mode's storage::loadFromCache directly here in GUI mode, given its lack of storage, for persistence.
     * Bundle load and greet functionalities to reduce nesting vs strict conformance to SLAP.
     * @return Greeting message identical to CLI mode's ui::getGreeting(true) solely for GUI mode.
     */
    public String loadTasksFromCache() {
        this.storage.loadFromCache(this.taskList);
        return this.ui.getGreeting(true);
    }

    /**
     * Simulate CLI mode's storage::saveToCache directly here in GUI mode, given its lack of storage, for persistence.
     * Bundle save and goodbye functionalities to reduce nesting vs strict conformance to SLAP.
     * @return Goodbye message identical to CLI mode's ui::getGoodbye(true) solely for GUI mode.
     */
    public String saveTasksToCache() {
        this.storage.saveToCache(this.taskList);
        return this.ui.getGoodbye(true);
    }

    /**
     * Call intended scheduler behaviour per usage in CLI mode, abstractifying out implementation details.
     * This author envisages expanding on its implementation via a timeout mechanism to avoid
     * the theoretical edge case of the scheduler being stuck in loop anticipating user response.
     */
    public void execute() {
        System.out.println(this.ui.getGreeting(false));
        this.storage.loadFromCache(this.taskList);
        while (true) {
            String rawInput = ui.readInput().trim();
            if (rawInput.equals("bye")) {
                System.out.println(this.ui.getGoodbye(false));
                this.storage.saveToCache(this.taskList);
                break;
            }
            String[] response = this.processCommandByMap(rawInput);
            System.out.println(response[0]); // Print response for CLI mode.
            this.ui.printDelimiter();
        }
    }

    /**
     * Run program in solely CLI mode.
     * @param args Optional arguments.
     */
    public static void main(String[] args) {
        new SteadyLah().execute();
    }
}
