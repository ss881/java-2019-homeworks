package battle;
import creature.*;

import java.lang.reflect.Array;
import java.util.*;

public class Formation {
    /**可否直接生成一个生成队员的函数*/

    private static void heYiGood(List<Good>list){
        preprocess(list, 7);
        int x = 4, y = 4;
        list.get(0).setPos(x, y);
        list.get(1).setPos(x+1, y-1);
        list.get(2).setPos(x+1, y+1);
        list.get(3).setPos(x+2, y+2);
        list.get(4).setPos(x+2, y-2);
        list.get(5).setPos(x+3, y-3);
        list.get(6).setPos(x+3, y+3);

    }

    private static void heYiBad(List<Bad>list){
        preprocess(list, 7);
        list.get(0).setPos(10, 4);
        list.get(1).setPos(11, 3);
        list.get(2).setPos(11, 5);
        list.get(3).setPos(12, 3);
        list.get(4).setPos(12, 5);
        list.get(5).setPos(13, 3);
        list.get(6).setPos(12, 5);
    }
    private static void yanXingGood(List<Good>list){
        preprocess(list, 7);
        for(int i = 0;i<7;i++){
            list.get(i).setPos(i, i+1);
        }
    }

    private static void yanXingBad(List<Bad>list){
        preprocess(list, 5);
        list.get(0).setPos(11, 4);
        list.get(1).setPos(12, 3);
        list.get(2).setPos(13, 2);
        list.get(3).setPos(10, 5);
        list.get(4).setPos(9, 6);
    }

    private static void hengEGood(List<Good>list){
        preprocess(list, 7);
        list.get(0).setPos(2, 2);
        list.get(1).setPos(2, 4);
        list.get(2).setPos(2, 6);
        list.get(3).setPos(1, 1);
        list.get(4).setPos(1, 3);
        list.get(5).setPos(1, 5);
        list.get(6).setPos(1, 6);
    }

    private static void hengEBad(List<Bad>list){
        preprocess(list, 6);
        list.get(0).setPos(12, 2);
        list.get(1).setPos(12, 4);
        list.get(2).setPos(12, 6);
        list.get(3).setPos(13, 1);
        list.get(4).setPos(13, 3);
        list.get(5).setPos(13, 5);
    }

    private static void changSheGood(List<Good>list){
        preprocess(list, 7);
        for(int i = 0;i< 7;i++){
            list.get(i).setPos(1, i+1);
        }
    }

    private static void changSheBad(List<Bad>list){
        preprocess(list, 7);
        for(int i = 0;i<7;i++){
            list.get(i).setPos(13, i+1);
        }
    }

    private static void yuLingBad(List<Bad>list){
        preprocess(list, 10);
        list.get(0).setPos(9,4);
        list.get(1).setPos(10, 3);
        list.get(2).setPos(11, 2);
        list.get(3).setPos(11, 4);
        list.get(4).setPos(11, 6);
        list.get(5).setPos(12, 1);
        list.get(6).setPos(12, 3);
        list.get(7).setPos(12, 5);
        list.get(8).setPos(12, 7);
        list.get(9).setPos(13, 4);
    }

    private static void fangYuanBad(List<Bad>list){
        preprocess(list, 8);
        list.get(0).setPos(9, 4);
        list.get(1).setPos(10, 3);
        list.get(2).setPos(10, 5);
        list.get(3).setPos(11, 2);
        list.get(4).setPos(11, 6);
        list.get(5).setPos(12, 3);
        list.get(6).setPos(12, 5);
        list.get(7).setPos(13, 4);
    }

