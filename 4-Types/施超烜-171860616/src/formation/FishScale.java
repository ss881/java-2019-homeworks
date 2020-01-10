package formation;

import creature.*;
import location.Location;

public class FishScale extends Formation{
    public FishScale(){
        super(7,5);
        Creature wannabes[]=new Creature[9];
        for(int i=0;i<9;i++)
            wannabes[i]=generator.generate("Wannabe");
        Creature leader=generator.generate("ScorpionEssence");
        leader.setLocation(3,3);
        this.all[leader.getLocation().getX()][leader.getLocation().getY()]=leader;
        Location locations[]={new Location(3,0),new Location(4,1),
            new Location(5,2),new Location(6,3),new Location(4,3),
            new Location(3,4),new Location(2,3),new Location(1,2),new Location(0,3)};
        for(int i=0;i<9;i++){
            wannabes[i].setLocation(locations[i].getX(), locations[i].getY());
            this.all[wannabes[i].getLocation().getX()][wannabes[i].getLocation().getY()]=wannabes[i];
        }
    }
}