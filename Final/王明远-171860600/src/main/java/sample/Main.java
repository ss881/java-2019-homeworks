package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.scene.paint.Color.WHITE;


public class Main extends Application {
    static final int size_of_current_space=15;
    static  final int max_size_of_stage=900;
    static  GraphicsContext graphicsContext;
    static  GridPane gridPane;
    static Stage c_stage;
    static SimpleIntegerProperty simpleIntegerProperty=new SimpleIntegerProperty(0);
    static  SimpleIntegerProperty run_flag=new SimpleIntegerProperty(0);
    static SpaceForBattle bufferspace=null;
    SpaceForBattle space=null;
    Mythread_viewer viewer=null;
    Mythread_creature grandpa=null;
    Mythread_creature []brotherstandl=null;
    Mythread_creature scorpion=null;
    Mythread_creature []fish=null;
    Mythread_creature snake=null;
    Replay replay=null;
   AtomicInteger flag_to_reset=new AtomicInteger(0);
    static ArrayList<Image> images;
    static Image  background=null;
    static  void image_load()//get images to save time
    {background=new Image("file:src/main/java/sample/resource/background.png");
        images=new ArrayList<>();
        images.add(new Image("file:src/main/java/sample/resource/blank.png"));
        images.add(new Image("file:src/main/java/sample/resource/grandpa1.png"));
        images.add(new Image("file:src/main/java/sample/resource/shejing1.png"));
        images.add(new Image("file:src/main/java/sample/resource/xiezi1.png"));
        images.add( new Image("file:src/main/java/sample/resource/zayu1.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa1-1.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa2-1.png"));
        images.add( new Image("file:src/main/java/sample/resource/huluwa3-1.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa4-1.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa5-1.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa6-1.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa7-1.png"));
        images.add(new Image("file:src/main/java/sample/resource/grandpa2.png"));
        images.add(new Image("file:src/main/java/sample/resource/shejing2.png"));
        images.add(new Image("file:src/main/java/sample/resource/xiezi2.png"));
        images.add(new Image("file:src/main/java/sample/resource/zayu2.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa1-2.png"));
        images.add( new Image("file:src/main/java/sample/resource/huluwa2-2.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa3-2.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa4-2.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa5-2.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa6-2.png"));
        images.add(new Image("file:src/main/java/sample/resource/huluwa7-2.png"));
    }





    @Override
    public void start(Stage stage) throws Exception{
        stage.setResizable(false);
        stage.setHeight(max_size_of_stage);
        stage.setWidth(max_size_of_stage);
        stage.setTitle("葫芦娃大战妖怪");
        image_load();
        simpleIntegerProperty=new SimpleIntegerProperty(0);
        Canvas canvas=new Canvas(max_size_of_stage,max_size_of_stage);
        graphicsContext=canvas.getGraphicsContext2D();
        graphicsContext.drawImage(new Image("file:src/main/java/sample/resource/background.png"),0,0,Main.max_size_of_stage,Main.max_size_of_stage);
        SpaceForBattle.image_load();
        Group group=new Group();
        group.getChildren().add(canvas);
        Scene scene=new Scene(group);
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/java/sample/resource/background.png"));
        stage.show();
        c_stage=stage;
        space=new SpaceForBattle(size_of_current_space);
        simpleIntegerProperty.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(t1.intValue()!=0)
                    if(!flag_to_reset.compareAndSet(0,1))
                    return;
                    if(t1.intValue()!=0) {
                        int size_of_space = size_of_current_space;
                        int [][]space =new int [Main.size_of_current_space][Main.size_of_current_space];
                        for(int i=0;i<size_of_space;++i)
                            for(int j=0;j<size_of_space;++j)
                                space[i][j]=bufferspace.read_space(i,j);
                            image_load();
                        graphicsContext.drawImage(new Image("file:src/main/java/sample/resource/background.png"),0,0,Main.max_size_of_stage,Main.max_size_of_stage);
                        for (int i = 0; i < size_of_space; ++i) {
                            for (int j = 0; j < size_of_space; ++j) {
                                {
                                    Image image = null;
                                    int image_size = Main.max_size_of_stage / Main.size_of_current_space;
                                    if (space[i][j] >= 0) {
                                        if (space[i][j] <= 1)
                                            image = images.get(space[i][j]);
                                        else if (space[i][j] <= 5)
                                            image = images.get(space[i][j] - 1);
                                        else
                                            image = images.get(space[i][j] - 6);
                                    } else {
                                        if (space[i][j] == -1)
                                            image = images.get(12);
                                        else if (space[i][j] >= -5) {
                                            image = images.get(-1 * space[i][j] + 10);
                                        } else
                                            image = images.get(-1 * space[i][j] + 5);
                                    }

                                    if(space[i][j]!=0)
                                    //  graphicsContext.fillRect(0,0,max_size_of_stage,max_size_of_stage);
                                    graphicsContext.drawImage(image, i * image_size, j * image_size, image_size, image_size);
                                }

                            }
                            //System.out.println();
                        }
                        flag_to_reset.set(0);
                    }
            }
        });
        FileChooser fileChooser=new FileChooser();
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
//                System.out.println(keyEvent.getCode());
                if(keyEvent.getCode()== KeyCode.SPACE)
                {
                    if(Main.run_flag.get()!=0)
                        return;
                    Main.run_flag.set(1);
                    try {
                        bufferspace=space;
                        do_threads(c_stage);;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else  if(keyEvent.getCode()== KeyCode.L)
                {
                    int t=0;
                    simpleIntegerProperty.set(t);
//                    System.out.println(run_flag);
                    if(run_flag.get()==0)
                    {
                        try {
                            bufferspace=space;
                            run_flag.set(2);
                            File file = fileChooser.showOpenDialog(stage);
                            if(file==null) {
                                run_flag.set(0);
                                return;
                            }
                            MyFiles.input=file.getPath();
                            MyFiles.open(true);
                            replay=new Replay();
                            replay.space=space;
                            replay.get_set(size_of_current_space,size_of_current_space);
                            replay.start();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        finally {

                        }

                    }
                }
            }
        });
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                canvas.requestFocus();

