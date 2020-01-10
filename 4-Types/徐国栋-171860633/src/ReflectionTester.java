import creature.*;
import gamectrl.Grid2D;

import java.lang.reflect.*;

public class ReflectionTester {
    int countCalabashBrother = 0, countLolo = 0, countScorpion = 0;

    public ReflectionTester() {

    }

    public void displayMethod(String className) throws ClassNotFoundException {
        try {
            Class c = Class.forName(className);
            Method m[] = c.getDeclaredMethods();
            System.out.println(className + "拥有的方法：");
            System.out.println("----------------------------------------");
            for (int i = 0; i < m.length; i++) {
                System.out.println(m[i].toString());
            }
            System.out.println("----------------------------------------");
        }catch (ClassNotFoundException e){
            System.out.println(e);
        }
    }

    public void countThroughReflection(Grid2D[][] grids, int w, int h) {
        countCalabashBrother = 0;
        countLolo = 0;
        countScorpion = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (grids[i][j].isFree()) {
                    // 如果为空，即<T> theCreature == null
                    continue;
                }
                // getTheCreature()返回T
                Class<?> currentHolder = grids[i][j].getTheCreature().getClass();
                if (CalabashBrother.class == currentHolder) {
                    countCalabashBrother++;
                } else if (EvilLolo.class == currentHolder) {
                    countLolo++;
                } else if (ScorpionSperm.class == currentHolder) {
                    countScorpion++;
                }
            }
        }
        System.out.print("运行时通过反射计数：\nCalabashBrother：" + countCalabashBrother + "；\nEvilLolo：" + countLolo + "；\nScorpion：" + countScorpion + "；\n");
    }
}
