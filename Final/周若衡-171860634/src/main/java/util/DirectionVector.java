package util;

public class DirectionVector implements  Cloneable,java.io.Serializable{
    private double x;
    private double y;
    public DirectionVector(double x1,double y1){
        x=x1;   y=y1;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    @Override
    public Object clone() {
        DirectionVector temp = null;
        try{
            temp = (DirectionVector)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return temp;
    }
}

