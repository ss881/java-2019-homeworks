package Formation;

public enum Type{
    Crane("鹤翼", false, false), Goose("雁行", false, false), Yoke("衡轭", false, false), Fish("鱼鳞", false, false), Square("方门", false, false), Moon("偃月", false, false), Arrow("锋矢", false, false), Snake("长蛇", false, false);

    private String name;
    private boolean exist;
    private boolean character;  //适不适合葫芦娃

    private Type(String n, boolean b, boolean c){
        name = n;
        exist = b;
        character = c;
    }

    @Override
    public String toString() {
        return "阵型:" + name + " " + exist + " " + character;
    }

    public boolean isExist(){
        return exist;
    }

    public void setExist(boolean exist){
        this.exist = exist;
    }

    public String getName(){
        return name;
    }

    public void setCharacter(boolean c){
        character = c;
    }

    public boolean checkCharacter(){
        return character;
    }

}