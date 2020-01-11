package space;


import creature.*;
import exception.HuluwaOutOfNumException;
import team.HeroTeam;
import team.HeroTeamFactory;
import team.VillainTeam;
import team.VillainTeamFactory;

public class Space {
    public HeroTeam heros;
    public VillainTeam villains;
    public Tile[][] floor;
    int N;

    public Space(int n) throws HuluwaOutOfNumException {
        N = n;
        heros = new HeroTeamFactory(this).create();
        villains = new VillainTeamFactory(this).create();
        floor = new Tile[n][n];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                floor[i][j] = new Tile(i, j);

        heros.born();
        villains.born();
    }

    public int getN() {
        return N;
    }

    public void show() {
        for (int i = 0; i < this.N; i++)
            System.out.print("--");
        System.out.println();
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                this.floor[j][i].show();
            }
            System.out.print('\n');
        }
        for (int i = 0; i < this.N; i++)
            System.out.print("--");
        System.out.println();
    }

    public void move(int src_x, int src_y, int dest_x, int dest_y) {
        Tile.swap(floor[src_x][src_y], floor[dest_x][dest_y]);
    }
}
