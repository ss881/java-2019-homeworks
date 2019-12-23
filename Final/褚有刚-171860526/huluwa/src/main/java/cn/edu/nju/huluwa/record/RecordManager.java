package cn.edu.nju.huluwa.record;

import cn.edu.nju.huluwa.huluworld.HuluWorld;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RecordManager {
    private List<Record> records;
    private long timeMills;
    private HuluWorld huluWorld;

    public RecordManager(HuluWorld huluWorld) {
        this.huluWorld = huluWorld;
        timeMills = 0;
        records = new ArrayList<>();
    }

    public void clear() {
        timeMills = 0;
        records.clear();
    }

    public synchronized void add(Record record) {
        if (timeMills == 0) timeMills = System.currentTimeMillis();
        else {
            long currentTimeMillis = System.currentTimeMillis();
            record.setTimeMillis(currentTimeMillis - timeMills);
            timeMills = currentTimeMillis;
        }
        if (record.type == Record.RecordType.DEAD) {
            huluWorld.getScene().showInfo(huluWorld.getCreature(record.getId())
                    + " is killed.\n");
        }
        records.add(record);
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }

    public void exportRecords(File f) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(f), "utf-8"))) {
            Gson gson = new Gson();
            StringBuilder sb = new StringBuilder();
            sb.append("[\n");
            for (Record record : records) {
                sb.append("\t" + gson.toJson(record, record.getClass()) + ",\n");
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("]");
            bw.write(sb.toString());
//            TypeToken<CopyOnWriteArrayList<Record>> typeToken = new TypeToken<>() {
//            };
//            bw.write(gson.toJson(records, typeToken.getType()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        records.clear();
    }

    public void importRecords(File f) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(f), "utf-8"))) {
            Gson gson = new Gson();
            TypeToken<CopyOnWriteArrayList<Record>> typeToken = new TypeToken<>() {
            };
            records = gson.fromJson(br, typeToken.getType());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Record> getRecords() {
        return records;
    }
}
