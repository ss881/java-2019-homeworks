package cn.whh.talk;
import cn.whh.creature.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class Talking extends Label {
    private static String[] grandPaTalking={
            "孩子们加油啊！",
            "孩子们别怕",
            "爷爷帮你们疗伤",
            "打死那些坏人!"
    };

    private static String[] huluwaTalking={
            "兄弟们，开冲！",
            "奥利给！",
            "俺还是个孩子",
            "那个蝎子一看就不是好人",
            "杀！！！",
            "为了爷爷，冲啊！",
            "可恶的蛇精!",
            "区区一些喽啰",
            "打完这一仗我就回老家"
    };

    private static String[] snakeTalking={
            "受死吧！",
            "葫芦娃们一定很美味",
            "大王冲啊！",
            "士兵们给我杀!",
            "我好看吗？"
    };

    private static String[] scorpionTalking={
            "俺要把你们全宰了",
            "面对恐惧的办法就是面对它",
            "一个能打的都没有",
            "格杀勿论！",
            "我才是最强的",
            "一分钟内把你们都撒了"
    };

    private static String[] sodilerTalking={
            "俺要杀了你们",
            "俺虽然弱，但不是炮灰",
            "兄弟们冲！",
            "杀一个就赚了",
            "为了大王!冲！"
    };

    public Talking(int i,int j,Creature creature,double display,double rand)
    {
        super();
        this.setLayoutX(77*j);
        this.setLayoutY(60*i);
        this.setTextFill(Color.YELLOW);

        String text=null;
        this.setText("");
        if(creature.isCritical()) {text=criticalTalking(creature);this.setTextFill(Color.ORANGE);}
        else if(creature.getDebuff()==Debuff.Poison&&creature.getDebuffTime()>=2) text="可恶我中毒了！";
        else if(creature.getDebuff()==Debuff.Broken&&creature.getDebuffTime()>=2) text="啊被破甲了好痛";
        else text=produceTalking(creature,display,rand);
        this.setText(text);

    }

    private String criticalTalking(Creature creature)
    {
        creature.setCritical(false);
        if(creature instanceof Huluwa)
        {
            switch (((Huluwa)creature).getId())
            {
                case 1:return "暴击！杀!";
                case 2:return "我已经看到你的弱点";
                case 3:return "固若金汤!";
                case 4:return "趁你病，拿你命";
                case 5:return "被撕开防御的感觉如何?";
                case 6:return "杀人于千里之外";
                case 7:return "中毒的滋味如何?";
            }
        }

        else if(creature instanceof Grandpa) return "圣光保佑你";

        else if(creature instanceof Scorpion) return "杀!杀!杀!";

        else if(creature instanceof Snake) return "致命一击!";

        else if(creature instanceof Sodier) return "暴击";

        return null;
    }

    private String produceTalking(Creature creature,double display,double rand)
    {
        /*Random random=new Random(new Date().getTime());
        double display=random.nextDouble();
        double rand=random.nextDouble();
        System.out.println(display+" "+rand );*/
        if(display>0.02) return "";
        if(creature instanceof Huluwa) return huluwaTalking[(int)(rand*huluwaTalking.length)];
        if(creature instanceof Grandpa) return grandPaTalking[(int)(rand*grandPaTalking.length)];
        if(creature instanceof Snake) return snakeTalking[(int)(rand*snakeTalking.length)];
        if(creature instanceof Scorpion) return scorpionTalking[(int)(rand*scorpionTalking.length)];
        if(creature instanceof Sodier) return sodilerTalking[(int)(rand*sodilerTalking.length)];
        return null;
    }

    public static void main(String[] args)
    {
        while (true) {
            int index = (int) (Math.random() * huluwaTalking.length);
            System.out.println(huluwaTalking[index]);
        }
    }
}
