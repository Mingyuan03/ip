package steadylah;

import steadylah.command.Command;
import steadylah.command.CommandParser;
import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

public class SteadyLah {
    private final Storage storage = new Storage();
    private final TaskList taskList = new TaskList();
    private final Ui ui = new Ui();

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
            Command command = CommandParser.parseCommand(rawInput);
            command.execute(this.taskList, this.ui, this.storage);
            this.ui.printDelimiter();
        }
    }
    public static void main(String[] args) {
        new SteadyLah().execute();
    }
}