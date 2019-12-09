package com.wars;

public class Enemys {
    Enemy Xiezi = new Enemy('X');
    Enemy Louluo[]=new Enemy[20];

    public Enemys(){
        /*for(Enemy e:Louluo){
            e=new Enemy('#');
            e.setPos(-1,-1);
        }*/
        for(int i=0;i<20;i++){
            Louluo[i]=new Enemy('#');
            Louluo[i].setPos(-1,-1);
        }
        Xiezi.setPos(11,7);
    }

    public void louluoDefaultPos(){
        for(int i=0;i<20;i++){
            Louluo[i].setPos(-1,-1);
        }
    }

    public class Enemy extends Organism{

        public Enemy(char c){
            ch=c;
        }
    }

}
