package chessman;

import chessboard.Position;

import java.util.ArrayList;
import java.util.List;

public class MonsterList {
    final static int numOfPawns = 7;
    final String nameOfPawns[] = {"蝎子精", "阿大", "阿二", "阿三", "阿四", "阿五", "阿六"};

    public List<Pawn> evilSoldier;

    public MonsterList(){
        evilSoldier = new ArrayList<Pawn>();
        Position temp = new Position(-1, -1);

        for (int i = 0; i < numOfPawns; i++) {
            evilSoldier.add(new Pawn(nameOfPawns[i], temp, false));
        }
    }
    public static void main(String[] args) {
        MonsterList test=new MonsterList();
        for(Pawn x:test.evilSoldier){
            System.out.println(x.toString());
        }
    }
}
