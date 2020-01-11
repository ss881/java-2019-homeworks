package formation;

import creature.*;
import location.Location;

public class YanYue extends Formation{
    public YanYue(){
        super(7,9);
        Creature wannabes[]=new Creature[18];
        for(int i=0;i<18;i++)
            wannabes[i]=generator.generate("Wannabe");
        Creature leader=generator.generate("ScorpionEssence");
        leader.setLocation(0,4);
        this.all[leader.getLocation().getX()][leader.getLocation().getY()]=leader;
        Location locations[]={new Location(6,0),new Location(5,1),
            new Location(3,1),new Location(4,2),new Location(2,2),
            new Location(0,3),new Location(1,3),new Location(3,3),
            new Location(1,4),new Location(3,4),new Location(0,5),
            new Location(1,5),new Location(3,5),new Location(2,6),
            new Location(4,6),new Location(3,7),new Location(5,7),new Location(6,8)};
        for(int i=0;i<18;i++){
            wannabes[i].setLocation(locations[i].getX(), locations[i].getY());
            this.all[wannabes[i].getLocation().getX()][wannabes[i].getLocation().getY()]=wannabes[i];
        }
    }
}