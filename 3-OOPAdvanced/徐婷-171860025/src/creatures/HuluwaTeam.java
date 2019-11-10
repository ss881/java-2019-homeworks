package creatures;

import java.util.*;
import ground.*;


public class HuluwaTeam{
    private Huluwa[]  brothers;
    private Grandpa grandpa;
    public HuluwaTeam(){
        brothers=new Huluwa[7];
        grandpa=new Grandpa(0,0,"爷爷");
        List<Integer> list=new ArrayList<Integer>();
        for(int i=1;i<=7;i++)
            list.add(i);
        Collections.shuffle(list);
        for(int i=0;i<7;i++){
            String name="";
            HuluwaColor color=HuluwaColor.RED;
            switch(list.get(i)){
                case 1:name="老大";color=HuluwaColor.RED;break;
                case 2:name="老二";color=HuluwaColor.ORANGE;break;
                case 3:name="老三";color=HuluwaColor.YELLOW;break;
                case 4:name="老四";color=HuluwaColor.GREEN;break;
                case 5:name="老五";color=HuluwaColor.CYAN;break;
                case 6:name="老六";color=HuluwaColor.BLUE;break;
                case 7:name="老七";color=HuluwaColor.PURPLE;break;
            }
            brothers[i]=new Huluwa(2,i+3,name,list.get(i),color);
        }

    }
    private void swap(int i, int j){
        Huluwa t;
        System.out.println(brothers[i].name+" move from ("+brothers[i].coordX+","+brothers[i].coordY+") to ("+brothers[j].coordX+"," +brothers[j].coordY+")");
        System.out.println(brothers[j].name+" move from ("+brothers[j].coordX+","+brothers[j].coordY+") to ("+brothers[i].coordX+"," +brothers[i].coordY+")");
        t=brothers[i];
        brothers[i]=brothers[j];
        brothers[j]=t;
        int x=brothers[j].getCoordX();
        int y=brothers[j].getCoordY();
        brothers[j].setPos(brothers[i].getCoordX(),brothers[i].getCoordY());
        brothers[i].setPos(x,y);
    }
    public void Sort(Ground ground){
        for(int i=6;i>=0;i--){
            for(int j=0;j<i;j++){
                if(brothers[j].getRank()>brothers[j+1].getRank()){
                   swap(j,j+1);

                    Creature[] t=getTeamMembers();
                    ground.update(t);
                    ground.print();
                }
            }
        }
    }
    public Creature[] getTeamMembers(){
        Creature tmp[]=new Creature[8];
        tmp[0]=grandpa;
        for(int i=0;i<7;i++){
            tmp[i+1]=brothers[i];
            
        }
        return tmp;
    }
    public Grandpa getGrandpa(){
        return grandpa;
    }
}