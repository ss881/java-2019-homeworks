package creatures;


import ground.*;

enum ArrangeMent{HeYi,YanXing,HengE,YuLing,FangYuan,YanYue,FengShi}

public class SnakeTeam{
    private Damon damons[];
    private Snake snake;
    private ArrangeMent arrangement;
    public SnakeTeam(int num){
        damons=new Damon[num];
        damons[0]=new Damon(0,0,"蝎子");
        snake=new Snake(12,12,"蛇精");
        for(int i=1;i<num;i++){
            damons[i]=new Damon(0,0,"喽啰");

        }
        heYi(7,6);
        arrangement=ArrangeMent.HeYi;
    }
    public SnakeTeam(){
        damons=new Damon[25];
        damons[0]=new Damon(0,0,"蝎子");
        snake=new Snake(12,12,"蛇精");
        for(int i=1;i<25;i++){
            damons[i]=new Damon(0,0,"喽啰");
        
        }
        heYi(7,6);
        arrangement=ArrangeMent.HeYi;
    }
    private void heYi(int x, int y){
        //7,6
        damons[0].setPos(x,y);
        damons[1].setPos(x+1,y-1);
        damons[2].setPos(x+1,y+1);
        damons[3].setPos(x+2,y+2);
        damons[4].setPos(x+2,y-2);
        damons[5].setPos(x+3,y+3);
        damons[6].setPos(x+3,y-3);
        for(int i=0;i<7;i++)
            damons[i].setLive();
        for(int i=7;i<damons.length;i++) {
            damons[i].setDead();
            damons[i].setPos(0,0);
        }
    }

    public void yanXing(int x, int y){
        damons[0].setPos(x,y);
        for(int i=1;i<=5;i++){
            damons[i].setPos(x+i,y+i);
            damons[i].setLive();
       }
        for(int i=6;i<damons.length;i++) {
            damons[i].setDead();
            damons[i].setPos(0,0);
        }
    }

    public void hengE(int x, int y){
        damons[0].setPos(x,y);
        damons[1].setPos(x,y-2);
        damons[2].setPos(x,y+2);
        damons[3].setPos(x+1,y-1);
        damons[4].setPos(x+1,y+1);
        damons[5].setPos(x+1,y+3);
        for(int i=0;i<=5;i++)
            damons[i].setLive();
        for(int i=6;i<damons.length;i++) {
            damons[i].setDead();
            damons[i].setPos(0, 0);
        }
    }
    public void yuLing(int x, int y){
        damons[0].setPos(x,y);
        damons[1].setPos(x+1,y+1);
        damons[2].setPos(x+2,y-2);
        damons[3].setPos(x+2,y);
        damons[4].setPos(x+2,y+2);
        damons[5].setPos(x+3,y-3);
        damons[6].setPos(x+3,y-1);
        damons[7].setPos(x+3,y+1);
        damons[8].setPos(x+3,y+3);
        damons[9].setPos(x+4,y);
        for(int i=0;i<10;i++){
            damons[i].setLive();
        }
        for(int i=10;i<damons.length;i++) {
            damons[i].setDead();
            damons[i].setPos(0, 0);
        }
    }

    public void fangYuan(int x, int y){
        damons[0].setPos(x,y);
        damons[1].setPos(x+1,y-1);
        damons[2].setPos(x+1,y+1);
        damons[3].setPos(x+2,y-2);
        damons[4].setPos(x+2,y+2);
        damons[5].setPos(x+3,y-1);
        damons[6].setPos(x+3,y+1);
        damons[7].setPos(x+4,y);
        for(int i=0;i<8;i++){
            damons[i].setLive();
        }
        for(int i=8;i<damons.length;i++) {
            damons[i].setDead();
            damons[i].setPos(0, 0);
        }
    }

    public void yanYue(int x, int y){
        damons[0].setPos(x,y);
        damons[1].setPos(x,y+1);
        damons[2].setPos(x,y-1);
        for(int i=-1;i<=1;i++)
            damons[4+i].setPos(x+2,y+i);
        damons[6].setPos(x+3,y+2);
        damons[7].setPos(x+3,y-2);
        damons[8].setPos(x+4,y+3);
        damons[9].setPos(x+4,y-3);
        damons[10].setPos(x+4,y-1);
        damons[11].setPos(x+4,y);
        damons[12].setPos(x+4,y+1);
        damons[13].setPos(x+5,y+2);
        damons[14].setPos(x+5,y-2);
        damons[15].setPos(x+6,y-3);
        damons[16].setPos(x+6,y+3);
        damons[17].setPos(x+7,y-4);
        damons[18].setPos(x+7,y+4);
        for(int i=0;i<19;i++)
            damons[i].setLive();
        for(int i=19;i<damons.length;i++) {
            damons[i].setDead();
            damons[i].setPos(0, 0);
        }
    }

    public void fengShi(int x, int y){
        damons[0].setPos(x,y);
        for(int i=0;i<3;i++){
            damons[i*3+1].setPos(x+i+1,y);
            damons[i*3+2].setPos(x+i+1,y-i-1);
            damons[i*3+3].setPos(x+i+1,y+i+1);
        }
        damons[10].setPos(x+4,y);
        damons[11].setPos(x+5,y);
        for(int i=0;i<12;i++)
            damons[i].setLive();
        for(int i=12;i<damons.length;i++) {
            damons[i].setDead();
            damons[i].setPos(0, 0);
        }
    }
    public void arrange(Ground ground){
        switch(arrangement){
            case HeYi: yanXing(6,3);arrangement=ArrangeMent.YanXing;break;
            case YanXing:hengE(7,5);arrangement=ArrangeMent.HengE;break;
            case HengE:yuLing(7,6);arrangement=ArrangeMent.YuLing;break;
            case YuLing:fangYuan(6,6);arrangement = ArrangeMent.FangYuan;break;
            case FangYuan:yanYue(5,6);arrangement=ArrangeMent.YanYue;break;
            case YanYue:fengShi(6,6);arrangement=ArrangeMent.FengShi;break;
            case FengShi:heYi(7,6);arrangement=ArrangeMent.HeYi;break;
        }
        ground.update(getTeamMembers());
        ground.print();
    }
    public Creature[] getTeamMembers(){
        Creature c[]=new Creature [damons.length+1];
        c[0]=snake;
        for(int i=0;i<damons.length;i++){
            c[i+1]=damons[i];
        }
        return c;
    }
    public Snake getSnake(){
        return snake;
    }
}