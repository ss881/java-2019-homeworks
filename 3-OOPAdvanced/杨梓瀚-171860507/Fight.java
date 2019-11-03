import java.util.Vector;

public class Fight {
    private static final int N = 10;

    private static Grandpa grandpa;

    private static Vector<Minions>minionsVector;

    private static Snake snake;

    private static Scorpion scorpion;

    private static Creature[][] war;

    Fight(){
        war = new Creature[N][N];
        grandpa = new Grandpa(N);
        snake = new Snake();
        scorpion = new Scorpion(N);

    }

    public static void main(String[] argc){
        Fight fight = new Fight();

        snake.setPosition(N / 2, N - 1 );
        grandpa.setPosition(N / 2, 0);
        //snake.setWar(war);
        //葫芦娃乱序
        grandpa.drawLots();

        //葫芦娃长蛇形
        grandpa.snake();
        grandpa.set(war);
        print();

        //蝎子精变换阵型
        for (int i = 0; i < 7; i++){
            scorpion.go(war);  //第i种方案
            snake.setWar(war);
            print();
            clearMinions();
        }

    }

    public static void clearMinions() {
        for (int i = 0; i < N; i++ ){
            for (int j = N / 2; j < N; j++ ) {
                war[i][j] = null;
            }
        }
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (war[i][j] == null){
                    System.out.print(' ' + " ");
                }else {
                    KIND k = war[i][j].getKind();
                    if (k == KIND.calaBro){
                        System.out.print('C' + " ");
                    }else if(k == KIND.grandpa) {
                        System.out.print('G' + " ");
                    }else if(k == KIND.minion) {
                        System.out.print('M' + " ");
                    }else if(k == KIND.scorpion) {
                        System.out.print('S' + " ");
                    }else if(k == KIND.snake) {
                        System.out.print('N' + " ");
                    }
                }
            }
            System.out.print('\n');
        }
    }

}
