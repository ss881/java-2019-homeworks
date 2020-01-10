package  sample;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;


public class SpaceForBattle{


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

    public static final int time_gap=500;
    private int size_of_space;// the size of space
    private int [][]space;// to store the whole space
    public Object [][]sign;
   public SpaceForBattle(int initial_size)
    {
        size_of_space=initial_size;
        space =new int [size_of_space][size_of_space];
        for(int i=0;i<size_of_space;++i)
            for(int j=0;j<size_of_space;++j)
                space[i][j]=0;
            sign=new Object[size_of_space][size_of_space];
        for(int i=0;i<size_of_space;++i)
            for(int j=0;j<size_of_space;++j)
                sign[i][j]=new Object();
    }

    private int identityTomark(Identity id)// change id into int
    {
        int mark=0;
        switch(id)
        {
            case Grandpa:   
                mark=1;break;
            case BrotherStandl: //this is the general mark ,
                                // we use more concrete mark to represent differet brotherstandl 
                mark=2;break;
            case MonsterSnake:
                mark=3;break;
            case MonsterScorpion:
                mark=4;break;
            case MonsterSaltFish:
                mark=5;break;
            case Unkown:break;
        }
        return mark;
    }

    void rebulid_space()// empty the space
    {
        for(int i=0;i<size_of_space;++i)
            for(int j=0;j<size_of_space;++j)
                space[i][j]=0;
    }
    boolean rush_to_coordinate(Identity id, int x,int y)
    //one creature with id rush to somespace from out
    {
        int mark=identityTomark(id);
        if(x<0||y<0||x>=size_of_space||y>=size_of_space)
            return false;
        if(space[x][y]==0)
        {
            space[x][y]=mark;
            return true;
        }
        else 
            return false;
    }

    void rush_to_coordinate(Creature creature,int mark, int x,int y)
    //one creature with id rush to somespace from out
    {
        space[creature.get_current_x()][creature.get_current_y()]=0;
        space[x][y]=mark;
    }

    boolean rush_to_coordinate(BrotherStandl brotherStandl, int x,int y)
    // use the special function to tell the difference of brotherstandl
    {
        int mark=brotherStandl.report_color()+10;
        if(x<0||y<0||x>=size_of_space||y>=size_of_space)
            return false;
        if(space[x][y]==0)
        {
            space[x][y]=mark;
            return true;
        }
        else 
            return false;
    }


    void change_coordinate(Identity id,int x1, int x2, int y1, int y2)
    // one creature with id change its space to another
    {
        if(space[x2][y2]==0)
        {
            rush_to_coordinate(id, x2, y2);
            if(x1<0||y1<0||x1>=size_of_space||y1>=size_of_space);
            else
            space[x1][y1]=0;
        }   
        else
        {
            int partner=space[x2][y2];
            space[x2][y2]=0;
            rush_to_coordinate(id, x2, y2);
            space[x1][y1]=partner;
        }
    }
    void overlook_space()// output the whole space from high
    {
        for(int i=0;i<size_of_space;++i)
        {
            for(int j=0;j<size_of_space;++j)
            {
                int tmp=space[j][i];
                //if(tmp<0)
                  //  tmp=-tmp;
                switch(tmp)
                {
                    case 0:System.out.printf("%1s","  ");break;
                    case 1:System.out.printf("%1s","爷");break;
                    case 3:System.out.printf("%1s","蛇");break;
                    case 4:System.out.printf("%1s","蝎");break;
                    case 5:System.out.printf("%1s","兵");break;
                    case 11:System.out.printf("%1s","红");break;// we cannot tell the rank of the brotherstandl from high
                                                                // so we use color to represent each brotherstandl
                    case 12:System.out.printf("%1s","橙");break;
                    case 13:System.out.printf("%1s","黄");break;
                    case 14:System.out.printf("%1s","绿");break;
                    case 15:System.out.printf("%1s","青");break;
                    case 16:System.out.printf("%1s","蓝");break;
                    case 17:System.out.printf("%1s","紫");break;
                    default:System.out.printf("%1s"," ");break;
                }
            }
            System.out.print("\n");
        }
    }
    int get_size()// get the size of space
    {
        return size_of_space;
    }
    void set_space(int chess,int x,int y)// set one concrete space
    {
        space[x][y]=chess;
    }
    public int read_space(int x,int y)// try to know the state of concrete space
    {
        if(x<0||y<0||x>=size_of_space||y>=size_of_space)
        return 0;
        else
        return space[x][y];
    }
    boolean check_space(Identity id ,int x ,int y)// try to check one concrete space's state
    {
        if(x<0||y<0||x>=size_of_space||y>=size_of_space)
            return false;
        if(space[x][y]==identityTomark(id))
            return true;
        return false;
    }

