package GameModule;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

public class LoadRep implements Runnable{
    private static final Image cb1 = new Image("1.png");
    private static final Image cb2 = new Image("2.png");
    private static final Image cb3 = new Image("3.png");
    private static final Image cb4 = new Image("4.png");
    private static final Image cb5 = new Image("5.png");
    private static final Image cb6 = new Image("6.png");
    private static final Image cb7 = new Image("7.png");
    private static final Image[] cb = {cb1, cb2, cb3, cb4, cb5, cb6, cb7};
    private static final Image cbullet = new Image("cbullet.png");
    private static final Image mbullet = new Image("mbullet.png");
    private static final Image flame = new Image("flame.png");
    private static final Image water = new Image("water.png");
    private static final Image stinger = new Image("stinger.png");
    private static final Image[] bullets = {cbullet, mbullet, flame, water, stinger};
    private static final Image dead = new Image("tomb.png");
    private static final Image defeat = new Image("defeat.png");
    private static final Image grandfather = new Image("grandfather.png");
    private static final Image lackey = new Image("lackey.png");
    private static final Image scorpion = new Image("scorpion.png");
    private static final Image snake = new Image("snake.png");
    private static final Image victory = new Image("victory.png");
    private static final Image effect = new Image("effect.png");

    private List<Replay> replayList = new ArrayList<>();
    private GraphicsContext gc;
    private TextArea textArea;
    private boolean isReplaying = true;

    public LoadRep(File log, GraphicsContext gc, TextArea textArea) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(log), StandardCharsets.UTF_8));
            Scanner scanner = new Scanner(reader);
            this.gc = gc;
            this.textArea = textArea;
            Replay replay = new Replay();
            String head;
            while(scanner.hasNextLine()) {
                head = scanner.next();
                if(head.equals("==")) {
                    replayList.add(replay);
                    replay = new Replay();
                }

                else if(head.equals("Victory")) {
                    replay.addElement(new Unit(victory,330,100,300,300));
                    replayList.add(replay);
                    replay = new Replay();
                }

                else if(head.equals("Defeat")) {
                    replay.addElement(new Unit(defeat,330,100,300,300));
                    replayList.add(replay);
                    replay = new Replay();
                }

                else if(head.equals("NoResult")) {
                    replayList.add(replay);
                    replay = new Replay();
                }

                else if(head.equals("C")) {
                    int index = scanner.nextInt();
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    float ratio = scanner.nextFloat();
                    replay.addElement(new Unit(cb[index],x,y,60,60,ratio));
                }

                else if(head.equals("G")) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    float ratio = scanner.nextFloat();
                    replay.addElement(new Unit(grandfather, x, y,60,60,ratio));
                }

                else if(head.equals("L")) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    float ratio = scanner.nextFloat();
                    replay.addElement(new Unit(lackey, x, y,60,60,ratio));
                }

                else if(head.equals("SC")) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    float ratio = scanner.nextFloat();
                    replay.addElement(new Unit(scorpion, x, y,60,60,ratio));
                }

                else if(head.equals("SN")) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    float ratio = scanner.nextFloat();
                    replay.addElement(new Unit(snake, x, y,60,60,ratio));
                }

                else if(head.equals("B")) {
                    int index = scanner.nextInt();
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    if(index == 4)
                        replay.addElement(new Unit(bullets[index],x,y + 25,60,10));
                    else if (index == 2 || index == 3)
                        replay.addElement(new Unit(bullets[index],x,y + 20,60,20));
                    else
                        replay.addElement(new Unit(bullets[index],x,y + 20,20,20));
                }

                else if(head.equals("T")) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    replay.addElement(new Unit(dead, x, y,60,60));
                }

                else if(head.equals("E")) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    replay.addElement(new Unit(effect, x, y,40,40));
                }

                else if(head.equals("M"))
                    replay.addText(scanner.next());

                else {
                    System.out.println(head);
                    replay.addText(head);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        for(Replay replay : replayList) {
            replay.display(gc, textArea);
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!isReplaying) {
                break;
            }
        }
        textArea.appendText("回放结束\n");
    }

    public void endReplay() {
        isReplaying = false;
    }
}