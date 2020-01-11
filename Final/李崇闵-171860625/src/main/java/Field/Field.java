package Field;

import Creature.Creature;

public class Field {
    private Creature[][]ground;
    public Field(){
        ground=new Creature[20][10];
        for(int i=0;i<20;i++)
            for(int j=0;j<10;j++) {
                ground[i][j]=new Creature();
                ground[i][j]=null;
            }
    }
    public void addCreature(Creature creature, int x, int y){
        ground[x][y]=creature;
    }
    public synchronized void moveCreature(Creature creature, int x, int y){
        if(creature.isAlive()){
            ground[creature.getX()][creature.getY()]=null;
            ground[x][y]=creature;
        }
    }
    public synchronized Creature getCreature(int i, int j){
        if(i>=0&&i<=19&&j>=0&&j<=9)
            return ground[i][j];
        else
            return null;
    }
    public void setNull(int i,int j){
        ground[i][j]=null;
    }

}
