package Unit;

import Ground.Ground;

// final class FollowersSeq {
//     public static String[] HuLuSeq = { "First", "Second", "Third", "Forth", "Fifth", "Sixth", "Seventh" };
//     public static String[] HuLuColor = { "RED", "ORANGE", "YELLOEW", "GREEN", "CYAN", "BLUE", "PURPLE" };
//     public static String[] LouLoSeq = { "喽啰1", "喽啰2", "喽啰3", "喽啰4", "喽啰5", "喽啰6", "喽啰7", "喽啰8", "喽啰9", "喽啰10", "喽啰11",
//             "喽啰12", "喽啰13", "喽啰14", "喽啰15", "喽啰16", "喽啰17", "喽啰18", "喽啰19", "喽啰20" };
// }


// 这是一个从Follower类中继承而来的普通Follower类
// 之所以称作普通，是因为其具有编号这一特点，无论其阵营为正还是反
// 葫芦娃和喽啰都可以用某种序号来索引
public class NormalFollowers extends Follower {
    int Id;
    String color;
    // 这里的color并不算是normalfollower的共性，只是葫芦娃的共性，因此可以说是一个偷懒的败笔
    // 单纯是因为不太想再多写一个HuLuBaby类
    // 可以以后再做进一步的改进

    public NormalFollowers(UnitCommander u, int i, String n, int f, int c, Ground g, String col) {
        super(u, n, f, c, g);
        Id = i;
        color = col;
    }
    // 构造函数

    public String getMyInfo(String grOutlook, boolean myName, boolean myforce, boolean myCond, boolean mySeq,
            boolean myColor) {
        if (myName) {
            // System.out.printf("|%-12s", name);
            grOutlook = grOutlook.concat(String.format("|%-12s", name));
        }
        if (myforce) {
            // System.out.printf("|%-12s", force == 0?"DECENT":"DARK");
            grOutlook = grOutlook.concat(String.format("|%-12s", force == 0 ? "DECENT" : "DARK"));

        }
        if (myCond) {
            // System.out.printf("|%-12d", myCondition);
            grOutlook = grOutlook.concat(String.format("|%-12d", myCondition));

        }
        if (mySeq) {
            if (force == 0) {
                // System.out.printf("|%-12s",FollowersSeq.HuLuSeq[Id]);
                grOutlook = grOutlook.concat(String.format("|%-9s-%2d", name, Id));

            } else {
                grOutlook = grOutlook.concat(String.format("|%-9s-%2d", name, Id));
                // System.out.printf("|%-10s%2d","Follower-", Id);
            }
        }
        if (myColor) {
            if (force == 0)
                grOutlook = grOutlook.concat(String.format("|%-12s", color));
            // System.out.printf("|%-12s",FollowersSeq.HuLuColor[Id]);
            else
                grOutlook = grOutlook.concat(String.format("%-13s", "| - - - - - -"));
        }
        return grOutlook;
    }
    // 对返回自身格式化输出信息方法的函数的重载

}