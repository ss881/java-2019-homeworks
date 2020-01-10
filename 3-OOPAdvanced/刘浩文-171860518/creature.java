public class Creature {
    public String name;
    public Position pos;
    public char tag;
    Creature(){pos=new Position();name="";tag='_';}
    Creature(String given_name,char given_tag){name=given_name;tag=given_tag;}
    Creature(String given_name,char given_tag,Position given_pos){name=given_name;tag=given_tag;
        pos=new Position();pos.x_pos=given_pos.x_pos;pos.y_pos=given_pos.y_pos;}
}
