import java.util.Random;

public class CalabashBoy{
    static Ground ground = new Ground();
    private String Color;
    private int Rank;
    public int pX;
    CalabashBoy(){
        Color = "红色";
        Rank = 1;
        pX = 0;
    }
    CalabashBoy(String Color, int Rank){
        this.Color = Color;
        this.Rank = Rank;
        pX = 0;
    }

    public void setID(String Color, int Rank){
        this.Color = Color;
        this.Rank = Rank;
    }

    public String getColor(){
        return Color;
    }

    public int getRank(){
        return Rank;
    }

    public void printRank(){
        String r = "";
        switch(Rank){
            case 1: r = "    老大： "; break;
            case 2: r = "    老二： "; break;
            case 3: r = "    老三： "; break;
            case 4: r = "    老四： "; break;
            case 5: r = "    老五： "; break;
            case 6: r = "    老六： "; break;
            case 7: r = "    老七： "; break;
            default: ;
        }
        System.out.print(r);
    }

    public void printColor(){
        System.out.print(Color);
    }

    private void gotoPosition_bubble(int pX){
        int p = this.pX;
        this.pX = pX;
        Ground.tiles[pX].calabashBoy = this;
        printRank();
        System.out.println((p+1) + "->" + (this.pX+1));
    }

    private void gotoPosition_binary(int pX){
        int p = this.pX;
        this.pX = pX;
        Ground.tiles[pX].calabashBoy = this;
        printColor();
        System.out.println((p+1) + "->" + (this.pX+1));
    }

    private void randomGotoPosition(int pX){
        this.pX = pX;
        Ground.tiles[pX].calabashBoy = this;
    }

    public void randomStand(){
        while(true){
            Random rand = new Random();
            int r = rand.nextInt(7);
            if(!positionTaken(r)){
                randomGotoPosition(r);
                Ground.tiles[r].isTaken = true;
                break;
            }
        }

    }

    private boolean positionTaken(int r){
        return Ground.tiles[r].isTaken;
    }

    private CalabashBoy LookBack(){
        return Ground.next(pX).calabashBoy;
    }

    private boolean CmpWithNext(){
        CalabashBoy cala = LookBack();
        return Rank < cala.Rank;
    }

    public void SwapWithNext_bubble(){
        if(pX == Ground.tiles.length-1)return;
        if(CmpWithNext()) return;
        CalabashBoy next = LookBack();
        gotoPosition_bubble(pX+1);
        next.gotoPosition_bubble(pX-1);
    }

    public void SwapWithNext_binary(){
        if(pX == Ground.tiles.length-1)return;
        if(CmpWithNext()) return;
        CalabashBoy next = LookBack();
        gotoPosition_binary(pX+1);
        next.gotoPosition_binary(pX-1);
    }

    
}