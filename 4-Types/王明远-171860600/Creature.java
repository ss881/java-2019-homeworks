public abstract class Creature{
    private Identity id;//this val tells the  difference between different creature 
    int current_space_x=-1;// let the start coordinate be (-1,-1)
    int current_space_y=-1;
    Creature()
    {
        id=Identity.Unkown;
    }
    Creature(Identity initial_id)
    {
        id=initial_id;
    }

    void born(Identity initail_id)// in order to simulate the born of creature
    {
        id=initail_id;
    }

    Identity get_id()// return id
    {
        return id; 
    }
    void rush_to_somespace(int x,int y)// go to pointed place
    {
        current_space_x=x;
        current_space_y=y;
    }
    abstract void follow_orders(Identity id,int x,int y);// follow orders by id which lets creature go to some space
    abstract void provoke();// provoke when get order from the enemy
    abstract void follow_orders(Identity id,Creature creature);
    public int get_current_x()// report current coordinate_x
    {
        return current_space_x;
    }
    public int get_current_y()// report current coordinate_y
    {
        return current_space_y;
    }
}