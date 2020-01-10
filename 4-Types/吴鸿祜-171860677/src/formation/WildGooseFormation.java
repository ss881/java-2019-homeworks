package formation;
import organism.Organism;
import position.Position;

public class WildGooseFormation extends Formation {
    public WildGooseFormation()
    {
        super(7,7);

        Organism army[]=new Organism[18];
        Organism leader=generator.next("Scorpion");
        for(int i=0;i<18;i++) army[i]=generator.next("Soldier");

        //暂时未考虑不定数目的士兵，暂定为6个
        Position positions[] = new Position[7];
        positions[0] = new Position(0, 6);
        positions[1] = new Position(1, 5);
        positions[2] = new Position(2, 4);
        positions[3] = new Position(3, 3);
        positions[4] = new Position(4, 2);
        positions[5] = new Position(5, 1);

        positions[6] = new Position(6, 0);

        leader.setPosition(positions[6]);
        this.organisms[positions[6].getX()][positions[6].getY()] = leader;
        for (int i = 0; i < 6; i++)
        {
            army[i].setPosition(positions[i]);
            this.organisms[positions[i].getX()][positions[i].getY()] = army[i];
            // System.out.println(organisms[positions[i].getX()][positions[i].getY()].getName());
        }
    }
}
