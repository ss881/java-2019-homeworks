package gamectrl;
import annotation.Description;

@Description(comment = "游戏的配置文件")
public interface MainConfig {
    //根据屏幕分辨率动态配置长宽
    //int WIDTH=720;
    //int HEIGHT=480;
    int ROW=15;//表示行数，列数根据分辨率计算
    int UI_REFRESH_INTERVAL=(int)(1000/24-1);//UI刷新率/微秒,人眼是24fps
    int CREATURE_INTERVAL=416;//人物活动频率/微秒
    String hint="【空格键】开始/暂停 【L】回放 【Esc】退出";
    String name = "游戏：葫芦娃大战小喽啰";
}
