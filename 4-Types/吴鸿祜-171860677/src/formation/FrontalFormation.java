package formation;
import organism.Organism;
import position.Position;

public class FrontalFormation extends Formation{
    public FrontalFormation () {
        super(7, 7);

        Organism army[]=new Organism[18];
        Organism leader=generator.next("Scorpion");
        for(int i=0;i<18;i++) army[i]=generator.next("Soldier");

        Position positions[] = new Position[12];

        positions[0] = new Position(2, 1);
        positions[1] = new Position(1, 2);
        positions[2] = new Position(3, 0);
        positions[3] = new Position(0, 3);
        positions[4] = new Position(3, 1);
        positions[5] = new Position(4, 1);
        positions[6] = new Position(5, 2);
        positions[7] = new Position(6, 3);
        positions[8] = new Position(3, 2);
        positions[9] = new Position(3, 3);
        positions[10] = new Position(3, 4);
        positions[11] = new Position(3, 0);

        leader.setPosition(positions[11]);
        this.organisms[positions[11].getX()][positions[11].getY()] = leader;
        for (int i = 0; i < 11; i++) {
            army[i].setPosition(positions[i]);
            this.organisms[positions[i].getX()][positions[i].getY()] = army[i];
            // System.out.println(organisms[positions[i].getX()][positions[i].getY()].getName());
        }
    }
}
