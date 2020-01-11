package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.util.Pair;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.lang.*;
import java.util.*;

public class Creation extends Thread{
    public final static int
        GRANDPA = 0, DOLLONE = 1, DOLLTWO = 2,
        DOLLTHREE = 3, DOLLFOUR = 4, DOLLFIVE = 5,
        DOLLSIX = 6, DOLLSEVEN = 7, SCORP = 8,
        SNAKE = 9, SOLDIER = 10, TOTAL_NUMBER = 11;
    private final static String[] GRAPHY_ALIVE_FILENAME = {
            "src\\main\\resources\\sorce\\character\\Grandpa.png", "src\\main\\resources\\sorce\\character\\DollOne.png",
            "src\\main\\resources\\sorce\\character\\DollTwo.png", "src\\main\\resources\\sorce\\character\\DollThree.png",
            "src\\main\\resources\\sorce\\character\\DollFour.png", "src\\main\\resources\\sorce\\character\\DollFive.png",
            "src\\main\\resources\\sorce\\character\\DollSix.png", "src\\main\\resources\\sorce\\character\\DollSeven.png",
            "src\\main\\resources\\sorce\\character\\Scorp.png", "src\\main\\resources\\sorce\\character\\Snake.png",
            "src\\main\\resources\\sorce\\character\\Soldier.png"
    };
    private final static String[] GRAPHY_DEAD_FILENAME = {
            "src\\main\\resources\\sorce\\character\\GrandpaDead.png", "src\\main\\resources\\sorce\\character\\DollOneDead.png",
            "src\\main\\resources\\sorce\\character\\DollTwoDead.png", "src\\main\\resources\\sorce\\character\\DollThreeDead.png",
            "src\\main\\resources\\sorce\\character\\DollFourDead.png", "src\\main\\resources\\sorce\\character\\DollFiveDead.png",
            "src\\main\\resources\\sorce\\character\\DollSixDead.png", "src\\main\\resources\\sorce\\character\\DollSevenDead.png",
            "src\\main\\resources\\sorce\\character\\ScorpDead.png", "src\\main\\resources\\sorce\\character\\SnakeDead.png",
            "src\\main\\resources\\sorce\\character\\SoldierDead.png"
    };
    public final static int INTERVAL = 1000;
    public final static int INFLUENCE_SHOW = 4;

    private final static int SHOW_TIME = 5;
    private String _show_text = "";
    private int _show_time = 0;

    final GroundMap _map;
    int _type, _key;
    int _pos_y, _pos_x;
    FileOperator _filer;

    boolean _group, _alive;
    Random _rand;
    Image _image_alive;
    Image _image_dead;
    Attribute _attribute;
    Skill _skill;
    Vector<Influence> _influences;

