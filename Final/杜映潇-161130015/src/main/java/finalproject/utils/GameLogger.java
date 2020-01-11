package finalproject.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;

public class GameLogger implements Logger {

    private File logFile;
    private List<String> logs;
    private static GameLogger logger = null;

    private GameLogger(String logFileName) {
        logFile = new File(logFileName);
        logs = new ArrayList<>();
    }

    public static GameLogger getInstance(String logFileName) {
        if (logFileName != null) {
            logger = new GameLogger(logFileName);
        }
        return logger;
    }

    public static String getLogFileName() {
        return System.currentTimeMillis() + ".cglog";
    }

    @Override
    public String getCurrentLogFileName() {
        return logFile.getName();
    }

    @Override
    public void clear() {
        logger = null;
    }

    @Override
    public void do_logging() {
        try {
            Writer writer = new FileWriter(logFile);
            for (String log: logs) {
                writer.write(log);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add_log(String log) {
        logs.add(log);
    }
}
