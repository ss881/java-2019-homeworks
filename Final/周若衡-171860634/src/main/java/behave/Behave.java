package behave;


public class Behave implements java.io.Serializable,Cloneable{
    private int sleepTime;
    public Behave(int sleepTime){
        this.sleepTime=sleepTime;
    }
    @Override
    public Object clone() {
        Behave temp = null;
        try{
            temp = (Behave)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return temp;
    }
    public int getSleepTime(){return sleepTime;}
}
