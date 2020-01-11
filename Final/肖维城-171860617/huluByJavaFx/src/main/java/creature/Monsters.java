package creature;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import location.Position;
import war.BattleField;

import java.util.ArrayList;
import java.util.Random;

public class Monsters {

    public static class Monster extends Creature implements Runnable{
        //public int id;
        public Monster(){
            super(new Position(-1,-1),80,80,-1);
            //id = Creature.LITTLE;
        }
        Monster(Position p, int HP, int MP, int i){
            super(p,HP,MP,i);
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
                DIRECTION direction = DIRECTION.LEFT;
                Random r = new Random();
                int num = r.nextInt(8);
                switch (num){
                    case 0: case 1:{
                        break;
                    }
                    case 2:{
                        direction = DIRECTION.UP;
                        break;
                    }
                    case 3:{
                        direction = DIRECTION.DOWN;
                        break;
                    }
                    case 4:{
                        direction = DIRECTION.RIGHT_UP;
                        break;
                    }
                    case 5:{
                        direction = DIRECTION.RIGHT_DOWN;
                        break;
                    }
                    case 6:{
                        direction = DIRECTION.LEFT_UP;
                        break;
                    }
                    case 7:{
                        direction = DIRECTION.LEFT_DOWN;
                        break;
                    }
                }
                do{
                    Position newPos = new Position(pos.x,pos.y);//newPos必须初始化为当前生物位置
                    ArrayList<Position> posList = BattleField.getInstance().posList;
                    //System.out.println("Monster: Here1: ");
                    Boolean valid = isValidMove(direction,newPos);
                    System.out.println("Monster: id="+id+"  valid="+valid);
                    //if(isValidMove(direction,newPos)){//移动，需要得到posList数组
                    if(valid){//移动，需要得到posList数组
                        //System.out.println(BattleField.getIndex(newPos));
                        Position nextPos = posList.get(BattleField.getIndex(newPos));
                        //System.out.println("Monster: Here2");
                        synchronized (nextPos){
                            if(nextPos != null && nextPos.holder == null){
                                //Recorder.getInstance().append("move "+id+' '+pos.x+' '+pos.y+"  "+nextPos.x+' '+nextPos.y+'\n');
                                //System.out.println("Monster: Here3");
                                //move();
                                nextPos.holder = this;
                                pos.holder = null;
                                pos = nextPos;
                                //ImageView iv = BattleField.getInstance().creatureViewList.get()
                                int index = 8;
                                if(id == (int)'$'){
                                    ;
                                }else if(id == (int)'#'){
                                    index += 1;
                                }else{
                                    //index += (id - 'A');
                                    index = index + (id - 'A') + 2;
                                }
                                System.out.println("Monsters: index = "+index);
                                assert (index>=0 && index<BattleField.getInstance().f.mCount+2+8);
                                ImageView iv = BattleField.getInstance().creatureViewList.get(index);
                                synchronized (iv){
                                    iv.setY(nextPos.x*Position.P_WIDTH);
                                    iv.setX(nextPos.y*Position.P_HEIGHT);
                                    break;
                                }
                            }else if(nextPos != null && nextPos.holder != null){
                                if(!nextPos.holder.getClass().getSimpleName().equals(this.getClass().getSimpleName())){
                                    nextPos.holder.setHealthPoint(nextPos.holder.getHealthPoint()-this.getManaPoint());
                                    this.setHealthPoint(this.getHealthPoint()-nextPos.holder.getManaPoint());
                                    /*Recorder.getInstance().append("afterAttack "+id+' '+this.getHealthPoint()+'\n');
                                    Recorder.getInstance().append("afterAttack "+nextPos.holder.id+' '+nextPos.holder.getHealthPoint()+'\n');*/
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
                    int index = 8;
                    if(id == (int)'$'){
                        ;
                    }else if(id == (int)'#'){
                        index += 1;
                    }else{
                        //index += (id - 'A');
                        index = index + (id - 'A') + 2;
                    }
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

    /*TODO,用数组来保存字符对应生物在list中的下标*/
    public int monsterCount;
    public ArrayList<Monster> mList;
    public Monster SnakeSpirit;
    public Monster ScorpionSpirit;

    public Monsters(int mCount){
        monsterCount = mCount;
        int snakeId = (int)'$';
        //SnakeSpirit = new Monster(new Position(-1,-1),120,120,Creature.SNAKE);
        SnakeSpirit = new Monster(new Position(-1,-1),120,120,snakeId);
        int scorpionId = (int)'#';
        //ScorpionSpirit = new Monster(new Position(-1,-1),150,150,Creature.SCORPION);
        ScorpionSpirit = new Monster(new Position(-1,-1),150,150,scorpionId);
        mList = new ArrayList<Monster>();
        for(int i=0; i<monsterCount; i++){
            int Id = (int)'A'+i;
            //mList.add(new Monster(new Position(-1,-1),80,80,Creature.LITTLE));
            mList.add(new Monster(new Position(-1,-1),80,80,Id));
        }
    }

    public void print(){
        System.out.println("Snake: "+SnakeSpirit.toString());
        System.out.println("Scorpion: "+ScorpionSpirit.toString());
        for(int i=0; i<monsterCount; i++){
            System.out.println(mList.get(i).toString());
        }
    }

    public Boolean isAllDead(){
        assert SnakeSpirit!=null&&ScorpionSpirit!=null;
        Boolean ret = SnakeSpirit.isAlive() | ScorpionSpirit.isAlive();
        for(Monster m: mList){
            assert m!=null;
            ret = ret | m.isAlive();
            if(ret) return false;
        }
        return !ret;
    }

    public void loadIcons(){
        assert (SnakeSpirit!=null && ScorpionSpirit!=null && mList!=null);
        SnakeSpirit.loadIcon("snakeSpirit.png");
        ScorpionSpirit.loadIcon("scorpionSpirit.png");
        for(Monster m: mList){
            m.loadIcon("frog.png");
        }
    }

    public ArrayList<ImageView> getImageView(){
        System.out.println("getImageView:");
        ArrayList<ImageView> imgView = new ArrayList<ImageView>();
        ImageView snakeIv = new ImageView(SnakeSpirit.icon);
        //siv.setX(SnakeSpirit.pos.x*Position.P_WIDTH);
        //siv.setY(SnakeSpirit.pos.y*Position.P_HEIGHT);
        System.out.println("Snake: "+SnakeSpirit.pos);
        snakeIv.setY(SnakeSpirit.pos.x*Position.P_WIDTH);
        snakeIv.setX(SnakeSpirit.pos.y*Position.P_HEIGHT);
        imgView.add(snakeIv);
        ImageView xiv = new ImageView(ScorpionSpirit.icon);
        //siv.setX(ScorpionSpirit.pos.x*Position.P_WIDTH);
        //siv.setY(ScorpionSpirit.pos.y*Position.P_HEIGHT);
        System.out.println("Scorp: "+ScorpionSpirit.pos);
        xiv.setY(ScorpionSpirit.pos.x*Position.P_WIDTH);
        xiv.setX(ScorpionSpirit.pos.y*Position.P_HEIGHT);
        imgView.add(xiv);

        for(Monster m: mList){
            ImageView iv = new ImageView(m.icon);
            //iv.setX(m.pos.x*Position.P_WIDTH);
            //iv.setY(m.pos.y*Position.P_HEIGHT);
            iv.setY(m.pos.x*Position.P_WIDTH);
            iv.setX(m.pos.y*Position.P_HEIGHT);
            imgView.add(iv);
        }
        return imgView;
    }

}
