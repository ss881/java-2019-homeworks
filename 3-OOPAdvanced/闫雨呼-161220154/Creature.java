//生命体类
class Creature{
    //生命体名字以及坐标
    private String name;
    int x;
    int y;
    //生命体是否为好的一方（葫芦娃与爷爷一方）
    boolean badGuy;
    Creature(String name,boolean badGuy){
        this.name=name;
        x=-1;
        y=-1;
        this.badGuy=badGuy;
    }
    //生命体报出自己的名字
    void solute(){
        System.out.print(name);
    }
    //生命体查看[a,b]位置是否可达
    boolean canMove(Tile[][] battleField,int a,int b){
        if(x==a&&y==b)
            return false;
        return battleField[a][b].creature == null;
    }
    //生命体移动到[x,y]位置
    void runTo(Tile[][] battleField,int x,int y){
        if(this.x==x&&this.y==y)
            return;
        if(this.x!=-1)
            battleField[this.x][this.y].creature=null;
        battleField[x][y].creature=this;
        this.x=x;
        this.y=y;
    }
}