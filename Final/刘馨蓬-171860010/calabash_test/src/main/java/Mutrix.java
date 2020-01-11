//package main.java;

import java.util.Vector;

enum MutrixType {HEYI,YANXING,HENGGEN,CHANGSHE,YULIN,FANGMEN,YANYUE,FENGSHI}
public class Mutrix {
    private Vector<Point> point;
    private Point startdard;
    private int count;
    public Mutrix(MutrixType type,Point startdard,int id){
        this.startdard=startdard;
        point=new Vector<Point>();
        if(id==1){
            initHuluwa(type);
        }
        else{
            initMinions(type);
        }
    }
    public Point getPos(int i) {
        return point.get(i);
    }
    public int getCount(){
        return count;
    }
    private void initHuluwa(MutrixType type){
        count=8;
        switch (type){
            case YULIN:
                point.add(new Point(4,2));
                point.add(new Point(5,2));
                point.add(new Point(5,3));
                point.add(new Point(6,2));
                point.add(new Point(6,3));
                point.add(new Point(6,4));
                point.add(new Point(7,2));
                point.add(new Point(6,0));
                break;
            case HEYI:
                point.add(new Point(2,2));
                point.add(new Point(3,3));
                point.add(new Point(4,4));
                point.add(new Point(5,5));
                point.add(new Point(6,4));
                point.add(new Point(7,3));
                point.add(new Point(8,2));
                point.add(new Point(5,0));
                break;
            case YANYUE:
                point.add(new Point(3,1));
                point.add(new Point(7,1));
                point.add(new Point(4,2));
                point.add(new Point(6,2));
                point.add(new Point(5,3));
                point.add(new Point(6,3));
                point.add(new Point(5,4));
                point.add(new Point(6,4));
                break;
            case FANGMEN:
                point.add(new Point(5,5));
                point.add(new Point(4,4));
                point.add(new Point(6,4));
                point.add(new Point(3,3));
                point.add(new Point(7,3));
                point.add(new Point(4,2));
                point.add(new Point(6,2));
                point.add(new Point(5,1));
                break;
            case CHANGSHE:
                point.add(new Point(3,3));
                point.add(new Point(4,3));
                point.add(new Point(5,3));
                point.add(new Point(6,3));
                point.add(new Point(7,3));
                point.add(new Point(8,3));
                point.add(new Point(9,3));
                point.add(new Point(6,2));
                break;
            case FENGSHI:
                point.add(new Point(6,4));
                point.add(new Point(5,3));
                point.add(new Point(6,3));
                point.add(new Point(7,3));
                point.add(new Point(4,2));
                point.add(new Point(6,2));
                point.add(new Point(8,2));
                point.add(new Point(6,1));
                break;
            case HENGGEN:
                point.add(new Point(3,4));
                point.add(new Point(4,3));
                point.add(new Point(5,4));
                point.add(new Point(6,3));
                point.add(new Point(7,4));
                point.add(new Point(8,3));
                point.add(new Point(9,4));
                point.add(new Point(5,1));
                break;
            case YANXING:
                point.add(new Point(3,6));
                point.add(new Point(4,5));
                point.add(new Point(5,4));
                point.add(new Point(6,3));
                point.add(new Point(7,2));
                point.add(new Point(8,1));
                point.add(new Point(9,0));
                point.add(new Point(5,3));
                break;
        }
    }
    private void initMinions(MutrixType type){
        int dx=startdard.getX();
        int dy=startdard.getY();
        count=8;
        switch (type){
            case YULIN:
                point.add(new Point(4,2+dy));
                point.add(new Point(5,2+dy));
                point.add(new Point(5,3+dy));
                point.add(new Point(6,2+dy));
                point.add(new Point(6,3+dy));
                point.add(new Point(6,4+dy));
                point.add(new Point(7,2+dy));
                point.add(new Point(6,6+dy));
                break;
            case HEYI:
                point.add(new Point(2,5+dy));
                point.add(new Point(3,4+dy));
                point.add(new Point(4,3+dy));
                point.add(new Point(5,2+dy));
                point.add(new Point(6,3+dy));
                point.add(new Point(7,4+dy));
                point.add(new Point(8,5+dy));
                point.add(new Point(5,6+dy));
                break;
            case YANYUE:
                point.add(new Point(3,4+dy));
                point.add(new Point(7,4+dy));
                point.add(new Point(4,3+dy));
                point.add(new Point(6,3+dy));
                point.add(new Point(5,2+dy));
                point.add(new Point(6,2+dy));
                point.add(new Point(5,1+dy));
                point.add(new Point(6,1+dy));
                break;
            case FANGMEN:
                point.add(new Point(5,1+dy));
                point.add(new Point(4,4+dy));
                point.add(new Point(6,4+dy));
                point.add(new Point(3,3+dy));
                point.add(new Point(7,3+dy));
                point.add(new Point(4,2+dy));
                point.add(new Point(6,2+dy));
                point.add(new Point(5,5+dy));
                break;
            case CHANGSHE:
                point.add(new Point(3,3+dy));
                point.add(new Point(4,3+dy));
                point.add(new Point(5,3+dy));
                point.add(new Point(6,3+dy));
                point.add(new Point(7,3+dy));
                point.add(new Point(8,3+dy));
                point.add(new Point(9,3+dy));
                point.add(new Point(6,4+dy));
                break;
            case FENGSHI:
                point.add(new Point(6,1+dy));
                point.add(new Point(5,2+dy));
                point.add(new Point(6,2+dy));
                point.add(new Point(7,2+dy));
                point.add(new Point(4,3+dy));
                point.add(new Point(6,3+dy));
                point.add(new Point(8,3+dy));
                point.add(new Point(6,4+dy));
                break;
            case HENGGEN:
                point.add(new Point(3,4+dy));
                point.add(new Point(4,3+dy));
                point.add(new Point(5,4+dy));
                point.add(new Point(6,3+dy));
                point.add(new Point(7,4+dy));
                point.add(new Point(8,3+dy));
                point.add(new Point(9,4+dy));
                point.add(new Point(5,5+dy));
                break;
            case YANXING:
                point.add(new Point(3,6+dy));
                point.add(new Point(4,5+dy));
                point.add(new Point(5,4+dy));
                point.add(new Point(6,3+dy));
                point.add(new Point(7,2+dy));
                point.add(new Point(8,1+dy));
                point.add(new Point(9,0+dy));
                point.add(new Point(5,5+dy));
                break;
        }
    }
}
