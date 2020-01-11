package creature;

import controller.MyMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.Random;


public class Creature implements Fight {
    protected int i; //在棋盘里面的位置
    protected int j;
    protected String name;
    protected boolean nature;
    protected Image image; //显示图片
    protected Image battleImage;//战斗图片
    protected Image deadImage;//死亡图片
    protected Image skillImage; //技能图片
    //引入战斗序列
    protected int allHP; //总体的血量
    protected int currentHP; //当前的血量
    protected int attack; //攻击力
    protected int defence; //防御力
    protected boolean alive;
    protected boolean critical;

    public Creature(int x,int y){
        i = x;
        j = y;
        //初始化都为满的
        allHP = 100;
        currentHP = allHP; //都为满血
        attack = 10;
        defence = 0; //攻击力和防御力
        alive = true; //存活的
        critical=false;
        this.battleImage =  new Image(this.getClass().getClassLoader().getResource(new String("pic/剑.png")).toString(),20,20,false,false);
        this.deadImage =  new Image(this.getClass().getClassLoader().getResource(new String("pic/死亡.png")).toString(),50,50,false,false);
    }

    public Creature(){
        i = -1;
        j = -1;
        allHP = 100;
        currentHP = allHP; //都为满血
        attack = 10;
        defence = 0; //攻击力和防御力
        alive = true;
        this.battleImage =  new Image(this.getClass().getClassLoader().getResource(new String("pic/剑.png")).toString(), 20,20,false,false);
        this.deadImage =  new Image(this.getClass().getClassLoader().getResource(new String("pic/死亡.png")).toString(), 50,50,false,false);
    }

    public void setPosition(int i,int j){
        this.i = i;
        this.j = j;
    }

    public void getInfo(){
        if((name != "蝎子精")&&(name !="小喽啰")) System.out.print(name+"  ");
        else  System.out.print(name);
        System.out.print(i+"坐标"+j);
    }

    public int getI(){ return i; }

    public int getJ(){ return j; }

    public void showGUI(Canvas canvas){
        //显示图片
        if(this.alive){
            canvas.getGraphicsContext2D().drawImage(this.image, this.j * 50, this.i * 50);
            //显示血条
            double rate = (double) this.currentHP / this.allHP; //进行初始化
            canvas.getGraphicsContext2D().setFill(Color.RED);
            canvas.getGraphicsContext2D().fillRect(this.j * 50, this.i * 50, 50 * rate, 5);
            canvas.getGraphicsContext2D().setFill(Color.BLACK);
            canvas.getGraphicsContext2D().fillRect(this.j * 50 + 50 * rate, this.i * 50, 50 * (1 - rate), 5);
        }
        else{
            canvas.getGraphicsContext2D().drawImage(this.deadImage, this.j * 50, this.i * 50);
        }
    }

