package sample;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class FileOperator {
    DataOutputStream _output = null;
    DataInputStream _input = null;
    boolean _gameresult = false;
    boolean _winner = false;
    public FileOperator() {
        String filename = ".gourd";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        filename = "src\\main\\resources\\record\\" + format.format(date) + filename;
        File file = new File(filename);
        try {
            if (file.exists() == false) {
                file.createNewFile();
            }
            _output = new DataOutputStream(
                    new FileOutputStream(file)
            );
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public FileOperator(String filename){
        if (filename == null || filename == "") return;
        try {
            _input = new DataInputStream(
                    new FileInputStream(filename)
            );
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public synchronized void writeCreation(Creation c){
        if (_output == null) return;
        try {
            _output.writeInt(c.getKeyNumber());
            _output.writeInt(c.getType());
            _output.writeInt(c.getPositionX());
            _output.writeInt(c.getPositionY());
            _output.writeBoolean(c.getAlive());
            _output.writeInt(c.getAttribute(Attribute.HEALTH));
            _output.writeInt(c.getAttribute(Attribute.CONSTITUTION));
            _output.writeInt(c.getShowText().getBytes().length);
            _output.writeBytes(c.getShowText());
            _output.writeInt(c.getShowTime());

            Vector<Influence> influences = c.getInfluence();
            if (influences.size() < Creation.INFLUENCE_SHOW)
                _output.writeInt(influences.size());
            else
                _output.writeInt(Creation.INFLUENCE_SHOW);
            for (int i = 0; i < influences.size() && i < Creation.INFLUENCE_SHOW; i++) {
                Influence influence = influences.get(i);
                _output.writeInt(influence.getType());
                _output.writeInt(influence.getNumber());
            }
            _output.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public void writeResult(boolean result, boolean winner) {
        if (_output == null) return;
        try {
            _output.writeInt(-10086);
            _output.writeBoolean(result);
            _output.writeBoolean(winner);
            _output.flush();
            _output.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public Creation readCreation(GroundMap map) {
        if (_input == null) return null;
        int number = 0, type = 0, pos_x = 0, pos_y = 0, health = 0, constitution = 0;
        int bytes_length = 0, show_time = 0;
        byte[] bytes = new byte[20];
        String show_text = "";
        Creation ret = null;
        boolean alive = true;
        try {
            number = _input.readInt();
            if (number == -10086) {
                _gameresult = _input.readBoolean();
                _winner = _input.readBoolean();
                _input.close();
                return null;
            }
            type = _input.readInt();
            pos_x = _input.readInt();
            pos_y = _input.readInt();
            alive = _input.readBoolean();
            health = _input.readInt();
            constitution = _input.readInt();
            bytes_length = _input.readInt();
            _input.read(bytes, 0, bytes_length); show_text = new String(bytes);
            show_time = _input.readInt();

            ret = new Creation(number, type, pos_x, pos_y, map, this);
            ret.setAlive(alive);
            ret.setAttribute(Attribute.HEALTH, health);
            ret.setAttribute(Attribute.CONSTITUTION, constitution);
            ret.setShowText(show_text);
            ret.setShowTime(show_time);

            int size = _input.readInt();
            for (int i = 0; i < size; i++) {
                int i_type = _input.readInt(), i_number = _input.readInt();
                ret.addInfluence(new Influence(i_type, i_number, 10));
            }
        }
        catch (EOFException e) {
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return ret;
    }

    public boolean getGameResult() {return _gameresult;}
    public boolean getWinner() {return _winner;}
}
