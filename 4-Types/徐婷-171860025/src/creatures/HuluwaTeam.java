package creatures;
import java.util.*;

import formation.Formation;
import ground.Ground;

public class HuluwaTeam {
    Grandpa grandpa = new Grandpa("爷爷", 0, 0);
    List<Huluwa> huluwaList = new ArrayList<>();
    public HuluwaTeam(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1;i<=7;i++){
            list.add(i);
        }
        Collections.shuffle(list);
        for(int i = 0; i < 7; i++){
            huluwaList.add(new Huluwa(list.get(i)));
            huluwaList.get(i).setName();
        }
        Formation.changShe(huluwaList, 2, 3);
    }
    private void swap(int i, int j){
        Huluwa t;
        Huluwa huluwaI = huluwaList.get(i);
        Huluwa huluwaJ = huluwaList.get(j);
        System.out.println(huluwaI.getName()+" move from ("+huluwaI.getCoordX()+","+huluwaI.getCoordY()+") to ("+huluwaJ.getCoordX()+","+huluwaJ.getCoordY()+")");
        System.out.println(huluwaJ.getName()+" move from ("+huluwaJ.getCoordX()+","+huluwaJ.getCoordY()+") to ("+huluwaI.getCoordX()+","+huluwaI.getCoordY()+")");
        t=huluwaI;
        huluwaList.set(i, huluwaJ);
        huluwaList.set(j, huluwaI);
        int x=huluwaList.get(i).getCoordX();
        int y=huluwaList.get(i).getCoordY();
        huluwaList.get(i).setPos(huluwaList.get(j).getCoordX(), huluwaList.get(j).getCoordY());
        huluwaList.get(j).setPos(x, y);
    }
    public void sort(Ground ground){
        ground.update();
        ground.printGround();
        for(int i=6;i>=0;i--){
            for(int j=0;j<i;j++){
                if(huluwaList.get(j).getRank()>huluwaList.get(j+1).getRank()){
                    swap(j,j+1);
                    //Creature[] t=getTeamMembers();
                    ground.update();
                    ground.printGround();
                }
            }
        }
    }
    public List<HuluwaSide> getTeamMembers(){
        List<HuluwaSide> ret = new ArrayList<>();
        ret.add(grandpa);
        ret.addAll(1, huluwaList);
        return ret;
    }
}