    public Creation(int number, int type, int x, int y, GroundMap map, FileOperator fileOperator) {
        if (type < 0 || type >= TOTAL_NUMBER) throw new RuntimeException("" + type);
        _map = map;
        setDaemon(true);
        if (type >= TOTAL_NUMBER) return;
        _type = type; _key = number;
        _group = (_type <= DOLLSEVEN) ? true : false;
        _alive = true; _pos_x = x; _pos_y = y;
        _filer = fileOperator;

        _rand = new Random(System.nanoTime());
        try {
            _image_alive = new Image(new FileInputStream(GRAPHY_ALIVE_FILENAME[_type]));
            _image_dead = new Image(new FileInputStream(GRAPHY_DEAD_FILENAME[_type]));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        _attribute = new Attribute(_rand);
        _skill = new Skill(type);
        _influences = new Vector<Influence>();
        if (_map.isGameStart() == true)
            _filer.writeCreation(this);
        start();
    }
    public void run() {
        while (true) {
            if (_map.isGameStart() == false)
                return;
            else if (_map.isGameSuspend() == true)
                yield();
            else if (_alive == false) {
                try { wait(); }
                catch (Exception e) { ; }
            }
            else {
                refresh();
                int interval = INTERVAL;
                synchronized (_map) {
                    if (_map.isGameStart())
                    interval = lockedAction();
                }
                try { sleep(interval); }
                catch (Exception e) { ; }
            }
        }
    }
    private int lockedAction() {
        boolean skip = _skill.executeSkill(this, _map);
        if (skip == false) {
            skip = attack();
            if (skip == false)
                move();
        }
        _filer.writeCreation(this);
        Attribute a = getAttributesInfluence();
        int rate = Judge.judge(a.getAttribute(Attribute.SPEED), _rand) + 1;
        return INTERVAL / rate;
    }
    private boolean attack() {
        Vector<Creation> targets = _map.selectCreations(this, 1, !_group, true);
        if (targets.isEmpty() == true) return false;
        Creation target = (Creation) targets.get(_rand.nextInt(targets.size()));

        Attribute order_attr = getAttributesInfluence();
        int atksuccess = Judge.judge(order_attr.getAttribute(Attribute.ATTACK), _rand);
        int damage = _rand.nextInt(order_attr.getAttribute(Attribute.ATTACK));
        target.attacked(atksuccess, damage);

        return true;
    }
    int attacked(int success, int damage) {
        if (_alive == false) return 0;
        Attribute a = getAttributesInfluence();
        int spd = Judge.judge(a.getAttribute(Attribute.SPEED), _rand);
        if (spd > success) { _show_text = "Miss"; _show_time = SHOW_TIME; return 0;}
        int con = Judge.judge(a.getAttribute(Attribute.CONSTITUTION), _rand);
        damage /= (1 + con);
        changeAttribute(Attribute.HEALTH, 0-damage);
        _show_text = "-" + damage; _show_time = SHOW_TIME;
        if (_attribute.getAttribute(Attribute.HEALTH) <= 0) die();
        return damage;
    }
    private void move() {
        Pair<Integer, Integer> next_step = _map.selectNextStep(this);
        if (next_step != null) {
            _pos_x = next_step.getKey();
            _pos_y = next_step.getValue();
        }
    }
    private void die() { _alive = false; _filer.writeCreation(this); }


    void drawImage(GraphicsContext gc) {
        int x = _pos_x, y = _pos_y;
        int width = GroundMap.WIDTH / GroundMap.SIZE_X;
        int heigh = GroundMap.HEIGH / GroundMap.SIZE_Y;
        x *= width; y *= heigh;
        if (_attribute.getAttribute(Attribute.HEALTH) <= 0)
            _alive = false;
        if (_alive == true)
            gc.drawImage(_image_alive, x, y, width, heigh);
        else
            gc.drawImage(_image_dead, x, y, width, heigh);
        double health_rate = (double)_attribute.getAttribute(Attribute.HEALTH) / (double)_attribute.getAttribute(Attribute.CONSTITUTION);
        if (health_rate > 1) health_rate = 1;
        gc.setFill(new Color(1-health_rate, health_rate, 0, 1));
        gc.fillRect(x, y, (double) width * health_rate, heigh / 5);
        gc.setFont(Font.font("STXingkai", heigh / 2));
        gc.fillText("" + _attribute.getAttribute(Attribute.HEALTH), x, y + heigh * 2 / 3);

        int ifl_width = width / INFLUENCE_SHOW, ifl_heigh = heigh / INFLUENCE_SHOW;
        for (int i = 0; i < _influences.size() && i < INFLUENCE_SHOW; i++) {
            Influence influence = _influences.get(i);
            gc.drawImage(influence.getImage(), x + i * ifl_width, y + width - ifl_heigh * 3 / 2, ifl_width, ifl_heigh);
        }

        if (_show_time > 0) {
            _show_time--;
            gc.setFill(Color.CYAN);
            gc.setFont(Font.font("STXingkai", FontWeight.EXTRA_BOLD, heigh / 3));
            gc.fillText(_show_text, x + width / 2, y + heigh * 2 / 3);
        }
    }
    private void refresh() {
        Attribute na = this.getAttributesInfluence();
        _skill.refresh(na, _rand);
        for (int i = _influences.size() - 1; i >= 0; i--) {
            Influence in = (Influence)_influences.get(i);
            in.refresh(na, _rand);
            if (in._time <= 0)
                _influences.remove(i);
            else if (in.getType() == Attribute.HEALTH)
                in.implement(_attribute);
        }
    }

    boolean getGroup() { return _group; }
    boolean getAlive() { return _alive; }
    void setAlive(boolean alive) { _alive = alive; }
    int getPositionX() { return _pos_x; }
    int getPositionY() { return _pos_y; }
    int getType() { return _type; }
    int getKeyNumber() { return _key; }
    Random getRandom() { return _rand; }

    Attribute getAttributesInfluence() {
        Attribute ret = new Attribute(_attribute);
        for (int i = 0; i < _influences.size(); i++) {
            if (((Influence) _influences.get(i)).getType() != Attribute.HEALTH) {
                ((Influence) _influences.get(i)).implement(ret);
            }
        }
        return ret;
    }
    int getAttribute(int type) { return _attribute.getAttribute(type); }
    void setAttribute(int type, int number) { _attribute.setAttribute(type, number); }
    void changeAttribute(int type, int number) { _attribute.changeAttribute(type, number); }

    String getShowText() { return _show_text; }
    int getShowTime() { return _show_time; }
    void setShowText(String text) {_show_text = text; }
    void setShowTime(int time) { _show_time = time; }
    FileOperator getFileOperator() {return _filer; }
    void addInfluence(Influence influence) { _influences.add(influence); }
    Vector<Influence> getInfluence() { return _influences; }
}
