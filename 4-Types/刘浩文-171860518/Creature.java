public class Creature {
    public String name;
    public Position pos;
    //char tag;
    Creature(){pos=new Position();name="";}
    Creature(String given_name){name=given_name;}
    Creature(String given_name,Position given_pos){name=given_name;
        pos=new Position();pos.x_pos=given_pos.x_pos;pos.y_pos=given_pos.y_pos;}
}
