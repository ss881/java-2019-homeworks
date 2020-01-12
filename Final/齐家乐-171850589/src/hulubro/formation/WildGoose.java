package hulubro.formation;

import hulubro.life.Life;
import hulubro.map.Grid;

class WildGoose extends Formation{
    WildGoose(Life[] life){
        this.M=7;
        this.N=7;
        this.grids=new Grid[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                grids[i][j]=new Grid(i,j);
            }
        }
        grids[0][6].LifeIn(life[0]);
        grids[1][5].LifeIn(life[1]);
        grids[2][4].LifeIn(life[2]);
        grids[3][3].LifeIn(life[3]);
        grids[4][2].LifeIn(life[4]);
        grids[5][1].LifeIn(life[5]);
        grids[6][0].LifeIn(life[6]);
    }
}