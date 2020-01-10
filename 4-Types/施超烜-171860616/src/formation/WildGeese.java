package formation;

import creature.*;
import location.Location;

public class WildGeese extends Formation{
    public WildGeese(){
        super(5,5);
        Creature wannabes[]=new Creature[4];
        for(int i=0;i<4;i++)
            wannabes[i]=generator.generate("Wannabe");
        Creature leader=generator.generate("ScorpionEssence");
        leader.setLocation(2, 2);
        this.all[leader.getLocation().getX()][leader.getLocation().getY()]=leader;
        Location locations[]={new Location(0,4),new Location(1,3),
            new Location(3,1),new Location(4,0)};
        for(int i=0;i<4;i++){
            wannabes[i].setLocation(locations[i].getX(), locations[i].getY());
            this.all[wannabes[i].getLocation().getX()][wannabes[i].getLocation().getY()]=wannabes[i];
        }
    }
}