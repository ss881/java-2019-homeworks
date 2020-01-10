package game;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;

public class Log {
    public static File nowlog;
    public static LinkedList data;

    public static void createLog() throws IOException {
        nowlog = new File("./src/resources/log/CalabashGame" + new Date().getTime() + ".txt");
        nowlog.createNewFile();
        System.out.println("为本次游戏创建日志" + nowlog.toString());
        FileWriter writer = new FileWriter(nowlog);
        writer.write("假装自己是Magic\r\n日期：" + new Date() + "\r\n");
        writer.flush();
        writer.close();
    }

    public static void writeLog(String s) throws IOException {
        FileWriter writer = new FileWriter(nowlog, true);
        writer.write(s + "\r\n");
        writer.flush();
        writer.close();
    }

    public static void readLog(File log) throws IOException {
        //读取log中的随机因子存入data
        data = new LinkedList<Integer>();

        FileReader reader = new FileReader(log);
        BufferedReader br = new BufferedReader(reader);
        String line;
        line = br.readLine();
        line = br.readLine();
        line = br.readLine();

        while (line != null) {
            data.add(Integer.valueOf(line).intValue());
            line = br.readLine();
        }

        br.close();
    }
}
