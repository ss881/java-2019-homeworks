package gamectrl;

import Record.*;
import creature.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainControl implements Initializable , MainConfig {
    // UI刷新和交互控制
    @FXML private BorderPane root;
    @FXML public Label downTextField,upTextField;
    @FXML private GridPane grid;
    @FXML private Button
            btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,
            btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16;
    private Canvas canvas=null;
    public static Stage stage = null;
    public GraphicsContext gc;
    private SceneRefresher refresher;

    public Image
            background= new Image("images/background.jpg"),
            pauseImg=new Image("images/pause.png"),
            huluwaWinImg=new Image("images/huluwawin.png"),
            loloWinImg =new Image("images/lolowin.png");
    //  创世者，葫芦娃和小喽啰由他们生成
    private GrandPa papa;
    private  ScorpionSperm xiezijing;
    private SnakeEssence shejing;
    //  游戏逻辑控制，列表中的人物会自行观察、辨别敌友、攻击目标
    public Ground2D map;
    public int row,col,step;
    private ArrayList<Creature>creatures;
    public boolean
            isPaused=false,gameOver=false;
    private boolean
            isPlaying=false,isInited=false,
            loloInited =false,huluwaInited=false;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public MainControl() {
        System.out.println("构造完成");
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("窗体初始化");
        downTextField.setAlignment(Pos.CENTER);
        upTextField.setAlignment(Pos.CENTER);
        downTextField.setText(MainConfig.hint);
        upTextField.setText(MainConfig.name);
        grid.setStyle("-fx-background-color: rgb(205.0,205.0,205.0);");
    }

    public void init(){
        //  初始化地图
        if(isInited){
            return;
        }
        refresher=new SceneRefresher(this);
        if(canvas==null){
            canvas = new Canvas(grid.getWidth(), grid.getHeight());
            grid.add(canvas, 0, 0);
            grid.setHgrow(canvas, Priority.ALWAYS);
            gc = canvas.getGraphicsContext2D();
            row=MainConfig.ROW;
            step= (int) (canvas.getHeight()/row);
            System.out.println("方格步长="+step);
            col= (int) (canvas.getWidth()/step);
        }
        initRoles();
    }

    private void initRoles(){
        //初始化角色
        Creature.ctrl=this;
        papa=new GrandPa();
        papa.callCalabashBrothers();//老爷爷种出葫芦娃
        shejing=new SnakeEssence();//这出戏没蛇精什么事
        xiezijing=new ScorpionSperm();
        xiezijing.callEvilLolos(20);//蝎子精召唤小喽啰
        isInited=true;
    }

    private void initMap(){
        // 起始坐标的控制权在老爷爷和蝎子精手中
        // 未初始化则调用默认阵型
        if (!loloInited) {
            loloInited=true;
            xiezijing.makeNewFormation(new Random().nextInt(8));
        }
        if (!huluwaInited) {
            huluwaInited=true;
            papa.makeNewFormation(new Random().nextInt(8));
        }
        // 将所有人物放到任务队列，刷新到UI上，自由对战
        creatures=new ArrayList<>();
        map=new Ground2D(col,row);
        for(int i=0;i<7;i++){
            creatures.add(papa.getCalabashBrothers()[i]);
            map.acceptMove(papa.getCalabashBrothers()[i]);
        }
        creatures.add(papa);
        creatures.add(shejing);
        creatures.add(xiezijing);
        map.acceptMove(papa);
        map.acceptMove(shejing);
        map.acceptMove(xiezijing);
        for(int i=0;i<xiezijing.getLoloCounter();i++){
            creatures.add(xiezijing.getEvilLolos()[i]);
            map.acceptMove(xiezijing.getEvilLolos()[i]);
        }
    }

    private void resetFlags(){
        isPlaying=false;isInited=false;
        loloInited =false;huluwaInited=false;
        isPaused=false;gameOver=false;
        downTextField.setText(MainConfig.hint);
        upTextField.setText(MainConfig.name);
    }

    private void disableButtons(){
        btn1.setDisable(true);btn2.setDisable(true);btn3.setDisable(true);btn4.setDisable(true);
        btn5.setDisable(true);btn6.setDisable(true);btn7.setDisable(true);btn8.setDisable(true);
        btn9.setDisable(true);btn10.setDisable(true);btn11.setDisable(true);btn12.setDisable(true);
        btn13.setDisable(true);btn14.setDisable(true);btn15.setDisable(true);btn16.setDisable(true);
    }

    private void enableButtons(){
        btn1.setDisable(false);btn2.setDisable(false);btn3.setDisable(false);btn4.setDisable(false);
        btn5.setDisable(false);btn6.setDisable(false);btn7.setDisable(false);btn8.setDisable(false);
        btn9.setDisable(false);btn10.setDisable(false);btn11.setDisable(false);btn12.setDisable(false);
        btn13.setDisable(false);btn14.setDisable(false);btn15.setDisable(false);btn16.setDisable(false);
    }

    private void play() throws IOException {
        if(isPlaying){
            return;
        }isPlaying=true;gameOver=false;
        disableButtons();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
        File file = new File(df.format(new Date()).toString() + ".log");
        System.out.println(file.getAbsolutePath());//以当前系统时间命名
        if(!file.exists())
            file.createNewFile();
        writer = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        //  记录生物总数，即使生物变成墓碑，生物总数还是不变
        writer.writeInt(creatures.size());
        new Thread(refresher).start();
        for(Creature i : creatures){
            new Thread(i).start();
        }
    }

    public void update() throws IOException {
        //  记录刷新率，且对所有Creature进行序列化
        //  以刷新率-x为间隔，对Creature反序列化，展示
        //  忽略暂停图标，重播过程不予暂停，允许按键介入退出
        gc.drawImage(background, 0, 0, canvas.getWidth(),canvas.getHeight());
        for(Creature i : creatures){
            if (i != null && !i.isAlive) {
                i.show();
                Record tmp=new Record(i);
                if(writer!=null)
                    writer.writeObject(tmp);
            }
        }
        for(Creature i : creatures){
            if (i != null && i.isAlive) {
                i.show();
                Record tmp=new Record(i);
                if(writer!=null)
                    writer.writeObject(tmp);
            }
        }
        if(isPaused && !gameOver){
            //  画暂停图标
            gc.drawImage(pauseImg,0, 0, canvas.getWidth(),canvas.getHeight());
            //Record tmp=new Record();
            //tmp.setType(26);
            //writer.writeObject(tmp);
        }
        boolean hasAliveHuluwa=false,hasAliveLolo=false;
        for(Creature i : creatures){
            if (i.isAlive && !i.isEvil) {
                hasAliveHuluwa = true;
                break;
            }
        }
        for(Creature i : creatures){
            if (i.isAlive && i.isEvil) {
                hasAliveLolo = true;
                break;
            }
        }
        if(!hasAliveHuluwa){
            gc.drawImage(loloWinImg,0, 0, canvas.getWidth(),canvas.getHeight());
            gameOver=true;
            //Record tmp=new Record();
            //tmp.setType(27);
            //writer.writeObject(tmp);
            if(writer!=null)
                writer.close();
            writer=null;
            isPlaying=false;
        } else if (!hasAliveLolo) {
            gc.drawImage(huluwaWinImg,0, 0, canvas.getWidth(),canvas.getHeight());
            gameOver = true;
            //Record tmp=new Record();
            //tmp.setType(28);
            //writer.writeObject(tmp);
            if(writer!=null)
                writer.close();
            writer=null;
            isPlaying=false;
        }
        //map.displayCli();
    }

    private void restart(){
        resetFlags();
        enableButtons();
        initRoles();
        initMap();
        try{
            update();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML private void onKeyPressed(KeyEvent keyEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setOnCloseRequest(
                event -> System.out.print("窗体销毁"));
        System.out.println(keyEvent.getCode());
        switch (keyEvent.getCode()) {
            case SPACE:
            {
                if(isReplaying){
                    break;
                }
                else if(gameOver){
                    restart();
                }
                else if (!isPlaying) {
                    init();
                    if(!loloInited || !huluwaInited){
                        initMap();
                    }
                    try{
                        play();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }else{
                    isPaused=!isPaused;
                }
                break;
            }
            case ESCAPE:{
                stage.close();
                System.exit(0);
            }
            case L:{
                if(isPlaying||isReplaying){break;}
                upTextField.setText("进入回放，功能按键已屏蔽");
                disableButtons();
                try{
                    review();
                }catch (Exception e){
                    e.printStackTrace();
                }
                //restart();
            }
            default:
            {
                break;
            }
        }
    }

    private void review() throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("."));
        chooser.setTitle("请选择游戏的历史记录文件");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("游戏记录文件", "*.log"));
        File file = chooser.showOpenDialog(stage);
        if (file == null) {
            return;
        }
        try {
            reader = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int countCreature= 0;
        try {
            countCreature = reader.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("countCreature="+countCreature);
        boolean isEnd=false;
        if(gc==null){
            canvas = new Canvas(grid.getWidth(), grid.getHeight());
            grid.add(canvas, 0, 0);
            grid.setHgrow(canvas, Priority.ALWAYS);
            gc = canvas.getGraphicsContext2D();
            row=MainConfig.ROW;
            step= (int) (canvas.getHeight()/row);
            col= (int) (canvas.getWidth()/step);
        }
        System.out.println("方格步长="+step);
        //  一次性读取所有数据
        ArrayList<ArrayList<Record>> records=new ArrayList<ArrayList<Record>>();
        while(true){
            try {
                ArrayList<Record> group=new ArrayList<Record>();
                for(int i=0;i<countCreature;i++){
                    Record re;
                    try{
                        re=(Record)reader.readObject();
                        group.add(re);
                        //System.out.println(re.toString());
                    }catch (EOFException e){
                        isEnd=true;
                        break;
                    }
                }
                if(isEnd){
                    break;
                }else{
                    records.add(group);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  建立播放线程
        RecordPlayer recordPlayer=new RecordPlayer(records,gc,this);
        isReplaying=true;
        new Thread(recordPlayer).start();
    }
    public boolean isReplaying=false;
    @FXML private void huluyanhangAction(ActionEvent actionEvent) {
        init();
        papa.makeNewFormation(5);
        huluwaInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void huluchangsheAction(ActionEvent actionEvent) {
        init();
        papa.makeNewFormation(0);
        huluwaInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void hulufangmenAction(ActionEvent actionEvent) {
        init();
        papa.makeNewFormation(1);
        huluwaInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void hulufengshiAction(ActionEvent actionEvent) {
        init();
        papa.makeNewFormation(2);
        huluwaInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void huluhengzhiAction(ActionEvent actionEvent) {
        init();
        papa.makeNewFormation(3);
        huluwaInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void huluheyiAction(ActionEvent actionEvent) {
        init();
        papa.makeNewFormation(4);
        huluwaInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void huluyulinAction(ActionEvent actionEvent) {
        init();
        papa.makeNewFormation(7);
        huluwaInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void huluyanyueAction(ActionEvent actionEvent) {
        init();
        papa.makeNewFormation(6);
        huluwaInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void loloyanhangAction(ActionEvent actionEvent) {
        init();
        xiezijing.makeNewFormation(5);
        loloInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void lolochangsheAction(ActionEvent actionEvent) {
        init();
        xiezijing.makeNewFormation(0);
        loloInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void lolofengshiAction(ActionEvent actionEvent) {
        init();
        xiezijing.makeNewFormation(2);
        loloInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void lolofangenAction(ActionEvent actionEvent) {
        init();
        xiezijing.makeNewFormation(1);
        loloInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void lolohengzhiAction(ActionEvent actionEvent) {
        init();
        xiezijing.makeNewFormation(3);
        loloInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void loloheyiAction(ActionEvent actionEvent) {
        init();
        xiezijing.makeNewFormation(4);
        loloInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void loloyulinAction(ActionEvent actionEvent) {
        init();
        xiezijing.makeNewFormation(7);
        loloInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }

    @FXML private void loloyanyueAction(ActionEvent actionEvent) {
        init();
        xiezijing.makeNewFormation(6);
        loloInited =true;
        initMap();
        try{
            update();
        }catch (Exception e){
            ;
        }
    }
}
