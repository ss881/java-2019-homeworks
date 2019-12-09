package top.stqstq.maven_huluwa.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private BufferedWriter out = null;

    public void createLoggerFile() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
            //System.out.print("./log/" + df.format(new Date()).toString() + ".log\n");
            File fileName = new File("./log/" + df.format(new Date()).toString() + ".log");
            if (!fileName.getParentFile().exists()) {
                fileName.getParentFile().mkdirs();
            }
            fileName.createNewFile();
            out = new BufferedWriter((new FileWriter(fileName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeLoggerFile(String a) {
        try {
            if (out == null) return;
            out.write(a + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeLoggerFile() {
        try {
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
