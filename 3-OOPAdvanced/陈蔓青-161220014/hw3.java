import java.util.Random;

enum HuluwaNames {大娃, 二娃, 三娃, 四娃, 五娃, 六娃, 七娃}


class Creature{                                         //所有生物

    private String name;
    private String type;
    public String getName() { return name; }

    Creature(String name, String type){                 //构造函数
        this.name = name;
        this.type = type;
    }
}


class Grandpa extends Creature{                         //爷爷
    Grandpa(){
        super("爷爷", "人类");
    }
}


class SheJing extends Creature{                         //蛇精
    SheJing(){
        super("蛇精", "蛇妖");
    }
}


class Huluwa extends Creature{                          //葫芦娃
    Huluwa(int i){
        super(HuluwaNames.values()[i].toString(), "葫芦娃");
    }
}


class Monster extends Creature{                         //小妖怪
    Monster(){
        super("妖怪", "小妖怪");
    }
}


class BattleWorld{

    private final int SIZE = 12;                        //NxN
    private int [][]map;                                //战场
    private Creature[] creatures;                       //0_爷爷, 1234567_葫芦娃x7, 8_蛇精, 9...小妖怪们
    private final int Number = 30;                           //生物体总数

    BattleWorld(){                                      //战场空间就位！
        creatures = new Creature[Number];                    //初始化所有生物
        creatures[0] = new Grandpa();
        for (int i = 1; i <= 7; i++){
            creatures[i] = new Huluwa(i - 1);
        }
        creatures[8] = new SheJing();
        for (int i = 9; i < Number; i++) {
            creatures[i] = new Monster();
        }

        map = new int[SIZE][];                          //初始化战场
        for (int i = 0; i < SIZE; i++) {
            map[i] = new int[SIZE];
        }
    }

    /*private void clean(){                               //清空战场
        for (int i = 1; i < SIZE; i++) {
            for (int j = 4; j < SIZE; j++) {
                map[i][j] = -1;
            }
        }
    }*/

    void CalabashBroRandom(){                           //葫芦娃的长蛇阵
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = -1;
            }
        }
        int startLine = 3, startCol = 2;
        int[] calabashArray = {1, 2, 3, 4, 5, 6, 7};
        for (int i = 7; i > 0; i--){
            Random random = new Random();
            int rand = random.nextInt(i);
            int temp = calabashArray[rand];
            calabashArray[rand] = calabashArray[i - 1];
            calabashArray[i - 1] = temp;
        }
        for (int i = startLine; i < startLine + 7; i++){
            map[i][startCol] = calabashArray[i - startLine];
        }
        map[0][2] = 0;
    }

    void RandomSheJing(){
        /*for (int i = 0, j = 0; ; ) {
            Random randomGenerator = new Random();
            i = 3 + randomGenerator.nextInt(8);
            j = randomGenerator.nextInt(11);
            if (map[i][j] == -1) {
                map[i][j] = 8;
                break;
            }
        }*/
        map[0][10] = 8;
    }

    void heyi(){                                    //鹤翼
        //clean();
        int Row = 5, Col = 5;
        int j = 9;
        for (int i = Row; i < Row + 4; i++){
            map[i][Col + i - Row] = j++;
            map[i][Col + Row + 6 - i] = j++;
        }
        RandomSheJing();
    }

    void yanxing(){                                 //雁行
        //clean();
        int Row = 4, Col = 6;
        int j = 9;
        for (int i = 0; i < 5; i++){
            map[Row - i + 4][Col + i] = j++;
        }
        RandomSheJing();
    }

    void henge(){                                   //衡轭
        //clean();
        int Row = 3, Col = 7;
        int j = 9;
        for (int i = Row; i < Row + 6; i++){
            map[i][Col + (i - Row + 1) % 2] = j++;
        }
        RandomSheJing();
    }

    void yulin(){                                   //鱼鳞
        //clean();
        int Row = 4, Col = 5;
        int j = 9;
        for (int i = Row; i < Row + 4; i++){
            for (int k = 0; k < (i - Row) * 2 + 1; k++){
                map[i][Row + Col + 3 - i + k] = j++;
            }
        }
        map[Row + 4][Col + 2] = map[Row + 1][Col + 2];
        map[Row + 1][Col + 2] = -1;
        map[Row + 4][Col + 3] = map[Row + 1][Col + 3];
        map[Row + 1][Col + 3] = -1;
        RandomSheJing();
    }

    void fangyuan(){                                //方円
        //clean();
        int Row = 4, Col = 6;
        int j = 9;
        map[Row][Col + 2] = j++;
        for (int i = Row + 1; i < Row + 5; i++) {
            map[i][Col + Math.abs(i - Row - 2)] = j++;
            map[i][Col + 4 - Math.abs(i - Row - 2)] = j++;
        }
        RandomSheJing();
    }

    void yanyue(){                                  //偃月
        //clean();
        int Row = 2, Col = 6;
        int j = 9;
        for (int i = Row; i < Row + 3; i++){
            map[i][Col + Row + 3 - i] = j++;
            map[i + 1][Col + Row + 3 - i] = j++;
        }
        map[Row + 3][Col] = j++;
        map[Row + 3][Col - 1] = j++;
        map[Row + 4][Col] = j++;
        map[Row + 4][Col - 1] = j++;
        map[Row + 5][Col] = j++;
        map[Row + 5][Col - 1] = j++;
        for (int i = Row + 5; i < Row + 8; i++){
            map[i][Col + i - Row - 4] = j++;
            map[i + 1][Col + i - Row - 4] = j++;
        }
        map[Row + 4][Col + 1] = j;
        RandomSheJing();
    }

    void fengshi(){                                 //锋矢
        //clean();
        int startRow = 3, startCol = 5;
        int j = 9;
        map[startRow][startCol + 3] = j++;
        for (int i = startRow + 1; i < startRow + 4; i++){
            map[i][startCol + i - startRow + 3] = j++;
            map[i][startCol + startRow + 3 - i] = j++;
        }
        for (int i = startRow + 1; i < startRow + 7; i++){
            map[i][startCol + 3] = j++;
        }
        RandomSheJing();
    }

    void print() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == -1){
                    System.out.print("    ");
                }else{
                    System.out.print(creatures[map[i][j]].getName());
                }
            }
            System.out.print("\n");
        }
    }
}


public class hw3{

    public static void main(String[] args){

        BattleWorld battle = new BattleWorld();

        System.out.println("\n鹤翼\n");
        battle.CalabashBroRandom();
        battle.heyi();
        battle.print();

        System.out.println("\n雁行\n");
        battle.CalabashBroRandom();
        battle.yanxing();
        battle.print();

        System.out.println("\n衡轭\n");
        battle.CalabashBroRandom();
        battle.henge();
        battle.print();

        System.out.println("\n鱼鳞\n");
        battle.CalabashBroRandom();
        battle.yulin();
        battle.print();

        System.out.println("\n方円\n");
        battle.CalabashBroRandom();
        battle.fangyuan();
        battle.print();

        System.out.println("\n偃月\n");
        battle.CalabashBroRandom();
        battle.yanyue();
        battle.print();

        System.out.println("\n锋矢\n");
        battle.CalabashBroRandom();
        battle.fengshi();
        battle.print();
    }

}
