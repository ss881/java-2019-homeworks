package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.Random;

public class Influence {
    int _type;
    int _number;
    int _time;
    Image _image;
    private final static String[] BUFF = {
            "src\\main\\resources\\sorce\\influence\\HealthUp.png", "src\\main\\resources\\sorce\\influence\\AttackUp.png",
            "src\\main\\resources\\sorce\\influence\\SpeedUp.png", "src\\main\\resources\\sorce\\influence\\IntellectUp.png",
            "src\\main\\resources\\sorce\\influence\\ConstitutionUp.png",
    };
    private final static String[] DEBUFF = {
            "src\\main\\resources\\sorce\\influence\\HealthDown.png", "src\\main\\resources\\sorce\\influence\\AttackDown.png",
            "src\\main\\resources\\sorce\\influence\\SpeedDown.png", "src\\main\\resources\\sorce\\influence\\IntellectDown.png",
            "src\\main\\resources\\sorce\\influence\\ConstitutionDown.png",
    };

    public Influence(int type, int number, int time) {
        _type = type;
        _time = time;
        _number = number;
        try {
            if (number >= 0)
                _image = new Image(new FileInputStream(BUFF[_type]));
            else
                _image = new Image(new FileInputStream(DEBUFF[_type]));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    int getType() { return _type; }
    int getNumber() { return _number; }
    public void refresh(Attribute a, Random r) {
        _time--;
        if (_number < 0)
            _time -= Judge.judge(a.getAttribute(Attribute.CONSTITUTION), r);
    }
    public void implement(Attribute attribute) { attribute.changeAttribute(_type, _number); }
    Image getImage() { return _image; }
}
