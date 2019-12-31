package main.java.sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Replayer implements MyObservable,Runnable{
    private static List<MyObserver> list;
    private File gamelog;

    RouteNotifier rn;
    BattleNotifier bn;
    public Replayer(File gamelog,MyObserver o)
    {
        this.gamelog=gamelog;
        rn=new RouteNotifier();
        bn=new BattleNotifier();
        list=new ArrayList<>();
        registerObserver(o);
    }
    @Override
    public void registerObserver(MyObserver o) {
        list.add(o);
    }

    @Override
    public void routeNotify() {
        list.get(0).routeUpdate(rn);
    }

    @Override
    public void battleNotify() {
        list.get(0).battleUpdate(bn);
    }

    @Override
    public void logNotify(int type, int a, int b, int c, int d) throws IOException {
        //useless here
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(gamelog));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String cmd="";
        while (true) {
            try {
                if ((cmd = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(cmd);
            String[] cmdSplit = cmd.split("\\s+");
            if (cmdSplit[0].equals("MOV")) {
                rn.id=Integer.parseInt(cmdSplit[1]);
                rn.tarX=Integer.parseInt(cmdSplit[2]);
                rn.tarY=Integer.parseInt(cmdSplit[3]);
                routeNotify();
            } else if (cmdSplit[0].equals("KIL")) {
                bn.killer_id=Integer.parseInt(cmdSplit[1]);
                bn.killed_id=Integer.parseInt(cmdSplit[2]);
                battleNotify();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
