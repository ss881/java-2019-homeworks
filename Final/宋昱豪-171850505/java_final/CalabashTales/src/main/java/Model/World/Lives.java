package Model.World;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Lives implements Runnable{
    public static int num=0;
    public int id;
    static Image deadImage=new Image("pic/dead.png");
    protected Position position;
    protected Attributes attributes;
    protected ImageView myAppearance;
    protected Label myHp;

    Random direction=new Random();
    Random evade=new Random();
    protected static Tile ground[][];
    public static int rx[]=new int[8];
    public static int ry[]=new int[8];
    public static void init(Tile y[][])
    {
        ground=y;
        //deadImage=new Image("pic/dead.png");
        rx[0]=0;ry[0]=1;
        rx[1]=1;ry[1]=0;
        rx[2]=1;ry[2]=1;
        rx[3]=-1;ry[3]=-1;
        rx[4]=-1;ry[4]=0;
        rx[5]=0;ry[5]=-1;
        rx[6]=-1;ry[6]=1;
        rx[7]=1;ry[7]=-1;

    }
    public void run() {
        normalThread();
    }
    public Lives()
    {

    }
    public Lives(Position x, Attributes y)
    {

        id=num;
        num=num+1;
        System.out.print("id==="+id+"\n");
        attributes=y;
        position=x;
        this.myAppearance = new ImageView();
        Image image = new Image(y.URL);
        myAppearance.setImage(image);
        myHp=new Label("blood:"+attributes.Hp+"");
        myHp.setTextFill(Color.BLACK);
        SetPic();
    }
    public void SetPic()
    {
        myAppearance.setX(position.x*50);
        myAppearance.setY(position.y*50+10);
        myAppearance.setFitHeight(1000/BattleGround.N-10);
        myAppearance.setFitWidth(1000/BattleGround.M);
        myHp.setFont(new Font("Arial", 16));
        myHp.setWrapText(true);
        myHp.setTranslateX(position.x*50+5);
        myHp.setTranslateY(position.y*50-6);
    }

    public ImageView GetImage()
    {
        return this.myAppearance;
    }
    public Label GetLabel(){return this.myHp;}
    public Attributes GetAttributes()
    {
        return attributes;
    }
    public void SetAttributes(Attributes x)
    {
        attributes=x;
    }
    public Position GetPosition()
    {
        return position;
    }
    public void SetPosition(Position x)
    {
        position =x;
    }
    public void normalThread()
    {
        new Thread(() -> {
            while (this.attributes.living== Attributes.livingStatus.live) {
                try {
                    int dir=direction.nextInt(100);
                    if(dir%7==1)
                    {
                        deadlyWalk();
                    }
                    else
                    {
                        randomWalk();
                    }

                    //randomWalk();
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
    public void deadlyWalk()
    {
        Lives life=this;
        // System.out.print(id+"myHp="+attributes.Hp);
        int dir=direction.nextInt(5);
        Position dst=null;
        Position old=new Position(position);
        synchronized (ground)
        {
        for(int i=0;i<BattleGround.M;i++)
        {
            for(int j=0;j<BattleGround.N;j++)
            {
                if((ground[i][j].GetIsOccupied()==true)&&(ground[i][j].GetWho().attributes.group!=life.attributes.group))
                {
                    dst=new Position(ground[i][j].GetWho().position);
                    break;
                }
            }
        }

            for(int i=0;i<8;i++)
            {

                if((dst.x+rx[i]>=0)&&(dst.y+ry[i]>=0)&&(dst.y+ry[i]<BattleGround.N)&&(dst.x+rx[i]<BattleGround.M)&&(ground[dst.x+rx[i]][dst.y+ry[i]].GetIsOccupied()==false))
                {
                    System.out.print("x="+(dst.x+rx[i])+" y="+(dst.y+ry[i])+"\n");
                    dst.x=dst.x+rx[i];
                    dst.y=dst.y+ry[i];
                    walk(dst);
                    break;
                }
            }

        }
        for(int i=0;i<8;i++)
        {
            if((position.x+rx[i]>=0)&&(position.y+ry[i]>=0)&&(position.y+ry[i]<BattleGround.N)&&(position.x+rx[i]<BattleGround.M)&&(ground[position.x+rx[i]][position.y+ry[i]].GetIsOccupied()==true))
            {
                Lives Object=ground[position.x+rx[i]][position.y+ry[i]].GetWho();
                if(Object==null)
                {
                    System.out.print("o n\n");
                }
                if(Object.attributes==null)
                {
                    System.out.print("a n\n");
                }
                if(Object.attributes.group==null)
                {
                    System.out.print("a n\n");
                }
                if(Object.attributes.group!=this.attributes.group)
                {
                    attack(Object);
                }
            }
        }
        Platform.runLater(new Runnable(){
            public void run(){
                ImageView myAppearance1 = life.myAppearance;
                Label myHp1 = life.myHp;
                Timeline t = new Timeline();
                t.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,new KeyValue(myAppearance1.xProperty(),old.x*50)),
                        new KeyFrame(new Duration(500),new KeyValue(myAppearance1.xProperty(), life.position.x *50)),
                        new KeyFrame(Duration.ZERO,new KeyValue(myAppearance1.yProperty(),old.y*50+10)),
                        new KeyFrame(new Duration(500),new KeyValue(myAppearance1.yProperty(),life.position.y*50+10)),
                        new KeyFrame(Duration.ZERO,new KeyValue(myHp1.translateXProperty(),old.x *50+5)),
                        new KeyFrame(new Duration(500),new KeyValue(myHp1.translateXProperty(),life.position.x *50+5)),
                        new KeyFrame(Duration.ZERO,new KeyValue(myHp1.translateYProperty(),old.y*50-6)),
                        new KeyFrame(new Duration(500),new KeyValue(myHp1.translateYProperty(),life.position.y*50-6))

                );
                t.play();
            }
        });
    }

    public void randomWalk()
    {
        Lives life=this;
        int dir=direction.nextInt(5);
        Position dst=new Position(position);
        Position old=new Position(position);
        switch(dir)
        {
            case 0:break;//don't move
            case 1:{
                if(dst.x<1)
                {

                }else
                {
                    dst.x--;
                    break;
                }
            }
            case 2:{
                if(dst.x>=BattleGround.M-1)
                {

                }
                else
                {
                    dst.x++;
                    break;
                }

            }
            case 3:{
                if(dst.y>=BattleGround.N-1)
                {

                }
                else
                {
                    dst.y++;
                    break;
                }
            }
            case 4:{
                if(dst.y<1)
                {

                }
                else
                {
                    dst.y--;
                    break;
                }

            }
            default:break;
        }
        synchronized (ground)
        {
            if((ground[dst.x][dst.y].GetIsOccupied()==false))
            {
                walk(dst);
            }



        for(int i=0;i<8;i++)
        {
            if((position.x+rx[i]>=0)&&(position.y+ry[i]>=0)&&(position.y+ry[i]<BattleGround.N)&&(position.x+rx[i]<BattleGround.M)&&(ground[position.x+rx[i]][position.y+ry[i]].GetIsOccupied()==true))
            {
                Lives Object=ground[position.x+rx[i]][position.y+ry[i]].GetWho();
                if(Object.attributes.group!=this.attributes.group)
                {
                    attack(Object);
                    break;
                }
            }
        }
        }
        Platform.runLater(new Runnable(){
            public void run(){
                ImageView myAppearance1 = life.myAppearance;
                Label myHp1 = life.myHp;
                Timeline t = new Timeline();
                t.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,new KeyValue(myAppearance1.xProperty(),old.x*50)),
                        new KeyFrame(new Duration(500),new KeyValue(myAppearance1.xProperty(), life.position.x *50)),
                        new KeyFrame(Duration.ZERO,new KeyValue(myAppearance1.yProperty(),old.y*50+10)),
                        new KeyFrame(new Duration(500),new KeyValue(myAppearance1.yProperty(),life.position.y*50+10)),
                        new KeyFrame(Duration.ZERO,new KeyValue(myHp1.translateXProperty(),old.x *50+5)),
                        new KeyFrame(new Duration(500),new KeyValue(myHp1.translateXProperty(),life.position.x *50+5)),
                        new KeyFrame(Duration.ZERO,new KeyValue(myHp1.translateYProperty(),old.y*50-6)),
                        new KeyFrame(new Duration(500),new KeyValue(myHp1.translateYProperty(),life.position.y*50-6))

                );
                t.play();
            }
        });
    }
    public void walk(Position x)
    {
        if(ground[x.x][x.y].GetIsOccupied()==true)
        {
            Lives one=ground[x.x][x.y].GetWho();
            ground[position.x][position.y].SetALL(true,one);
            one.position=position;
            ground[x.x][x.y].SetALL(true,this);
        }
        else
        {
            ground[position.x][position.y].SetALL(false,null);
            ground[x.x][x.y].SetALL(true,this);
        }

        this.position=x;

    }
    public void attack(Lives enemy)
    {
      enemy.defend(this.attributes.Ack);

    }
    public void defend(int ack)
    {
        int evadeSuccess=evade.nextInt(this.attributes.Evade);
        if(evadeSuccess!=1)
        {

            this.attributes.Hp-=ack;
            int Hp=this.attributes.Hp;
            Platform.runLater(new Runnable(){
                public void run()
                {
                   myHp.setText(Hp+"");
                   myHp.setTextFill(Color.RED);
                }});

        }
        if(this.attributes.Hp<=0)
        {
            die();
        }
    }
    public void die()
    {
        this.attributes.living= Attributes.livingStatus.dead;
        synchronized (ground)
        {
            ground[this.position.x][this.position.y].SetALL(false,null);
        }
        BattleGround.whodie(id);
        ImageView I = this.myAppearance;
        Label L=this.myHp;
        this.myAppearance.setImage(deadImage);
        Platform.runLater(new Runnable(){
            public void run(){
                Timeline t = new Timeline();
                t.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,new KeyValue(I.rotateProperty(),0)),
                        new KeyFrame(new Duration(1000),new KeyValue(I.rotateProperty(),180)),
                        new KeyFrame(Duration.ZERO,new KeyValue(I.opacityProperty(),1)),
                        new KeyFrame(new Duration(1000),new KeyValue(I.opacityProperty(),0)),
                        new KeyFrame(Duration.ZERO,new KeyValue(L.opacityProperty(),1)),
                        new KeyFrame(new Duration(1000),new KeyValue(L.opacityProperty(),0))

                );
                t.play();
            }
        });
    }

}

