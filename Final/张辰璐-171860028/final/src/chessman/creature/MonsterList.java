package chessman.creature;

import javafx.scene.image.Image;
import chessboard.Constants;

import java.util.ArrayList;
import java.util.List;

public class MonsterList implements Constants{
    public List<Pawn> pawnCollection = new ArrayList<Pawn>();
    private Pawn SHE;
    private Pawn Xiezi;
    private Pawn[] Loulo = new Pawn[LouloNumber];

    public void init() {

        Image image = new Image("xiezi2.png");
        Xiezi.setImage(image);
        Xiezi.setName("蝎子");
        Xiezi.setBlood(10);
        Xiezi.setForce(20);
        Xiezi.setSpeed(1000);

        Image image2 = new Image("SHE.png");
        SHE.setImage(image2);
        SHE.setName("蛇精");
        SHE.setBlood(40);
        SHE.setForce(8);
        SHE.setSpeed(1500);


        pawnCollection.add(SHE);
        pawnCollection.add(Xiezi);

        image = new Image("Loulo.png");
        for (int i = 0; i < LouloNumber; i++) {
            String t = i + "";
            Loulo[i].setName("喽啰" + t);
            Loulo[i].setForce(8);
            Loulo[i].setBlood(18);
            Loulo[i].setImage(image);
            Loulo[i].setSpeed(1000);
            pawnCollection.add(Loulo[i]);
        }
    }
    public MonsterList() {
        SHE = new Pawn();

        Xiezi = new Pawn();
        for (int i = 0; i < LouloNumber; i++) {
            Loulo[i] = new Pawn();
        }
    }
}