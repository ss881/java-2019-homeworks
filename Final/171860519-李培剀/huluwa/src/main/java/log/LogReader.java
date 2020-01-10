package log;

import java.io.*;
import java.util.ArrayList;

public class LogReader {

    private ArrayList<String> logs = new ArrayList<>();
    private int curLogIndex = 0;
    private boolean isReplaying = false;

    public void readeIn(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null) {
                logs.add(line);
            }
            reader.close();
            isReplaying = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRefreshCycle() { return Integer.parseInt(logs.get(curLogIndex++)); }

    public String[] getOneFrame() {
        ArrayList<String> frame = new ArrayList<>();
        while (curLogIndex < logs.size()) {
            String str = logs.get(curLogIndex++);
            if (str.equals("stop"))
                break;
            frame.add(str);
        }
        if (frame.isEmpty()) {
            isReplaying = false;
            return null;
        }
        else {
            return frame.toArray(new String[0]);
        }
    }

    public boolean isReplaying() { return isReplaying; }

    public boolean isEmpty() { return logs == null || logs.isEmpty(); }

    public void clear() { curLogIndex = 0; isReplaying = false; logs.clear(); }

}
