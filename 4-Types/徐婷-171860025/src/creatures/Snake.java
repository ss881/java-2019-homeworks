package creatures;

public class Snake extends SnakeSide implements Cheerer {
    public Snake(){super();}
    public Snake(String name){super(name);}
    public Snake(String name, int x, int y){super(name, x, y);}
    public void cheer(){
        System.out.println(name + ": 妖怪加油！");
    }
}
