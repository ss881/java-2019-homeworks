package hulubro.formation;

import hulubro.life.Life;
import hulubro.map.Grid;

public class Formation {
    int M,N;
    Grid[][] grids;
    Formation(){
    }
    public int get_M(){
        return this.M;
    }
    public int get_N(){
        return this.N;
    }
    public Boolean is_empty(int i,int j){
        return grids[i][j].empty();
    }
    public Life get_M_N(int M, int N){
        assert(!grids[M][N].empty());
        return grids[M][N].get_life();
    }
}
