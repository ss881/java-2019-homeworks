package record;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static utils.Config.RECORDS;

public class RecordManager {
    public RecordManager() {
        RECORDS = new ArrayList<Record>();
    }

    void clear() {
        RECORDS.clear();
    }

    public void saveFile() {
        int i = 0;
        JSONArray jsonArray = new JSONArray();
        for (Record record : RECORDS) {
            JSONObject jsonObject = record.toJson();
            jsonObject.put("step", i);
            jsonArray.put(jsonObject);
            i++;
        }
        String string = jsonArray.toString();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH_mm_ss");//可以方便地修改日期格式
        String time = dateFormat.format(now);

        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(System.getProperty("user.dir") + "/" + time + ".json"), false)));
            writer.write(string);
            writer.close();
            System.out.println("Save completed at "+System.getProperty("user.dir") + "/" + time + ".json");
        }catch (IOException e){
            System.out.println("Saving fail");
        }

    }

    public void readFile(File file) throws IOException, NullPointerException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String temp = "";
        while ((temp = bufferedReader.readLine()) != null)
            stringBuilder.append(temp);
        bufferedReader.close();
        JSONArray jsonArray = new JSONArray(stringBuilder.toString());
        RECORDS.clear();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject temJson = jsonArray.getJSONObject(i);
            String winTeam = temJson.getString("winTeam");
            String name = temJson.getString("name");
            String action = temJson.getString("action");
            String objName = temJson.getString("objName");
            int ownLive = temJson.getInt("ownLive");
            int enemyLive = temJson.getInt("enemyLive");
            int preX = temJson.getInt("preX");
            int preY = temJson.getInt("preY");
            int objX = temJson.getInt("objX");
            int objY = temJson.getInt("objY");
            RECORDS.add(new Record(winTeam, name, action, objName, ownLive, enemyLive, preX, preY, objX, objY));
        }
    }

}
