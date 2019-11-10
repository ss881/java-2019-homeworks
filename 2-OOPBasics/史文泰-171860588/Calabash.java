import SWT.*;
/**
 * 葫芦娃类
 */
public class Calabash {
    private int id;
    private Color color;
    private Rank rank;
    private int position;

    public Calabash(){}
    /**
     * 不要使用id进行初始化，葫芦娃出生时应该给予颜色和排行
     * @param color
     * @param rank
     * @param image
     */
    public Calabash(Color color, Rank rank){
        this.color = color;
        this.rank = rank;
        this.id = rank.ordinal();
    }
    public int getId(){
        return this.id;
    }
    public Color getColor(){
        return this.color;
    }
    public Rank getRank(){
        return this.rank;
    }
    public int getPosition(){
        return this.position;
    }
    public void setPosition(int position){
        this.position = position;
    }

    /**
     * 报数
     * @param isColor   true时按颜色报数，否则按排行报数
     */
    public void shout(boolean isColor){
        if(isColor){
            System.out.print(this.color + " ");
        }else{
            System.out.print(this.rank + " ");
        }
    }

    /**
     * 葫芦娃移动
     * @param movePos   移动到的位置
     */
    public void move(int movePos){
        if(this.position != -1){
            System.out.println(this.rank + ": " + this.position + "->" + movePos);
        }
        this.position = movePos;
    }
}
