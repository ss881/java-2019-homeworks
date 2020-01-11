class Creature{
    protected final String NAME;

    public Creature(){
        NAME = null;
    }

    public Creature(String aName){
        NAME = aName;
    }
    public String getName(){
        return NAME;
    }

}