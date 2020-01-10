package chessboard;

import chessman.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Ground {
    public final int N;
    public final int M;
    private List<Grid> nxM_Map;
    private Vector<Position> direction;


    public Ground(int n, int m) {
        N = n;
        M = m;
        nxM_Map = new ArrayList<Grid>();
        for (int i = 0; i < N * M; i++) {
            nxM_Map.add(new Grid());
        }
        direction = new Vector<Position>();
        direction.add(new Position(1, 0));
        direction.add(new Position(-1, 0));
        direction.add(new Position(0, 1));
        direction.add(new Position(0, -1));
    }

    public void show() {
        for (int i = 0; i < M; i++)
            System.out.printf("------");
        System.out.println("");

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < M; i++) {
                Grid g = nxM_Map.get(j * M + i);
                //System.out.printf("("+j+","+i+")");

                if (!g.isEmpty())
                    System.out.printf("|" + "%3s", g.get().getName());
                else
                    System.out.printf("|" + "%5s", " ");
            }
            System.out.println("|");
            for (int i = 0; i < M; i++)
                System.out.printf("------");
            System.out.println();
        }
    }

    public <T extends Creature> boolean tryAdd(T aChessman, Position dst) {
        //limitation on T
        Grid g = nxM_Map.get(dst.getX() + dst.getY() * M);
        if (g.isEmpty()) {
            g.set(aChessman);
            g.get().setLive();
            return true;
        } else return false;
    }

    public boolean tryDelete(Position p) {
        Grid g = nxM_Map.get(p.getX() + p.getY() * M);
        if (!g.isEmpty()) {
            g.clear();
            return true;
        } else return false;
    }


}