package hulubro.formation;

import hulubro.life.Life;
import hulubro.map.Grid;

public class CraneWing extends Formation{
    public CraneWing(Life[] life){
        this.M=7;
        this.N=4;
        this.grids=new Grid[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                grids[i][j]=new Grid(i,j);
            }
        }
        grids[0][3].LifeIn(life[0]);
        grids[1][2].LifeIn(life[1]);
        grids[2][1].LifeIn(life[2]);
        grids[3][0].LifeIn(life[3]);
        grids[4][1].LifeIn(life[4]);
        grids[5][2].LifeIn(life[5]);
        grids[6][3].LifeIn(life[6]);
    }
}