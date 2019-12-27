public class Snake extends Leaders {

    Snake() {
        this.name = "蛇精";
        this.pos_y = -5;
        this.center_x = 5;

        //创建蝎子精，默认为阵型中的(0,0)位置
        kids.add(new Scorpion(0));

        //创建喽啰
        for (int i = 1; i < 7; i++) {
            //创建一个喽啰
            kids.add(new Bandit(i));
        }
    }

}
