package creature;

public class Lackeys extends Creature{
    protected String name;;//暂时用Sting来表示其形象，待图形化时要打印的则是真正的image

    public void setName(String name) {
        this.name = name;
    }
}
