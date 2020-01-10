import creature.*;
import factory.*;
import gamemaster.GameDirector;

public class MainMethod {
    public static void main(String []args){
        GameDirector gameDirector=new GameDirector();
        gameDirector.gamePlay();
    }
}
