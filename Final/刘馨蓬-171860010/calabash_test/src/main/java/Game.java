//package main.java;

import com.sun.deploy.ui.ImageLoader;
import com.sun.javafx.iio.common.ImageLoaderImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


public class Game extends Application {
    private static Object object;
    private static Life [][]Map;
    private static Player playerone,playertwo;
    private static final int N=12;
    private static final int M=15;
    private static GameStateCheck statecheck;
    private static AnchorPane root;
    private static BufferedReader filein;
    private static GameRecord recordThread;
    private static String rootPath;
    private String recordPath;
    private static BufferedWriter fileout;
    private static Scene rootScene;
    @Override
    public void start(Stage primaryStage) throws IOException {
        root=new AnchorPane();
        rootPath=new String();
        try {
            FXMLLoader fx=new FXMLLoader();
            fx.setRoot(root);
            String path=String.valueOf(getClass().getResource(""));
            for(int i=6;i<path.length();i++)
                rootPath+=path.charAt(i);
            root=fx.load(getClass().getResource("/GameWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        object=new Object();

        Scene scene=new Scene(root);
        rootScene=scene;
        primaryStage.setScene(scene);
        primaryStage.setTitle("葫芦娃大战蛇精");
        primaryStage.setResizable(false);
        primaryStage.show();
        initMap();
        initPlayer(MutrixType.CHANGSHE);
        initHuluwa();
        initMinions();
        update();
        statecheck=new GameStateCheck();
    }
    public void initMap(){
        Map=new Life[N][M];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
                Map[i][j]=null;
        }
    }
    public void GameStart(){
        statecheck.start();
        recordPath=System.currentTimeMillis()+".txt";
        File file=new File(rootPath+File.separator+recordPath);
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileout=new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (object) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (Map[i][j] != null)
                        Map[i][j].start();
                }
            }
        }

    }
    public void GameRestart() {
        statecheck=new GameStateCheck();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                root.getChildren().clear();
                FXMLLoader fx=new FXMLLoader();
                fx.setRoot(root);
                try {
                    root=fx.load(getClass().getResource("/GameWindow.fxml"));
                    rootScene.setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        object=new Object();
        initMap();
        initPlayer(MutrixType.CHANGSHE);
        initHuluwa();
        initMinions();
        update();
    }
    public void initPlayer(MutrixType type){
        playerone=new Player(type,1);
        playertwo=new Player(type,2);
    }
    public void initHuluwa(){
        Map[playerone.getPos(0).getX()][playerone.getPos(0).getY()]=new Huluwa(60,5,1,playerone.getPos(0),"大娃");
        Map[playerone.getPos(1).getX()][playerone.getPos(1).getY()]=new Huluwa(40,4,1,playerone.getPos(1),"二娃");
        Map[playerone.getPos(2).getX()][playerone.getPos(2).getY()]=new Huluwa(50,5,1,playerone.getPos(2),"三娃");
        Map[playerone.getPos(3).getX()][playerone.getPos(3).getY()]=new Huluwa(40,6,2,playerone.getPos(3),"四娃");
        Map[playerone.getPos(4).getX()][playerone.getPos(4).getY()]=new Huluwa(40,6,1,playerone.getPos(4),"五娃");
        Map[playerone.getPos(5).getX()][playerone.getPos(5).getY()]=new Huluwa(50,5,1,playerone.getPos(5),"六娃");
        Map[playerone.getPos(6).getX()][playerone.getPos(6).getY()]=new Huluwa(50,5,2,playerone.getPos(6),"七娃");
        Map[playerone.getPos(7).getX()][playerone.getPos(7).getY()]=new Huluwa(40,3,1,playerone.getPos(7),"爷爷");

    }
    public void initMinions(){
        int i;
        for( i=0;i<playertwo.getNum()-2;i++)
            Map[playertwo.getPos(i).getX()][playertwo.getPos(i).getY()]=new Minions(50,4,1,playertwo.getPos(i),"小喽啰");
        Map[playertwo.getPos(i).getX()][playertwo.getPos(i).getY()]=new Minions(40,4,2,playertwo.getPos(i),"蛇精");
        i++;
        Map[playertwo.getPos(i).getX()][playertwo.getPos(i).getY()]=new Minions(80,7,2,playertwo.getPos(i),"蝎子精");
    }
    public static Point lifeMove(Point destination,Point source) {

        Point re=new Point();
        synchronized (object) {
            if (Map[source.getX()][source.getY()] != null) {
                if (Map[destination.getX()][destination.getY()] != null) {
                    int direction = 0;
                    int length = 0;
                    //向上
                    if (source.getX() > 0) {
                        int hang = source.getX() - 1;
                        int lie = source.getY();
                        if (Map[hang][lie] == null) {

                            length = Math.abs(destination.getX() - hang) * 10 + Math.abs(destination.getY() - lie) * 10 + 10;
                            direction = 1;
                            re.setX(hang);
                            re.setY(lie);
                        } else if (hang == destination.getX() && lie == destination.getY()) {
                            re.setX(hang);
                            re.setY(lie);

                            return re;
                        }
                    }
                    //左上
                    if (source.getX() > 0 && source.getY() > 0) {
                        int hang = source.getX() - 1;
                        int lie = source.getY() - 1;
                        if (Map[hang][lie] == null) {

                            int temp = Math.abs(destination.getX() - hang) * 10 + Math.abs(destination.getY() - lie) * 10 + 10;
                            if (length > temp) {
                                length = temp;
                                direction = 2;
                                re.setX(hang);
                                re.setY(lie);
                            }
                        } else if (hang == destination.getX() && lie == destination.getY()) {
                            re.setX(hang);
                            re.setY(lie);

                            return re;
                        }
                    }
                    //左
                    if (source.getY() > 0) {
                        int hang = source.getX();
                        int lie = source.getY() - 1;
                        if (Map[hang][lie] == null) {

                            int temp = Math.abs(destination.getX() - hang) * 10 + Math.abs(destination.getY() - lie) * 10 + 10;
                            if (length > temp) {
                                length = temp;
                                direction = 3;
                                re.setX(hang);
                                re.setY(lie);
                            }
                        } else if (hang == destination.getX() && lie == destination.getY()) {
                            re.setX(hang);
                            re.setY(lie);

                            return re;
                        }
                    }
                    //左下
                    if (source.getY() > 0 && source.getX() < 11) {
                        int hang = source.getX() + 1;
                        int lie = source.getY() - 1;
                        if (Map[hang][lie] == null) {

                            int temp = Math.abs(destination.getX() - hang) * 10 + Math.abs(destination.getY() - lie) * 10 + 10;
                            if (length > temp) {
                                length = temp;
                                direction = 4;
                                re.setX(hang);
                                re.setY(lie);
                            }
                        } else if (hang == destination.getX() && lie == destination.getY()) {
                            re.setX(hang);
                            re.setY(lie);

                            return re;
                        }
                    }
                    //下
                    if (source.getX() < 11) {
                        int hang = source.getX() + 1;
                        int lie = source.getY();
                        if (Map[hang][lie] == null) {

                            int temp = Math.abs(destination.getX() - hang) * 10 + Math.abs(destination.getY() - lie) * 10 + 10;
                            if (length > temp) {
                                length = temp;
                                direction = 5;
                                re.setX(hang);
                                re.setY(lie);
                            }
                        } else if (hang == destination.getX() && lie == destination.getY()) {
                            re.setX(hang);
                            re.setY(lie);

                            return re;
                        }
                    }
                    //右下
                    if (source.getY() < 14 && source.getX() < 11) {
                        int hang = source.getX() + 1;
                        int lie = source.getY() + 1;
                        if (Map[hang][lie] == null) {

                            int temp = Math.abs(destination.getX() - hang) * 10 + Math.abs(destination.getY() - lie) * 10 + 10;
                            if (length > temp) {
                                length = temp;
                                direction = 6;
                                re.setX(hang);
                                re.setY(lie);
                            }
                        } else if (hang == destination.getX() && lie == destination.getY()) {
                            re.setX(hang);
                            re.setY(lie);

                            return re;
                        }
                    }
                    //右
                    if (source.getY() < 14) {
                        int hang = source.getX();
                        int lie = source.getY() + 1;
                        if (Map[hang][lie] == null) {

                            int temp = Math.abs(destination.getX() - hang) * 10 + Math.abs(destination.getY() - lie) * 10 + 10;
                            if (length > temp) {
                                length = temp;
                                direction = 7;
                                re.setX(hang);
                                re.setY(lie);
                            }
                        } else if (hang == destination.getX() && lie == destination.getY()) {
                            re.setX(hang);
                            re.setY(lie);

                            return re;
                        }
                    }
                    //右上
                    if (source.getY() < 14 && source.getX() > 0) {
                        int hang = source.getX() - 1;
                        int lie = source.getY() + 1;
                        if (Map[hang][lie] == null) {

                            int temp = Math.abs(destination.getX() - hang) * 10 + Math.abs(destination.getY() - lie) * 10 + 10;
                            if (length > temp) {
                                length = temp;
                                direction = 8;
                                re.setX(hang);
                                re.setY(lie);
                            }
                        } else if (hang == destination.getX() && lie == destination.getY()) {

                            re.setX(hang);
                            re.setY(lie);

                            return re;
                        }
                    }

                    if (re.getX() >= 0) {
                        final int y1=source.getX()*30+4;
                        final int x1=source.getY()*30+81;
                        final int y2=re.getX()*30+4;
                        final int x2=re.getY()*30+81;
                        final String str=Map[source.getX()][source.getY()].getLifeName();
                        Map[source.getX()][source.getY()].setPlace(re);
                        Map[re.getX()][re.getY()] = Map[source.getX()][source.getY()];
                        Map[source.getX()][source.getY()] = null;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                BorderPane b = new BorderPane();
                                b.setMaxSize(30, 30);
                                Image image = new Image("file:///"+rootPath+"image/空.png");//七娃.png
                                ImageView imageView = new ImageView();
                                imageView.setPreserveRatio(true);
                                imageView.setFitWidth(30);
                                imageView.setFitHeight(30);
                                imageView.setImage(image);
                                b.setCenter(imageView);
                                root.getChildren().add(b);
                                b.setTranslateX(x1);
                                b.setTranslateY(y1);

                                b=new BorderPane();
                                b.setMaxSize(30, 30);
                                image = new Image("file:///"+rootPath+"image/"+str+".png");//七娃.png
                                imageView = new ImageView();
                                imageView.setPreserveRatio(true);
                                imageView.setFitWidth(30);
                                imageView.setFitHeight(30);
                                imageView.setImage(image);
                                b.setCenter(imageView);
                                root.getChildren().add(b);
                                b.setTranslateX(x2);
                                b.setTranslateY(y2);
                            }
                        });
                        return re;
                    }
                }
                //找另一个怪，重置destination
                int pathLength = 15 * 12;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (Map[i][j] != null) {
                            if (Map[source.getX()][source.getY()] instanceof Huluwa) {
                                if (Map[i][j] instanceof Minions) {
                                    if (Math.abs(i - source.getX()) + Math.abs(j - source.getY()) < pathLength) {
                                        re.setY(j);
                                        re.setX(i);
                                        pathLength = Math.abs(i - source.getX()) + Math.abs(j - source.getY());
                                    }
                                }
                            } else {
                                if (Map[i][j] instanceof Huluwa) {
                                    if (Math.abs(i - source.getX()) + Math.abs(j - source.getY()) < pathLength) {
                                        re.setY(j);
                                        re.setX(i);
                                        pathLength = Math.abs(i - source.getX()) + Math.abs(j - source.getY());
                                    }
                                }
                            }
                        }
                    }
                }
                if (re.getX() > 0) {
                    //if (Map[source.getX()][source.getY()] != null)
                    Map[source.getX()][source.getY()].setDestination(re);
                }
            }
        }
        return new Point(-1,-1);
    }
    public static void lifeAttack(Point destination,Point source){
        synchronized (object) {
            if (Map[destination.getX()][destination.getY()] != null) {
                if (Map[source.getX()][source.getY()] != null)
                    Map[destination.getX()][destination.getY()].beAttack(Map[source.getX()][source.getY()].getAtk());
            }
        }
    }
    public static void clearMap(){
        for(int i=0;i<N;i++) {
            for (int j = 0; j < M; j++) {
                if(Map[i][j]!=null)
                {
                    if(!Map[i][j].isInterrupted())
                        Map[i][j].interrupt();
                }
                Map[i][j] = null;
            }
        }
        statecheck.interrupt();
    }
    public static void checkState(){
        synchronized (object) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (Map[i][j] != null) {
                        if (Map[i][j].isDead()) {
                            if (Map[i][j] instanceof Huluwa)
                                playerone.setNum(playerone.getNum() - 1);
                            else
                                playertwo.setNum(playertwo.getNum() - 1);
                            Map[i][j] = null;
                            try {
                                fileout.write("空");
                                fileout.newLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            final int dx=j*30+81;
                            final int dy=i*30+4;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    BorderPane b = new BorderPane();
                                    b.setMaxSize(30, 30);
                                    Image image = new Image("file:///"+rootPath+"image/空.png");//七娃.png);//七娃.png
                                    ImageView imageView = new ImageView();
                                    imageView.setPreserveRatio(true);
                                    imageView.setFitWidth(30);
                                    imageView.setFitHeight(30);
                                    imageView.setImage(image);
                                    b.setCenter(imageView);
                                    root.getChildren().add(b);
                                    b.setTranslateX(dx);
                                    b.setTranslateY(dy);
                                }
                            });
                        }
                        else {
                            try {
                                fileout.write(Map[i][j].getLifeName());
                                fileout.newLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else{
                        try {
                            fileout.write("空");
                            fileout.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if(playerone.getNum()<=0)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Label l=new Label();//玩家一输了,新建空白画布
                    l.setText("玩家一输了！");
                    root.getChildren().add(l);
                    l.setTranslateX(300);
                    l.setTranslateY(180);
                }
            });
            try {
                fileout.write("玩家一输了！");
                fileout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clearMap();
        }
        else if(playertwo.getNum()<=0)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Label l=new Label();//玩家一输了,新建空白画布
                    l.setText("玩家二输了！");
                    root.getChildren().add(l);
                    l.setTranslateX(300);
                    l.setTranslateY(180);
                }
            });
            try {
                fileout.write("玩家二输了！");
                fileout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clearMap();
        }


    }
    private static void update()  {
        //更新界面
        final Group g = new Group();
        synchronized (object) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int dx = 30 * j;
                    int dy = 30 * i;
                    if (Map[i][j] != null) {
                        BorderPane b = new BorderPane();
                        b.setMaxSize(30, 30);
                        String path=rootPath+"image/"+Map[i][j].getLifeName()+".png";

                        Image image = new Image("file:///"+path);//七娃.png
                        ImageView imageView = new ImageView();
                        imageView.setPreserveRatio(true);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        imageView.setImage(image);
                        b.setCenter(imageView);
                        g.getChildren().add(b);
                        b.setTranslateX(dx);
                        b.setTranslateY(dy);
                    } else {
                        BorderPane b = new BorderPane();
                        b.setMaxSize(30, 30);
                        Image image = new Image("file:///"+rootPath+"image/空.png");//七娃.png
                        ImageView imageView = new ImageView();
                        imageView.setPreserveRatio(true);
                        imageView.setFitWidth(30);
                        imageView.setFitHeight(30);
                        imageView.setImage(image);
                        b.setCenter(imageView);
                        g.getChildren().add(b);
                        b.setTranslateX(dx);
                        b.setTranslateY(dy);
                    }
                }
            }
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                root.getChildren().add(g);
                g.setTranslateX(81);
                g.setTranslateY(4);
            }
        });

    }
    private void clearWindow(){
        final Group g = new Group();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int dx = 30 * j;
                int dy = 30 * i;
                    BorderPane b = new BorderPane();
                    b.setMaxSize(30, 30);
                    Image image = new Image("file:///"+rootPath+"image/空.png");//七娃.png
                    ImageView imageView = new ImageView();
                    imageView.setPreserveRatio(true);
                    imageView.setFitWidth(30);
                    imageView.setFitHeight(30);
                    imageView.setImage(image);
                    b.setCenter(imageView);
                    g.getChildren().add(b);
                    b.setTranslateX(dx);
                    b.setTranslateY(dy);
            }

    }
        Platform.runLater(new Runnable() {
        @Override
        public void run() {

            root.getChildren().add(g);
            g.setTranslateX(81);
            g.setTranslateY(4);
        }
    });
    }
    public void changeMutrix(int id,MutrixType type){
        if(id==1){
            Map[playerone.getPos(0).getX()][playerone.getPos(0).getY()]=null;
            Map[playerone.getPos(1).getX()][playerone.getPos(1).getY()]=null;
            Map[playerone.getPos(2).getX()][playerone.getPos(2).getY()]=null;
            Map[playerone.getPos(3).getX()][playerone.getPos(3).getY()]=null;
            Map[playerone.getPos(4).getX()][playerone.getPos(4).getY()]=null;
            Map[playerone.getPos(5).getX()][playerone.getPos(5).getY()]=null;
            Map[playerone.getPos(6).getX()][playerone.getPos(6).getY()]=null;
            Map[playerone.getPos(7).getX()][playerone.getPos(7).getY()]=null;
            playerone=new Player(type,1);
            initHuluwa();

        }
        else if(id==2){
            int i;
            for( i=0;i<playertwo.getNum()-2;i++)
                Map[playertwo.getPos(i).getX()][playertwo.getPos(i).getY()]=null;
            Map[playertwo.getPos(i).getX()][playertwo.getPos(i).getY()]=null;
            i++;
            Map[playertwo.getPos(i).getX()][playertwo.getPos(i).getY()]=null;
            playertwo=new Player(type,2);
            initMinions();
        }
        clearWindow();
        update();
    }
    public void oneChooseHY() {
        changeMutrix(1, MutrixType.HEYI);
    }
    public void oneChooseYX(){
        changeMutrix(1, MutrixType.YANXING);
    }
    public void oneChooseHG(){
        changeMutrix(1, MutrixType.HENGGEN);
    }
    public void oneChooseCS(){
        changeMutrix(1, MutrixType.CHANGSHE);
    }
    public void oneChooseYL(){
        changeMutrix(1, MutrixType.YULIN);
    }
    public void oneChooseFM(){
        changeMutrix(1, MutrixType.FANGMEN);
    }
    public void oneChooseYY(){
        changeMutrix(1, MutrixType.YANYUE);
    }
    public void oneChooseFS(){
        changeMutrix(1, MutrixType.FENGSHI);
    }

    public void twoChooseHY() {
        changeMutrix(2, MutrixType.HEYI);
    }
    public void twoChooseYX(){
        changeMutrix(2, MutrixType.YANXING);
    }
    public void twoChooseHG(){
        changeMutrix(2, MutrixType.HENGGEN);
    }
    public void twoChooseCS(){
        changeMutrix(2, MutrixType.CHANGSHE);
    }
    public void twoChooseYL(){
        changeMutrix(2, MutrixType.YULIN);
    }
    public void twoChooseFM(){
        changeMutrix(2, MutrixType.FANGMEN);
    }
    public void twoChooseYY(){
        changeMutrix(2, MutrixType.YANYUE);
    }
    public void twoChooseFS(){
        changeMutrix(2, MutrixType.FENGSHI);
    }

    public void GameReplay()  {
        //打开文件，选择路径，进行回放
        String fileName;//获取wenjianming
        FileChooser fc=new FileChooser();
        fc.setInitialDirectory(new File(rootPath));
        Stage stage=new Stage();
        File file=fc.showOpenDialog(stage);
        if(file!=null) {//回放
            recordThread = new GameRecord();
            filein = null;
            try {
                filein = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            recordThread.start();
        }


 }
    public static void RecordPlay(){
        String ending=null;
        boolean p=false;
        final Group g=new Group();
        BorderPane b = new BorderPane();
        b.setMaxSize(450, 360);
        Image image = new Image("file:///"+rootPath+"image/空.png");//"file:image/空.png");//七娃.png
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(450);
        imageView.setFitHeight(360);
        imageView.setImage(image);
        b.setCenter(imageView);
        g.getChildren().add(b);
        b.setTranslateX(0);
        b.setTranslateY(0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                String name = null;
                try {
                    name = filein.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (name == null||"玩家二输了！".equals(name)||"玩家一输了！".equals(name)) {
                    p=true;
                    ending=name;
                    break;
                }
                else if(name.charAt(0)=='空'){}
                else {
                    int dx = 30 * j;
                    int dy = 30 * i;
                    b = new BorderPane();
                    b.setMaxSize(30, 30);
                     image = new Image("file:///"+rootPath+"image/"+name+".png");//"file:image/" + name + ".png");
                     imageView = new ImageView();
                    imageView.setPreserveRatio(true);
                    imageView.setFitWidth(30);
                    imageView.setFitHeight(30);
                    imageView.setImage(image);
                    b.setCenter(imageView);
                    g.getChildren().add(b);
                    b.setTranslateY(dy);
                    b.setTranslateX(dx);
                }
            }
            if(p)
                break;
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                root.getChildren().add(g);
                g.setTranslateX(81);
                g.setTranslateY(4);
            }
        });
        if(p) {
            recordThread.interrupt();
            final String finalEnding = ending;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Label l = new Label();
                    l.setText(finalEnding);
                    root.getChildren().add(l);
                    l.setTranslateX(300);
                    l.setTranslateY(180);
                }
            });

        }
    }
}

