package log;

import creature.Creature;
import trajectory.Trajectory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LogWriter {

    private final ArrayList<String> logs = new ArrayList<>();

    public void writeLog(int refreshCycle) { synchronized (logs) { logs.add(refreshCycle + "\n"); } }

    public void writeLog(Creature creature) {
        synchronized (logs) {
            String str = creature.toString()
                    + creature.getCurHealth() / creature.getMaxHealth()
                    + "\n";
            logs.add(str);
        }
    }

    public void writeLog(Trajectory trajectory) {
        synchronized (logs) {
            String str = trajectory.toString()
                    + "\n";
            logs.add(str);
        }
    }

    public void writeStop() { synchronized (logs) { logs.add("stop\n"); } }

    public void finish(int result) { synchronized (logs) { logs.add("WINNER\n" + result); } }

    public void writeOut(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String log : logs) {
                writer.write(log);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() { return logs.isEmpty(); }

    public void clear() { logs.clear(); }

}
