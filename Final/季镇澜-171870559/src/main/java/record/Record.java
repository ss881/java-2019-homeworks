package record;

import org.json.JSONObject;

public class Record {
    public String winTeam;
    public String name;
    public String action;
    public String objName;
    public int ownLive;
    public int enemyLive;
    public int preX;
    public int preY;
    public int objX;
    public int objY;

    public Record(String winTeam, String name, String action, String objName, int ownLive, int enemyLive, int preX, int preY, int objX, int objY) {
        this.winTeam = winTeam;
        this.name = name;
        this.action = action;
        this.objName = objName;
        this.ownLive = ownLive;
        this.enemyLive=enemyLive;
        this.preX = preX;
        this.preY = preY;
        this.objX = objX;
        this.objY = objY;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("winTeam", winTeam);
        jsonObject.put("name", name);
        jsonObject.put("action", action);
        jsonObject.put("objName", objName);
        jsonObject.put("ownLive", ownLive);
        jsonObject.put("enemyLive", enemyLive);
        jsonObject.put("preX", preX);
        jsonObject.put("preY", preY);
        jsonObject.put("objX", objX);
        jsonObject.put("objY", objY);
        return jsonObject;
    }
}
