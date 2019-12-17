package cn.whh.bloodBar;

import cn.whh.creature.Creature;
import cn.whh.creature.Debuff;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class BloodBar extends Rectangle
{
    private  int bloodwidth=50;
    private  int bloodheight=5;
    private Creature creature;
    private Label hpValue;

    static ArrayList<BloodBar> arrayList=new ArrayList<>();

    public static BloodBar getBloodBar(Creature target)
    {
        BloodBar res=null;
        for(int i=0;i<arrayList.size();i++)
        {
            res=arrayList.get(i);
            if(res.creature==target) return res;
        }

        return null;
    }

    public static void updateForField(Creature[][] field, int width, int height)
    {
        int index=0,HP=0;
        for(int i=0;i<arrayList.size();i++)
        {
            arrayList.get(i).setWidth(0);
            arrayList.get(i).getHpValue().setVisible(false);
        }

        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                HP=field[i][j].getCurHP();
                if(HP>0)
                {
                    BloodBar bloodBar=arrayList.get(index++);
                    bloodBar.setFill(Color.RED);
                    bloodBar.setX(j * 77);
                    bloodBar.setY(i * 60);
                    if(field[i][j].getDebuff()== Debuff.Poison) bloodBar.setFill(Color.PURPLE);
                    else if(field[i][j].getDebuff()== Debuff.Broken) bloodBar.setFill(Color.BLUE);
                    bloodBar.setWidth(50*HP/field[i][j].getAllHP());

                    Label hpValue=bloodBar.getHpValue();
                    hpValue.setVisible(true);
                    hpValue.setLayoutX(j*77);
                    hpValue.setLayoutY(i*60);
                    hpValue.setText(Integer.toString(field[i][j].getCurHP())+"/"+ Integer.toString(field[i][j].getAllHP()));
                }
            }
        }
    }

    public static void clearArraylist()
    {
        for(int i=0;i<arrayList.size();i++) arrayList.get(i).setWidth(0);
        arrayList.clear();
    }

    public BloodBar(int x, int y, Creature creature)
    {
        super(y*77,x*60,50,3);
        this.setFill(Color.RED);
        this.setArcHeight(15);
        this.setArcWidth(15);
        this.creature=creature;

        this.hpValue=new Label(Integer.toString(creature.getCurHP())+"/"+ Integer.toString(creature.getAllHP()));
        hpValue.setLayoutX(y*77);
        hpValue.setLayoutY(x*60);
        hpValue.setFont(Font.font("Cambria",10));
        hpValue.setTextFill(Color.YELLOW);

        arrayList.add(this);
    }

    public void setCreature(int x, int y, Creature creature) //开局改变阵型时，不显示血条具体数值
    {
        this.setX(y*77);
        this.setY(x*60);
        this.setWidth(50*creature.getCurHP()/creature.getAllHP());

        hpValue.setLayoutX(y*77);
        hpValue.setLayoutY(x*60);
        this.creature=creature;
    }

    public Label getHpValue() {return hpValue;}

    //根据生物的当前位置和血量大小，更新血条的位置和宽度
    public void update()
    {
        this.setX(creature.getPosition().getY() * 77);
        this.setY(creature.getPosition().getX() * 60);
        hpValue.setLayoutX(creature.getPosition().getY()*77);
        hpValue.setLayoutY(creature.getPosition().getX()*60);

        if(creature.isAlive())
        {
            this.setWidth(50*creature.getCurHP()/creature.getAllHP());
            hpValue.setText(Integer.toString(creature.getCurHP())+"/"+ Integer.toString(creature.getAllHP()));
            if(!hpValue.isVisible()) hpValue.setVisible(true);
        }
        else if(creature.getCurHP()<=0||!creature.isAlive())
        {
            this.setWidth(0);
            hpValue.setVisible(false);
        }

    }
}
