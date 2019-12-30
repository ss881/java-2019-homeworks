package myUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyUtils {
    public static Random random = new Random(System.nanoTime());

    public static class ClockChecker{
        private long startNanoTime = 0;

        public void start(){
            startNanoTime = System.nanoTime();
        }

        public long check(){
            long curNanoTime = System.nanoTime();
            return TimeUnit.NANOSECONDS.toMillis(curNanoTime - startNanoTime);
        }
    }

    // 标准的 虚拟像素范围
    public static final int DEFAULT_STD_PACE = 10;

    /*
    * 获取 标准范围的n倍
     */
    public static int getPace(int n){
        return n * DEFAULT_STD_PACE;
    }
}
