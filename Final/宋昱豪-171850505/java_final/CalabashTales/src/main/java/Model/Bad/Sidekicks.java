package Model.Bad;

import Model.World.Attributes;
import Model.World.Lives;
import Model.World.Position;
import Model.World.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Sidekicks extends Lives {
    public Sidekicks(Position x,  Attributes z)
    {
        id=num;
        num=num+1;
       // System.out.print("id==="+id+"\n");
        position=x;
        if(ground[x.x][x.y].GetIsOccupied()==false)
        {
            ground[x.x][x.y].SetALL(true,this);
        }
        attributes=new Attributes(z);
        this.myAppearance = new ImageView();
        Image image = new Image(z.URL);
        //System.out.print(z.URL);
        myAppearance.setImage(image);
        myHp=new Label(attributes.Hp+"");
        myHp.setTextFill(Color.GREEN);
        SetPic();
    }
}
