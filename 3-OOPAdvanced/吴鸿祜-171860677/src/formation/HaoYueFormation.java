package formation;
import organism.*;
import position.Position;

public class HaoYueFormation extends  Formation{
    public HaoYueFormation () {
        super(9,5);

        Organism army[]=new Organism[18];
        Organism leader=new Scorpion();
        for(int i=0;i<18;i++) army[i]=new Soldier();

        Position positions[] = new Position[19];

        positions[0]=new Position(0,4);
        positions[1]=new Position(8,4);
        positions[2]=new Position(1,3);
        positions[3]=new Position(7,3);

        int j=1;
        for(int i=4;i<=10;i++) positions[i]=new Position(j++,2);

        j=2;
        for(int i=11;i<=15;i++) positions[i]=new Position(j++,1);

        positions[16]=new Position(3,0);
        positions[17]=new Position(5,0);
        positions[18]=new Position(4,0);

        leader.setPosition(positions[18]);
        this.organisms[positions[18].getX()][positions[18].getY()] = leader;
        for (int i = 0; i < 18; i++) {
            army[i].setPosition(positions[i]);
            this.organisms[positions[i].getX()][positions[i].getY()] = army[i];
            // System.out.println(organisms[positions[i].getX()][positions[i].getY()].getName());
        }
    }
}
