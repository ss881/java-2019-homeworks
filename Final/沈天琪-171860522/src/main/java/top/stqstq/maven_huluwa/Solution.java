package top.stqstq.maven_huluwa;

import top.stqstq.maven_huluwa.involved.Involved;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Involved i=new Involved();
        //i.test();;
        i.huluwaCome();
        i.huluwaSort();
        //i.elderAndSnackCome();
        for(int i1=0;i1<8;i1++){
            i.scorpionOrder(i1);
            i.cheerTogether();
        }
        System.out.println("-------------------------------\n\n-----------------------------------");
        //i.restoreState();
        //i.war();
        //Logger logger =new Logger();
        //logger.createLoggerFile();
        //i.warMultithreading();
        /*Thread.sleep(1000);
        i.restoreState();
        i.huluwaCome();
        //i.elderAndSnackCome();
        i.scorpionOrder(1);
        i.warMultithreading();//*/
    }
}
