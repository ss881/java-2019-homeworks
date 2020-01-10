public class RemoveBody extends Buff{
    RemoveBody(Creature target){
        super(target);
        remman=5;
    }
    void finalEffect(){
        target.reMove();
    }
}