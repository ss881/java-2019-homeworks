public class Creature <T> {

    protected T name;
    protected T type;

    public Creature() {
        name = null;
        type = null;
    }

    public T getName() {
        return name;
    }

    public T getType() {
        return type;
    }

}
