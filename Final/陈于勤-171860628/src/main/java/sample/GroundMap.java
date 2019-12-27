package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class GroundMap{
    public final static int WIDTH = 1200, HEIGH = 900;
    public final static int SIZE_X = 20, SIZE_Y = 16;
    public final static int INTERVAL = 100, MAX_CREATION_SIZE = SIZE_X + SIZE_Y;
    public final static String BACKGROUND = "src\\main\\resources\\sorce\\Background.png";
    public final static int INITIAL_NUM = 7;
    public final static int MAX_SPECIAL = 10;
    int _special = 0;
    boolean _gamestart;
    boolean _gamereview;
    boolean _suspend = false;
    int _number;
    Random _rand;
    Vector<Creation> _creations;
    Canvas _canvas;
    FileOperator _file;
    Image _background;
    MapCheck _check;
    MapPrint _print;

    public GroundMap(Canvas canvas, boolean gamestart, boolean review, String filename) {
        _canvas = canvas;
        _gamestart = gamestart;
        if (_gamestart) _gamereview = false;
        else _gamereview = review;
        if (filename != null) _file = new FileOperator(filename);
        else _file = new FileOperator();

        _number = 0;
        _rand = new Random(System.nanoTime());
        _creations = new Vector<Creation>();
        try { _background = new Image(new FileInputStream(BACKGROUND)); }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        if (_gamestart)
            init();
        if (_canvas != null) {
            _print = new MapPrint(this, _creations, _canvas, _file, _background);
            _print.drawBackGround();
            _check = new MapCheck(this, _creations, _print, _file);
        }
    }
    synchronized void init() {
        _creations.add(new Creation(_number++, Creation.GRANDPA, 1, SIZE_Y / 2, this, _file));
        StrategyFormation formation = StrategyFormation.getFormation(_rand.nextInt(StrategyFormation.MAX_NUM));
        Vector<Pair<Integer, Integer>> strategy = formation.getImplement(SIZE_X, SIZE_Y, 7, true);
        Pair<Integer, Integer> pos = strategy.get(0);
        _creations.add(new Creation(_number++, Creation.DOLLONE, pos.getKey(), pos.getValue(), this, _file));
        pos = strategy.get(1);
        _creations.add(new Creation(_number++, Creation.DOLLTWO, pos.getKey(), pos.getValue(), this, _file));
        pos = strategy.get(2);
        _creations.add(new Creation(_number++, Creation.DOLLTHREE, pos.getKey(), pos.getValue(), this, _file));
        pos = strategy.get(3);
        _creations.add(new Creation(_number++, Creation.DOLLFOUR, pos.getKey(), pos.getValue(), this, _file));
        pos = strategy.get(4);
        _creations.add(new Creation(_number++, Creation.DOLLFIVE, pos.getKey(), pos.getValue(), this, _file));
        pos = strategy.get(5);
        _creations.add(new Creation(_number++, Creation.DOLLSIX, pos.getKey(), pos.getValue(), this, _file));
        pos = strategy.get(6);
        _creations.add(new Creation(_number++, Creation.DOLLSEVEN, pos.getKey(), pos.getValue(), this, _file));
        _creations.add(new Creation(_number++, Creation.SCORP, SIZE_X - 2, SIZE_Y * 2 / 5 - 1, this, _file));
        _creations.add(new Creation(_number++, Creation.SNAKE, SIZE_X - 2, SIZE_Y * 3 / 5, this, _file));
        formation = StrategyFormation.getFormation(_rand.nextInt(StrategyFormation.MAX_NUM));
        strategy = formation.getImplement(SIZE_X, SIZE_Y, INITIAL_NUM, false);
        for (int i = 0; i < INITIAL_NUM; i++) {
            pos = strategy.get(i);
            _creations.add(new Creation(_number++, Creation.SOLDIER, pos.getKey(), pos.getValue(), this, _file));
        }
    }

    public Vector<Creation> selectCreations(Creation order, int scope, boolean group, boolean alive) {
        Vector<Creation> ret = new Vector<Creation>();
        if (scope < 1)
            return ret;
        int order_x = order.getPositionX(), order_y = order.getPositionY();
        for (int i = 0; i < _creations.size(); i++) {
            Creation target = (Creation)_creations.get(i);
            if (target.getKeyNumber() == order.getKeyNumber()) continue;
            if (target.getGroup() != group) continue;
            if (target.getAlive() != alive) continue;
            int target_x = target.getPositionX(), target_y = target.getPositionY();
            int distance = 0;
            distance += (order_x > target_x) ? order_x - target_x : target_x - order_x;
            distance += (order_y > target_y) ? order_y - target_y : target_y - order_y;
            if (distance <= scope)
                ret.add(target);
        }
        return ret;
    }
    public Creation selectCreation(int pos_x, int pos_y, boolean alive) {
        Creation ret = null;
        for (int i = 0; i < _creations.size(); i++) {
            Creation target = _creations.get(i);
            if (target.getPositionX() == pos_x && target.getPositionY() == pos_y &&
                target.getAlive() == alive) {
                ret = target;
                break;
            }
        }
        return  ret;
    }
    public Pair<Integer, Integer> selectNextStep(Creation order) {
        boolean[][] temp_map = new boolean[SIZE_X][SIZE_Y];
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++)
                temp_map[i][j] = false;
        }
        for (int i = 0; i < _creations.size(); i++) {
            Creation c = (Creation)_creations.get(i);
            temp_map[c.getPositionX()][c.getPositionY()] = true;
        }
        List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> queue =
                new Vector<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>();
        int order_x = order.getPositionX(), order_y = order.getPositionY();
        //初始化队列
        if (order_x + 1 < SIZE_X && temp_map[order_x + 1][order_y] == false) {
            queue.add(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>
                    (new Pair<Integer, Integer>(order_x + 1, order_y), new Pair<Integer, Integer>(order_x + 1, order_y)));
            temp_map[order_x + 1][order_y] = true;
        }
        if (order_x - 1 >= 0 && temp_map[order_x - 1][order_y] == false) {
            queue.add(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>
                    (new Pair<Integer, Integer>(order_x - 1, order_y), new Pair<Integer, Integer>(order_x - 1, order_y)));
            temp_map[order_x - 1][order_y] = true;
        }
        if (order_y + 1 < SIZE_Y && temp_map[order_x][order_y + 1] == false) {
            queue.add(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>
                    (new Pair<Integer, Integer>(order_x, order_y + 1), new Pair<Integer, Integer>(order_x, order_y + 1)));
            temp_map[order_x][order_y + 1] = true;
        }
        if (order_y - 1 >= 0 && temp_map[order_x][order_y - 1] == false) {
            queue.add(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>
                    (new Pair<Integer, Integer>(order_x, order_y - 1), new Pair<Integer, Integer>(order_x, order_y - 1)));
            temp_map[order_x][order_y - 1] = true;
        }
        Pair<Integer, Integer> ret = null;
        while (queue.isEmpty() == false) {
            Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> this_pair = queue.get(0);
            queue.remove(0);
            Pair<Integer, Integer> this_pos = this_pair.getKey();
            if (this_pos.getKey() + 1 < SIZE_X &&temp_map[this_pos.getKey() + 1][this_pos.getValue()] == false) {
                queue.add(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>
                        (new Pair<Integer, Integer>(this_pos.getKey() + 1, this_pos.getValue()), this_pair.getValue()));
                temp_map[this_pos.getKey() + 1][this_pos.getValue()] = true;
            }
            else {
                Creation c = selectCreation(this_pos.getKey() + 1, this_pos.getValue(), true);
                if (c != null && c.getGroup() != order.getGroup()) {
                    ret = this_pair.getValue();
                    break;
                }
            }
            if (this_pos.getKey() - 1 >= 0 && temp_map[this_pos.getKey() - 1][this_pos.getValue()] == false) {
                queue.add(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>
                        (new Pair<Integer, Integer>(this_pos.getKey() - 1, this_pos.getValue()), this_pair.getValue()));
                temp_map[this_pos.getKey() - 1][this_pos.getValue()] = true;
            }
            else {
                Creation c = selectCreation(this_pos.getKey() - 1, this_pos.getValue(), true);
                if (c != null && c.getGroup() != order.getGroup()) {
                    ret = this_pair.getValue();
                    break;
                }
            }
            if (this_pos.getValue() + 1 < SIZE_Y && temp_map[this_pos.getKey()][this_pos.getValue() + 1] == false) {
                queue.add(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>
                        (new Pair<Integer, Integer>(this_pos.getKey(), this_pos.getValue() + 1), this_pair.getValue()));
                temp_map[this_pos.getKey()][this_pos.getValue() + 1] = true;
            }
            else {
                Creation c = selectCreation(this_pos.getKey(), this_pos.getValue() + 1, true);
                if (c != null && c.getGroup() != order.getGroup()) {
                    ret = this_pair.getValue();
                    break;
                }
            }
            if (this_pos.getValue() - 1 >= 0 &&temp_map[this_pos.getKey()][this_pos.getValue() - 1] == false) {
                queue.add(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>
                        (new Pair<Integer, Integer>(this_pos.getKey(), this_pos.getValue() - 1), this_pair.getValue()));
                temp_map[this_pos.getKey()][this_pos.getValue() - 1] = true;
            }
            else {
                Creation c = selectCreation(this_pos.getKey(), this_pos.getValue() - 1, true);
                if (c != null && c.getGroup() != order.getGroup()) {
                    ret = this_pair.getValue();
                    break;
                }
            }
        }
        if (ret == null) _special++;
        return ret;
    }

    public boolean isGameStart() { return _gamestart; }
    public void setGameStart(boolean start) { _gamestart = start; }
    public boolean isGameReview() { return _gamereview; }
    public void setGameReview(boolean review) { _gamereview = review; }
    public boolean isGameSuspend() { return _suspend; }
    public void switchSuspend() { _suspend = !_suspend; }

    int getSpecial() { return _special; }
    void setSpecial(int special) { _special = special;}
    int getCreationSize() { return _creations.size(); }
    Pair<Integer, Integer> getRandomPosition() {
        while (true) {
            int pos_x = _rand.nextInt(SIZE_X);
            int pos_y = _rand.nextInt(SIZE_Y);
            boolean complete = true;
            for (int i = 0; i < _creations.size(); i++) {
                Creation target = (Creation)_creations.get(i);
                if (target.getAlive() == true &&
                    target.getPositionX() == pos_x &&
                    target.getPositionY() == pos_y){
                    complete = false;
                    break;
                }
            }
            if (complete == true)
                return new Pair<Integer, Integer>(pos_x, pos_y);
        }
    }
    int getNextNumber() {return _number++; }
    void addCreaiont(Creation c) {
        for (int i = 0; i < _creations.size(); i++) {
            Creation target = (Creation)_creations.get(i);
            if (target.getKeyNumber() == c.getKeyNumber()) {
                return;
            }
        }
        _creations.add(c);
    }
}

