package gui;

import javafx.application.Application;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class Launcher {
    /**
     * Bypass launcherHelper's launch abortion if main exists in Application subclass yet graphics does not exist.
     * @param args Optional Java varargs.
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
