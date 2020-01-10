package gamemaster;

import chessboard.ChessBoard;
import chessboard.Cell;
import creature.Creature;
import factory.CreatureFactory;

import java.util.Vector;

public class GameDirector {
    ChessBoard chessBoard=new ChessBoard();
    CreatureFactory creatureFactory=new CreatureFactory();
    Vector<Creature> creatures=new Vector<>();

    private static  final int GOODCOL=1;
    private static  final int BADCOL=8;

    private class CreatureMoveReport{
        private int x1;
        private int y1;
        private String name;
        public CreatureMoveReport(Creature c){
            name=c.toString();
            x1=c.getX();
            y1=c.getY();
            System.out.print(name+"("+x1+","+y1+") ");
            if(c.toString().equals("蛇精")) System.out.print("\n");
        }
        public CreatureMoveReport(Creature c,int x0,int y0){
            name=c.toString();
            x1=c.getX();
            y1=c.getY();
            System.out.println(name+"("+x0+","+y0+") ===> ("+x1+","+y1+")");
        }
    }

    public void gamePlay(){
        init();
        System.out.println(chessBoard.toString());
        setChongEFormation();
        System.out.println(chessBoard.toString());
        setYanXing();
        System.out.println(chessBoard.toString());
    }
    public void init(){
        System.out.println("===========================================长蛇===========================================");
        initCalabashBrother();
        initElder();
        initScorpion();
        initGoblin();
        initSnake();
    }
    public void initCalabashBrother(){
        int broNum=7;
        int column=GOODCOL;
        for(int i=0;i<broNum;i++){
            Creature c=creatureFactory.generate("CalabashBrother",i);
            creatures.add(c);
            chessBoard.placeCreature(c,i,column);
            new CreatureMoveReport(c);
        }
    }
    public void initElder(){
        int column=GOODCOL-1;
        Creature c=creatureFactory.generate("Elder",-1);
        creatures.add(c);
        chessBoard.placeCreature(c,3,column);
        new CreatureMoveReport(c);
    }
    public void initScorpion(){
        int column=BADCOL;
        Creature c=creatureFactory.generate("Scorpion",-1);
        creatures.add(c);
        chessBoard.placeCreature(c,0,column);
        new CreatureMoveReport(c);
    }
    public void initGoblin(){
        int goblinNum=6;
        int column=BADCOL;
        for(int i=1;i<=goblinNum;i++){
            Creature c=creatureFactory.generate("Goblin",-1);
            creatures.add(c);
            chessBoard.placeCreature(c,i,column);
            new CreatureMoveReport(c);
        }
    }
    public void initSnake(){
        int column=BADCOL+1;
        Creature c=creatureFactory.generate("Snake",-1);
        creatures.add(c);
        chessBoard.placeCreature(c,3,column);
        new CreatureMoveReport(c);
    }
    public void setChongEFormation(){
        System.out.println("===========================================冲轭===========================================");
        boolean flag=false;
        for(int i=0;i<creatures.size();i++){
            if(creatures.elementAt(i).toString().equals("蝎子")){
                int x0=creatures.elementAt(i).getX();
                int y0=creatures.elementAt(i).getY();
                creatures.elementAt(i).walkTo(0,0);
                new CreatureMoveReport(creatures.elementAt(i),x0,y0);
            }
            else if(creatures.elementAt(i).toString().equals("喽啰")){
                int x0=creatures.elementAt(i).getX();
                int y0=creatures.elementAt(i).getY();
                if(!flag){
                    creatures.elementAt(i).walkTo(0,-1);
                    flag=true;
                }
                else{
                    creatures.elementAt(i).walkTo(0,0);
                    flag=false;
                }
                new CreatureMoveReport(creatures.elementAt(i),x0,y0);
            }
        }

        chessBoard.formationChange();
    }
    public void setYanXing(){
        System.out.println("===========================================雁行===========================================");
        int id=1;
        for(int i=0;i<creatures.size();i++){
            Creature c=creatures.elementAt(i);
            int x0=c.getX(); int y0=c.getY();
            if(c.toString().equals("蝎子")){
                c.walkTo(0,+1);
                new CreatureMoveReport(c,x0,y0);
            }
            else if(c.toString().equals("喽啰")){
                switch (id){
                    case 1:{
                        c.walkTo(0,+1);
                        break;
                    }
                    case 2: case 3:{
                        c.walkTo(0,-1);
                        break;
                    }
                    case 4: case 5:{
                        c.walkTo(0,-3);
                        break;
                    }
                    case 6:{
                        c.walkTo(0,-5);
                        break;
                    }
                    default:break;
                }
                new CreatureMoveReport(c,x0,y0);
                id++;
            }
        }
        chessBoard.formationChange();
    }
}
