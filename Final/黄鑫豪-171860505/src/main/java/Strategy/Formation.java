package Strategy;

import Beings.EvilSon;
import Beings.GourdEva;
import Field.Position;
import Field.TwoDSpace;

import java.util.ArrayList;

public class Formation {
    // wings
    int[] gourdX = {2, 4, 6, 8, 10, 12, 14};
    int[] gourdY = {1, 2, 1, 2, 1, 2, 1};
    // balance
    int[] evilX = {2, 4, 6, 8, 10, 12, 14};
    int[] evilY = {14, 13, 12, 11, 12, 13, 14};
    //grandpa position
    int gpax = 0;
    int gpay = 0;
    //em position
    int emx = 0;
    int emy = 12;
    //ef position
    int efx = 0;
    int efy = 14;


    void applyGourd(TwoDSpace tds_0) {
        ArrayList<GourdEva> gourdevas = tds_0.getGourdevas();
        Position[][] tds = tds_0.getTDS();
        for (int i = 0; i < 7; ++i) {
            gourdevas.get(i).setXY(gourdX[i], gourdY[i]);
            tds[gourdX[i]][gourdY[i]].setHolder(gourdevas.get(i));
        }
        tds_0.getGrandpa().setXY(0, 0);
        tds[0][0].setHolder(tds_0.getGrandpa());
    }

    void applyEvil(TwoDSpace tds_0) {
        ArrayList<EvilSon> evilsons = tds_0.getEvilsons();
        Position[][] tds = tds_0.getTDS();
        for (int i = 0; i < 7; ++i) {
            evilsons.get(i).setXY(evilX[i], evilY[i]);
            tds[evilX[i]][evilY[i]].setHolder(evilsons.get(i));
        }
        tds_0.getEvilmother().setXY(0, 12);
        tds[0][12].setHolder(tds_0.getEvilmother());
        tds_0.getEvilfather().setXY(0, 14);
        tds[0][14].setHolder(tds_0.getEvilfather());
    }
}
