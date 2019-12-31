package Unit;

import java.util.Random;
import java.util.Vector;

import Ground.Ground;
import Ground.Position;
import Unit.MyFormation;
import Unit.Unit;

public class UnitCommander extends Unit {
    int commandCapability;
    // 领导力的衡量值，为以后做可能的铺垫
    Vector<Unit> myFollowers;
    Random myRandom;
    // 能够给其他单位发送命令的属性，需要一个能够产生随机数的大脑（手动滑稽
    MyFormation myForm;
    // 记录自己follower的阵型

    public UnitCommander(int a, String n, int f, int c, Ground g) {
        super(n, f, c, g);
        myRandom = new Random();
        commandCapability = a;
        myFollowers = new Vector<Unit>();
        myForm = new MyFormation();
    }
    // 构造函数

    public void plantYourFollowersRandomly() {
        for (int i = 0; i < myFollowers.size(); ++i) {
            int rLine;
            int rRow;
            do {
                rLine = myRandom.nextInt(myGr.getLen());
                rRow = myRandom.nextInt(myGr.getWid());
            } while (!myFollowers.elementAt(i).findYourPathTo(new Position(rLine, rRow)));
            // 随机让自己的followers走到不同的地点
        }
    }

    public void changeMyFormationToSnake() {
        changeFollowerMyFormation(7);
    }
    // 特殊的变阵方法，直接变阵为长蛇阵

    public boolean changeFollowerMyFormation(int ind) {
        int len = 0;
        int wid = 0;
        OneMyFormation tem = myForm.getMyFormation(ind);
        len = tem.len;
        wid = tem.wid;

        int[][] form = new int[len][wid];
        int size = 0;
        String name = new String();

        size = tem.size;
        name = tem.name;
        form = tem.form;

        System.out.printf("Changing Formation To: %s\n", name);

        if (size > 7 && force == 0) {
            return false;
        }
        // 暂时的正确性判断函数，有些阵型葫芦娃变不了

        int startLine = (force == 0) ? 0 : myGr.getWid() - 1;
        int startRow = 0;

        int formStartLine = (force == 0) ? 0 : wid - 1;
        int scanDirection = (force == 0) ? 1 : -1;
        // 寻找阵型匹配的相关参数
        // 葫芦娃阵型从左往右找
        // 蛇精阵型从右找

        boolean[] mark = new boolean[myFollowers.size() - size];
        int markNum = 0;
        for (int i = 0; i < myFollowers.size() - size; ++i){
            if (myGr.isThisUnitOnTheGround(myFollowers.elementAt(size + i)))
                mark[i] = false;
                // 检查这个单位是否已经下去了
            else{
                mark[i] = true;
                ++markNum;
                // 如果单位本身不在地上，则标记该单位   
            }
        }

        
        while (markNum != myFollowers.size() - size) {
            for (int i = size; i < myFollowers.size(); ++i) {
                if (mark[i - size] == false && myFollowers.elementAt(i).standOut()){
                    ++markNum;
                    mark[i - size] = true;
                    // 没有全都走下去，还得继续试验
                }
            }
        }
        // 让多余的单位走下去

        for (int i = 0; i < myGr.getLen() - len; ++i) {
            for (int j = 0; j < myGr.getWid() - wid; ++j) {
                if (canIPlantMyFormationHere(form, len, wid, startLine + j * scanDirection, startRow + i, formStartLine,
                        scanDirection, size)) {
                    return true;
                    // 如果能安排阵型在这个地方，那么就直接放置，之后返回可以放置的信号
                }
            }
        }

        return false;
    }

    boolean canIPlantMyFormationHere(int[][] form, int len, int wid, int startLine, int startRow, int formStartLine,
            int scanDirection, int size) {
        if (len > myGr.getLen() || wid > myGr.getWid())
            return false;
            // 显然没法放，阵型都比地面大
        else {
            int gr[][] = myGr.getGroundObstacles();
            // 看一下地面，获得单位图
            int forceNum = (force == 0) ? -2 : -1;
            for (int i = startRow; i < startRow + len; ++i) {
                for (int j = startLine; (j - startLine) > -wid && j - startLine < wid; j += scanDirection) {
                    if (form[i - startRow][j - startLine + formStartLine] != -1 && gr[i][j] == forceNum)
                        return false;
                        // 如果是敌方单位，在目标点，那肯定就走不了了
                }
            }

            boolean[] mark = new boolean[myFollowers.size()];
            for (int i = 0; i < myFollowers.size(); ++i) {
                mark[i] = false;
            }
            //建立对单位是否移动到位的标记数组

            boolean canIMoveOneUnit = false;
            // 如果一个都不能再动了，就返回不能放置阵型的信号
            int markNum = 0;

            // 如果不是所有单位全部到位，那就继续循环
            while (markNum != size) {
                canIMoveOneUnit = false;
                for (int i = startRow; i < startRow + len; ++i) {
                    for (int j = startLine; (j - startLine) > -wid && j - startLine < wid; j += scanDirection) {
                        if (form[i - startRow][j - startLine + formStartLine] != -1) {
                            if (mark[form[i - startRow][j - startLine + formStartLine]] == false
                                    && myFollowers.get(form[i - startRow][j - startLine + formStartLine])
                                            .findYourPathTo(new Position(i, j))) {
                                // lookAround(true, true, true, true, true);
                                mark[form[i - startRow][j - startLine + formStartLine]] = true;
                                canIMoveOneUnit = true;
                                ++markNum;
                                // 如果能动，那么所有的标志全部都记录一下
                                System.out.printf("markNum = %d, size = %d", markNum, size);
                            }
                        }
                    }
                }
                if (canIMoveOneUnit == false)
                    return false;
            }
            return true;
        }
    }

    public void addFollowers(Unit u) {
        u.changeForce(force);
        myFollowers.add(u);
    }

    public void checkFollowers() {
        for (int i = 0; i < myFollowers.size();) {
            if (myFollowers.elementAt(i).getMyCondition() <= 0)
                myFollowers.remove(i);
            else
                ++i;
        }
    }

    public void lookAround(boolean myName, boolean myforce, boolean myCond, boolean mySeq, boolean myColor) {
        myGr.showGround(myName, myforce, myCond, mySeq, myColor);
    }

}