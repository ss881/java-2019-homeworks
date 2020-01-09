package cn.whh.creature;

import cn.whh.map.Field;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


//用途：作为“关于游戏”功能中角色介绍的行对象数据
public class CreatureIntroduction {
    private StringProperty name;
    private StringProperty hp;
    private StringProperty attack;
    private StringProperty defense;
    private StringProperty critRate;
    private StringProperty distance;
    private StringProperty speed;
    private StringProperty special;

    public static ObservableList<CreatureIntroduction> factory()
    {
        Field field=new Field();
        ObservableList<CreatureIntroduction> data= FXCollections.observableArrayList();

        ArrayList<Huluwa> arrayList=new ArrayList<>();
        for(int i=0;i<7;i++) arrayList.add(new Huluwa(i+1,field));
        Grandpa grandpa=new Grandpa(field);
        Scorpion scorpion=new Scorpion(field);
        Snake snake=new Snake(field);
        Sodier sodier=new Sodier(field);

        for(int i=0;i<7;i++)
        {
            switch (i+1)
            {
                case 1:data.add(new CreatureIntroduction("大娃",arrayList.get(i),"攻击和生命值较高，暴击可造成3~4倍伤害"));break;
                case 2:data.add(new CreatureIntroduction("二娃",arrayList.get(i),"善于窥破敌人弱点，战斗中能够不断提高暴击率"));break;
                case 3:data.add(new CreatureIntroduction("三娃",arrayList.get(i),"防御力高，暴击时提高防御，并造成防御力百分比额外伤害"));break;
                case 4:data.add(new CreatureIntroduction("四娃",arrayList.get(i),"暴击时会对中了Debuff的敌人造成额外伤害"));break;
                case 5:data.add(new CreatureIntroduction("五娃",arrayList.get(i),"暴击时会给敌人造成破甲Debuff"));break;
                case 6:data.add(new CreatureIntroduction("六娃",arrayList.get(i),"身法飘逸，拥有高速和远距离攻击"));break;
                case 7:data.add(new CreatureIntroduction("七娃",arrayList.get(i),"善于用毒，暴击时会给敌人造成中毒Debuff"));break;
            }
        }

        data.add(new CreatureIntroduction("爷爷",grandpa,"爷爷没有攻击能力，但能对孩子们加血治疗"));
        data.add(new CreatureIntroduction("蝎子精",scorpion,"具有高生命值高攻高防，攻击吸血，但速度较慢"));
        data.add(new CreatureIntroduction("蛇精",snake,"高速高攻，远距离攻击，但防御较低"));
        data.add(new CreatureIntroduction("小喽啰",sodier,"中等程度的属性，比较均匀"));

        return data;
    }

    public CreatureIntroduction(String name, Creature creature, String special)
    {
        this.name=new SimpleStringProperty(name);
        this.hp=new SimpleStringProperty(Integer.toString(creature.getAllHP()));
        this.attack=new SimpleStringProperty(Integer.toString(creature.getAttack()));
        this.defense=new SimpleStringProperty(Integer.toString(creature.getDefense()));
        this.critRate=new SimpleStringProperty(Double.toString(creature.getCritRate()));
        this.distance=new SimpleStringProperty(Integer.toString(creature.getAttackDistance()));
        this.speed=new SimpleStringProperty(Integer.toString(creature.getSpeed()));
        this.special=new SimpleStringProperty(special);
    }


    public StringProperty getName(){return name;}
    public StringProperty getHp(){return hp;}
    public StringProperty getAttack(){return attack;}
    public StringProperty getDefense(){return defense;}
    public StringProperty getCritRate(){return critRate;}
    public StringProperty getDistance(){return distance;}
    public StringProperty getSpeed(){return speed;}
    public StringProperty getSpecial(){return special;}
}
