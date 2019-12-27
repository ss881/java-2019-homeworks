public class GourdEva extends Creature {
    private String color, name;
    private int rank, position;

    public GourdEva(int rank, String color, String name) {
        super();
        this.rank = rank;
        this.color = color;
        this.name = name;
    }

    public void saycolor() {
        System.out.print(color);
    }

    public void saymyname() {
        System.out.print(name);
    }

    public int getrank() {
        return rank;
    }

    public void setPositionWithOutTalk(int newPosition) {
        position = newPosition;
    }

    public void setPositionAndTalk(int newPosition) {
        if (newPosition == position)
            return;
        System.out.printf("%s：%d->%d\n", name, position + 1, newPosition + 1);
        position = newPosition;
    }

    @Override
    public String describe() {
        return color.substring(0, 1);
    }
}