    int color_space(Creature a,int x,int y)// we don't use this
    {
        if(x<0||y<0||x>=size_of_space||y>=size_of_space)
            return -1;
        if(read_space(x, y)!=0)
            return -1;
        int [][]tmp=new int[size_of_space][size_of_space];
        for(int i=0;i<size_of_space;++i)
            for(int j=0;j<size_of_space;++j)
                tmp[i][j]=-1;
        tmp[x][y]=0;
        draw_space(tmp, x-1, y, 1);
        draw_space(tmp, x+1, y, 1);
        draw_space(tmp, x, y+1, 1);
        draw_space(tmp, x, y+1, 1);
        int min=-1;
        for(int i=0;i<size_of_space;++i)
            for(int j=0;j<size_of_space;++j)
                {
                    if(tmp[i][j]!=-1&&(min==-1||tmp[i][j]<min)&&space[i][j]>0&&Creature.monster(space[i][j])!=a.monster())
                        min=tmp[i][j];
                }
        return -1;
    }
    private void draw_space(int [][]tmp,int x,int y,int distance)
    {
        if(x<0||y<0||x>=size_of_space||y>=size_of_space)
            return;
        if(tmp[x][y]!=-1&&tmp[x][y]<distance)
            return;
        if(read_space(x, y)!=0)
            return;
        tmp[x][y]=distance;
        draw_space(tmp, x-1, y, distance+1);
        draw_space(tmp, x+1, y, distance+1);
        draw_space(tmp, x, y+1, distance+1);
        draw_space(tmp, x, y+1, distance+1);
        

    }
    void find_empty(int []x,int []y)
    {
        int min=-1;
        for(int i=0;i<size_of_space;++i)
            for(int j=0;j<size_of_space;++j)
            {
                if(read_space(i, j)==0)
                {
                    if(min==-1||compute_distance(x[0], y[0], i, j)<min)
                    {
                        min=compute_distance(x[0], y[0], i, j);
                        x[1]=i;
                        y[1]=j;
                    }
                }
            }
    }
    static int compute_distance(int x1,int y1,int x2,int y2)
    {
        int dx=x1-x2;
        if(dx<0)
            dx=-dx;
        int dy=y1-y2;
        if(dy<0)
            dy=-dy;
        return dx+dy;
    }
   public boolean isEnd()
    {
        int flag=-1;
        boolean f1=true;
        for(int i=0;i<size_of_space;++i)
            for(int j=0;j<size_of_space;++j)
            {
                if(space[i][j]>0&&flag==-1)
                {
                    flag=1;
                    f1=Creature.monster(space[i][j]);
                }
                else if(space[i][j]>0&&Creature.monster(space[i][j])!=f1)
                    return false;
            }
        return true;
    }

    void show(Stage stage)//find bugs in javafx8
            //ok in javafx11
            //so  we drop this func
    {

        GridPane gridPane=new GridPane();
        for(int i=0;i<Main.size_of_current_space;++i)
            for(int j=0;j<Main.size_of_current_space;++j)
            {
                Rectangle rectangle=new Rectangle();
                rectangle.setHeight(Main.max_size_of_stage/Main.size_of_current_space);
                rectangle.setWidth(Main.max_size_of_stage/Main.size_of_current_space);
                rectangle.setFill(Color.WHITE);
                gridPane.add(rectangle,i,j);
            }
        int t=0;
        for(int i=0;i<size_of_space;++i)
            for(int j=0;j<size_of_space;++j)
            {
                Rectangle rectangle=(Rectangle) gridPane.getChildren().get(t);
                switch (space[i][j])
                {
                    case 0:rectangle.setFill(Color.WHITE);break;
                    case 1:rectangle.setFill(new ImagePattern(new Image("sample/resource/grandpa1.png")));break;
                    case 4:rectangle.setFill(new ImagePattern(new Image("sample/resource/xiezi1.png")));break;
                    case 3:rectangle.setFill(new ImagePattern(new Image("sample/resource/shejing1.png")));break;
                    case 5:rectangle.setFill(new ImagePattern(new Image("sample/resource/zayu1.png")));break;
                    case 11:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa1-1.png")));break;
                    case 12:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa2-1.png")));break;
                    case 13:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa3-1.png")));break;
                    case 14:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa4-1.png")));break;
                    case 15:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa5-1.png")));break;
                    case 16:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa6-1.png")));break;
                    case 17:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa7-1.png")));break;

                    case -1:rectangle.setFill(new ImagePattern(new Image("sample/resource/grandpa2.png")));break;
                    case -4:rectangle.setFill(new ImagePattern(new Image("sample/resource/xiezi2.png")));break;
                    case -3:rectangle.setFill(new ImagePattern(new Image("sample/resource/shejing2.png")));break;
                    case -5:rectangle.setFill(new ImagePattern(new Image("sample/resource/zayu2.png")));break;
                    case -11:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa1-2.png")));break;
                    case -12:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa2-2.png")));break;
                    case -13:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa3-2.png")));break;
                    case -14:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa4-2.png")));break;
                    case -15:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa5-2.png")));break;
                    case -16:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa6-2.png")));break;
                    case -17:rectangle.setFill(new ImagePattern(new Image("sample/resource/huluwa7-2.png")));break;
                    default:break;
                }
                ++t;
            }

        AnchorPane anchorPane=new AnchorPane();
        anchorPane.getChildren().add(gridPane);
        Scene scene=new Scene(anchorPane);
        stage.setScene(scene);

    }

    public boolean judge(int x,int y)
    {
        if(x<0||y<0||x>=size_of_space||y>=size_of_space)
            return false;
        return true;
    }

    void show(GraphicsContext graphicsContext,int [][]tmp)//find bugs with javafx8
            //ok for javafx11 so we drop this func
    {
 {
     graphicsContext.drawImage(background,0,0,Main.max_size_of_stage,Main.max_size_of_stage);
           // graphicsContext.clearRect(0,0,Main.max_size_of_stage,Main.max_size_of_stage);
            for (int i = 0; i < size_of_space; ++i)
                for (int j = 0; j < size_of_space; ++j) {

                    // System.out.println(1+ " "+j);
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
                            else if (space[i][j] >= -5)
                                image = images.get(-1 * space[i][j] + 10);
                            else
                                image = images.get(-1 * space[i][j] + 5);
                        }
                        if(space[i][j]!=0)
                        graphicsContext.drawImage(image, i * image_size, j * image_size, image_size, image_size);
                    }

                }

        }
    }





    
}