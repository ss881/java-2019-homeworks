public class homework4 {
    public static void main(String[] args){
        //String[][] boyName={{"老五","青","5"},{"老三","黄色","3"},{"老大","红色","1"},{"老二","橙色","2"},{"老七","紫色","7"},{"老六","蓝色","6"},{"老四","绿色","4"}};
        String[] boyName = {"老五","老三","老大","老二","老七","老六","老四"};
        String[] enemyName = {"蝎子精","炮灰1号","炮灰2号","炮灰3号","炮灰4号","炮灰5号","炮灰6号","炮灰7号"};

        God god = new God();
        System.out.println("生成世界");
        World<Creature> world = new World();
        System.out.println("生成生物体");
        Boy[] boy = god.createBoy(boyName);//god.createBoy(boyName);//创建七个葫芦娃
        Enemy[] enemy = god.createEnemy(enemyName);//god.createEnemy(enemyName);//创建蝎子精等七个敌人
        Creature grandpa = god.createCreature("爷爷");
        Creature snake = god.createCreature("蛇精");

        System.out.println("葫芦娃站队（长蛇阵型）");
        god.formation.snake(world, boy,1, 1);//葫芦娃站队：乱序的长蛇阵
        System.out.println("葫芦娃排序");
        god.sortY(world, 1,1, 7);//乱序葫芦娃排序
        System.out.println("爷爷移动");
        god.move(world, grandpa, 0, 0);//放置爷爷

        System.out.println("敌人站队（鹤翼阵型）");
        god.formation.wing(world, enemy, 7, 14);//敌人以鹤翼阵型站队
        System.out.println("蛇精移动");
        god.move(world, snake, 0, 14);//放置蛇精
        world.show();//打印对峙局面

        /*System.out.println("敌人变换阵型为雁行");
        god.formation.snake(world, enemy,0,19);
        god.formation.goose(world, enemy, 2, 14);//敌人变化阵型为雁行
        System.out.println("蛇精移动");
        god.move(world, snake, 1, 14);//蛇精移动
        world.show();//打印对峙局面

        System.out.println("敌人变换阵型为衡轭");
        god.formation.snake(world, enemy,0,19);
        god.formation.yoke(world, enemy, 3, 14);//敌人变化阵型为衡轭
        System.out.println("蛇精移动");
        god.move(world, snake, 2, 14);//蛇精移动
        world.show();//打印对峙局面

        System.out.println("敌人变换阵型为方圆");
        god.formation.snake(world,enemy,0,19);
        god.formation.square(world,enemy, 3, 10);//敌人变化阵型为方圆
        System.out.println("蛇精移动");
        god.move(world, snake, 2, 10);//蛇精移动
        world.show();//打印对峙局面

        System.out.println("敌人变换阵型为箭矢");
        god.formation.snake(world, enemy,0,19);
        god.formation.arrow(world, enemy, 3, 14);//敌人变化阵型为箭矢
        System.out.println("蛇精移动");
        god.move(world, snake, 2, 14);//蛇精移动
        world.show();//打印对峙局面
         */
    }

}
