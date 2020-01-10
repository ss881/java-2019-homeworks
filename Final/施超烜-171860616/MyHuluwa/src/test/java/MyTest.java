import controller.*;
import creature.*;
import org.junit.Test;

public class MyTest {
    /*由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试*/
    @Test
    public void testAttack(){//测试攻击
        System.out.println("由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试");
        Creature a=new CalabashBrother(1,1,1);
        Creature b=new Wannabe();
        System.out.println("现在进行攻击测试");
        System.out.println("二娃当前攻击力："+Integer.toString(a.getAttack())+",小喽啰目前血量："+Integer.toString(b.getCurrentHP())+",小喽啰目前防御力"+Integer.toString(b.getDefence()));
        a.attack(b,null);
        System.out.println("二娃攻击小喽啰后：");
        System.out.println("小喽啰目前血量："+Integer.toString(b.getCurrentHP()));
    }
    @Test
    public void test1Skill(){//测试大娃技能
        System.out.println("由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试");
        Creature a=new CalabashBrother(0,1,1);
        System.out.println("现在进行大娃技能测试：大娃力大无穷，可以无限增加攻击力（5）");
        System.out.println("大娃当前攻击力："+Integer.toString(a.getAttack()));
        a.skill(null,null);
        System.out.println("大娃使用技能后");
        System.out.println("大娃现在攻击力："+Integer.toString(a.getAttack()));
    }
    @Test
    public void test2Skill(){//测试二娃技能
        System.out.println("由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试");
        Creature a=new CalabashBrother(1,1,1);
        System.out.println("现在进行二娃技能测试：二娃千里眼顺风耳，能够发现敌人弱点使下一次攻击暴击");
        System.out.println("二娃当前暴击可能："+Boolean.toString(a.isCritical()));
        a.skill(null,null);
        System.out.println("二娃使用技能后");
        System.out.println("二娃现在暴击可能："+Boolean.toString(a.isCritical()));
    }
    @Test
    public void test3Skill(){//测试三娃技能
        System.out.println("由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试");
        Creature a=new CalabashBrother(2,1,1);
        System.out.println("现在进行三娃技能测试：三娃钢筋铁骨，拥有最高的防御力，并且能够恢复自己血量（10）");
        a.lostBlood(20);
        System.out.println("三娃当前血量："+Integer.toString(a.getCurrentHP()));
        a.skill(null,null);
        System.out.println("三娃使用技能后");
        System.out.println("三娃现在血量："+Integer.toString(a.getCurrentHP()));
    }
    @Test
    public void test45Skill(){//测试四娃，五娃技能
        System.out.println("由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试");
        MyMap map=new MyMap(10,20);
        Creature a=new CalabashBrother(3,-1,-1);
        Creature b=new CalabashBrother(4,-1,-1);
        Creature c=new Wannabe();
        map.moveCreatureTo(a,2,2);
        map.moveCreatureTo(b,2,4);
        map.moveCreatureTo(c,2,3);
        System.out.println("现在进行四娃和五娃技能测试：四娃喷火，五娃喷水，能够在自己周围造成范围伤害（30）");
        System.out.println("四娃目前位于（2,2），五娃位于（2,4），小喽啰位于（2,3）");
        System.out.println("小喽啰当前血量："+Integer.toString(c.getCurrentHP()));
        a.skill(map,null);
        System.out.println("四娃使用技能后");
        System.out.println("小喽啰当前血量："+Integer.toString(c.getCurrentHP()));
        b.skill(map,null);
        System.out.println("五娃使用技能后");
        System.out.println("小喽啰当前血量："+Integer.toString(c.getCurrentHP()));
    }
    @Test
    public void test6Skill(){//测试六娃技能
        System.out.println("由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试");
        Creature a=new CalabashBrother(5,1,1);
        System.out.println("现在进行六娃技能测试：六娃能隐身，隐身后攻击力翻倍（30）但防御力清零（0）");
        System.out.println("六娃目前攻击力："+Integer.toString(a.getAttack())+",防御力："+Integer.toString(a.getDefence()));
        a.skill(null,null);
        System.out.println("六娃使用技能后");
        System.out.println("六娃目前攻击力："+Integer.toString(a.getAttack())+",防御力："+Integer.toString(a.getDefence()));
    }
    @Test
    public void test7Skill(){//测试七娃技能
        System.out.println("由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试");
        Creature a=new CalabashBrother(6,-1,-1);
        Creature b=new Wannabe();
        Creature c=new Wannabe();
        MyMap map=new MyMap(10,20);
        map.moveCreatureTo(a,1,1);
        map.moveCreatureTo(b,1,5);
        map.moveCreatureTo(c,5,1);
        System.out.println("现在进行七娃技能测试：七娃释放宝贝葫芦中的力量，对敌方所有敌人造成伤害(15)");
        System.out.println("七娃目前位于（1,1），小喽啰甲位于（1,5），小喽啰乙位于（5,1）");
        System.out.println("小喽啰甲当前血量："+Integer.toString(b.getCurrentHP())+",小喽啰乙当前血量"+Integer.toString(c.getCurrentHP()));
        a.skill(map,null);
        System.out.println("七娃使用技能后");
        System.out.println("小喽啰甲当前血量："+Integer.toString(b.getCurrentHP())+",小喽啰乙当前血量"+Integer.toString(c.getCurrentHP()));
    }
    @Test
    public void testGSkill(){//测试爷爷技能
        System.out.println("由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试");
        Creature a=new Grandfather();
        a.lostBlood(20);
        Creature b=new CalabashBrother(0,-1,-1);
        b.lostBlood(10);
        Creature c=new CalabashBrother(1,-1,-1);
        MyMap map=new MyMap(10,20);
        map.moveCreatureTo(a,1,1);
        map.moveCreatureTo(b,1,5);
        map.moveCreatureTo(c,5,1);
        System.out.println("现在进行爷爷技能测试：爷爷能够为所有友方单位回复生命值(15)");
        System.out.println("爷爷目前位于（1,1），大娃位于（1,5），二娃乙位于（5,1）");
        System.out.println("爷爷当前血量："+Integer.toString(a.getCurrentHP())+"，大娃当前血量："+Integer.toString(b.getCurrentHP())+"，二娃当前血量："+Integer.toString(c.getCurrentHP()));
        a.skill(map,null);
        System.out.println("爷爷使用技能后");
        System.out.println("爷爷当前血量："+Integer.toString(a.getCurrentHP())+"，大娃当前血量："+Integer.toString(b.getCurrentHP())+"，二娃当前血量："+Integer.toString(c.getCurrentHP()));
    }
    @Test
    public void testWSkill(){//测试小喽啰技能
        System.out.println("由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试");
        Creature a=new Wannabe();
        a.lostBlood(20);
        System.out.println("现在进行小喽啰技能测试：最垃圾的小喽啰，只能无能狂怒为自己回血（5）");
        System.out.println("小喽啰当前血量："+Integer.toString(a.getCurrentHP()));
        a.skill(null,null);
        System.out.println("小喽啰使用技能后");
        System.out.println("小喽啰当前血量："+Integer.toString(a.getCurrentHP()));
    }
    @Test
    public void testScSkill(){//测试蝎子精技能
        System.out.println("由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试");
        Creature a=new ScorpionEssence();
        System.out.println("现在进行蝎子精技能测试：蝎子精头脑简单四肢发达，能够发动无上妖力造成3倍暴击");
        System.out.println("蝎子精当前暴击可能："+Boolean.toString(a.isCritical()));
        a.skill(null,null);
        System.out.println("蝎子精使用技能后");
        System.out.println("蝎子精现在暴击可能："+Boolean.toString(a.isCritical()));
    }
    @Test
    public void testSnSkill(){//测试蛇精技能
        System.out.println("由于代码中Image在获取图片来源时使用相对路径，在进行测试时需要将代码中有关Image获取图片的代码注释掉，避免出现错误无法测试");
        Creature a=new SnakeEssence();
        Creature b=new Wannabe();
        Creature c=new CalabashBrother(0,-1,-1);
        b.lostBlood(20);
        MyMap map=new MyMap(10,20);
        map.moveCreatureTo(a,1,1);
        map.moveCreatureTo(b,1,5);
        map.moveCreatureTo(c,5,1);
        System.out.println("现在进行蛇精技能测试：蛇精释放妖风，对所有地方单位造成伤害(10)，所有友方单位进行恢复(10)");
        System.out.println("蛇精目前位于（1,1），小喽啰位于（1,5），大娃位于（5,1）");
        System.out.println("蛇精当前血量："+Integer.toString(a.getCurrentHP())+"，小喽啰当前血量："+Integer.toString(b.getCurrentHP())+"，大娃当前血量："+Integer.toString(c.getCurrentHP()));
        a.skill(map,null);
        System.out.println("蛇精使用技能后");
        System.out.println("蛇精当前血量："+Integer.toString(a.getCurrentHP())+"，小喽啰当前血量："+Integer.toString(b.getCurrentHP())+"，大娃当前血量："+Integer.toString(c.getCurrentHP()));
    }
}
