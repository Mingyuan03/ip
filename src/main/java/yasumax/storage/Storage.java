package yasumax.storage;

import java.io.IOException;
import java.nio.file.Files; // Utility class for files and directories
import java.nio.file.Path;
import java.nio.file.Paths; // Utility class comprising static methods returning Path object
import java.nio.file.StandardOpenOption;
import java.util.List;

import yasumax.task.TaskList;

/**
 * @author Lu Mingyuan
 * @version v1.0.0-alpha
 */
public class Storage {
    private static final Path CACHE_DIR = Paths.get("../cache").resolve("cache.txt");

    /**
     * Implement persistence by initialising taskList Data Structure to any previously-saved state.
     * @param taskList Underlying list-like DS for in-place task manipulation.
     */
    public void loadTasksFromCache(TaskList taskList) {
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

    /**
     * Implement persistence by saving final scheduler state on machine for any future executes.
     * @param taskList Underlying list-like DS for in-place task manipulation.
     */
    public void saveTasksToCache(TaskList taskList) {
        try {
            Files.write(CACHE_DIR, taskList.toLines(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Could not save to cache file: " + CACHE_DIR);
        }
    }
}
