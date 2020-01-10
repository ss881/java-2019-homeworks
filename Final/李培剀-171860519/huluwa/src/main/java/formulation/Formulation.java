package formulation;

import java.lang.reflect.Method;
import java.util.*;

import creature.*;
import ground.*;
import javafx.scene.control.TextArea;

public class Formulation {

    private Ground ground;
    private Generator generator;

    public Formulation(Ground ground) {
        this.ground = ground;
        this.generator = new Generator();
    }

    @FormSeqAnnotation(0)
    public void transformToHeyi(Side side, TextArea ta) {
        System.out.println("Transforming to Heyi......");
        ta.appendText("Transforming to Heyi......\n");
        if(side == Side.Good) { // 葫芦娃阵营
            int red_row = 7;
            int red_col = 10;
            Color tmpColor = Color.red;
            for(int i = 0; i < 4; i++) {
                switch (i) {
                    case 1:
                        tmpColor = Color.orange;
                        break;
                    case 2:
                        tmpColor = Color.yellow;
                        break;
                    case 3:
                        tmpColor = Color.green;
                        break;
                    default:
                }
                generator.genHuluwa(ground, red_row - i, red_col - i, tmpColor);
            }
            for(int i = 0; i < 3; i++) {
                switch (i + 4) {
                    case 4:
                        tmpColor = Color.cyan;
                        break;
                    case 5:
                        tmpColor = Color.blue;
                        break;
                    case 6:
                        tmpColor = Color.purple;
                        break;
                    default:
                }
                generator.genHuluwa(ground, red_row + (i + 1), red_col - (i + 1), tmpColor);
            }
        }
        else { // 妖怪阵营
            int scorp_row = 7;
            int scorp_col = 12;
            generator.genScorpion(ground, scorp_row, scorp_col);
            generator.genCreep(ground, scorp_row, scorp_col + 1);
            for(int i = 1; i <= 5; i++) {
                generator.genCreep(ground, scorp_row - i, scorp_col + i);
                generator.genCreep(ground, scorp_row + i, scorp_col + i);
            }
            for(int i = 1; i <= 4; i++) {
                generator.genCreep(ground, scorp_row - i, scorp_col + i + 1);
                generator.genCreep(ground, scorp_row + i, scorp_col + i + 1);
            }
        }
    }

    @FormSeqAnnotation(1)
    public void transformToYanxing(Side side, TextArea ta) {
        System.out.println("Transforming to Yanxing......");
        ta.appendText("Transforming to Yanxing......\n");
        if(side == Side.Good) {
            int red_row = 4;
            int red_col = 10;
            Color tmpColor = Color.red;
            for(int i = 0; i < 7; i++) {
                switch (i) {
                    case 1:
                        tmpColor = Color.orange;
                        break;
                    case 2:
                        tmpColor = Color.yellow;
                        break;
                    case 3:
                        tmpColor = Color.green;
                        break;
                    case 4:
                        tmpColor = Color.cyan;
                        break;
                    case 5:
                        tmpColor = Color.blue;
                        break;
                    case 6:
                        tmpColor = Color.purple;
                        break;
                    default:
                }
                generator.genHuluwa(ground, red_row + i, red_col - i, tmpColor);
            }
        }
        else {
            int scorp_row = 3;
            int scorp_col = 12;
            generator.genScorpion(ground, scorp_row, scorp_col);
            generator.genCreep(ground, scorp_row, scorp_col + 1);
            for(int i = 1; i <= 9; i++) {
                generator.genCreep(ground, scorp_row + i, scorp_col + i);
                generator.genCreep(ground, scorp_row + i, scorp_col + i + 1);
            }
        }
    }

