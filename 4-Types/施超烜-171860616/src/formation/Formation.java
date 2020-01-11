package formation;
import creature.*;
import generator.CreatureGenerator;
public class Formation{
    private int length;
    private int width;
    protected Creature all[][];
    protected CreatureGenerator generator=new CreatureGenerator();
    public Formation(int length,int width){
        this.length=length;
        this.width=width;
        all=new Creature[length][width];
        for(int i=0;i<length;i++){
            for(int j=0;j<width;j++){
                all[i][j]=generator.generate("Space");
                all[i][j].setLocation(i, j);
            }
        }
    }
    public Creature[][] getAllCreature(){
        return all;
    }
    public int getLength(){return length;}
    public int getWidth(){return width;}
}