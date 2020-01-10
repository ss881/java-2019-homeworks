package game;

import java.io.IOException;

final class Snake extends Conductor {
    Snake() {
        super("蛇精");
        we.add(new Monster("蝎子精", 1000, 10, "file:@picture/蝎子精.jpg"));  //没错这必须是一群攻低血厚的辣鸡 ：）
        we.add(new Monster("蝙蝠精", 800, 10, "file:@picture/蝙蝠精.jpg"));
        we.add(new Monster("蛤蟆精", 800, 10, "file:@picture/蛤蟆精.jpg"));
        we.add(new Monster("蜈蚣精", 800, 10, "file:@picture/蜈蚣精.jpg"));
        we.add(new Monster("天牛精", 800, 10, "file:@picture/天牛精.jpg"));
        we.add(new Monster("黄蜂精", 800, 10, "file:@picture/黄蜂精.jpg"));
        we.add(new Monster("野猪精", 800, 10, "file:@picture/野猪精.jpg"));
        we.add(new Monster("蜘蛛精", 800, 10, "file:@picture/蜘蛛精.jpg"));
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
                talk("加油！小的们！【蛇精为妖精们助威，全体能量值随机上升】");
                break;
            case 1:
                talk("猥琐发育，别浪！【蛇精为妖精们助威，全体能量值随机上升】");
                break;
            case 2:
                talk("不要叫我大王！【蛇精为妖精们助威，全体能量值随机上升】");
                break;
            case 3:
                talk("要叫我女王大人！【蛇精为妖精们助威，全体能量值随机上升】");
                break;
            case 4:
                talk("如意如意，顺我心意！【蛇精为妖精们助威，全体能量值随机上升】");
                break;
        }
    }
}
