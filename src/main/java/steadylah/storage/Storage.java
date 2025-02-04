package steadylah.storage;

import steadylah.task.TaskList;

import java.io.IOException;
import java.nio.file.Files; // Utility class for files and directories
import java.nio.file.Paths; // Utility class comprising static methods returning Path object
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Storage {
    private static final Path CACHE_DIR = Paths.get("../cache").resolve("cache.txt");

    public void loadFromCache(TaskList taskList) {
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
            taskList.fromLines(lines);
        } catch (IOException e) {
            System.err.println("Could not read cache file: " + CACHE_DIR);
        }
    }

    public void saveToCache(TaskList taskList) {
        try {
            Files.write(CACHE_DIR, taskList.toLines(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Could not save to cache file: " + CACHE_DIR);
        }
    }
}
