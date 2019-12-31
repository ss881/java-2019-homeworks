package Unit;

import Ground.Ground;

public class Follower extends Unit {
    UnitCommander myCommander;

    public Follower(UnitCommander u, String n, int f, int c, Ground g) {
        super(n, f, c, g);
        myCommander = u;
    }
    // 普通的follower类其实当前用处不大，为以后的功能做一些铺垫
}