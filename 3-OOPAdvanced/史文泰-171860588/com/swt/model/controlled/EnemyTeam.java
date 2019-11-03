package com.swt.model.controlled;

import com.swt.control.FileUtils;
import com.swt.model.basic.Point;
import com.swt.model.basic.Picture;
import com.swt.model.advance.Scorpion;
import com.swt.model.advance.SmallEnemy;
import com.swt.model.advance.Snake;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnemyTeam {
    private Snake snake;                            //蛇精
    private Scorpion scorpion;                      //蝎子精
    private List<SmallEnemy> smallEnemyList;        //小喽啰

    public EnemyTeam(List<Point> pointList) throws IOException {
        smallEnemyList = new ArrayList<>();
        //蝎子精，第一个坐标
        scorpion = new Scorpion(pointList.get(0).getPx(), pointList.get(0).getPy(), FileUtils.getImage("SRCFile/Scorpion.jpg"));
        //小喽啰
        for(int i = 1; i < pointList.size(); ++i){
            SmallEnemy smallEnemy = new SmallEnemy(pointList.get(i).getPx(), pointList.get(i).getPy(), FileUtils.getImage("SRCFile/0.jpg"));
            smallEnemyList.add(smallEnemy);
        }
        //蛇精
        Picture orderPicture = new Picture(new Point(7, 0), FileUtils.getImage("SRCFile/Form0.jpg"));
        snake = new Snake(9, 0, FileUtils.getImage("SRCFile/Snake.jpg"), orderPicture);
    }

//    /**
//     * 绘制蝎子精和小喽啰
//     * @param graphicsContext
//     */
//    public void drawEnemyTeam(GraphicsContext graphicsContext){
////        //清除原先的点
////        for(Point point: NMap.occupiedList){
////            NMap.NMap[point.getPx()][point.getPy()] = 0;
////        }
////        NMap.occupiedList.clear();
//        scorpion.draw(graphicsContext);
//        for(SmallEnemy smallEnemy: smallEnemyList){
//            smallEnemy.draw(graphicsContext);
//        }
//    }

    /**
     * 改变阵型
     * @param pointList
     */
    public void ChangeFormation(List<Point> pointList){
        //需要移动的小喽啰数
        int movNum = (pointList.size() - 1) > smallEnemyList.size()?smallEnemyList.size():(pointList.size() - 1);
        //每个人得到一个目的坐标
        scorpion.setDestPoint(pointList.get(0).getPx(), pointList.get(0).getPy());
        for(int i = 0; i < movNum; ++i){
            smallEnemyList.get(i).setDestPoint(pointList.get(i + 1).getPx(), pointList.get(i + 1).getPy());
        }
        //开始移动
        while (!isChangeOver(movNum)){
            scorpion.changePosition();
            for(int i = 0; i < movNum; ++i){
                smallEnemyList.get(i).changePosition();
            }
        }
    }

    /**
     * 判断是否改变结束
     */
    private boolean isChangeOver(int movNum){
        if(!scorpion.moveIsOver()){
            return false;
        }
        for(int i = 0; i < movNum; ++i){
            if(!smallEnemyList.get(i).moveIsOver()){
                return false;
            }
        }
        return true;
    }

    public Scorpion getScorpion() {
        return scorpion;
    }

    public List<SmallEnemy> getSmallEnemyList() {
        return smallEnemyList;
    }

    public Snake getSnake(){
        return snake;
    }
}
