package creatures;

public class Grandpa extends HuluwaSide implements Cheerer {
    public Grandpa(){super();}
    public Grandpa(String name){super(name);}
    public Grandpa(String name, int x, int y){super(name, x, y);}
    public void cheer(){
        System.out.println(name+ "：葫芦娃加油！");
    }

}
