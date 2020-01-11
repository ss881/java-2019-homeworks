package creature;

import util.ImageType;

public class Being implements Cloneable,java.io.Serializable{
    protected String name;  //表示名称
    protected int x,y;    //表示坐标
    protected ImageType image;        //图片
    public Being(int x, int y, ImageType image, String name){
        this.x=x;
        this.y=y;
        this.image=image;
        this.name=name;
    }
    public ImageType getStyleImage(){
        return image;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public String getName(){return name;}
    @Override
    public Object clone() {
        Being temp = null;
        try{
            temp = (Being)super.clone();	//浅复制
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