    @FormSeqAnnotation(2)
    public void transformToChonge(Side side, TextArea ta) {
        System.out.println("Transforming to Chonge......");
        ta.appendText("Transforming to Chonge......\n");
        if(side == Side.Good) {
            int red_row = 4;
            int red_col = 10;
            Color tmpColor = Color.red;
            for(int i = 0; i < 4; i++) {
                switch (i) {
                    case 1:
                        tmpColor = Color.orange;
                        break;
                    case 2:
                        tmpColor = Color.yellow;
                        break;
                    case 3:
                        tmpColor = Color.green;
                        break;
                    default:
                }
                generator.genHuluwa(ground, red_row + i * 2, red_col, tmpColor);
            }
            for(int i = 0; i < 3; i++) {
                switch (i + 4) {
                    case 4:
                        tmpColor = Color.cyan;
                        break;
                    case 5:
                        tmpColor = Color.blue;
                        break;
                    case 6:
                        tmpColor = Color.purple;
                        break;
                    default:
                }
                generator.genHuluwa(ground, red_row + i * 2 + 1, red_col - 1, tmpColor);
            }
        }
        else {
            int scorp_row = 3;
            int scorp_col = 12;
            generator.genScorpion(ground, scorp_row, scorp_col);
            generator.genCreep(ground, scorp_row + 1, scorp_col + 1);
            generator.genCreep(ground, scorp_row, scorp_col + 2);
            generator.genCreep(ground, scorp_row + 1, scorp_col + 3);
            for(int i = 1; i <= 4; i++) {
                generator.genCreep(ground, scorp_row + i * 2, scorp_col);
                generator.genCreep(ground, scorp_row + i * 2 + 1, scorp_col + 1);
                generator.genCreep(ground, scorp_row + i * 2, scorp_col + 2);
                generator.genCreep(ground, scorp_row + i * 2 + 1, scorp_col +3);
            }
        }
    }

    @FormSeqAnnotation(3)
    public void transformToChangshe(Side side, TextArea ta) {
        System.out.println("Transforming to Changshe......");
        ta.appendText("Transforming to Changshe......\n");
        if(side == Side.Good) {
            int red_row = 4;
            int red_col = 10;
            Color tmpColor = Color.red;
            for(int i = 0; i < 7; i++) {
                switch (i) {
                    case 1:
                        tmpColor = Color.orange;
                        break;
                    case 2:
                        tmpColor = Color.yellow;
                        break;
                    case 3:
                        tmpColor = Color.green;
                        break;
                    case 4:
                        tmpColor = Color.cyan;
                        break;
                    case 5:
                        tmpColor = Color.blue;
                        break;
                    case 6:
                        tmpColor = Color.purple;
                        break;
                    default:
                }
                generator.genHuluwa(ground, red_row + i, red_col, tmpColor);
            }
        }
        else {
            int scorp_row = 3;
            int scorp_col = 12;
            generator.genScorpion(ground, scorp_row, scorp_col);
            generator.genCreep(ground, scorp_row, scorp_col + 1);
            for(int i = 1; i <= 9; i++) {
                generator.genCreep(ground, scorp_row + i, scorp_col);
                generator.genCreep(ground, scorp_row + i, scorp_col + 1);
            }
        }
    }

    @FormSeqAnnotation(4)
    public void transformToYulin(Side side, TextArea ta) {
        System.out.println("Transforming to Yulin......");
        ta.appendText("Transforming to Yulin......\n");
        if(side == Side.Good) {
            generator.genHuluwa(ground, 6, 8, Color.red);
            generator.genHuluwa(ground, 7, 7, Color.orange);
            generator.genHuluwa(ground, 8, 6, Color.yellow);
            generator.genHuluwa(ground, 8, 8, Color.green);
            generator.genHuluwa(ground, 8, 10, Color.cyan);
            generator.genHuluwa(ground, 9, 7, Color.blue);
            generator.genHuluwa(ground, 9, 9, Color.purple);
        }
        else {
            int scorp_row = 6;
            int scorp_col = 16;
            generator.genScorpion(ground, scorp_row, scorp_col);
            for(int i = 0; i < 3; i++) {
                generator.genCreep(ground, scorp_row - i, scorp_col + 1 - i);
            }
            for(int i = 0; i < 7; i++) {
                generator.genCreep(ground, scorp_row + 1, scorp_col + 2 - i);
            }
            generator.genCreep(ground, scorp_row + 2, scorp_col - 1);
            for(int i = 0; i < 3; i++) {
                generator.genCreep(ground, scorp_row + 3, scorp_col - i);
            }
            for(int i = 0; i < 5; i++) {
                generator.genCreep(ground, scorp_row + 4, scorp_col + 1 - i);
            }
        }
    }