    private static void yanYueBad(List<Bad>list){
        preprocess(list, 19);
        list.get(0).setPos(7, 4);
        list.get(1).setPos(7, 3);
        list.get(2).setPos(7, 5);
        list.get(3).setPos(9, 3);
        list.get(4).setPos(9, 4);
        list.get(5).setPos(9, 5);
        list.get(6).setPos(11, 3);
        list.get(7).setPos(11, 4);
        list.get(8).setPos(11, 5);
        list.get(9).setPos(10, 2);
        list.get(10).setPos(10, 6);
        list.get(11).setPos(11, 1);
        list.get(12).setPos(11, 7);
        list.get(13).setPos(12, 2);
        list.get(14).setPos(12, 6);
        list.get(15).setPos(13, 1);
        list.get(16).setPos(13, 7);
        list.get(17).setPos(14, 0);
        list.get(18).setPos(15, 8);
    }

    private static void fengShiGood(List<Good>list){
        preprocess(list, 7);
        list.get(0).setPos(4, 4);
        list.get(1).setPos(5, 3);
        list.get(2).setPos(5, 5);
        list.get(3).setPos(6, 2);
        list.get(4).setPos(6, 6);
        list.get(5).setPos(4, 6);
        list.get(6).setPos(4, 7);
    }

    private static void fengShiBad(List<Bad>list){
        preprocess(list, 12);
        list.get(0).setPos(9, 4);
        list.get(1).setPos(10, 3);
        list.get(2).setPos(10, 4);
        list.get(3).setPos(10, 5);
        list.get(4).setPos(11, 2);
        list.get(5).setPos(11, 4);
        list.get(6).setPos(11, 6);
        list.get(7).setPos(12, 1);
        list.get(8).setPos(12, 4);
        list.get(9).setPos(12, 7);
        list.get(10).setPos(13, 4);
        list.get(11).setPos(13, 4);
    }



    private static <T extends Creature> void preprocess(List<T>list, int end){
        for(int i = 0;i<end;i++){
            list.get(i).setState(State.LIVE);
            list.get(i).setHealth(100);
        }
        for(int i = end;i<list.size();i++) {
            list.get(i).setState(State.DEAD);
            list.get(i).setPos(-1, -1);
            list.get(i).setHealth(-1);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Creature>void heYi(List<T>list){
        if(list.get(0) instanceof Good){
            heYiGood((List<Good>)list);
        }
        else{
            heYiBad((List<Bad>)list);
        }
    }
    @SuppressWarnings("unchecked")
    public static <T extends Creature> void yanXing(List<T>list){
        if(list.get(0) instanceof Good)
            yanXingGood((List<Good>)list);
        else yanXingBad((List<Bad>)list);
    }

    @SuppressWarnings("unchecked")
    public static<T extends Creature> void hengE(List<T> list){
        if(list.get(0) instanceof Good)
            hengEGood((List<Good>)list);
        else hengEBad((List<Bad>)list);
    }

    @SuppressWarnings("unchecked")
    public static<T extends Creature> void yuLing(List<T>list){
        if (list.get(0) instanceof Bad)
            yuLingBad((List<Bad>)list);
        else System.err.println("wrong type in yuLing");
    }
    @SuppressWarnings("unchecked")
    public static <T extends Creature> void fangYuan(List<T>list){
        if(list.get(0) instanceof Bad)
            fangYuanBad((List<Bad>)list);
        else System.err.println("wrong type in fangYuan");
    }
    @SuppressWarnings("unchecked")
    public static <T extends Creature> void yanYue(List<T>list){
        if(list.get(0) instanceof Bad)
            yanYueBad((List<Bad>)list);
        else System.err.println("wrong type in yanYue");

    }
    @SuppressWarnings("unchecked")
    public static <T extends Creature> void fengShi(List<T>list){
        if(list.get(0) instanceof Bad)
            fengShiBad((List<Bad>)list);
        else fengShiGood((List<Good>)list);

    }
    @SuppressWarnings("unchecked")
    public static <T extends Creature> void changShe(List<T>list){
        if(list.get(0) instanceof Good)
            changSheGood((List<Good>)list);
        else changSheBad((List<Bad>)list);
    }

}

