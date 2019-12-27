package item;

public class BasicItem implements Item {
    protected boolean removal;

    @Override
    public boolean isRemovable() {
        return removal;
    }

    @Override
    public void setRemovable(boolean flag) {
        removal = flag;
    }
}
