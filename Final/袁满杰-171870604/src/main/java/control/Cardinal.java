package control;

import creature.CreatureFactory;
import exception.CharacterErrorException;
import exception.HuluwaOutOfNumException;
import exception.ZhenfaIDOutOfNumException;
import javafx.scene.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import space.Space;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class Cardinal implements Runnable {
    Space space;
    HBox group;
    static boolean started=false;
    static boolean first=true;
    Label label;
    public Cardinal() throws HuluwaOutOfNumException, ZhenfaIDOutOfNumException, CharacterErrorException, URISyntaxException {
        CreatureFactory.load_config(Cardinal.class.getResource("/config.txt").toURI());
        group=new HBox();
        space=new Space(21);
        Random r = new Random();
        int r1 = r.nextInt(7);
        space.heros.zhenfa(r1);
        int r2 = r.nextInt(7);
        space.villains.zhenfa(r2);
        space.save_zhenfa(r1,r2);

        label=new Label("获胜");
        label.setVisible(false);

        // Create and position camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                new Rotate(0, Rotate.Y_AXIS),
                new Rotate(-40, Rotate.X_AXIS),
                new Translate(10, 8, -35));

        AmbientLight light=new AmbientLight();
        // Build the Scene Graph
        space.getChildren().addAll(camera,light);
        // Use a SubScene
        SubScene subScene = new SubScene(space, 1600,800);
        subScene.setFill(Color.ALICEBLUE);
        subScene.setCamera(camera);
        group.getChildren().add(subScene);
//        space.show();

    }
    public HBox getGroup(){return group;}
    public void init() throws CharacterErrorException, ZhenfaIDOutOfNumException, HuluwaOutOfNumException {
        if(first)
        {
            first=false;
            return;
        }
        space.init();
        AmbientLight light=new AmbientLight();
        space.getChildren().add(light);
        Random r = new Random();
        int r1 = r.nextInt(7);
        space.heros.zhenfa(r1);
        int r2 = r.nextInt(7);
        space.villains.zhenfa(r2);
        space.save_zhenfa(r1,r2);
    }
    public void save(File path) throws IOException {
        space.save(path);
    }
    public void preload(File path) throws ClassNotFoundException, ZhenfaIDOutOfNumException, HuluwaOutOfNumException, CharacterErrorException, IOException {
        space.load_zhenfa(path);
    }
    public Thread load(File path) throws IOException, ClassNotFoundException, InterruptedException, CharacterErrorException, ZhenfaIDOutOfNumException, HuluwaOutOfNumException {
        return new Thread(space.load(path));
    }
    public void run() {
        if(started)
            return;
        space.clear_log();
        started=true;
        space.heros.start();
        space.villains.start();

        int i=1;
        do{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.printf("----round %d-------\n",i++);
//            space.show();
            if(space.villains.check_alive()==0){
                space.win=1;
            }else if(space.heros.check_alive()==0){
                space.win=-1;
            }
        }while(space.win==0);
//        space.show();
//        if(space.win==-1)
//            space.heros.set_visiable(false);
//        else if(space.win==1)
//            space.villains.set_visiable(false);
        System.out.printf("------finished!-------\n");
        space.heros.interrupt();
        space.villains.interrupt();
        started=false;

    }
}
