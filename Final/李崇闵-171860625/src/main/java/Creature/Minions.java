package Creature;

//小喽啰类
public class Minions extends Evil {
    //鹤翼阵
    public void CraneWing(Scorpion scorpion, int order){
        this.field=scorpion.field;
        if(order<3){
            MoveTo(scorpion.x-order-1,scorpion.y-order-1);
        }
        else{
            MoveTo(scorpion.x+order-2,scorpion.y-order+2);
        }
        this.field.addCreature(this,this.x,this.y);
    }
}
