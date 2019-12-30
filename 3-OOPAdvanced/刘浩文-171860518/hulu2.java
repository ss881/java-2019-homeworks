//package hulu2;
import java.util.Random;

public class hulu2 {
    public static void main(String argc[]){
        final int N=15;
        final int impNums=7;
        char map[][]=new char[N][N];//标记性地图 方便输出
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++) map[i][j]='_';
        Random r=new Random();
        huluboys[] boys;
        boys=new huluboys[7];//葫芦娃

        imps[] impians;
        impians=new imps[impNums];//小喽啰
        for(int i=0;i<impNums;i++) impians[i]=new imps();

        oracles grandpa=new oracles();
        villians Scorpion=new villians();
        villians Snake=new villians();
        //老爷爷 蝎子精

        for(int i=0;i<7;i++)
        {
            boys[i]=new huluboys(i);
            boys[i].x_pos=r.nextInt(N);
            boys[i].y_pos=r.nextInt(N);
        }//初始乱序

        //葫芦娃站队
        for(int i=0;i<7;i++)
        {
            boys[i].move(1,1+i);
            map[1][i+1]='H';
        }

        //蝎子精
        Scorpion.move(9,3);
        map[9][3]='S';
        impians[0].move(8,4);
        map[8][4]='I';
        impians[1].move(10,4);
        map[10][4]='I';
        impians[2].move(7,5);
        map[7][5]='I';
        impians[3].move(11,5);
        map[11][5]='I';
        impians[4].move(8,6);
        map[8][6]='I';
        impians[5].move(10,6);
        map[10][6]='I';
        impians[6].move(9,7);
        map[9][7]='I';

        //蛇精 老爷爷
        Snake.move(9,5);
        map[9][5]='K';
        grandpa.move(2,3);
        map[2][3]='O';

        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.print('\n');
        }
        //蝎子精 重新站队
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]=='S' || map[i][j]=='I' || map[i][j]=='K') map[i][j]=' ';
            }
        }
        Scorpion.move(9,3);
        map[9][3]='S';
        impians[0].move(8,4);
        map[8][4]='I';
        impians[1].move(9,5);
        map[9][5]='I';
        impians[2].move(8,6);
        map[8][6]='I';
        impians[3].move(9,7);
        map[9][7]='I';
        impians[4].move(8,8);
        map[8][8]='I';
        impians[5].move(9,9);
        map[9][9]='I';
        impians[6].move(8,10);
        map[8][10]='I';

        Snake.move(8,2);
        map[8][2]='K';

        System.out.print('\n');
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.print('\n');
        }
    }

}
