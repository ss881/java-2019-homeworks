package figure;

public class BasicFigure implements Figure {
    private String name;

    public BasicFigure(){
        this.name = getClass().getSimpleName();
    }

    public BasicFigure(String name){
        this.name = name;
    }

    @Override
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    private boolean removable = false;

    @Override
    public boolean isRemovable() {
        return removable;
    }

    @Override
    public void setRemovable(boolean flag) {
        this.removable = flag;
    }
}
