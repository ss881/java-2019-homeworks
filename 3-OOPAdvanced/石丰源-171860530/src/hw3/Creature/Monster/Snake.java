package hw3.Creature.Monster;
import hw3.Creature.CheerUp;
public class Snake extends Monster implements CheerUp {
    public Snake(){
        super("蛇精");
    }
    @Override
    public void cheerUp() {
        System.out.println("蛇精: 小的们给我上，把这群家伙都给我抓起来!!!");
    }
}
