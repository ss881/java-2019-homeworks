package creature;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import location.Position;
import war.BattleField;

import java.util.ArrayList;
import java.util.Random;


public class HuluBros {

    public static class Hulu extends Creature implements Runnable{
        //HULUWA id;
        //public int id;
        public Hulu(){
            super(new Position(0,0),100,100,-1);
        }
        Hulu(Position p, int HP, int MP, int i){
            super(p,HP,MP,i);
            //name = n;
            //id = i;
        }

        @Override
        public String toString() {
            Integer ID = new Integer(super.id);
            return "id="+ID.toString()+" "+super.toString();
        }

        @Override
        public void run() {
            while(!Thread.interrupted() && isAlive() && BattleField.getInstance().isPlay()){
                Random r = new Random();
                DIRECTION direction = DIRECTION.values()[r.nextInt(8)];
                do{
                    Position newPos = new Position(pos.x,pos.y);//newPos必须初始化为当前生物位置
                    ArrayList<Position> posList = BattleField.getInstance().posList;
                    if(isValidMove(direction,newPos)){//移动，需要得到posList数组
                        Position nextPos = posList.get(BattleField.getIndex(newPos));
                        synchronized (nextPos){
                            if(nextPos != null && nextPos.holder == null){
                                //Recorder.getInstance().append("move "+id+' '+pos.x+' '+pos.y+"  "+nextPos.x+' '+nextPos.y+'\n');
                                //move();
                                nextPos.holder = this;
                                pos.holder = null;
                                pos = nextPos;
                                //ImageView iv = BattleField.getInstance().creatureViewList.get()
                                int index = id;
                                System.out.println("Hulubros: index"+index);
                                //System.out.println("index = "+index);
                                assert (index>=0 && index<BattleField.getInstance().f.mCount+2+8);
                                ImageView iv = BattleField.getInstance().creatureViewList.get(index);
                                synchronized (iv){
                                    iv.setY(nextPos.x*Position.P_WIDTH);
                                    iv.setX(nextPos.y*Position.P_HEIGHT);
                                }
                                break;
                            }
                            else if(nextPos != null && nextPos.holder != null){
                                if(!nextPos.holder.getClass().getSimpleName().equals(this.getClass().getSimpleName())){
                                    nextPos.holder.setHealthPoint(nextPos.holder.getHealthPoint()-this.getManaPoint());
                                    this.setHealthPoint(this.getHealthPoint()-nextPos.holder.getManaPoint());
                                    //Recorder.getInstance().append("afterAttack "+id+' '+this.getHealthPoint()+'\n');
                                    //Recorder.getInstance().append("afterAttack "+nextPos.holder.id+' '+nextPos.holder.getHealthPoint()+'\n');
                                    if(!nextPos.holder.isAlive()){
                                        //int index = nextPos.holder.
                                        int index = -1;
                                        if(nextPos.holder.id >= 0 && nextPos.holder.id <= 7){
                                            index = nextPos.holder.id;
                                        }else if(nextPos.holder.id == 35 || nextPos.holder.id == 36){
                                            index = 8 + ((nextPos.holder.id == 35)?1:0);
                                        }else if(nextPos.holder.id >= 65 && nextPos.holder.id <= ('Z'-'A'+65)){
                                            index = 10 + nextPos.holder.id - 65;
                                        }
                                        assert index>=0;
                                        //Recorder.getInstance().append("dead "+nextPos.holder.id+' '+nextPos.holder.pos.x+' '+nextPos.holder.pos.y+'\n');
                                        ImageView iv = BattleField.getInstance().creatureViewList.get(index);
                                        synchronized (iv){
                                            //iv.setY(nextPos.x*Position.P_WIDTH);
                                            //iv.setX(nextPos.y*Position.P_HEIGHT);
                                            Image img = new Image("tomb2.png");
                                            iv.setImage(img);
                                            nextPos.holder = null;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    direction = randomDirection();
                }while (isAlive());
                if(!isAlive()){
                    int index = id;
                    assert index>=0;
                    ImageView iv = BattleField.getInstance().creatureViewList.get(index);
                    //Recorder.getInstance().append("dead "+id+' '+pos.x+' '+pos.y+'\n');
                    synchronized (iv){
                        Image img = new Image("tomb2.png");
                        iv.setImage(img);
                        pos.holder = null;
                    }
                }
                Random rand = new Random();
                try{
                    Thread.sleep(rand.nextInt(1000)+500);
                    //Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(BattleField.getInstance().isPlay()) {
                    if (BattleField.getInstance().isGameOver()) {
                        BattleField.getInstance().setEnd();
                        //Recorder.getInstance().writeToFile();
                    }
                }
            }
        }
    }

    final static int INITIAL_HP = 100;
    final static int INITIAL_MP = 100;

    public ArrayList<Hulu> hList;
    //List<Hulu> list;
    //public Creature grandPa;
    public Hulu grandPa;

    public HuluBros(){
        hList = new ArrayList<Hulu>();
        for(int i=0; i<7; i++){
            //hList.add(new Hulu())
            hList.add(new Hulu(new Position(0,0),100,100,i+1));
        }
        //grandPa = new Creature(new Position(0,0),50,50);
        grandPa = new Hulu(new Position(0,0),50,50,0);
    }

    public void print(){
        System.out.println("Grandpa: "+grandPa.toString());
        for(int i=0; i<7; i++){
            System.out.println(hList.get(i).toString());
        }
    }

    public Boolean isAllDead(){
        assert grandPa!=null;
        Boolean ret = grandPa.isAlive();
        for(Hulu h: hList){
            assert h!=null;
            ret = ret | h.isAlive();
            if(ret) return false;
        }
        return !ret;
    }

    public Hulu getAt(int i){
        return hList.get(i);
    }

    public void loadIcons(){
        assert grandPa!=null;
        grandPa.loadIcon("grandpa.png");
        assert hList!=null;
        hList.get(0).loadIcon("one.png");
        hList.get(1).loadIcon("two.png");
        hList.get(2).loadIcon("three.png");
        hList.get(3).loadIcon("four.png");
        hList.get(4).loadIcon("five.png");
        hList.get(5).loadIcon("six.png");
        hList.get(6).loadIcon("seven.png");
    }

    public ArrayList<ImageView> getImageView(){
        ArrayList<ImageView> imgView = new ArrayList<ImageView>();
        ImageView giv = new ImageView(grandPa.icon);
        /*giv.setX(grandPa.pos.x*Position.P_HEIGHT);
        giv.setY(grandPa.pos.y*Position.P_WIDTH);*/
        giv.setY(grandPa.pos.x*Position.P_WIDTH);
        giv.setX(grandPa.pos.y*Position.P_HEIGHT);
        imgView.add(giv);

        for(Hulu h: hList){
            ImageView iv = new ImageView(h.icon);
            //iv.setX(h.pos.x*Position.P_WIDTH);
            //iv.setY(h.pos.y*Position.P_HEIGHT);
            iv.setY(h.pos.x*Position.P_WIDTH);
            iv.setX(h.pos.y*Position.P_HEIGHT);
            imgView.add(iv);
        }
        return imgView;
    }
}
