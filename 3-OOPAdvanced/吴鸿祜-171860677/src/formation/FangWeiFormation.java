package formation;
import organism.*;
import position.Position;

public class FangWeiFormation extends  Formation{
    public FangWeiFormation() {
        super(7, 7);

        Organism army[]=new Organism[18];
        Organism leader=new Scorpion();
        for(int i=0;i<18;i++) army[i]=new Soldier();

        Position positions[] = new Position[8];
        positions[0] = new Position(2, 1);
        positions[1] = new Position(1, 2);
        positions[2] = new Position(4, 1);
        positions[3] = new Position(5, 2);
        positions[4] = new Position(2, 3);
        positions[5] = new Position(4, 3);
        positions[6] = new Position(3, 4);

        positions[7] = new Position(3,0);

        leader.setPosition(positions[7]);
        this.organisms[positions[7].getX()][positions[7].getY()] = leader;
        for (int i = 0; i <7; i++) {
            army[i].setPosition(positions[i]);
            this.organisms[positions[i].getX()][positions[i].getY()] = army[i];
            // System.out.println(organisms[positions[i].getX()][positions[i].getY()].getName());
        }
    }
}
