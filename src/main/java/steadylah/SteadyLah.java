package steadylah;

import steadylah.command.Command;
import steadylah.command.CommandParser;
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
     * Call intended scheduler behaviour per usage, abstractifying out implementation details.
     * This author envisages expanding on its implementation via a timeout mechanism to avoid
     * the theoretical edge case of the scheduler being stuck in loop anticipating user response.
     */
    public void execute() {
        this.ui.printGreeting();
        this.storage.loadFromCache(this.taskList);
        while (true) {
            String rawInput = ui.readInput();
            if (rawInput.equals("bye")) {
                this.ui.printGoodbye();
                this.storage.saveToCache(this.taskList);
                break;
            }
            try {
                Command command = CommandParser.parseCommand(rawInput);
                command.execute(this.taskList, this.ui, this.storage);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            this.ui.printDelimiter();
        }
    }

    public static void main(String[] args) {
        new SteadyLah().execute();
    }

    public String getResponse(String searchInput) {
        try {
            Command command = CommandParser.parseCommand(searchInput);
            return command.execute(this.taskList, this.ui, this.storage);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
