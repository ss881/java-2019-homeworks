package formation;

import creature.*;
import location.Location;

public class Yokes extends Formation{
    public Yokes(){
        super(2,6);
        Creature wannabes[]=new Creature[5];
        for(int i=0;i<5;i++)
            wannabes[i]=generator.generate("Wannabe");
        Creature leader=generator.generate("ScorpionEssence");
        leader.setLocation(0,3);
        this.all[leader.getLocation().getX()][leader.getLocation().getY()]=leader;
        Location locations[]={new Location(1,0),new Location(0,1),
            new Location(1,2),new Location(1,4),new Location(0,5)};
        for(int i=0;i<5;i++){
            wannabes[i].setLocation(locations[i].getX(), locations[i].getY());
            this.all[wannabes[i].getLocation().getX()][wannabes[i].getLocation().getY()]=wannabes[i];
        }
    }
}