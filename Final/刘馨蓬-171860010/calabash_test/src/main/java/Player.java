//package main.java;

public class Player {
    private int num;
    private Mutrix mutrix;
    private int id;
    public Player(MutrixType type,int id){
        this.id=id;
        if(id==1) {
            mutrix = new Mutrix(type, new Point(0, 0),1);
            this.num=mutrix.getCount();
        }
        else {
            mutrix = new Mutrix(type, new Point(6, 6),2);
            this.num = mutrix.getCount();
        }

    }
    public Point getPos(int i){
        return mutrix.getPos(i);
    }
    public void setNum(int num){
        this.num=num;
    }
    public int getNum(){
        return this.num;
    }
}
