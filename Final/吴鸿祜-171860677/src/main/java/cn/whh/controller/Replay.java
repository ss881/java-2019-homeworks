package cn.whh.controller;

import cn.whh.creature.*;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Replay implements Callable<ArrayList<Creature[][]>>
{
    private ArrayList<Image> imageCache;
    //private String pathname;
    private File saveFile;
    private int width;
    private int height;

    public Replay(ArrayList<Image> imageCache, /*String pathname*/File saveFile, int width, int height)
    {
        this.imageCache=new ArrayList<>();
        this.imageCache=imageCache;
        //this.pathname=pathname;
        this.saveFile=saveFile;
        this.width=width;
        this.height=height;
    }

    @Override
    public ArrayList<Creature[][]> call()
    {
        ObjectInputStream in=null;
        ArrayList<Creature> creatures=new ArrayList<>();
        ArrayList<Creature[][]> historyField=new ArrayList<>();
        try {
            //in = new ObjectInputStream(new FileInputStream(new File(pathname)));
            in = new ObjectInputStream(new FileInputStream(saveFile));
            while (true) {
                creatures.add((Creature) in.readObject());
            }
        }catch (IOException e) {
            int row = 0, col = 0, index = 0;
            int frameCount = creatures.size() / (width * height);
            for (int i = 0; i < frameCount; i++) {
                Creature[][] tmp = new Creature[height][width];
                historyField.add(tmp);
            }

            for (int i = 0; i < creatures.size(); i++)
            {
                Creature creature = creatures.get(i);
                /*if(creature instanceof Land) creature.aliveImage=new Image(this.getClass().getResourceAsStream("/land.png"));*/
                if (creature instanceof Huluwa)
                {
                    int id = ((Huluwa) creature).getId();
                    creature.aliveImage = imageCache.get(2 * id - 2);//new Image(this.getClass().getResourceAsStream("/"+Integer.toString(id)+".png"));
                    creature.deadImage = imageCache.get(2 * id - 1);//new Image(this.getClass().getResourceAsStream("/"+Integer.toString(id)+"_dead.png"));
                } else if (creature instanceof Sodier)
                {
                    creature.aliveImage = imageCache.get(14);//new Image(this.getClass().getResourceAsStream("/soldier.png"));
                    creature.deadImage = imageCache.get(15);//new Image(this.getClass().getResourceAsStream("/soldier_dead.png"));
                } else if (creature instanceof Scorpion)
                {
                    creature.aliveImage = imageCache.get(16);//new Image(this.getClass().getResourceAsStream("/scorpion.png"));
                    creature.deadImage = imageCache.get(17);//new Image(this.getClass().getResourceAsStream("/scorpion_dead.png"));
                }else if(creature instanceof Grandpa)
                {
                    creature.aliveImage = imageCache.get(18);
                    creature.deadImage = imageCache.get(19);
                }else if(creature instanceof Snake)
                {
                    creature.aliveImage = imageCache.get(20);
                    creature.deadImage = imageCache.get(21);
                }


                historyField.get(index)[row][col] = creature;
                col++;
                if (col == width)
                {
                    col = 0;
                    row++;
                    if (row == height)
                    {
                        row = 0;
                        index++;
                    }
                }
            }

        }catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
        return historyField;
    }

    /*public static void main(String[] args)
    {
        ArrayList<Image> imageCache=new ArrayList<>();
        String root="./src/main/resources/";
        for(int i=1;i<=7;i++)
        {
            imageCache.add(new Image(root+Integer.toString(i)+".png"));
            imageCache.add(new Image(root+Integer.toString(i)+"_dead.png"));
        }

        imageCache.add(new Image(root+"soldier.png"));
        imageCache.add(new Image(root+"soldier_dead.png"));
        imageCache.add(new Image(root+"scorpion.png"));
        imageCache.add(new Image(root+"scorpion_dead.png"));

        Replay replay=new Replay(imageCache,"./src/main/resources/save1.history",13,10);
        FutureTask<ArrayList<Creature[][]>> futureTask=new FutureTask<ArrayList<Creature[][]>>(replay);
        Thread thread=new Thread(futureTask) ;
        thread.start();

        for(int i=0;i<50;i++) System.out.print("?");
        System.out.println();

        try {
            ArrayList<Creature[][]> arrayList = futureTask.get();
            System.out.println(arrayList.size());
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }*/
}