public class Ground{
    public static Tile[] tiles = new Tile[7];
    {
        for(int i = 0; i < 7; i++){
            //System.out.println(1);
            tiles[i] = new Tile();
            tiles[i].setTile(i);
        }
    }
    public static Tile next(int pX){
        return tiles[pX+1];
    }

    public static void ClearAll(){
        for(Tile t:tiles){
            t.isTaken = false;
        }
    }

    public static void PrintRank(){
        for(int i = 0; i < 7; i++){
            tiles[i].calabashBoy.printRank();
            System.out.println((tiles[i].calabashBoy.pX+1));
        }
    }
    public static void PrintColor(){
        for(int i = 0; i < 7; i++){
            tiles[i].calabashBoy.printColor();
            System.out.println((tiles[i].calabashBoy.pX+1));
        }
    }
}