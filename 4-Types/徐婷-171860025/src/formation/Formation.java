package formation;
import creatures.*;
import java.util.*;

public class Formation {
    private static <T extends Creature> void preprocess(List<T>list, int end){
        for(T c :list){
            c.setState(State.DEAD);
        }
        for(int i = 0;i<end;i++){
            list.get(i).setState(State.LIVE);
        }
    }
    public static <T extends Creature>void heYi(List<T>list, int x, int y){
        preprocess(list, 7);
        list.get(0).setPos(x, y);
        list.get(1).setPos(x+1, y-1);
        list.get(2).setPos(x+1, y+1);
        list.get(3).setPos(x+2, y+2);
        list.get(4).setPos(x+2, y-2);
        list.get(5).setPos(x+3, y-3);
        list.get(6).setPos(x+3, y+3);
    }
    public static <T extends Creature> void yanXing(List<T>list, int x, int y){
        preprocess(list, 5);
        list.get(0).setPos(x, y);
        for(int i = 1;i<5;i++){
            list.get(i).setPos(x+i, y+i);
        }
    }
    public static<T extends Creature> void hengE(List<T> list, int x, int y){
        preprocess(list, 6);
        list.get(0).setPos(x, y);
        list.get(1).setPos(x, y-2);
        list.get(2).setPos(x, y+2);
        list.get(3).setPos(x+1, y-1);
        list.get(4).setPos(x+1, y+1);
        list.get(5).setPos(x+1, y+3);
    }
    public static<T extends Creature> void yuLing(List<T>list, int x, int y){
        preprocess(list, 10);
        list.get(0).setPos(x, y);
        list.get(1).setPos(x+1, y+1);
        list.get(2).setPos(x+2, y-2);
        list.get(3).setPos(x+2, y);
        list.get(4).setPos(x+2, y+2);
        list.get(5).setPos(x+3, y-3);
        list.get(6).setPos(x+3, y-1);
        list.get(7).setPos(x+3, y+1);
        list.get(8).setPos(x+3, y+3);
        list.get(9).setPos(x+4, y);
    }
    public static <T extends Creature> void fangYuan(List<T>list, int x, int y){
        preprocess(list, 8);
        list.get(0).setPos(x, y);
        list.get(1).setPos(x+1, y-1);
        list.get(2).setPos(x+1, y+1);
        list.get(3).setPos(x+2, y-2);
        list.get(4).setPos(x+2, y+2);
        list.get(5).setPos(x+3, y-1);
        list.get(6).setPos(x+3, y+1);
        list.get(7).setPos(x+4, y);
    }
    public static <T extends Creature> void yanYue(List<T>list, int x, int y){
        preprocess(list, 19);
        list.get(0).setPos(x, y);
        list.get(1).setPos(x, y+1);
        list.get(2).setPos(x, y-1);
        for(int i = -1;i<=1;i++){
            list.get(4+i).setPos(x+2, y+i);
        }
        list.get(6).setPos(x+3,y+2);
        list.get(7).setPos(x+3, y-2);
        list.get(8).setPos(x+4, y+3);
        list.get(9).setPos(x+4, y-3);
        list.get(10).setPos(x+4, y+1);
        list.get(11).setPos(x+4, y);
        list.get(12).setPos(x+4, y-1);
        list.get(13).setPos(x+5, y+2);
        list.get(14).setPos(x+5, y-2);
        list.get(15).setPos(x+6, y-3);
        list.get(16).setPos(x+6, y+3);
        list.get(17).setPos(x+7, y-4);
        list.get(18).setPos(x+7, y+4);

    }
    public static <T extends Creature> void fengShi(List<T>list, int x, int y){
        preprocess(list, 12);
        list.get(0).setPos(x, y);
        for(int i = 0;i<3;i++){
            list.get(i*3+1).setPos(x+i+1, y);
            list.get(i*3+2).setPos(x+i+1, y-i-1);
            list.get(i*3+3).setPos(x+i+1, y+i+1);
        }
        list.get(10).setPos(x+4,y);
        list.get(11).setPos(x+5, y);

    }
    public static <T extends Creature> void changShe(List<T>list, int x, int y){
        preprocess(list, 7);
        for(int i = 0;i<7;i++){
            list.get(i).setPos(x, y+i);
        }
    }
}

