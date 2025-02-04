package steadylah.command;

import steadylah.storage.Storage;
import steadylah.task.TaskList;
import steadylah.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand(String contentInput) {
        super(contentInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("You sought help! "
                + "As Dumbledore says, help will always be given to those who ask for it");
        System.out.println(Help.getFullHelp());
    }
}
