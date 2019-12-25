package com.company;
import java.util.*;
enum Eorm {Heyi,Yanxing,Hengzhi,Changshe,Yulin,Fangmen,Yanyue,Fengshi}
enum Identity {GRANDPA,CALABASH,SNAKE,SCORPION,PIPS}
enum Picture {GR,CA1,CA2,CA3,CA4,CA5,CA6,CA7,SN,SC,PI,BLOCK}
enum Color_of_Calabash { NO,RED, ORANGE, YELLOW, GREEN, CYAN, BLUE, PURPLE }
public class Processor implements SwapEormation{
    static int N;
    private Block<Character> [][]block;
    private Eorm eormation_attack;
    private int num_of_pips;

    public Processor(int n){
            N=n;
            block=new Block[N][N];
            for(int i=0;i<N;i++)
            {
                for(int j=0;j<N;j++)
                    block[i][j]=new Block<>();
            }
    }
    //初始化排阵
    public void show_all(){
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {

                block[i][j].show();
            }
            System.out.println(" ");
        }

    }
    public void init_grandpa(){

        block[0][0].init_(new Grandpa(),0,0,Color_of_Calabash.NO);

    }
    public void init_calabash() {
        for(int i=1;i<8;i++)
        {
            switch (i) {
                case 1:
                    block[1][0].init_(new Calabash(),0,1,Color_of_Calabash.RED);
                    break;
                case 2:
                    block[2][0].init_(new Calabash(),0,2,Color_of_Calabash.ORANGE);
                    break;
                case 3:
                    block[3][0].init_(new Calabash(),0,3,Color_of_Calabash.YELLOW);
                    break;
                case 4:
                    block[4][0].init_(new Calabash(),0,4,Color_of_Calabash.GREEN);
                    break;
                case 5:
                    block[5][0].init_(new Calabash(),0,5,Color_of_Calabash.CYAN);
                    break;
                case 6:
                    block[6][0].init_(new Calabash(),0,6,Color_of_Calabash.BLUE);
                    break;
                case 7:
                    block[7][0].init_(new Calabash(),0,7,Color_of_Calabash.PURPLE);
                    break;
                default:
                    break;





            }
        }
        }

    public void init_pips(Eorm eormation) {
        Eormation h=new Eormation(num_of_pips);
        h.self_init_(Eorm.Heyi,num_of_pips,N);
        int i=0;
        for(;i<num_of_pips;i++)
        {
            int row=h.ret_row(i);
            int col=h.ret_col(i);
            block[row][col].init_(new Pips(),col,row,Color_of_Calabash.NO);

        }



        }
    public void change_pips(Eorm pre_eormation,Eorm later_eormation) {
        SwapEormation.swap_method(block,pre_eormation,later_eormation,num_of_pips,N);
    }
    public void init_snake()
    {
        block[0][N-1].init_(new Snake(),N-1,0,Color_of_Calabash.NO);
    }
    public void init_scorpion(){
        block[num_of_pips/2+1][N-1-num_of_pips/2].init_(new Scorpion(),num_of_pips/2-1,N-1-num_of_pips/2,Color_of_Calabash.NO);}
    public void start(int num){
        System.out.println("Game Start！！！");
        num_of_pips=num;
        init_grandpa();
        init_calabash();
        init_pips(Eorm.Heyi);
        init_snake();
        init_scorpion();
        show_all();
        System.out.println("所有人物皆已上阵\n现在怪物开始改变阵型");
    }
    public void swap_eormation(){
        for(int i=-1;i<5;i++)
        {
            System.out.println("Swap Eormation！！！");
            switch(i){
                case -1:
                    System.out.println("雁行阵");
                    change_pips(Eorm.Heyi,Eorm.Yanxing);
                    break;
                case 0:
                    System.out.println("横之阵");
                    change_pips(Eorm.Yanxing,Eorm.Hengzhi);
                    break;
                case 1:
                    System.out.println("鱼鳞阵");
                    change_pips(Eorm.Hengzhi,Eorm.Yulin);
                    break;
                case 2:
                    System.out.println("方门阵");
                    change_pips(Eorm.Yulin,Eorm.Fangmen);
                    break;
                case 3:
                    System.out.println("偃月阵");
                    change_pips(Eorm.Fangmen,Eorm.Yanyue);
                    break;
                case 4:
                    System.out.println("锋矢阵");
                    change_pips(Eorm.Yanyue,Eorm.Fengshi);
                    break;
                default:
                    break;
            }
        }
    }
    public void end(){
        System.out.println("Game Over！！！");
    }


}
