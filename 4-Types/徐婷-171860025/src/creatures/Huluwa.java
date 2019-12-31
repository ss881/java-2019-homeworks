package creatures;

public class Huluwa extends HuluwaSide {
    int rank = -1;
    public Huluwa(String name){super(name);}
    public Huluwa(){super();}
    public Huluwa(String name, int x, int y){super(name, x, y);}
    public Huluwa(int rank){this.rank = rank;}
    public void setName(){
        switch (rank){
            case 1: name = "老大";break;
            case 2:name = "老二";break;
            case 3:name = "老三";break;
            case 4:name = "老四";break;
            case 5:name = "老五";break;
            case 6:name = "老六";break;
            case 7:name = "老七";break;
            default:System.err.println("未知rank："+ rank);name = "";
        }
    }

    public int getRank() {
        return rank;
    }
}
