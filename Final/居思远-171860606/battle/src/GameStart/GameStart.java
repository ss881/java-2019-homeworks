package GameStart;

import Map.Map;
import Creature.*;
import Formation.*;

public class GameStart{

    public void gameStart(Hulu []hulu,Grandpa grandpa,Snake snake,Scorpion scorpion,Monster []monster,Formation formation)
    {
        try{
            int n = 0;
            grandpa.place(0, 7);
            snake.place(14, 7);
            scorpion.place(14, 6);

            Circle c1 = new Circle(hulu[0]);
            Circle c2 = new Circle(monster[0]);
            for (int jk = 0; jk < 7; jk++)
                hulu[jk].place(c1.returnX(jk), c1.returnY(jk));
            for (int jk = 0; jk < 7; jk++)
                monster[jk].place(c2.returnX(jk), c2.returnY(jk));
        }catch (Exception e) {
        e.printStackTrace();
    }

    }

}