class MapPrint extends Thread{
    private final GroundMap _map;
    private Vector<Creation> _creations;
    private Canvas _canvas;
    private FileOperator _file;
    private Image _background;
    MapPrint(GroundMap map, Vector<Creation> creations, Canvas canvas, FileOperator file, Image background) {
        setDaemon(true);
        _map = map;
        _creations = creations;
        _canvas = canvas;
        _file = file;
        _background = background;
        start();
    }

    public void drawBackGround(){
        GraphicsContext gc = _canvas.getGraphicsContext2D();
        gc.drawImage(_background, 0, 0, GroundMap.WIDTH, GroundMap.HEIGH);
        int width = GroundMap.WIDTH / GroundMap.SIZE_X, heigh = GroundMap.HEIGH / GroundMap.SIZE_Y;
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        for (int i = 0; i <= GroundMap.SIZE_X; i++) {
            gc.strokeLine(i * width, 0, i * width, GroundMap.HEIGH);
        }
        for (int i = 0; i <= GroundMap.SIZE_Y; i++) {
            gc.strokeLine(0, i * heigh, GroundMap.WIDTH, i * heigh);
        }
    }
    public synchronized void draw() {
        drawBackGround();
        GraphicsContext gc = _canvas.getGraphicsContext2D();
        for (int i = _creations.size() - 1; i >= 0; i--) {
            Creation c = _creations.get(i);
            if (c.getAlive() == false) {
                _creations.remove(i);
                _creations.add(c);
            }
            c.drawImage(gc);
        }
    }
    public boolean reviewGame() {
        Creation c = _file.readCreation(_map);
        if (c == null) return false;
        for (int i = 0; i < _creations.size(); i++) {
            Creation target = _creations.get(i);
            if (target.getKeyNumber() == c.getKeyNumber()) {
                _creations.remove(i);
                break;
            }
        }
        _creations.add(c);
        draw();
        return true;
    }
    public void drawEnd(boolean result, boolean winner) {
        int size = 150;
        GraphicsContext gc = _canvas.getGraphicsContext2D();
        gc.setFont(new Font("STXingkai", size));
        gc.setFill(Color.RED);
        if (result == true) {
            if (winner == true) {
                gc.fillText("葫芦娃胜", (GroundMap.WIDTH - 4 * size) / 2, GroundMap.HEIGH / 2);
            }
            else {
                gc.fillText("妖精胜", (GroundMap.WIDTH - 3 * size) / 2, GroundMap.HEIGH / 2);
            }
        }
        else {
            gc.fillText("平局", (GroundMap.WIDTH - 2 * size) / 2, GroundMap.HEIGH / 2);
        }
    }

