package creature;

import queuepattern.*;
import space.*;

public class SnakeTeam implements SnakeTeamQueuePattern{
    private Demon demons[];
    private Snake cheerleader;
    public SnakeTeam(){
        demons=new Demon[19];
        demons[0]=new Scorpion();
        demons[1]=new Frog(1);
        demons[2]=new Frog(2);
        demons[3]=new Frog(3);
        demons[4]=new Bat();
        demons[5]=new Bee();
        demons[6]=new Centipede();
        demons[7]=new Frog(1);
        demons[8]=new Frog(2);
        demons[9]=new Frog(3);
        demons[10]=new Bat();
        demons[11]=new Bee();
        demons[12]=new Centipede();
        demons[13]=new Frog(1);
        demons[14]=new Frog(2);
        demons[15]=new Frog(3);
        demons[16]=new Bat();
        demons[17]=new Bee();
        demons[18]=new Centipede();
        cheerleader=new Snake();
    }

    public Snake getCheerleader() {
        return cheerleader;
    }
    public Demon[] getDemons(){return demons;}

    public void generateChangshePattern(Space battleground, int x, int y){
        for (int i=0;i<demons.length;i++){
            demons[i].moveFrom(battleground);
        }
        if (!battleground.isExceedTheBattleField(x,y)&&!battleground.isExceedTheBattleField(x,y+5)){
            demons[0].moveTo(battleground, x, y);
            for (int i=1;i<6;i++){
                y += 1;
                demons[i].moveTo(battleground, x, y);
            }
        }
        else
        {
            System.out.println("changshe pattern move wrong!!!");
        }
    }

    public void generateChongePattern(Space battleground,  int x, int y){
        for (int i=0;i<demons.length;i++){
            demons[i].moveFrom(battleground);
        }
        if (!battleground.isExceedTheBattleField(x,y)&&!battleground.isExceedTheBattleField(x-1,y-3)){
            demons[0].moveTo(battleground, x, y);
            for (int i=1;i<6;i++){
                y += 1;
                if (i%2==1) demons[i].moveTo(battleground, x-1, y);
                else demons[i].moveTo(battleground, x, y);
            }
        }
        else
        {
            System.out.println("chonge pattern move wrong!!!");
        }
    }

    public void generateFangyuanPattern(Space battleground,  int x, int y){
        for (int i=0;i<demons.length;i++){
            demons[i].moveFrom(battleground);
        }
        if (!battleground.isExceedTheBattleField(x-2,y)&&!battleground.isExceedTheBattleField(x+2,y+4)){
            demons[0].moveTo(battleground, x, y);
            //second layer
            demons[1].moveTo(battleground,x-1,y+1);
            demons[2].moveTo(battleground,x+1,y+1);
            //third layer
            demons[3].moveTo(battleground,x-2,y+2);
            demons[4].moveTo(battleground,x+2,y+2);
            //forth layer
            demons[5].moveTo(battleground,x-1,y+3);
            demons[6].moveTo(battleground,x+1,y+3);
            //fifth layer
            demons[7].moveTo(battleground,x,y+4);
        }
        else
        {
            System.out.println("fangyuan pattern move wrong!!!");
        }
    }

    public void generateFengshiPattern(Space battleground,  int x, int y){
        for (int i=0;i<demons.length;i++){
            demons[i].moveFrom(battleground);
        }
        if (!battleground.isExceedTheBattleField(x-3,y)&&!battleground.isExceedTheBattleField(x+3,y+8)){
            demons[0].moveTo(battleground, x, y);
            //second layer
            demons[1].moveTo(battleground, x-1, y+1);
            demons[2].moveTo(battleground, x+1, y+1);
            //third layer
            demons[3].moveTo(battleground, x, y+1);
            //forth layer
            demons[4].moveTo(battleground, x-2, y+2);
            demons[5].moveTo(battleground, x+2, y+2);
            //fifth layer
            demons[6].moveTo(battleground, x, y+2);
            //sixth layer
            demons[7].moveTo(battleground, x-3, y+3);
            demons[8].moveTo(battleground, x+3, y+3);
            //seventh-ninth layer
            demons[9].moveTo(battleground, x, y+3);
            demons[10].moveTo(battleground, x, y+4);
            demons[11].moveTo(battleground, x, y+5);
        }
        else
        {
            System.out.println("fengshi pattern move wrong!!!");
        }
    }

