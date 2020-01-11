package chessman;

import chessboard.Position;

import java.util.ArrayList;
import java.util.List;

public class JusticeList {
    final static int numOfBros = 7;
    final String nameOfBros[] = {"老大", "老二", "老三", "老四", "老五", "老六", "老七"};

    public List<Huluwa> justiceSoldier;

    public JusticeList() {
        justiceSoldier = new ArrayList<Huluwa>();
        Position temp = new Position(-1, -1);
        for (int i = 0; i < numOfBros; i++) {
            justiceSoldier.add(new Huluwa(nameOfBros[i], temp, true ));
        }

    }

    public static void main(String[] args) {
        JusticeList test=new JusticeList();
        for(Huluwa x:test.justiceSoldier){
            System.out.println(x.toString());
        }
    }
}
