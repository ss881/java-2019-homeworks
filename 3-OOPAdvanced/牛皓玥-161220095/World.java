public class World {
    static int total_formation_num = 8;

    public static void main(String[] args ) {
        System.out.print("【系统】 人物及地图初始化中……\n");
        Map map = new Map();                        //创建地图
        GrandPa grandPa = new GrandPa();            //创建爷爷
        Snake snake = new Snake();                  //创建蛇精

        System.out.print("【爷爷】 “葫芦娃列阵！”\n");
        //葫芦娃产生阵型
        grandPa.genernate_formation(3);
        //蝎子精和小喽啰产生阵型
        System.out.print("【系统】 敌方列阵开始……\n");
        snake.genernate_formation(snake.random_choose_formation(total_formation_num));

        //双方将阵型放入地图
        System.out.print("【系统】 对阵开始!" + grandPa.formation.name + " VS " + snake.formation.name + "\n");
        grandPa.put_formation(map);
        snake.put_formation(map);
        //爷爷和蛇精进入地图
        grandPa.put_self(map);
        snake.put_self(map);
        //第一次打印阵型
        map.printMap();
        //敌方变换阵型
        System.out.print("【系统】 敌方变换阵型……\n");
        snake.retreat_formation(map);
        snake.genernate_formation(snake.random_choose_formation(total_formation_num));
        System.out.print("【系统】 对阵开始!" + grandPa.formation.name + " VS " + snake.formation.name + "\n");
        snake.put_formation(map);
        //第二次打印阵型
        map.printMap();
    }
}