    @FormSeqAnnotation(5)
    public void transformToFangyuan(Side side, TextArea ta) {
        System.out.println("Transforming to Fangyuan......");
        ta.appendText("Transforming to Fangyuan......\n");
        if(side == Side.Good) {
            generator.genHuluwa(ground, 7, 10, Color.red);
            generator.genHuluwa(ground, 6, 9, Color.orange);
            generator.genHuluwa(ground, 5, 8, Color.yellow);
            generator.genHuluwa(ground, 6, 7, Color.green);
            generator.genHuluwa(ground, 8, 9, Color.cyan);
            generator.genHuluwa(ground, 9, 8, Color.blue);
            generator.genHuluwa(ground, 8, 7, Color.purple);
        }
        else {
            int scorp_row = 7;
            int scorp_col = 12;
            generator.genScorpion(ground, scorp_row, scorp_col);
            generator.genCreep(ground, scorp_row - 5, scorp_col + 5);
            generator.genCreep(ground, scorp_row, scorp_col + 1);
            generator.genCreep(ground, scorp_row + 5, scorp_col + 5);
            for(int i = 1; i <= 4; i++) {
                generator.genCreep(ground, scorp_row - i, scorp_col + i);
                generator.genCreep(ground, scorp_row - 5 + i, scorp_col + 5 + i);
                generator.genCreep(ground, scorp_row + i, scorp_col + 10 - i);
                generator.genCreep(ground, scorp_row + 5 - i, scorp_col + 5 - i);
            }
        }
    }

    @FormSeqAnnotation(6)
    public void transformToYanyue(Side side, TextArea ta) {
        System.out.println("Transforming to Yanyue......");
        ta.appendText("Transforming to Yanyue......\n");
        if(side == Side.Good) {
            generator.genHuluwa(ground, 7, 9, Color.red);
            generator.genHuluwa(ground, 6, 9, Color.orange);
            generator.genHuluwa(ground, 5, 10, Color.yellow);
            generator.genHuluwa(ground, 8, 9, Color.green);
            generator.genHuluwa(ground, 9, 10, Color.cyan);
            generator.genHuluwa(ground, 6, 8, Color.blue);
            generator.genHuluwa(ground, 8, 8, Color.purple);
        }
        else {
            int scorp_row = 7;
            int scorp_col = 14;
            generator.genScorpion(ground, scorp_row, scorp_col);
            generator.genCreep(ground, scorp_row, scorp_col + 1);
            generator.genCreep(ground, scorp_row, scorp_col + 2);
            generator.genCreep(ground, scorp_row, scorp_col + 3);
            generator.genCreep(ground, scorp_row - 1, scorp_col + 3);
            generator.genCreep(ground, scorp_row + 1, scorp_col + 3);
            for(int i = 1; i <= 4; i++) {
                generator.genCreep(ground, scorp_row - i, scorp_col + 2 - i);
                generator.genCreep(ground, scorp_row + i, scorp_col + 2 - i);
            }
            for(int i = 1; i <= 3; i++) {
                generator.genCreep(ground, scorp_row - i, scorp_col + 3 - i);
                generator.genCreep(ground, scorp_row + i, scorp_col + 3 - i);
            }
        }
    }

    @FormSeqAnnotation(7)
    public void transformToFengshi(Side side, TextArea ta) {
        System.out.println("Transforming to Fengshi......");
        ta.appendText("Transforming to Fengshi......\n");
        if(side == Side.Good) {
            generator.genHuluwa(ground, 7, 10, Color.red);
            generator.genHuluwa(ground, 6, 9, Color.orange);
            generator.genHuluwa(ground, 5, 8, Color.yellow);
            generator.genHuluwa(ground, 7, 9, Color.green);
            generator.genHuluwa(ground, 7, 8, Color.cyan);
            generator.genHuluwa(ground, 8, 9, Color.blue);
            generator.genHuluwa(ground, 9, 8, Color.purple);
        }
        else {
            int scorp_row = 7;
            int scorp_col = 12;
            generator.genScorpion(ground, scorp_row, scorp_col);
            generator.genCreep(ground, scorp_row, scorp_col + 1);
            for(int i = 1; i <= 6; i++) {
                generator.genCreep(ground, scorp_row - i, scorp_col + i);
                generator.genCreep(ground, scorp_row, scorp_col + 1 + i);
                generator.genCreep(ground, scorp_row + i, scorp_col + i);
            }
        }
    }

    @FormSeqAnnotation(8)
    public Method[] getSortedDeclaredMethods() {
        Method[] methods = Formulation.class.getDeclaredMethods();
        List<Method> methodList = new ArrayList<>(Arrays.asList(methods));
        methodList.sort(Comparator.comparingInt(this::getSequence));
        methodList.toArray(methods);
        return methods;
    }

    int getSequence(Method method) {
        if(method.isAnnotationPresent(FormSeqAnnotation.class)) {
            return method.getAnnotation(FormSeqAnnotation.class).value();
        }
        return 8;
    }
}