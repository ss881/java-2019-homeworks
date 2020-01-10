package hw3;
public class Field {
    private static int N = 12;
    private Tile [][] tiles;

    public Field(){
        tiles = new Tile[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                tiles[i][j] = new Tile(i, j);
            }
        }
    }

    public int getN(){
        return N;
    }
    public Tile[][] getTiles(){
        return tiles;
    }
    public void show(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                tiles[i][j].show();
            }
            System.out.println();
        }
    }
    public void clear(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                tiles[i][j].clear();
            }
        }
    }
}
