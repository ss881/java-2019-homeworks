import java.util.Random;
import java.lang.reflect.Field;
public class Handler {
    private Map map;
    private Huluboy[] boys;
    private Creature[] imp;
    private Creature oracle;
    private Creature snake;
    private Creature scorpion;
    Random shuffler;
    Handler(){
        map=new Map();
        shuffler=new Random();
        boys=new Huluboy[7];
        //shuffler.nextInt(15);
        boys[0]=new Huluboy("LaoDa", new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.RED);
        boys[1]=new Huluboy("LaoEr", new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.ORANGE);
        boys[2]=new Huluboy("LaoSan", new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.YELLOW);
        boys[3]=new Huluboy("LaoSi", new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.GREEN);
        boys[4]=new Huluboy("LaoWu", new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.VIOLET);
        boys[5]=new Huluboy("LaoLiu", new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.BLUE);
        boys[6]=new Huluboy("LaoQi", new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.PURPLE);
        //for(int i=0;i<7;i++) System.out.print(boys[i].pos.x_pos+" "+boys[i].pos.y_pos+"|");
        //System.out.print('\n');
        imp =new Creature[7];
        imp[0]=new Creature("Impian",new Position(-1,-1));
        imp[1]=new Creature("Impian",new Position(-1,-1));
        imp[2]=new Creature("Impian",new Position(-1,-1));
        imp[3]=new Creature("Impian",new Position(-1,-1));
        imp[4]=new Creature("Impian",new Position(-1,-1));
        imp[5]=new Creature("Impian",new Position(-1,-1));
        imp[6]=new Creature("Impian",new Position(-1,-1));
        scorpion=new Creature("Scorpion",new Position(-1,-1));
        oracle=new Creature("Oracle",new Position(-1,-1));
        snake=new Creature("Snake",new Position(-1,-1));

    }
    public <T extends Creature> char giveLabel(T someone)
    //public char giveLabel(Creature someone)
    {
        try
        {
            Field fClass=someone.getClass().getField("name");
            //System.out.print((String)fClass.get(someone)+'\n');
            String someoneName=(String)fClass.get(someone);
            String huluJudge=someoneName.substring(0,3);
            //System.out.print(huluJudge);
            if(huluJudge.equals("Lao")) return 'H';
            else
            {
                switch(someoneName)
                {
                    case "Scorpion": return 'S';
                    case "Oracle": return 'O';
                    case "Impian": return 'I';
                    case "Snake": return 'K';
                    default: return '_';
                }
            }
        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
            return '_';
        }
    }
    public void move_to(Creature someone,Position tar)
    {
        if(someone.pos.x_pos>=0 && someone.pos.y_pos>=0)
            map.symbol_map[someone.pos.x_pos][someone.pos.y_pos]='_';
        someone.pos=tar;
        map.symbol_map[someone.pos.x_pos][someone.pos.y_pos]=giveLabel(someone);
    }
    public void printMap(){map.printMap();}
    public void snakePattern()
    {
        for(int i=0;i<7;i++) move_to(boys[i],new Position(1+i,0));
        for(int i=0;i<7;i++) if(map.symbol_map[1+i][0]!='H') move_to(boys[i],new Position(i+1,0));
    }
    public void leadScorpion()
    {
        move_to(scorpion,new Position(9,3));
        move_to(imp[0],new Position(8,4));
        move_to(imp[1],new Position(10,4));
        move_to(imp[2],new Position(7,5));
        move_to(imp[3],new Position(11,5));
        move_to(imp[4],new Position(8,6));
        move_to(imp[5],new Position(10,6));
        move_to(imp[6],new Position(9,7));
    }
    public void leadSnakeAndOracle()
    {
        move_to(oracle,new Position(10,0));
        move_to(snake,new Position(9,5));
    }
    public void reArrange()
    {
        move_to(scorpion,new Position(10,3));
        move_to(imp[0],new Position(11,4));
        move_to(imp[1],new Position(10,5));
        move_to(imp[2],new Position(11,6));
        move_to(imp[3],new Position(10,7));
        move_to(imp[4],new Position(11,8));
        move_to(imp[5],new Position(10,9));
        move_to(imp[6],new Position(11,10));
        move_to(snake,new Position(11,2));
    }
}
