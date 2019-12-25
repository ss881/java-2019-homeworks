package huluwa.creature;


import huluwa.util.Position;
import huluwa.zhenfa.Zhenfa;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Grandpa extends Creature implements Cheerable {
    public Grandpa() {
        super("爷爷", new ImageIcon("pics/爷爷.jpg").getImage());
//        super("爷爷", ImageLoader.getImage("pics/爷爷.jpg"));
    }
    @Override
    public String cheer() {
        // return cheer info
        return new Date() + "\n" + getName() + ": 加油啊，孩子们！\n";
    }

    public void sortHuluwa(ArrayList<Huluwa> huluwas) {
       for(int i = 0; i < huluwas.size() - 1; i++) {
           for(int j = 0; j < huluwas.size() - 1 - i; j++) {
               // let huluwa compare himself with another
               if(huluwas.get(j).compareTo(huluwas.get(j+1)) > 0) {
                   // Position exchange
                   swapHuluwa(huluwas, j, j+1);
               }
           }
       }
    }

    // changshe form is allowed
    public void buZhen(Position[][] positions, ArrayList<Huluwa> huluwas) {
        Zhenfa.CHANG_SHE.buZhen(positions, huluwas, new Position(1, 1));
    }

    private void swapHuluwa(ArrayList<Huluwa> huluwas, int i, int j) {
        // huluwa change his own position by himself
        huluwas.get(i).swapPosWith(huluwas.get(j));
        // change huluwa position in the arraylist
        Huluwa h = huluwas.get(i);
        huluwas.set(i, huluwas.get(j));
        huluwas.set(j, h);
    }

    public void shuffleHuluwa(ArrayList<Huluwa> huluwas) {
        Random random = new Random();
        for(int i = 0; i < huluwas.size(); i++) {
            // position exchange
            swapHuluwa(huluwas, i, random.nextInt(huluwas.size()));
        }
    }
}
