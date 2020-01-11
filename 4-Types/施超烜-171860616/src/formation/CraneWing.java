package formation;

import creature.*;
import location.Location;

public class CraneWing extends Formation{
    public CraneWing(){
        super(7,4);
        Creature wannabes[]=new Creature[6];
        for(int i=0;i<6;i++)
            wannabes[i]=generator.generate("Wannabe");
        Creature leader=generator.generate("ScorpionEssence");
        leader.setLocation(3, 3);
        this.all[leader.getLocation().getX()][leader.getLocation().getY()]=leader;
        Location locations[]={new Location(0,0),new Location(1,1),
            new Location(2,2),new Location(4,2),
            new Location(5,1),new Location(6,0)};
        for(int i=0;i<6;i++){
            wannabes[i].setLocation(locations[i].getX(), locations[i].getY());
            this.all[wannabes[i].getLocation().getX()][wannabes[i].getLocation().getY()]=wannabes[i];
        }
    }
}