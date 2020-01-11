enum COLOR
{
    RED,ORANGE,YELLOW,GREEN,VIOLET,BLUE,PURPLE
}
public class Huluboy extends Creature {
    COLOR color;
    Huluboy(){color=COLOR.RED;}
    Huluboy(String given_name,Position given_pos,COLOR i){name=given_name;
    pos.x_pos=given_pos.x_pos;pos.y_pos=given_pos.y_pos;color=i;
    //System.out.print("hulu created with x="+pos.x_pos+" y="+pos.y_pos+'\n');
    }
}
