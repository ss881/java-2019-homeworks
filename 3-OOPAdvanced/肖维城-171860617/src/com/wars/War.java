package com.wars;

import java.io.IOException;
//import java.util.Scanner;

public class War {
    final static int N=15;

    public char ch;
    char field[][]=new char[N][N];

    //Enemys.Enemy shejing=new Enemys().new Enemy('S');

    public War(char c){
        ch=c;
        defaultField();
    }

    public void defaultField(){
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                //field[i][j] = '*';
                field[i][j] = ch;
            }
        }
        field[N-1][N/2-1]='Y';//grandpa
        field[N-1][N/2+1]='S';//shejing
    }

    public void modify(Hulubros h, Enemys e){
        defaultField();
        /*field[h.h1.x][h.h1.y]=h.h1.ch;
        field[h.h2.x][h.h2.y]=h.h2.ch;
        field[h.h3.x][h.h3.y]=h.h3.ch;
        field[h.h4.x][h.h4.y]=h.h4.ch;
        field[h.h5.x][h.h5.y]=h.h5.ch;
        field[h.h6.x][h.h6.y]=h.h6.ch;
        field[h.h7.x][h.h7.y]=h.h7.ch;*/
        for(int i=0;i<7;i++){
            field[h.hl[i].x][h.hl[i].y]=h.hl[i].ch;
        }
        //Enemys
        field[e.Xiezi.x][e.Xiezi.y]=e.Xiezi.ch;
        for(int i=0;i<e.Louluo.length;i++){
            if(e.Louluo[i].x>0&&e.Louluo[i].y>0){
                field[e.Louluo[i].x][e.Louluo[i].y]=e.Louluo[i].ch;
            }
        }
    }

    public void show(){
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(field[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }


    public static void main(String argvs[]){
        Hulubros h=new Hulubros();
        Enemys e=new Enemys();
        War w=new War('*');
        Formation f=new Formation(N);

        /*f.changShe(h);
        f.heYi(e);
        w.modify(h,e);
        System.out.println("----------------鹤翼----------------");
        w.show();

        //w.defaultField();
        f.changShe(h);
        f.yanXing(e);
        w.modify(h,e);
        System.out.println("----------------雁行----------------");
        w.show();
        //....
        f.changShe(h);
        f.chongE(e);
        w.modify(h,e);
        System.out.println("----------------衝轭----------------");
        w.show();

        f.changShe(h);
        f.yuLin(e);
        w.modify(h,e);
        System.out.println("----------------鱼鳞----------------");
        w.show();

        f.changShe(h);
        f.fangYuan(e);
        w.modify(h,e);
        System.out.println("----------------方円----------------");
        w.show();

        f.changShe(h);
        f.yanYue(e);
        w.modify(h,e);
        System.out.println("----------------偃月----------------");
        w.show();

        f.changShe(h);
        f.fengShi(e);
        w.modify(h,e);
        System.out.println("----------------锋矢----------------");
        w.show();*/
        //w.modify(h,e);
        //w.show();
        //f.changShe(h);
        //w.modify(h,e);
        //w.show();
        //char ch=' ';
        //Scanner scan=new Scanner(System.in);
        System.out.println("请选择一种阵型：a:鹤翼    b:雁行    c: 衝轭    d: 鱼鳞    e:方円    f:偃月    g:锋矢    q:退出");
        do{
            //System.out.println("请选择一种阵型：a:鹤翼    b:雁行    c: 衝轭    d: 鱼鳞    e:方円    f:偃月    g:锋矢");
            char ch=' ';
            try {
                ch=(char)System.in.read();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(ch!='\n') {
                switch (ch) {
                    case 'a': {
                        f.changShe(h);//让葫芦娃排成长蛇
                        f.heYi(e);//让敌人排成鹤翼
                        w.modify(h, e);//修改field的值
                        System.out.println("----------------鹤翼----------------");
                        w.show();//显示战场
                        break;
                    }
                    case 'b': {
                        f.changShe(h);
                        f.yanXing(e);
                        w.modify(h, e);
                        System.out.println("----------------雁行----------------");
                        w.show();
                        break;
                    }
                    case 'c': {
                        f.changShe(h);
                        f.chongE(e);
                        w.modify(h, e);
                        System.out.println("----------------衝轭----------------");
                        w.show();
                        break;
                    }
                    case 'd': {
                        f.changShe(h);
                        f.yuLin(e);
                        w.modify(h, e);
                        System.out.println("----------------鱼鳞----------------");
                        w.show();
                        break;
                    }
                    case 'e': {
                        f.changShe(h);
                        f.fangYuan(e);
                        w.modify(h, e);
                        System.out.println("----------------方円----------------");
                        w.show();
                        break;
                    }
                    case 'f': {
                        f.changShe(h);
                        f.yanYue(e);
                        w.modify(h, e);
                        System.out.println("----------------偃月----------------");
                        w.show();
                        break;
                    }
                    case 'g': {
                        f.changShe(h);
                        f.fengShi(e);
                        w.modify(h, e);
                        System.out.println("----------------锋矢----------------");
                        w.show();
                        break;
                    }
                    case 'q':{
                        return;
                    }
                    default: {
                        System.out.println("Input Error!");
                        break;
                    }
                }
                System.out.println("请选择一种阵型：a:鹤翼    b:雁行    c: 衝轭    d: 鱼鳞    e:方円    f:偃月    g:锋矢    q:退出");
            }
        }while(true);

        //Formation f=new Formation(h,1);
        //Formation f2=new Formation(new Enemys(),1);
    }
}