    public void generateHeyiPattern(Space battleground,  int x, int y){
        for (int i=0;i<demons.length;i++){
            demons[i].moveFrom(battleground);
        }
        if (!battleground.isExceedTheBattleField(x,y)&&!battleground.isExceedTheBattleField(x+3,y+3)){
            demons[0].moveTo(battleground,x,y);
            for (int i=1;i<4;i++){
                x += 1;
                y += 1;
                demons[i].moveTo(battleground,x,y);
            }
            for (int i=4;i<7;i++){
                x += 1;
                y -= 1;
                demons[i].moveTo(battleground,x,y);
            }
        }
        else
        {
            System.out.println("heyi pattern move wrong!!!");
        }
    }

    public void generateYanxingPattern(Space battleground,  int x, int y){
        for (int i=0;i<demons.length;i++){
            demons[i].moveFrom(battleground);
        }
        if (!battleground.isExceedTheBattleField(x,y)&&!battleground.isExceedTheBattleField(x-4,y+4)){
            demons[0].moveTo(battleground, x, y);
            for (int i=1;i<5;i++){
                x -= 1;
                y += 1;
                demons[i].moveTo(battleground, x, y);
            }
        }
        else
        {
            System.out.println("yanxing pattern move wrong!!!");
        }
    }

    public void generateYanyuePattern(Space battleground,  int x, int y){
        for (int i=0;i<demons.length;i++){
            demons[i].moveFrom(battleground);
        }
        if (!battleground.isExceedTheBattleField(x,y)&&!battleground.isExceedTheBattleField(x-5,y+8)){
            demons[0].moveTo(battleground, x, y);
            //second layer
            demons[1].moveTo(battleground, x-2, y+1);
            demons[2].moveTo(battleground, x-1, y+1);
            //third layer
            demons[3].moveTo(battleground, x-3, y+2);
            demons[4].moveTo(battleground, x-2, y+2);
            //forth-sixth layer
            for(int i=0;i<3;i++) {
                demons[3*i+5].moveTo(battleground,x-5,y+3+i);
                demons[3*i+6].moveTo(battleground,x-4,y+3+i);
                demons[3*i+7].moveTo(battleground,x-3,y+3+i);
            }
            //seventh layer
            demons[14].moveTo(battleground, x-3, y+6);
            demons[15].moveTo(battleground, x-2, y+6);
            //eighth layer
            demons[16].moveTo(battleground, x-2, y+7);
            demons[17].moveTo(battleground, x-1, y+7);
            //ninth layer
            demons[18].moveTo(battleground,x,y+8);
        }
        else
        {
            System.out.println("yanyue pattern move wrong!!!");
        }
    }

    public void generateYulinPattern(Space battleground,  int x, int y){
        for (int i=0;i<demons.length;i++){
            demons[i].moveFrom(battleground);
        }
        if (!battleground.isExceedTheBattleField(x-3,y)&&!battleground.isExceedTheBattleField(x+3,y+4)){
            demons[0].moveTo(battleground, x, y);
            //second layer
            demons[1].moveTo(battleground, x+1, y+1);
            //third layer
            int start=x-2;
            for(int i=0;i<3;i++) {
                demons[i+2].moveTo(battleground,start+2*i,y+2);
            }
            //forth layer
            start=x-3;
            for(int i=0;i<4;i++) {
                demons[i+5].moveTo(battleground,start+2*i,y+3);
            }
            //fifth layer
            demons[9].moveTo(battleground,x,y+4);
        }
        else
        {
            System.out.println("yulin pattern move wrong!!!");
        }
    }

    public void generateTheCheerPattern(Space battleground, int x, int y){
        cheerleader.moveTo(battleground,x,y);
    }
}
