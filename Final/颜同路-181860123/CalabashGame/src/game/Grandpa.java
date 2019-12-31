package game;

import java.io.IOException;

final class Grandpa extends Conductor {
    Grandpa() {
        super("爷爷");
        we.add(new Calabash("大娃", 1000, 10, "file:@picture/大娃.jpg"));
        we.add(new Calabash("二娃", 300, 10, "file:@picture/二娃.jpg"));
        we.add(new Calabash("三娃", 800, 30, "file:@picture/三娃.jpg"));
        we.add(new Calabash("四娃", 600, 50, "file:@picture/四娃.jpg"));
        we.add(new Calabash("五娃", 400, 20, "file:@picture/五娃.jpg"));
        we.add(new Calabash("六娃", 400, 30, "file:@picture/六娃.jpg"));
        we.add(new Calabash("七娃", 1000, 100, "file:@picture/七娃.jpg"));
    }

    @Override
    public void cheer() throws IOException {    //Cheer方法，加油助威
        int all = 100;  //需要分配的总能量值
        for (int i = 0;
             i < we.size();
             i++) {
            if (i == we.size() - 1)
                we.get(i).mp += all;
            else {
                int get = Game.getRandom(all - (we.size() - 1 - i));
                we.get(i).mp += get;
                all -= get;
            }
        }

        switch (Game.getRandom(5)) {
            case 0:
                talk("加油！孩子们！【爷爷为葫芦娃们助威，全体能量值随机上升】");
                break;
            case 1:
                talk("稳住，我们能赢！【爷爷为葫芦娃们助威，全体能量值随机上升】");
                break;
            case 2:
                talk("征程就在眼前！【爷爷为葫芦娃们助威，全体能量值随机上升】");
                break;
            case 3:
                talk("恐惧屈于信仰！【爷爷为葫芦娃们助威，全体能量值随机上升】");
                break;
            case 4:
                talk("福禄，万世长存！【爷爷为葫芦娃们助威，全体能量值随机上升】");
                break;
        }
    }
}
