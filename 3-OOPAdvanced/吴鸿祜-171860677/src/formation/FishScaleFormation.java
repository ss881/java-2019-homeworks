package formation;
import organism.*;
import position.Position;

public class FishScaleFormation extends Formation{
    public FishScaleFormation() {
        super(7, 7);

        Organism army[]=new Organism[18];
        Organism leader=new Scorpion();
        for(int i=0;i<18;i++) army[i]=new Soldier();

        Position positions[] = new Position[11];
        positions[0] = new Position(1, 1);
        positions[1] = new Position(2, 1);
        positions[2] = new Position(3, 1);
        positions[3] = new Position(4, 1);
        positions[4] = new Position(2, 2);
        positions[5] = new Position(3, 2);
        positions[6] = new Position(4, 2);
        positions[7] = new Position(2,3);
        positions[8] = new Position(3,3);
        positions[9] = new Position(2,4);

        positions[10]=new Position(3,0);


        leader.setPosition(positions[10]);
        this.organisms[positions[10].getX()][positions[10].getY()] = leader;
        for (int i = 0; i <10; i++)
        {
            army[i].setPosition(positions[i]);
            this.organisms[positions[i].getX()][positions[i].getY()] = army[i];
            // System.out.println(organisms[positions[i].getX()][positions[i].getY()].getName());
        }
    }
}
