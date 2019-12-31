package administer;

import playrecord.Record;

import java.util.ArrayList;

public class RecordAdminister {
    ArrayList<Record> commands=new ArrayList<>();
    public void addOne(Record c){
        commands.add(c);
    }
    public ArrayList<Record> getCommands(){return commands;}
    public void clearAll(){commands.clear(); }
    public void setCommands(ArrayList<Record> commands){
        this.commands=commands;
    }
}
