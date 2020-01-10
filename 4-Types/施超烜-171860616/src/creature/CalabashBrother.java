package creature;
public class CalabashBrother extends Creature{
    private int rank;
    public CalabashBrother(){
        super(Race.CalabashBrother);
        rank=-1;
        this.symbol="  ";
    }
    public CalabashBrother(int rank){
        super(Race.CalabashBrother);
        this.rank=rank;
        switch(this.rank){
            case 1:this.symbol="一";break;
            case 2:this.symbol="二";break;
            case 3:this.symbol="三";break;
            case 4:this.symbol="四";break;
            case 5:this.symbol="五";break;
            case 6:this.symbol="六";break;
            case 7:this.symbol="七";break;
            default:this.symbol="  ";break;
        }
    }
    public void setRank(int rank){
        this.rank=rank;
        switch(this.rank){
            case 1:this.symbol="一";break;
            case 2:this.symbol="二";break;
            case 3:this.symbol="三";break;
            case 4:this.symbol="四";break;
            case 5:this.symbol="五";break;
            case 6:this.symbol="六";break;
            case 7:this.symbol="七";break;
            default:this.symbol="  ";break;
        }
    }
    @Override
    public void followOrder(Creature from,int x,int y){
        if(from.getRace()==Race.Grandfather){
            rushto(x, y);
        }
    }
}