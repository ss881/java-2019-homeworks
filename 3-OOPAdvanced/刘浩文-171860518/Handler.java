import java.util.Random;

public class Handler {
    private Map map;
    private Huluboy[] boys;
    private Creature[] impians;
    private Creature oracle;
    private Creature snake;
    private Creature scorpion;
    Random shuffler;
    Handler(){
        map=new Map();
        shuffler=new Random();
        boys=new Huluboy[7];
        //shuffler.nextInt(15);
        boys[0]=new Huluboy("LaoDa",'H', new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.RED);
        boys[1]=new Huluboy("LaoEr",'H', new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.ORANGE);
        boys[2]=new Huluboy("LaoSan",'H', new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.YELLOW);
        boys[3]=new Huluboy("LaoSi",'H', new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.GREEN);
        boys[4]=new Huluboy("LaoWu",'H', new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.VIOLET);
        boys[5]=new Huluboy("LaoLiu",'H', new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.BLUE);
        boys[6]=new Huluboy("LaoQi",'H', new Position(shuffler.nextInt(15),shuffler.nextInt(15)),COLOR.PURPLE);
        //for(int i=0;i<7;i++) System.out.print(boys[i].pos.x_pos+" "+boys[i].pos.y_pos+"|");
        //System.out.print('\n');
        impians=new Creature[7];
        impians[0]=new Creature("Impian",'I',new Position(-1,-1));
        impians[1]=new Creature("Impian",'I',new Position(-1,-1));
        impians[2]=new Creature("Impian",'I',new Position(-1,-1));
        impians[3]=new Creature("Impian",'I',new Position(-1,-1));
        impians[4]=new Creature("Impian",'I',new Position(-1,-1));
        impians[5]=new Creature("Impian",'I',new Position(-1,-1));
        impians[6]=new Creature("Impian",'I',new Position(-1,-1));
        scorpion=new Creature("Scorpion",'S',new Position(-1,-1));
        oracle=new Creature("Oracle",'O',new Position(-1,-1));
        snake=new Creature("Snake",'K',new Position(-1,-1));

    }
    void move_to(Creature someone,Position tar)
    {
        if(someone.pos.x_pos>=0 && someone.pos.y_pos>=0)
            map.symbol_map[someone.pos.x_pos][someone.pos.y_pos]='_';
        someone.pos=tar;
        map.symbol_map[someone.pos.x_pos][someone.pos.y_pos]=someone.tag;
    }
    void printMap(){map.printMap();}
    void snakePattern()
    {
        for(int i=0;i<7;i++) move_to(boys[i],new Position(1+i,0));
        for(int i=0;i<7;i++) if(map.symbol_map[1+i][0]!='H') move_to(boys[i],new Position(i+1,0));
    }
    void leadScorpion()
    {
        move_to(scorpion,new Position(9,3));
        move_to(impians[0],new Position(8,4));
        move_to(impians[1],new Position(10,4));
        move_to(impians[2],new Position(7,5));
        move_to(impians[3],new Position(11,5));
        move_to(impians[4],new Position(8,6));
        move_to(impians[5],new Position(10,6));
        move_to(impians[6],new Position(9,7));
    }
    void leadSnakeAndOracle()
    {
        move_to(oracle,new Position(10,0));
        move_to(snake,new Position(9,5));
    }
    void rearrange()
    {
        move_to(scorpion,new Position(10,3));
        move_to(impians[0],new Position(11,4));
        move_to(impians[1],new Position(10,5));
        move_to(impians[2],new Position(11,6));
        move_to(impians[3],new Position(10,7));
        move_to(impians[4],new Position(11,8));
        move_to(impians[5],new Position(10,9));
        move_to(impians[6],new Position(11,10));
        move_to(snake,new Position(11,2));
    }
}
