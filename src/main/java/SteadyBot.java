import java.io.IOException;
import java.nio.file.Files; // utility class for files and directories
import java.nio.file.Paths; // utility class comprising static methods returning Path object
import java.nio.file.Path;

import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class SteadyBot {
    private static final String NAME = "SteadyBot";
    private static final String GREETING = "Hello! I'm " + NAME + "\nWhat can I do for you?";
    private static final String GOODBYE = "Bye! Hope to see you again soon!";
    private static final String DELIMITER = "____________________________________________________________";
    private static final TaskLogs TASKLOGS = new TaskLogs();

    // Construct absolute Path object w/o I/O operations, hence safe from IOException below.
    // Internally resolve to java.nio.file.FileSystem::getPath(String) and auto-handle path separator
    private static final Path CACHE_DIR = Paths.get("../cache").resolve("cache.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Files.createDirectories(CACHE_DIR.getParent());
            if (!Files.exists(CACHE_DIR)) {
                Files.createFile(CACHE_DIR);
            }
        } catch (IOException e) {
            System.err.println("Could not create directory and/or file: " + CACHE_DIR);
        }
        try {
            List<String> lines = Files.readAllLines(CACHE_DIR);
            TASKLOGS.fromLines(lines);
        } catch (IOException e) {
            System.err.println("Could not read file: " + CACHE_DIR);
        }

        System.out.println(GREETING);
        System.out.println(DELIMITER);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(GOODBYE);
                System.out.println(DELIMITER);
                try {
                    Files.write(CACHE_DIR, TASKLOGS.toLines(),
                            StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                } catch (IOException e) {
                    System.err.println("Could not save to log file: " + CACHE_DIR);
                }
                break;
            }
            String[] words = input.split(" ");
            String header = words[0].toLowerCase();
            Command command = Command.parseHeader(header);
            command.execute(input.substring(header.length()), TASKLOGS);
            System.out.println(DELIMITER);
        }
    }
}