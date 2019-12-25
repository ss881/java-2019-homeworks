package creature;


import controller.MyMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class CalabashBrother extends Creature {
    private int rank;

    public CalabashBrother(int i,int x,int y) {
        super(x,y);
        rank = i;
        this.attack = 15;
        this.defence = 5;
        switch (rank)
        {
            case 0:this.attack = 18;this.defence = 5; break;
            case 1:this.attack = 20; this.defence = 0;break;
            case 2:this.attack = 10;this.defence = 10;break;
        }
        //this.imageURL = this.getClass().getClassLoader().getResource(new String("pic/"+ (this.rank+1) +".jpg"));
        this.image =  new Image("pic/"+Integer.toString(rank+1)+".jpg",50,50,false,false);
        switch(rank){
            case 0:name="大娃";break;
            case 1:name="二娃";break;
            case 2:name="三娃";break;
            case 3:name="四娃";break;
            case 4:name="五娃";break;
            case 5:name="六娃";break;
            case 6:name="七娃";break;
        }
        nature = true;
        this.initSkillImage();
    }

    private void initSkillImage(){
        if(this.rank == 3){
            this.skillImage = new Image(this.getClass().getClassLoader().getResource(new String("pic/huowa.jpg")).toString(),150,150,false,false);
        }
        else if(this.rank == 4){
            this.skillImage = new Image(this.getClass().getClassLoader().getResource(new String("pic/shuiwa.jpg")).toString(),150,150,false,false);
        }
        else if(this.rank == 6){
            this.skillImage = new Image(this.getClass().getClassLoader().getResource(new String("pic/ziwa.png")).toString(),20,20,false,false);
        }
    }

    @Override
    public void skill(MyMap map, Canvas canvas){ //使用技能
        //每个不同的葫芦娃的技能是不一样的
        if(this.rank==0){//大娃力大无穷，可以无限增加攻击力
            if (!this.alive) return;
            this.attack+=5;
        }else if(this.rank==1){//二娃千里眼顺风耳，能够发现敌人弱点使下一次攻击暴击
            if (!this.alive) return;
            this.setCritical();
        }else if(this.rank==2){//三娃钢筋铁骨，拥有最高的防御力，并且能够恢复自己血量
            if (!this.alive) return;
            this.addBlood(10);
        }else if(this.rank == 3 || this.rank == 4){ //四娃喷火，五娃喷水，能够在自己周围造成范围伤害
            if (!this.alive) return;
            int t_x = this.j;
            if(t_x == 0) t_x = 1;
            int t_y = this.i;
            if(t_y == 0) t_y = 1;
            canvas.getGraphicsContext2D().drawImage(this.skillImage,t_x * 50 - 50, t_y * 50 - 50);
            for(int m = i - 1; m <= i + 1 ;m ++ ){
                for(int n = j - 1; n <= j + 1; n++){
                    if(m >= 0 && m <= 9 && n >=0 && n <= 19) {
                        Creature temp = map.getCreature(m, n);
                        if (temp != null) {
                            if (temp.isAlive() && !temp.getNature()) { //正义的
                                temp.lostBlood(30);
                            }
                        }
                    }
                }
            }
        }else if(this.rank == 5) {//六娃能隐身，隐身后攻击力翻倍但防御力清零
            if (!this.alive) return;
            this.attack=30;
            this.defence=0;
        }else if(this.rank == 6){ //七娃释放宝贝葫芦中的力量，对敌方所有敌人造成伤害
            if(!this.alive) return;
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 20; j++){
                    Creature temp = map.getCreature(i,j);
                    if(temp != null){
                        if(temp.isAlive() && !temp.getNature()) { //正义的
                            temp.lostBlood(15);
                            canvas.getGraphicsContext2D().drawImage(this.skillImage, j * 50 + 15, i * 50 + 15);
                        }
                    }
                }
            }
        }
    }
}