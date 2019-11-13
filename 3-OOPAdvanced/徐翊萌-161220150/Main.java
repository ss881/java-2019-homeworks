public class Main {
    public static void main(String[] args) {
        CalabashBrother[] queue = {
                new CalabashBrother("大娃", "红色", 1),
                new CalabashBrother("四娃", "绿色", 4),
                new CalabashBrother("二娃", "橙色", 2),
                new CalabashBrother("五娃", "青色", 5),
                new CalabashBrother("三娃", "黄色", 3),
                new CalabashBrother("六娃", "蓝色", 6),
                new CalabashBrother("七娃", "紫色", 7)
        };
        GrandFather grandFather = new GrandFather();
        Controller.BubbleSort(queue);
        Camp<GrandFather> camp0 = new Camp(grandFather, queue, 7);

        Snake snake = new Snake();
        Camp<Snake> camp1 = new Camp(snake);
        Scorpion scorpion = new Scorpion();
        camp1.AddUnit(scorpion);
        for (int i = 0; i < 8; i++) {
            Monster monster = new Monster();
            camp1.AddUnit(monster);
        }

        Battle battle = new Battle(15, 15);

        grandFather.instruction();
        Controller.ChangShe(battle, camp0);
        snake.instruction();
        Controller.HeYi(battle, camp1);
        battle.showBattle();
        grandFather.cheer();
        snake.cheer();
        System.out.println();

        snake.instruction();
        Controller.YanXing(battle, camp1);
        battle.showBattle();
        grandFather.cheer();
        snake.cheer();
        System.out.println();

        snake.instruction();
        Controller.HengE(battle, camp1);
        battle.showBattle();
        grandFather.cheer();
        snake.cheer();
        System.out.println();

        snake.instruction();
        Controller.FengShi(battle, camp1);
        battle.showBattle();
        grandFather.cheer();
        snake.cheer();
        System.out.println();
    }
}
