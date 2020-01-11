package history;

public class Action{ //保存战斗动作
    private int type;//0表示移动，1表示攻击，2表示技能
    private int[] start;//攻击者
    private int[] end;//被攻击者
    public Action(int type, int[] start, int[] end){
        this.type = type; //是攻击还是移动
        this.start = start;
        this.end = end;
    }
    public int getType()
    {
        return this.type;
    }
    public int getStartX(){
        return start[0];
    }
    public int getStartY(){
        return start[1];
    }
    public int getEndX() {
        return end[0];
    }
    public int getEndY(){
        return end[1];
    }
}
