package map;
import java.util.*;
import creature.*;
import formation.*;
import generator.*;
public class Map{
    private int size;
    private Creature [][]map;
    private CreatureGenerator cGenerator=new CreatureGenerator();
    private FormationGenerator fGenerator=new FormationGenerator();
    private SnakeEssence snake;
    private Grandfather grandfather;
    private Formation currentFormation;
    public Map(int size){
        this.size=size;
        map=new Creature[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++)
                map[i][j]=cGenerator.generate("Space");
        }
    }
    private int[] randomArrayGenerator(int n){//生成1-n的的随机排列
        Random rand=new Random();
        int temp=0;
        int[] arrange=new int[n];
        int index=0;
        while(true){
            if(index==n){
                break;
            }
            temp=rand.nextInt(n)+1;
            boolean ifcontain=false;
            for(int i=0;i<arrange.length;i++){
                if(arrange[i]==temp){
                    ifcontain=true;
                }
            }
            if(ifcontain){
                continue;
            }else{
                arrange[index]=temp;
                index++;
            }
        }
        return arrange;
    }
    private void determinFormation(){
        Random rand=new Random();
        int index=rand.nextInt(7)+1;
        String classname=null;
        switch(index){
            case 1:classname="CraneWing";break;
            case 2:classname="WildGeese";break;
            case 3:classname="Yokes";break;
            case 4:classname="FishScale";break;
            case 5:classname="FangYan";break;
            case 6:classname="YanYue";break;
            case 7:classname="SharpArrow";break;
        }
        currentFormation=fGenerator.generate(classname);
    }
    private void arrangeEssence(){
        snake=new SnakeEssence();
        snake.fight(size-1);
        map[0][size-1]=snake;
        Creature all[][]=currentFormation.getAllCreature();
        for(int i=0;i<currentFormation.getLength();i++){
            for(int j=0;j<currentFormation.getWidth();j++){
                Creature temp=null;
                if(all[i][j] instanceof Space){
                    temp=new Space();
                    temp.setLocation(all[i][j].getLocation().getX(), all[i][j].getLocation().getY());
                }else if(all[i][j] instanceof Wannabe){
                    temp=new Wannabe();
                    temp.setLocation(all[i][j].getLocation().getX(), all[i][j].getLocation().getY());
                }else if(all[i][j] instanceof ScorpionEssence){
                    temp=new ScorpionEssence();
                    temp.setLocation(all[i][j].getLocation().getX(), all[i][j].getLocation().getY());
                }
                snake.makeOrder(temp, temp.getLocation().getX()+7, temp.getLocation().getY()+1);
                map[temp.getLocation().getY()][temp.getLocation().getX()]=temp;
            }
        }
    }
    private void arrangeGrandfatherAndCalabash(){
        grandfather=new Grandfather();
        grandfather.fight();
        map[0][0]=grandfather;
        CalabashBrother brothers[]=new CalabashBrother[7];
        for(int i=0;i<7;i++){
            brothers[i]=new CalabashBrother(i+1);
        }
        int[] arrange=randomArrayGenerator(7);
        for(int i=0;i<7;i++){
            grandfather.makeOrder(brothers[arrange[i]-1], 1, i+1);
            map[i+1][1]=brothers[arrange[i]-1];
        }
    }
    private void clean(){
        map=new Creature[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++)
                map[i][j]=cGenerator.generate("Space");
        }
    }
    private void print(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(map[i][j].getSymbol());
            }
            System.out.println();
        }
        System.out.println();
    }
    public void begin(){
        clean();
        determinFormation();
        arrangeEssence();
        arrangeGrandfatherAndCalabash();
        print();
    }
}