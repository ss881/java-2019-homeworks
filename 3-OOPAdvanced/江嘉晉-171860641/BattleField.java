import java.util.Random;

public class BattleField{
    private Creature nullPos;
    private Creature[][] goodGuys;
    private Creature[][] badGuys;
    private CalabashBros[] calabashBros;
    private Grandpa grandpa;
    private Scorpion scorpion;
    private Servant[] servants;
    private Snake snake;
    
    public BattleField(){
        nullPos = new Creature();
        goodGuys = new Creature[11][11];
        badGuys = new Creature[11][11];
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                goodGuys[i][j] = nullPos;
                badGuys[i][j] = nullPos;
            }
        }
        calabashBros = new CalabashBros[Constants.BROS_NUM];
        grandpa = new Grandpa("爺爺");
        scorpion = new Scorpion("蝎子");
        servants = new Servant[Constants.SERVANT_NUM];
        for(int i=0;i<Constants.SERVANT_NUM;i++){
            servants[i] = new Servant("嘍囉");
        }
        snake = new Snake("蛇精");

        CalabashBros first = new CalabashBros("老大", "紅色", 1);
        CalabashBros second = new CalabashBros("老二", "橙色", 2);
        CalabashBros third = new CalabashBros("老三", "黃色", 3);
        CalabashBros fourth = new CalabashBros("老四", "綠色", 4);
        CalabashBros fifth = new CalabashBros("老五", "青色", 5);
        CalabashBros sixth = new CalabashBros("老六", "藍色", 6);
        CalabashBros seventh = new CalabashBros("老七", "紫色", 7);
        CalabashBros[] bros = {first, second, third, fourth, fifth, sixth, seventh};
        calabashBros = bros;

    }
    public void randomPosCalaBros(){
        clearGoodGuys();
        Random rand = new Random();
        int x;
        int y;
        for(int i=0;i<Constants.BROS_NUM;i++){
            x = rand.nextInt(11);
            y = rand.nextInt(11);
            if(goodGuys[x][y].getName()==null){
                goodGuys[x][y] = calabashBros[i];
                // goodGuys[x][y].setX(x);
                // goodGuys[x][y].setY(y);
            }
        }
    }
    private void grandpaPos(){
        Random rand = new Random();
        int x = rand.nextInt(11);
        goodGuys[0][x] = grandpa;
    }
    private void snakePos(){
        Random rand = new Random();
        int x = rand.nextInt(11);
        badGuys[0][x] = snake;
    }
    public void changShe(){
        clearGoodGuys();
        grandpaPos();
        for(int i=2,j=0;i<=8;i++,j++){
            goodGuys[i][8] = calabashBros[j];
        }
    }
    public void heYi(){
        clearBadGuys();
        snakePos();
        badGuys[2][0] = scorpion;
        int k=0;
        int i=3;
        int j=1;
        for(;i<=7;i++,j++,k++){
            badGuys[i][j] = servants[k];
        }
        for(i=6;i>=2;i--,j++,k++){
            badGuys[i][j] = servants[k];
        }
    }
    public void yanHang(){
        clearBadGuys();
        snakePos();
        badGuys[10][0] = scorpion;
        for(int i=9,j=1,k=0;i>0;i--,j++,k++){
            badGuys[i][j] = servants[k];
        }
    }
    public void chongE(){
        clearBadGuys();
        snakePos();
        badGuys[1][3] = scorpion;
        for(int i=2,k=0;i<10;i+=2,k+=2){
            badGuys[i][2] = servants[k];
            badGuys[i+1][3] = servants[k+1];
        }
    }
    public void yuLin(){
        clearBadGuys();
        snakePos();
        badGuys[3][3] = scorpion;
        int k=0;
        int t=2;
        int j_s=2;
        int j;
        for(int r=4;r<7;r++){
            j=j_s;
            for(int i=0;i<t;i++,j+=2,k++){
                badGuys[r][j] = servants[k];
            }
            j_s-=1;
            t++;
        }
        badGuys[7][3] = servants[k];
    }
    public void fangYuan(){
        clearBadGuys();
        snakePos();
        badGuys[3][3] = scorpion;
        int j_s = 2;
        int k=0;
        for(int r=4;r<7;r++,k+=2){
            badGuys[r][j_s] = servants[k];
            badGuys[r][6-j_s] = servants[k+1];
            j_s-=1;
        }
        j_s = 1;
        for(int r=7;r<10;r++,k+=2){
            badGuys[r][j_s] = servants[k];
            badGuys[r][6-j_s] = servants[k+1];
            j_s+=1;
        }
    }
    public void yanYue(){
        clearBadGuys();
        snakePos();
        badGuys[2][3] = servants[0];
        badGuys[2][4] = servants[1];
        badGuys[3][2] = servants[2];
        badGuys[3][3] = servants[3];
        badGuys[4][0] = servants[4];
        badGuys[4][1] = servants[5];
        badGuys[4][2] = servants[6];
        badGuys[5][0] = scorpion;
        badGuys[5][1] = servants[7];
        badGuys[5][2] = servants[8];
        badGuys[6][0] = servants[9];
        badGuys[6][1] = servants[10];
        badGuys[6][2] = servants[11];
        badGuys[7][2] = servants[12];
        badGuys[7][3] = servants[13];
        badGuys[8][3] = servants[14];
        badGuys[8][4] = servants[15];

    }
    public void fengShi(){
        clearBadGuys();
        snakePos();
        int k=0;
        for(int r=6,j=0;r>=4;r--,j++,k++){
            badGuys[r][j] = servants[k];
        }
        badGuys[3][3] = scorpion;
        for(int r=4;r<=9;r++,k++){
            badGuys[r][3] = servants[k];
        }
        for(int r=4,j=4;r<=6;r++,k++,j++){
            badGuys[r][j] = servants[k];
        }
    }


    private void clearGoodGuys(){
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                goodGuys[i][j] = nullPos;
            }
        }
    }
    private void clearBadGuys(){
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                badGuys[i][j] = nullPos;
            }
        }
    }
    public void printArray(){
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(goodGuys[i][j].getName()!=null)
                    System.out.print(goodGuys[i][j].getName());
                else
                    System.out.print("    ");
            }
            System.out.print("        ");
            for(int j=0;j<11;j++){
                if(badGuys[i][j].getName()!=null)
                    System.out.print(badGuys[i][j].getName());
                else
                    System.out.print("    ");
            }
            System.out.println();
        }
        System.out.print("\n\n\n");
    }
}
class Constants{
    public static final int BROS_NUM = 7;
    public static final int SERVANT_NUM = 20;
}