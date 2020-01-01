package cn.edu.nju.huluwa.creature;


import cn.edu.nju.huluwa.position.Position;
import cn.edu.nju.huluwa.huluworld.HuluWorld;
import cn.edu.nju.huluwa.zhenfa.Zhenfa;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Grandpa extends Creature implements Cheerable, Leader {
    public Grandpa(HuluWorld huluWorld, Colour color) {
        super(huluWorld, "爷爷", color);
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
    @Override
    public void buZhen(Position[][] positions, ArrayList<? extends Creature> huluwas) {
        Zhenfa.CHANG_SHE.buZhen(positions, huluwas, new Position(1, 1));
    }

    @Override
    public void setInitialPos(Position[][] positions, ArrayList<? extends Creature> huluwas) {
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

    @Override
    public boolean isEnemy(Creature creature) {
        return creature instanceof Minion || creature instanceof Snake || creature instanceof Scorpion;
    }
}
