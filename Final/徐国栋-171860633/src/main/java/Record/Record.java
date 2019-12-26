package Record;

import annotation.Description;
import creature.*;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Description(comment = "记录类，压缩生物类的信息量来节约序列化的存储空间")
public class Record implements Serializable {
    private static final long serialVersionUID = -3286564461647015367L;
    private int type;
    public int x,y;//  每一帧只记录这3个数
    static Image[]a=new Image[30];
    public boolean isAlive(){
        return type!=29;
    }
    public boolean isEvil(){
        //System.out.println("type="+type);
        int tmp=type%13;
        if((tmp>=0&&tmp<=6)||tmp==10){
            return false;
        }else{
            return true;
        }
    }
    static{
        a[0]=new Image("images/1.png");
        a[1]=new Image("images/2.png");
        a[2]=new Image("images/3.png");
        a[3]=new Image("images/4.png");
        a[4]=new Image("images/5.png");
        a[5]=new Image("images/6.png");
        a[6]=new Image("images/7.png");
        a[7]=new Image("images/lolo-1.png");
        a[8]=new Image("images/lolo-2.png");
        a[9]=new Image("images/lolo-3.png");
        a[10]=new Image("images/papa.png");
        a[11]=new Image("images/sj.png");
        a[12]=new Image("images/xz.png");

        a[13]=new Image("images/1 - 副本.png");
        a[14]=new Image("images/2 - 副本.png");
        a[15]=new Image("images/3 - 副本.png");
        a[16]=new Image("images/4 - 副本.png");
        a[17]=new Image("images/5 - 副本.png");
        a[18]=new Image("images/6 - 副本.png");
        a[19]=new Image("images/7 - 副本.png");
        a[20]=new Image("images/lolo-1 - 副本.png");
        a[21]=new Image("images/lolo-2 - 副本.png");
        a[22]=new Image("images/lolo-3 - 副本.png");
        a[23]=new Image("images/papa - 副本.png");
        a[24]=new Image("images/sj - 副本.png");
        a[25]=new Image("images/xz - 副本.png");

        a[26]=new Image("images/pause.png");
        a[27]=new Image("images/lolowin.png");
        a[28]=new Image("images/huluwawin.png");

        a[29]=new Image("images/die.png");
    }
    public Record(Creature a){
        x=a.getPosition().getX();
        y=a.getPosition().getY();
        int flag=1,tmp;
        if(a.isLeft){
            flag=0;
        }
        if(!a.isAlive){
            type=29;
        }
        else if(a.getClass()== CalabashBrother.class){
            tmp=((CalabashBrother)a).rank;
            type=flag*13+tmp;
        }else if(a.getClass()== GrandPa.class){
            tmp=10;
            type=flag*13+tmp;
        }else if(a.getClass()== ScorpionSperm.class){
            tmp=12;
            type=flag*13+tmp;
        }else if(a.getClass()== SnakeEssence.class){
            tmp=11;
            type=flag*13+tmp;
        }else {
            tmp=6+((EvilLolo)a).appearanceType;
            type=flag*13+tmp;
        }
    }
    public Record(){

    }
    public void setType(int i){
        type=i;
    }
    @Override public String toString(){
        return new String("typeId="+type+", (x,y)=("+x+", "+y+")");
    }
    public Image getImage(){
        if(type>=0&&type<30){
            return a[type];
        }else{
            return null;
        }
    }
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }
}
