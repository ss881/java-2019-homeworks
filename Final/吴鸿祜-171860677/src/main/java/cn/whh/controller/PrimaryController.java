package cn.whh.controller;

import cn.whh.bloodBar.BloodBar;
import cn.whh.creature.*;
import cn.whh.formation.ChangSheFormation;
import cn.whh.formation.Formation;
import cn.whh.formation.FormationGenerator;
import cn.whh.formation.HengEFormation;
import cn.whh.map.Field;
import cn.whh.talk.Talking;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class PrimaryController {
    @FXML private Pane mainPane;

    @FXML private ImageView backGround;
    @FXML private ImageView title;
    @FXML private ImageView endImage;
    @FXML private Button startGame;
    @FXML private Button readHistory;
    @FXML private Button aboutGame;

    @FXML private Button returnMenu;
    @FXML private Button saveCurGame;
    @FXML private Pane fightPane;
    @FXML private GridPane checkerBoard;
    @FXML private Pane talkingPane;

    @FXML private TableView<CreatureIntroduction> gameTable;
    @FXML private TableColumn<CreatureIntroduction,String> roles;
    @FXML private TableColumn<CreatureIntroduction,String> hP;
    @FXML private TableColumn<CreatureIntroduction,String> atk;
    @FXML private TableColumn<CreatureIntroduction,String> dfs;
    @FXML private TableColumn<CreatureIntroduction,String> cri;
    @FXML private TableColumn<CreatureIntroduction,String> dis;
    @FXML private TableColumn<CreatureIntroduction,String> spe;
    @FXML private TableColumn<CreatureIntroduction,String> special;
    @FXML private Button closeIntroduction;


    private Random random;

    private Stage stage;

    private MusicPlayer musicPlayer;

    private Field field;
    private ArrayList<Image> imageCache;   //new Image太多图片会导致内存超限，只能预先保存一些常用图片了
    private ArrayList<Creature[][]> saveCreatures;  //游戏运行时不断保存在这个列表
    private ArrayList<Creature[][]> historyField;   //读取存档时将对象反序列化之后保存到该表，再打印出来
    private int historyFrameCount;
    private int gameMode;   //0是开始新游戏，1是读取历史
    private int gameState;  //0是未开始，1是游戏中，2是游戏结束

    private Thread updateThread;  //在后台刷新UI的线程

    public PrimaryController() {
    }


    public void initController(Stage primaryStage){

        //基本变量初始化
        gameMode=-1;
        gameState=0;
        field=new Field();
        saveCreatures=new ArrayList<>();
        historyField=new ArrayList<>();
        imageCache=new ArrayList<>();
        loadImage();
        historyFrameCount=0;
        random=new Random();
        updateThread=null;

        //控件初始化
        gameTable.setVisible(false);
        fightPane.setVisible(false);
        talkingPane.setVisible(false);
        checkerBoard.setVisible(false);
        saveCurGame.setVisible(false);
        returnMenu.setVisible(false);
        gameTable.setVisible(false);
        closeIntroduction.setVisible(false);
        initIntroduction();
        stage=primaryStage;

        //BGM初始化
        musicPlayer=new MusicPlayer("/menu.mp3");
        musicPlayer.play();

        //刷新UI线程初始化
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        /*if (gameState != 2 && gameMode != 1)*/ update();
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        updateThread=thread;


        //加入键盘监听事件
        mainPane.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent ->{
            System.out.println(keyEvent.getCode());
            if(keyEvent.getCode()== KeyCode.SPACE)
            {
                if(gameState==0)
                {
                    if(gameMode==0&&field.hasCreature(field.getCreatures()))gameState=1;  //第一次按空格，切换游戏状态
                    else if(gameMode==1&&field.hasCreature(historyField.get(0))) gameState=1;
                }

                if(!field.isInterrupt())
                {
                    field.setState(true);
                    for (int i = 0; i < field.height; i++)
                    {
                        for (int j = 0; j < field.width; j++)
                        {
                            field.Get(i, j).setStop(true);
                        }
                    }
                }

                else
                {
                    field.setState(false);
                    for (int i = 0; i < field.height; i++)
                    {
                        for (int j = 0; j < field.width; j++)
                        {
                            field.Get(i, j).setStop(false);
                        }
                    }
                }
            }

            if(keyEvent.getCode()== KeyCode.L)
            {

                if(gameState==0) {

                    cleanCheckerBoard();
                    BloodBar.clearArraylist();
                    readHistory();
                    //gameMode=1;
                    //readHistory();
                }

                else if(gameState==2){
                    gameState=0;
                    cleanCheckerBoard();
                    readHistory();
                }
            }
        });

        /*checkerBoard.addEventFilter(KeyEvent.KEY_PRESSED, event->{
            System.out.println(field.isInterrupt());
            if(event.getCode()==KeyCode.SPACE)
            {
                if(gameState==0) gameState=1;  //第一次按空格，切换游戏状态
                if(!field.isInterrupt())
                {
                    field.setState(true);
                    for (int i = 0; i < field.height; i++)
                    {
                        for (int j = 0; j < field.width; j++)
                        {
                            field.Get(i, j).setStop(true);
                        }
                    }
                }

                else
                {
                    field.setState(false);
                    for (int i = 0; i < field.height; i++)
                    {
                        for (int j = 0; j < field.width; j++)
                        {
                            field.Get(i, j).setStop(false);
                        }
                    }
                }
            }

            else if(event.getCode()==KeyCode.L)
            {
                System.out.println("战斗场景");
                if(gameState==2) {
                    gameState=0;
                    returnMenu.setVisible(false);
                    saveCurGame.setVisible(false);
                    endImage.setVisible(false);
                    readHistory();
                }
            }
        });*/

    }

    public int getGameMode(){return gameMode;}

    private void loadImage()
    {
        String root="./src/main/resources/";
        for(int i=1;i<=7;i++)
        {
            imageCache.add(new Image(this.getClass().getResourceAsStream("/"+Integer.toString(i)+".png")));
            imageCache.add(new Image(this.getClass().getResourceAsStream("/"+Integer.toString(i)+"_dead.png")));
            //imageCache.add(new Image(root+ Integer.toString(i)+".png"));
            //imageCache.add(new Image(root+ Integer.toString(i)+"_dead.png"));
        }

        /*imageCache.add(new Image(root+"soldier.png"));
        imageCache.add(new Image(root+"soldier_dead.png"));
        imageCache.add(new Image(root+"scorpion.png"));
        imageCache.add(new Image(root+"scorpion_dead.png"));
        imageCache.add(new Image(root+"grandPa.png"));
        imageCache.add(new Image(root+"grandPa_dead.png"));
        imageCache.add(new Image(root+"snake.png"));
        imageCache.add(new Image(root+"snake_dead.png"));*/

        imageCache.add(new Image(this.getClass().getResourceAsStream("/soldier.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/soldier_dead.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/scorpion.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/scorpion_dead.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/grandPa.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/grandPa_dead.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/snake.png")));
        imageCache.add(new Image(this.getClass().getResourceAsStream("/snake_dead.png")));

    }

    private void initIntroduction()
    {
        ObservableList<CreatureIntroduction> data= CreatureIntroduction.factory();
        roles.setCellValueFactory(CreatureIntroduction->CreatureIntroduction.getValue().getName());
        hP.setCellValueFactory(CreatureIntroduction->CreatureIntroduction.getValue().getHp());
        atk.setCellValueFactory(CreatureIntroduction->CreatureIntroduction.getValue().getAttack());
        dfs.setCellValueFactory(CreatureIntroduction->CreatureIntroduction.getValue().getDefense());
        cri.setCellValueFactory(CreatureIntroduction->CreatureIntroduction.getValue().getCritRate());
        dis.setCellValueFactory(CreatureIntroduction->CreatureIntroduction.getValue().getDistance());
        spe.setCellValueFactory(CreatureIntroduction->CreatureIntroduction.getValue().getSpeed());
        special.setCellValueFactory(CreatureIntroduction->CreatureIntroduction.getValue().getSpecial());
        gameTable.setItems(data);
    }

    private void cleanCheckerBoard()
    {
        field.cleanField();
        for(int i=0;i<field.height;i++)
        {
            for(int j=0;j<field.width;j++)
            {
                ImageView tmp=(ImageView)checkerBoard.getChildren().get(j*field.height+i);
                tmp.setImage(field.Get(i,j).aliveImage);
            }
        }

        fightPane.getChildren().clear();
        talkingPane.getChildren().clear();
    }


    public void update()
    {
        if(gameMode==0)
        {
            if(gameState==0)
            {
                Creature creature=null;
                for (int i = 0; i < field.height; i++) {
                    for (int j = 0; j < field.width; j++) {
                        creature = field.Get(i, j);
                        ImageView imageView = (ImageView) checkerBoard.getChildren().get(j * field.height + i);
                        if (creature.isAlive()) imageView.setImage(creature.aliveImage);
                        else imageView.setImage(creature.deadImage);
                    }
                }

            }

            else if(gameState==1)
            {
                if (field.isInterrupt()) return;
                Creature creature = null;
                Creature[][] curCreaturePosition = field.getCreatures();
                saveCreatures.add(curCreaturePosition);
                talkingPane.getChildren().clear();
                BloodBar.updateForField(field.getCreatures(),field.width,field.height);
                for (int i = 0; i < field.height; i++)
                {
                    for (int j = 0; j < field.width; j++)
                    {
                        //BloodBar curBlood = BloodBar.getBloodBar(creature);
                        //if(curBlood!=null) curBlood.update();
                        creature = field.Get(i, j);
                        //System.out.print(creature.isCritical()+" ");
                        ImageView imageView = (ImageView) checkerBoard.getChildren().get(j * field.height + i);
                        if (creature.isAlive())
                        {
                            imageView.setImage(creature.aliveImage);
                            double rand=random.nextDouble(),display=random.nextDouble();
                            talkingPane.getChildren().add(new Talking(i,j,creature,display,rand));
                        }
                        else imageView.setImage(creature.deadImage);
                    }
                    //System.out.println();
                }
                //System.out.println();
                //Creature[][] curCreaturePosition = field.getCreatures();
                //saveCreatures.add(curCreaturePosition);


                if (field.getBadGuysCount() <= 0 || field.getGoodGuysCount() <= 0)
                {
                    returnMenu.setVisible(true);
                    saveCurGame.setVisible(true);
                    endImage.setVisible(true);
                    if(field.getBadGuysCount()<=0)
                    {
                        endImage.setImage(new Image(this.getClass().getResourceAsStream("/goodWin.png")));
                        musicPlayer.setMusicName("/winner.mp3");
                    }
                    else
                    {
                        endImage.setImage(new Image(this.getClass().getResourceAsStream("/badWin.png")));
                        musicPlayer.setMusicName("/loser.mp3");
                    }
                    BloodBar.clearArraylist();
                    gameState=2;
                }
            }

        }


        else if(gameMode==1)
        {
            if(gameState==0)
            {
                Creature creature = null;
                if(historyField.size()<=0) return;
                Creature[][] tmp = historyField.get(0);
                for (int i = 0; i < field.height; i++) {
                    for (int j = 0; j < field.width; j++) {
                        creature = tmp[i][j];
                        //System.out.print(creature.getClass().getSimpleName()+" ");
                        ImageView imageView = (ImageView) checkerBoard.getChildren().get(j * field.height + i);
                        if (creature.isAlive()) imageView.setImage(creature.aliveImage);
                        else imageView.setImage(creature.deadImage);
                    }
                    //System.out.println();
                }
            }

            else if(gameState==1)
            {
                if (field.isInterrupt()) return;
                Creature creature=null;
                talkingPane.getChildren().clear();
                //System.out.println(historyField.size());
                if(historyFrameCount<historyField.size())
                {
                    Creature[][] tmp = historyField.get(historyFrameCount++);
                    BloodBar.updateForField(tmp,field.width,field.height);
                    for (int i = 0; i < field.height; i++)
                    {
                        for (int j = 0; j < field.width; j++)
                        {
                            creature = tmp[i][j];
                            ImageView imageView = (ImageView) checkerBoard.getChildren().get(j * field.height + i);
                            if (creature.isAlive())
                            {
                                imageView.setImage(creature.aliveImage);
                                double rand=random.nextDouble(),display=random.nextDouble();
                                talkingPane.getChildren().add(new Talking(i,j,creature,display,rand));
                            }
                            else imageView.setImage(creature.deadImage);
                        }
                    }

                }

                else  //游戏结束
                {
                    int goodCount=0;
                    Creature[][] tmp = historyField.get(historyFrameCount-1);
                    for (int i = 0; i < field.height; i++)
                    {
                        for (int j = 0; j < field.width; j++)
                        {
                            creature = tmp[i][j];
                            if((creature instanceof Huluwa)&&creature.isAlive()) goodCount++;
                        }
                    }

                    returnMenu.setVisible(true);
                    endImage.setVisible(true);
                    if(goodCount>0)
                    {
                        endImage.setImage(new Image(this.getClass().getResourceAsStream("/goodWin.png")));
                        musicPlayer.setMusicName("/winner.mp3");
                    }
                    else
                    {
                        endImage.setImage(new Image(this.getClass().getResourceAsStream("/badWin.png")));
                        musicPlayer.setMusicName("/loser.mp3");
                    }
                    historyFrameCount=0;
                    historyField.clear();
                    BloodBar.clearArraylist();
                    gameState=2;
                }
            }

        }
    }

    public void serializeCreatures(/*String pathname*/File file)
    {
        ObjectOutputStream out=null;
        try {
            //File file =new File(pathname);
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();

            //out = new ObjectOutputStream(new FileOutputStream(new File(pathname)));
            out = new ObjectOutputStream(new FileOutputStream(file));
            for(int i=0;i<saveCreatures.size();i++)
            {
                Creature[][] tmp=saveCreatures.get(i);
                for(int j=0;j<field.height;j++)
                {
                    for(int k=0;k<field.width;k++)
                    {
                        //System.out.println(tmp[j][k].getClass().getSimpleName());
                        out.writeObject(tmp[j][k]);
                    }
                }
            }
            out.close();
        }catch (FileNotFoundException e)
        {
            System.out.println(e);
        }catch (IOException e)
        {
            System.out.println(e);
        }
    }

    private void getReady(int gameMode)
    {
        this.gameMode=gameMode;
        startGame.setVisible(false);  //隐藏控件
        readHistory.setVisible(false);
        aboutGame.setVisible(false);
        title.setVisible(false);
        checkerBoard.setVisible(true);
        fightPane.setVisible(true);
        returnMenu.setVisible(false);
        saveCurGame.setVisible(false);
        endImage.setVisible(false);
        backGround.setImage(new Image(this.getClass().getResourceAsStream("/fightImage.png")));
        musicPlayer.setMusicName("/fighting.mp3");
        cleanCheckerBoard();
    }

    private void addSelectFormationCombo()
    {
        ComboBox<String> goodGuysComboBox=new ComboBox<String>(),badGuysComboBox=new ComboBox<String>();
        goodGuysComboBox.getItems().addAll("长蛇阵", "鹤翼阵", "雁行阵", "衡轭阵", "鱼鳞阵", "方円阵", "锋矢阵");
        badGuysComboBox.getItems().addAll("长蛇阵", "鹤翼阵", "雁行阵", "衡轭阵", "鱼鳞阵", "方円阵", "锋矢阵");
        goodGuysComboBox.setValue("长蛇阵");
        badGuysComboBox.setValue("衡轭阵");
        badGuysComboBox.setLayoutX(77*(field.width-2));
        fightPane.getChildren().add(goodGuysComboBox);
        fightPane.getChildren().add(badGuysComboBox);
    }

    @FXML
    void startGame()
    {
        getReady(0);
        addSelectFormationCombo();
        ComboBox<String> goodGuysComboBox=(ComboBox<String>)fightPane.getChildren().get(0),badGuysComboBox=(ComboBox<String>)fightPane.getChildren().get(1);
        ArrayList<Creature> brothers=new ArrayList<>();
        ArrayList<Creature> enemy=new ArrayList<>();
        for(int i=0;i<7;i++) brothers.add(new Huluwa(i+1,field));
        Grandpa grandpa=new Grandpa(field);
        brothers.add(grandpa);
        Snake snake=new Snake(field);
        Scorpion scorpion=new Scorpion(field);
        enemy.add(scorpion);
        for(int i=0;i<6;i++) enemy.add(new Sodier(field));
        enemy.add(snake);

        ChangSheFormation changSheFormation=new ChangSheFormation(brothers,0);
        HengEFormation hengEFormation=new HengEFormation(enemy,1);
        field.Add(changSheFormation);
        field.Add(hengEFormation);

        //首先添加血条到界面中
        for (int i = 0; i < field.height; i++) {
            for (int j = 0; j < field.width; j++) {
                if (!(field.Get(i, j) instanceof Land)) {
                    BloodBar bloodBar=new BloodBar(i,j,field.Get(i,j));
                    Label label=bloodBar.getHpValue();
                    label.setVisible(false);
                    fightPane.getChildren().add(bloodBar);
                    fightPane.getChildren().add(label);
                }
            }
        }

        Thread goodComboThread=new Thread(new Runnable() {
            String oldValue="长蛇阵";
            String newValue="长蛇阵";
            Formation formation=changSheFormation;
            FormationGenerator factory=new FormationGenerator();

            @Override
            public void run() {
                while(true){
                    if(gameState==0)
                    {
                        oldValue=newValue;
                        newValue=goodGuysComboBox.getValue();
                        //System.out.println(newValue);
                        int index=2;
                        BloodBar bloodBar=null;
                        Label label=null;
                        if(!newValue.equals(oldValue))
                        {
                            field.Remove(formation);
                            for(int i=2;i<fightPane.getChildren().size();i++)
                            {
                                if(i%2==0) {
                                    bloodBar = (BloodBar) fightPane.getChildren().get(i);
                                    bloodBar.setWidth(0);
                                }
                            }
                            //"长蛇阵", "鹤翼阵", "雁行阵", "衡轭阵", "鱼鳞阵", "方円阵", "锋矢阵");
                            //利用工厂模式可以使得域内的类不过于暴露在其他域
                            formation=factory.produceFormation(newValue,brothers,0);
                            field.Add(formation);
                            for (int i = 0; i < field.height; i++) {
                                for (int j = 0; j < field.width; j++) {
                                    if (!(field.Get(i, j) instanceof Land)) {
                                        bloodBar=(BloodBar)fightPane.getChildren().get(index);
                                        index=index+2;
                                        bloodBar.setCreature(i,j,field.Get(i,j));
                                    }
                                }
                            }

                        }
                    }
                    else
                    {
                        goodGuysComboBox.setVisible(false);
                        talkingPane.setVisible(true);
                        //启动线程，开始游戏
                        for (int i = 0; i < field.height; i++) {
                            for (int j = 0; j < field.width; j++) {
                                if(!(field.Get(i,j) instanceof Land))
                                {
                                    Thread t=new Thread(field.Get(i,j));
                                    field.Get(i,j).setThread(t);
                                    t.start();
                                }
                            }
                        }
                        break;
                    }
                }

            }
        });

        Thread badComboThread=new Thread(new Runnable() {
            String oldValue="衡轭阵";
            String newValue="衡轭阵";
            Formation formation=hengEFormation;
            FormationGenerator factory=new FormationGenerator();
            @Override
            public void run() {
                while(true){
                    if(gameState==0)
                    {
                        oldValue=newValue;
                        newValue=badGuysComboBox.getValue();
                        //System.out.println(newValue);
                        int index=2;
                        BloodBar bloodBar=null;
                        Label label=null;
                        if(!newValue.equals(oldValue))
                        {
                            field.Remove(formation);
                            for(int i=2;i<fightPane.getChildren().size();i++)
                            {
                                if(i%2==0) {
                                    bloodBar = (BloodBar) fightPane.getChildren().get(i);
                                    bloodBar.setWidth(0);
                                }
                            }

                            formation=factory.produceFormation(newValue,enemy,1);
                            field.Add(formation);

                            //重新设置好血条
                            for (int i = 0; i < field.height; i++) {
                                for (int j = 0; j < field.width; j++) {
                                    if (!(field.Get(i, j) instanceof Land)) {
                                        bloodBar=(BloodBar)fightPane.getChildren().get(index);
                                        index=index+2;
                                        bloodBar.setCreature(i,j,field.Get(i,j));
                                    }
                                }
                            }

                        }
                    }
                    else
                    {
                        badGuysComboBox.setVisible(false);
                        break;
                    }
                }

            }
        });

        goodComboThread.start();
        badComboThread.start();
    };

    @FXML
    void readHistory()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("读取存档");
        fileChooser.setInitialDirectory(new File("../"));
        //fileChooser.setInitialDirectory(new File("./src/main/resources"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HISTORY","*.history"));
        File historyFile=fileChooser.showOpenDialog(stage);
        if(historyFile==null) return;

        //Replay replay=new Replay(imageCache,"./src/main/resources/"+historyFile.getName(),field.width,field.height);
        System.out.println("存档位置: "+historyFile);
        Replay replay=new Replay(imageCache,historyFile,field.width,field.height);
        FutureTask<ArrayList<Creature[][]>> futureTask=new FutureTask<ArrayList<Creature[][]>>(replay);
        Thread thread=new Thread(futureTask) ;
        thread.start();

        getReady(1);
        talkingPane.setVisible(true);

        //加载每个人的血条
        try {
            historyField = futureTask.get();  //阻塞等待返回值
            //System.out.println(historyField.size());
            Creature[][] tmp=historyField.get(0);
            for (int i = 0; i < field.height; i++) {
                for (int j = 0; j < field.width; j++) {
                    if (!(tmp[i][j] instanceof Land)) {
                        BloodBar bloodBar=new BloodBar(i,j,tmp[i][j]);
                        Label label=bloodBar.getHpValue();
                        label.setVisible(false);
                        fightPane.getChildren().add(bloodBar);
                        fightPane.getChildren().add(label);

                    }
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ;
    }

    @FXML
    public void aboutGame()
    {
        gameTable.setVisible(true);
        closeIntroduction.setVisible(true);
    }

    @FXML
    public  void closeAboutGame()
    {
        gameTable.setVisible(false);
        closeIntroduction.setVisible(false);
    }


    @FXML
    public void saveGame()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("读取存档");
        fileChooser.setInitialDirectory(new File("../"));
        //fileChooser.setInitialDirectory(new File("./src/main/resources"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HISTORY","*.history"));
        File saveFile=fileChooser.showSaveDialog(stage);
        if(saveFile==null) return;
        System.out.println("保存位置: "+saveFile);
        //serializeCreatures("./src/main/resources/"+saveFile.getName());
        serializeCreatures(saveFile);
        saveCreatures.clear();
    }

    @FXML
    public void returnBackMenu()
    {
        endImage.setVisible(false);
        saveCurGame.setVisible(false);
        returnMenu.setVisible(false);
        startGame.setVisible(true);
        readHistory.setVisible(true);
        aboutGame.setVisible(true);
        title.setVisible(true);
        checkerBoard.setVisible(false);
        talkingPane.setVisible(false);
        backGround.setImage(new Image(this.getClass().getResourceAsStream("/backGround.png")));
        musicPlayer.setMusicName("/menu.mp3");

        gameMode=-1;
        gameState=0;
        cleanCheckerBoard();
        saveCreatures.clear();
    }

}
