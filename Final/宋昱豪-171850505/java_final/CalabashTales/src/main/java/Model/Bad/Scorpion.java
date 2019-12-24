package Model.Bad;

import Model.World.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Scorpion extends Lives {
    private Formation<Sidekicks> command;
    public Scorpion(Position x, Attributes z)
    {
        id=num;
        num=num+1;
      //  System.out.print("id==="+id+"\n");
        position=x;
        if(ground[x.x][x.y].GetIsOccupied()==false)
        {
            ground[x.x][x.y].SetALL(true,this);
        }
        attributes=new Attributes(z);
        this.myAppearance = new ImageView();
        Image image = new Image(z.URL);
       // System.out.print(z.URL);
        myAppearance.setImage(image);
        myHp=new Label(attributes.Hp+"");
        myHp.setTextFill(Color.GREEN);

        SetPic();
    }
    void summonSidekicks(Sidekicks x[],int number)
    {
        Attributes sidekicksA=new Attributes(100,100,30,3,Attributes.livingStatus.live,Attributes.Group.bad,"pic/Sidekicks.png");
        for(int i=0;i<number;i++)
        {
            Position temp=new Position(i+2,position.y+2);
            x[i]=new Sidekicks(temp,sidekicksA);
        }
    }
    public void StartCommand(int number,Sidekicks x[])
    {
       // System.out.print("Scorpion is at"+"x="+position.x+" y="+position.y);
        command=new Formation<Sidekicks>(position,number,ground,x);
    }
    public void T1()
    {
        command.T1();
    }
    public void T2()
    {
        command.T2();
    }
    public void T3()
    {
        command.T3();
    }
    public void T4()
    {
        command.T4();
    }
    public void T5()
    {
        command.T5();
    }
    public void T6()
    {
        command.T6();
    }
    public void T7()
    {
        command.T7();
    }
    public void T8()
    {
        command.T8();
    }
}

