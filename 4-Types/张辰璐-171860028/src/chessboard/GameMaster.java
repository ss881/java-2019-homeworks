package chessboard;
import chessman.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameMaster {
    Ground world;
    List<Huluwa> justiceSoldier;
    List<Pawn> evilSoldier;

    final static int numOfBros = 7;
    final static int numOfPawns = 7;

    public GameMaster(){
        generateChessboard();
        generateChessman();
        setInitFormation();
    }
    public void generateChessman() {
        JusticeList test0=new JusticeList();
        MonsterList test1=new MonsterList();
        justiceSoldier=test0.justiceSoldier;
        evilSoldier=test1.evilSoldier;
    }

    private void generateChessboard() {
//        String input = JOptionPane.showInputDialog("Please input the size of the map, M =:");
//        int M = Integer.parseInt(input);
//        input = JOptionPane.showInputDialog("Please input the size of the map, N =:");
//        int N = Integer.parseInt(input);
//        assert (N >= numOfBros);
//        assert (N >= numOfPawns);
//        assert (M >= numOfBros);
//        assert (M >= numOfPawns);
//        world = new Ground(N, M);
        world = new Ground(8, 8);
    }
    public void setInitFormation() {
        Formation layOut = new Formation();
        layOut.setDatumPoint( 4, world.N/2);
        layOut.facePositive();

        for (int i = 0; i < numOfBros; i++) {
            Creature x = justiceSoldier.get(i);
            Position dest = layOut.getCraneWingI(i);
            //change chessman src and dst(same)
            x.setSite(dest);
//            x.setDst(dest);
            //put them on the chessboard
            world.tryAdd(x, dest);
        }

        for (int i = 0; i < numOfPawns; i++) {
            Creature x = evilSoldier.get(i);
            //change chessman src and dst(same)
            x.setSite(new Position(world.M-1, i));
//            x.setDst(new Position(world.M-1, i));
            //put them on the chessboard
            world.tryAdd(x, new Position(world.M-1, i));
        }
    }



    public void changeInitFormation() {
        Formation layOut = new Formation();
        layOut.setDatumPoint( 4, world.N/2);
        layOut.facePositive();

        for (int i = 0; i < numOfBros; i++) {
            //change
            Creature x=justiceSoldier.get(i);
            Position dest = layOut.getArrowI(i);
//            System.out.println("layout dst:"+dest);
            world.tryDelete(x.getSite());
//            x.setSrc(dest);
//            x.setDst(dest);
            world.tryAdd(x, dest);
        }
    }

    public static void main(String[] args) {
        GameMaster gm=new GameMaster();
        gm.world.show();
        gm.changeInitFormation();
        gm.world.show();
    }

}

