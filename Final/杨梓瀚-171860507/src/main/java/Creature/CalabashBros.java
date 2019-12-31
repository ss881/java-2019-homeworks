package Creature;

import Battle.*;
import Formation.Formation;
import Formation.Type;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class CalabashBros {
    public static final int numOfCalabash = 7;

    private static final String[] imageFiles = new String[]{
        "src/main/resources/pic/hlw0.png",
        "src/main/resources/pic/hlw1.png",
        "src/main/resources/pic/hlw2.png",
        "src/main/resources/pic/hlw3.png",
        "src/main/resources/pic/hlw4.png",
        "src/main/resources/pic/hlw5.png",
        "src/main/resources/pic/hlw6.png"
    };

    private int indexOfFormation = 0;

    private CalabashBro[] calabashBros;

    private Thread[] threads;

    public CalabashBros(Object lock){
        calabashBros = new CalabashBro[numOfCalabash];
        threads = new Thread[numOfCalabash];
        for (int i = 0; i < numOfCalabash; i++){
            calabashBros[i] = new CalabashBro(COLOR.values()[i], lock, imageFiles[i]);
            threads[i] = new Thread(calabashBros[i]);
        }
    }

    public void initTurn(){
        for (int i = 0; i < numOfCalabash; i++){
            calabashBros[i].initTurn();
        }
    }

    public void run(){
        for (int i = 0;i < numOfCalabash; i++)
            threads[i].start();
    }

    public void queue(){
        Type[] types = Type.values();
        do {
            indexOfFormation = ((int)(Math.random() * 10)) % types.length;
        }while (!types[indexOfFormation].checkCharacter());

        /*for (int i = indexOfFormation; i <types.length; i=(i+1)%types.length){
            if (types[i].checkCharacter()){
                indexOfFormation = i;
                break;
            }
        }*/
        System.out.println("葫芦娃:" + types[indexOfFormation]);
        List<Pair<Integer, Integer>> position = Formation.getFormation(types[indexOfFormation]);
        for(int i = 0; i < numOfCalabash; i++) {
            //getCalBro(i).setPosition(p.get(i).getKey() - 1, p.get(i).getValue() - 1);
            assert position != null;
            Battle.setPosition(new Position<CalabashBro>(getCalBro(i), position.get(i).getKey(), position.get(i).getValue()));
        }
    }

    public CalabashBro getCalBro(int i){
        return calabashBros[i];
    }

    public List<ImageView> getAllImageView(){
        List<ImageView> list = new ArrayList<>();
        for (int i = 0; i < numOfCalabash; i++){
            list.add(calabashBros[i].getImageView());
        }
        return list;
    }

    private void setCalBro(CalabashBro c, int i){
        calabashBros[i] = c;
    }
}