    public int[] getDestination(MyMap ground) {
        //探寻四个方向的敌人，选择敌人多的方向
        int numOfUp = 0;
        int numOfDown = 0;
        int numOfRight = 0;
        int numOfLeft = 0;
        boolean left = true;//T左F右
        boolean up = true; //T上F下
        int pos[] = new int[2];//不用Position，避免类之间结构混乱
        pos[0] = this.i;
        pos[1] = this.j;
        //判断各个方位上的怪物
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 20; j++){
                if(i != this.i && j != this.j){
                    Creature temp = ground.getCreature(i,j); //得到
                    if(temp != null){
                        if(temp.alive&& this.alive && this.nature != temp.nature){ //敌人
                            if(i < this.i){
                                numOfUp++;
                            }else{
                                numOfDown++;
                            }
                            if(j < this.j){
                                numOfLeft ++;
                            }else{
                                numOfRight++;
                            }
                        }
                    }
                }
            }
        }
        if(numOfLeft < numOfRight){
            left =  false;
        }
        if(numOfUp < numOfDown){
            up = false;
        }
        int count = 0;
        //设置坐标
        while (true){
            int x = this.i;
            int y = this.j;
            Random random = new Random();
            int choose = random.nextInt(4);
            switch(choose){//有等可能随机的向x方向、y方向、xy和方向以及反方向前进
                case 0:x=getX(up,x);break;
                case 1:y=getY(left,y);break;
                case 2:{
                    x=getX(up,x);
                    y=getY(left,y);
                }break;
                default:{
                    Random random1=new Random();
                    int choose1=random1.nextInt(3);
                    switch (choose1){//反方向的可能中也等可能的向x方向，y方向和xy和方向前进
                        case 0:x=getX(!up,x);break;
                        case 1:y=getY(!left,y);break;
                        default:{
                            x=getX(!up,x);
                            y=getY(!left,y);
                        }break;
                    }
                }break;
            }
            Creature temp = ground.getCreature(x, y);
            if (temp != null) {
                if (!temp.isAlive()) {//前进方向上为墓碑无法前进
                    count++;
                }
            }else {
                pos[0] = x;
                pos[1] = y;
                break;
            }
            if(count == 10){//随机10次后都无法行动，选择周围八个格子中可以行走的格子前进
                for(int m = this.i - 1; m < this.i + 1 ; m++) {
                    for (int n = this.j - 1; n < this.j + 1; n++) {
                        if (m >= 0 && m < 10 && n >= 0 && n < 20) {
                            Creature t = ground.getCreature(m, n);
                            if (t == null) {
                                pos[0] = m;
                                pos[1] = n;
                            }
                        }
                    }
                }
                return pos;
            }
        }
        return pos;
    }

    private int getY(boolean left, int y) {
        if(left){
            y--;
            if(y < 0) y = 0;
        }else{
            y++;
            if(y > 19) y = 19;
        }
        return y;
    }

    private int getX(boolean up, int x) {
        if(up){
            x--;
            if(x < 0) x = 0;
        }else{
            x++;
            if(x > 9) x = 9;
        }
        return x;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public boolean getNature(){
        return this.nature;
    }

    public void lostBlood(int i){
        this.currentHP -= i;
        if(this.currentHP <= 0){
            this.allHP = 0;
            this.alive = false; //死亡
        }
    }

    public int getAttack(){
        return this.attack;
    }

    public int getDefence(){
        return this.defence;
    }

    public void killSelf(){
        this.alive = false; //死亡
    }

    public void setCritical(){
        this.critical=true;
    }

    public boolean isCritical() {
        return critical;
    }

    public void attack(Creature enemy, Canvas canvas) {
        if(!this.isAlive() || !enemy.isAlive()) return;
        int damage = this.attack - enemy.defence;
        if(critical){
            damage*=2;
            critical=false;
        }
        if(damage <= 0) damage = 0;
        enemy.lostBlood(damage);
        int enemy_x = enemy.i;
        int enemy_y = enemy.j; //坐标
        //根据坐标进行绘图，进行攻击显示
        if(canvas != null)
            drawAttack(enemy_x,enemy_y,canvas);
    }

    public void drawAttack(int t_x ,int t_y,Canvas canvas) {
        int cur_i = this.i;
        int cur_j = this.j;
        //考虑8个方向
        synchronized (canvas) {
            if (cur_j == t_y && cur_i < t_x) //上
            {
                for (int ti = cur_i * 50 + 25; ti < t_x * 50 + 25; ti += 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battleImage, this.j * 50 + 25, ti);
                }
            } else if (cur_j == t_y && cur_i > t_x) {//下
                for (int ti = cur_i * 50 + 25; ti > t_x * 50 + 25; ti -= 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battleImage, this.j * 50 + 25, ti);
                }
            } else if (cur_i == t_x && cur_j < t_y) {//右
                for (int tj = cur_j * 50 + 25; tj < t_y * 50 + 25; tj += 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battleImage, tj, this.i * 50 + 25);
                }
            } else if (cur_i == t_x && cur_j > t_y) {//左
                for (int tj = cur_j * 50 + 25; tj > t_y * 50 + 25; tj -= 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battleImage, tj, this.i * 50 + 25);
                }
            } else if (cur_i > t_x && cur_j < t_y) //左上
            {
                for (int ti = cur_i * 50 + 25, tj = cur_j * 50 + 25; ti > t_x * 50 + 25 && tj < t_y * 50 + 25; ti -= 20, tj += 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battleImage, tj, ti);
                }
            } else if (cur_i > t_x && cur_j > t_y) //左下
            {
                for (int ti = cur_i * 50 + 25, tj = cur_j * 50 + 25; ti > t_x * 50 + 25 && tj > t_y * 50 + 25; ti -= 20, tj -= 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battleImage, tj, ti);
                }
            } else if (cur_i < t_x && cur_j < t_y) //右上
            {
                for (int ti = cur_i * 50 + 25, tj = cur_j * 50 + 25; ti < t_x * 50 + 25 && tj < t_y * 50 + 25; ti += 20, tj += 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battleImage, tj, ti);
                }
            } else if (cur_i < t_x && cur_j > t_y) //右下
            {
                for (int ti = cur_i * 50 + 25, tj = cur_j * 50 + 25; ti < t_x * 50 + 25 && tj > t_y * 50 + 25; ti += 20, tj -= 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battleImage, tj, ti);
                }
            }
        }
    }

    public int getCurrentHP(){
        return this.currentHP;
    }

    public void addBlood(int blood){
        if(this.alive == true){
            this.currentHP += blood;
            if(this.currentHP >= this.allHP){
                this.currentHP = this.allHP; //相等
            }
        }
    }
    public void skill(MyMap map,Canvas canvas){

    }

    public String getName() {
        return name;
    }
}