    public void run() {
        if (_map.isGameReview())
            _creations.clear();
        while (_map.isGameStart() || _map.isGameReview()) {
            if (_map.isGameSuspend())
                yield();
            else if (_map.isGameStart()) {
                synchronized (_map) {
                    if (_map.isGameStart()) {
                        drawBackGround();
                        draw();
                    }
                }
                try { sleep(GroundMap.INTERVAL); }
                catch (Exception e) { ; }
            }
            else if (_map.isGameReview()) {
                boolean rest = reviewGame();
                if (rest == false) {
                    _map.setGameReview(false);
                    drawEnd(_file.getGameResult(), _file.getWinner());
                }
                try { sleep(GroundMap.INTERVAL / 2); }
                catch (Exception e) { ; }
            }
        }
    }
}

class MapCheck extends Thread{
    private final GroundMap _map;
    private Vector<Creation> _creations;
    private MapPrint _print;
    private FileOperator _file;

    MapCheck(GroundMap map, Vector<Creation> creations, MapPrint print, FileOperator file) {
        setDaemon(true);
        _map = map;
        _creations = creations;
        _print = print;
        _file = file;
        start();
    }
    public boolean normalCheck() {
        boolean t = false, f = false;
        for (int i = 0; i < _creations.size(); i++) {
            Creation c = _creations.get(i);
            if (c.getAlive() == true) {
                if (c.getGroup() == true)
                    t = true;
                if (c.getGroup() == false)
                    f = true;
            }
        }
        return t && f;
    }
    public boolean specialCheck() {
        for (int i = 0; i < _creations.size(); i++) {
            Creation c = _creations.get(i);
            if (c.getAlive()) {
                 if (_map.selectNextStep(c) != null || _map.selectCreations(c, 1, !c.getGroup(), true).size() != 0)
                    return true;
            }
        }
        return false;
    }
    public boolean checkWinner() {
        for (int i = 0; i < _creations.size(); i++) {
            Creation c = _creations.get(i);
            if (c.getAlive())
                return c.getGroup();
        }
        return true;
    }
    public void run() {
        while (_map.isGameStart()) {
            boolean end = true;
            synchronized (_map) {
                end = normalCheck();
                if (end == true && _map.getSpecial() >= GroundMap.MAX_SPECIAL) {
                    _map.setSpecial(0);
                    end = specialCheck();
                }
                if (end == false) {
                    _map.setGameStart(false);
                    _print.drawBackGround();
                    _print.draw();
                    boolean result = !normalCheck(), winner = checkWinner();
                    _file.writeResult(result, winner);
                    _print.drawEnd(result, winner);
                }
            }
            try { sleep(GroundMap.INTERVAL * 2); }
            catch (Exception e) { ; }
        }
    }
}
