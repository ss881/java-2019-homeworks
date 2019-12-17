package ground;
import creatures.*;


public class Cell{
    private String name;
    private State state;
    public Cell(){
        name="";
        state=State.DEAD;
    }
    public void setNameAndState(String n, State s){
        if(s!=State.DEAD) {
            name = n;
            state = s;
        }

    }
    public String getName(){
        if(state!=State.DEAD){
            return name;
        }
        else return "    ";
    }
}