package formation;

import creature.*;
import location.Location;

public class FangYan extends Formation{
    public FangYan(){
        super(5,5);
        Creature wannabes[]=new Creature[7];
        for(int i=0;i<7;i++)
            wannabes[i]=generator.generate("Wannabe");
        Creature leader=generator.generate("ScorpionEssence");
        leader.setLocation(0,2);
        this.all[leader.getLocation().getX()][leader.getLocation().getY()]=leader;
        Location locations[]={new Location(1,1),new Location(2,0),
            new Location(3,1),new Location(4,2),new Location(3,3),
            new Location(2,4),new Location(1,3),new Location(1,2)};
        for(int i=0;i<7;i++){
            wannabes[i].setLocation(locations[i].getX(), locations[i].getY());
            this.all[wannabes[i].getLocation().getX()][wannabes[i].getLocation().getY()]=wannabes[i];
        }
    }
}