package formation;

import creature.*;
import location.Location;

public class SharpArrow extends Formation{
    public SharpArrow(){
        super(7,9);
        Creature wannabes[]=new Creature[11];
        for(int i=0;i<11;i++)
            wannabes[i]=generator.generate("Wannabe");
        Creature leader=generator.generate("ScorpionEssence");
        leader.setLocation(3,0);
        this.all[leader.getLocation().getX()][leader.getLocation().getY()]=leader;
        Location locations[]={new Location(0,5),new Location(1,3),
            new Location(2,1),new Location(4,1),new Location(5,3),
            new Location(6,5),new Location(3,2),new Location(3,4),
            new Location(3,6),new Location(3,7),new Location(3,8)};
        for(int i=0;i<11;i++){
            wannabes[i].setLocation(locations[i].getX(), locations[i].getY());
            this.all[wannabes[i].getLocation().getX()][wannabes[i].getLocation().getY()]=wannabes[i];
        }
    }
}