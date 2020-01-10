package myfinal;
//import java.util.*;

public class Monsters extends Organism{

    int evil;   //怪物有邪恶的心
    public Monsters(MyMap mypos,String myname) {
        super(mypos,myname);
        evil = 1;
    }
}
    