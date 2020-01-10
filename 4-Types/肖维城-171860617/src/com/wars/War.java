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

    public <T> void modify(T x){//泛型方法
        if(Hulubros.class.isInstance(x)) {
            Hulubros h = (Hulubros)x;
            for (int i = 0; i < 7; i++) {
                field[h.hl[i].x][h.hl[i].y] = h.hl[i].ch;
            }
        }
        else if(Enemys.class.isInstance(x)) {
            Enemys e = (Enemys)x;
            field[e.Xiezi.x][e.Xiezi.y] = e.Xiezi.ch;
            for (int i = 0; i < e.Louluo.length; i++) {
                if (e.Louluo[i].x > 0 && e.Louluo[i].y > 0) {
                    field[e.Louluo[i].x][e.Louluo[i].y] = e.Louluo[i].ch;
                }
            }
        }
        else{
            System.out.println("No definition");
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
                        f.setFormation(h,"ChangShe");
                        f.setFormation(e,"HeYi");
                        w.defaultField();
                        w.modify(h);
                        w.modify(e);
                        System.out.println("----------------鹤翼----------------");
                        w.show();//显示战场
                        break;
                    }
                    case 'b': {
                        f.setFormation(h,"ChangShe");
                        f.setFormation(e,"YanXing");
                        w.defaultField();
                        w.modify(h);
                        w.modify(e);
                        System.out.println("----------------雁行----------------");
                        w.show();
                        break;
                    }
                    case 'c': {
                        f.setFormation(h,"ChangShe");
                        f.setFormation(e,"ChongE");
                        //w.modify(h, e);
                        w.defaultField();
                        w.modify(h);
                        w.modify(e);
                        System.out.println("----------------衝轭----------------");
                        w.show();
                        break;
                    }
                    case 'd': {
                        f.setFormation(h,"ChangShe");
                        f.setFormation(e,"YuLin");
                        //w.modify(h, e);
                        w.defaultField();
                        w.modify(h);
                        w.modify(e);
                        System.out.println("----------------鱼鳞----------------");
                        w.show();
                        break;
                    }
                    case 'e': {
                        f.setFormation(h,"ChangShe");
                        f.setFormation(e,"FangYuan");
                        //w.modify(h, e);
                        w.defaultField();
                        w.modify(h);
                        w.modify(e);
                        System.out.println("----------------方円----------------");
                        w.show();
                        break;
                    }
                    case 'f': {
                        f.setFormation(h,"ChangShe");
                        f.setFormation(e,"YanYue");
                        //w.modify(h, e);
                        w.defaultField();
                        w.modify(h);
                        w.modify(e);
                        System.out.println("----------------偃月----------------");
                        w.show();
                        break;
                    }
                    case 'g': {
                        f.setFormation(h,"ChangShe");
                        f.setFormation(e,"FengShi");
                        //w.modify(h, e);
                        w.defaultField();
                        w.modify(h);
                        w.modify(e);
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