//                if(Main.run_flag.get()!=0)
//                    return;
//                Main.run_flag.set(1);
//                try {
//                    bufferspace=space;
//                    do_threads(c_stage);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });

    }
    void do_threads(Stage stage) throws Exception
    {
        MyFiles.output=System.currentTimeMillis();
        MyFiles.open(false);
        Mythread_viewer.empty();
        space.rebulid_space();
        viewer=new Mythread_viewer();
        viewer.get_set(space,"");
        grandpa=new Mythread_creature();
        grandpa.get_set(viewer.cyclicBarrier,Mythread_viewer.creature_list,1,viewer.flags,space);
        brotherstandl=new Mythread_creature[Grandpa.the_num_of_brotherstandl];
        for(int i=0;i<Grandpa.the_num_of_brotherstandl;++i)
        {
            brotherstandl[i]=new Mythread_creature();
            brotherstandl[i].get_set(viewer.cyclicBarrier,Mythread_viewer.creature_list,grandpa.grandpa.brotherstandls[i],viewer.flags,space);
        }


        scorpion=new Mythread_creature();
        scorpion.get_set(viewer.cyclicBarrier,Mythread_viewer.creature_list,4,viewer.flags,space);
       fish=new Mythread_creature[MonsterScorpion.the_num_of_monstersaltfish];
        for(int i=0;i<MonsterScorpion.the_num_of_monstersaltfish;++i)
        {
            fish[i]=new Mythread_creature();
            fish[i].get_set(viewer.cyclicBarrier,Mythread_viewer.creature_list,scorpion.monsterScorpion.monsterSaltFishs[i],viewer.flags,space);
        }
        snake=new Mythread_creature();
        snake.get_set(viewer.cyclicBarrier,Mythread_viewer.creature_list,3,viewer.flags,space);
        viewer.get_set(stage);
        gridPane=new GridPane();
        for(int i=0;i<size_of_current_space;++i)
            for(int j=0;j<size_of_current_space;++j)
            {
                Rectangle rectangle=new Rectangle();
                rectangle.setHeight(max_size_of_stage/size_of_current_space);
                rectangle.setWidth(max_size_of_stage/size_of_current_space);
                rectangle.setFill(WHITE);
                gridPane.add(rectangle,i,j);
            }
//        space.show(graphicsContext,null);
        simpleIntegerProperty.set(1);
        grandpa.start();
        snake.start();
        scorpion.start();
        for(int i=0;i<MonsterScorpion.the_num_of_monstersaltfish;++i)
        {
            fish[i].start();
        }


        for(int i=0;i<Grandpa.the_num_of_brotherstandl;++i)
        {
            brotherstandl[i].start();
        }
        viewer.start();
//        grandpa.join();
//        snake.join();
//        scorpion.join();
//        for(int i=0;i<MonsterScorpion.the_num_of_monstersaltfish;++i)
//        {
//            fish[i].join();
//        }
//
//
//        for(int i=0;i<Grandpa.the_num_of_brotherstandl;++i)
//        {
//            brotherstandl[i].join();
//        }



//
    }




    public static void main(String[] args) {


        launch(args);

    }
}
