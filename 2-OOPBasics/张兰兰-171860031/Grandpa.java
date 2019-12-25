package com.company;

import java.util.Random;

public class Grandpa {
    private Random rand ;
    private Huluwa []huluwa;
    private int []arr ;
    private int []col_arr;
    public Grandpa(){
        rand=new Random();
        huluwa=new Huluwa[7];
        for(int i=0;i<7;i++)
           huluwa[i]=new Huluwa();
        arr=new int[7];//初始化y轴坐标
        col_arr=new int[7];//初始化x轴坐标
    }
    public void shuffle() {
        int length = arr.length;
        for ( int i = length; i > 0; i-- ){
            int randInd = rand.nextInt(i);
            int temp = arr[randInd];
            arr[randInd] = arr[i-1];
            arr[i-1] = temp;
        }
    }//打乱位置顺序
    public void col_shuffle() {
        int length = col_arr.length;
        for ( int i = length; i > 0; i-- ){
            int randInd = rand.nextInt(i);
            int temp = col_arr[randInd];
            col_arr[randInd] = arr[i-1];
            col_arr[i-1] = temp;
        }
    }//打乱位置顺序
    public void init_huluwa(){

        String set_role="赤";
        String set_order="老大";
        int set_age=0;
        huluwa[0].init_info(set_role,set_age,set_order);
        set_role="橙";
        set_age=1;
        set_order="老二";
        huluwa[1].init_info(set_role,set_age,set_order);
        set_role="黄";
        set_order="老三";
        set_age=2;
        huluwa[2].init_info(set_role,set_age,set_order);
        set_role="绿";
        set_order="老四";
        set_age=3;
        huluwa[3].init_info(set_role,set_age,set_order);
        set_role="青";
        set_order="老五";
        set_age=4;
        huluwa[4].init_info(set_role,set_age,set_order);
        set_role="蓝";
        set_order="老六";
        set_age=5;
        huluwa[5].init_info(set_role,set_age,set_order);
        set_role="紫";
        set_order="老七";
        set_age=6;
        huluwa[6].init_info(set_role,set_age,set_order);
    }//初始化葫芦娃数据
    public void init_arr(){

        for(int i=0;i<7;i++)
            arr[i]=i;
    }//初始化位置
    public void init_col_arr() {

        for(int i=0;i<7;i++)
            col_arr[i]=i;
    }//初始化位置
    public void set_place(){
        for(int i=0;i<7;i++)
        {
            int order=arr[i];
            huluwa[order].set_place(col_arr[order],i);
        }
    }//打乱顺序后，设定每个葫芦娃的位置
    public void show_order(){
        for(int i=0;i<7;i++){
            int place=arr[i];
            String order=huluwa[place].transfer_order();
            int row=huluwa[place].transfer_row();
            int col=huluwa[place].transfer_col();
            System.out.println(order+"："+row+"行"+col+"列");
        }
    }//按照y轴从小到大顺序展示葫芦娃对应的名称和位置
    public void show_role(){
        for(int i=0;i<7;i++){
            int place=arr[i];
            String role=huluwa[place].transfer_role();
            int row=huluwa[place].transfer_row();
            int col=huluwa[place].transfer_col();
            System.out.println(role+"： "+row+"行"+col+"列");
        }
    }//按照y轴从小到大顺序展示葫芦娃的颜色和对应的位置
    public void paixu(){
        shuffle ();
        col_shuffle();//打乱葫芦娃位置
        set_place();//确定位置
        System.out.println("排序前位置：");
        show_order();
        System.out.println();//输出排序前位置
        Bubble_Sort.bubbleSort(huluwa,arr);//冒泡排序
        System.out.println();
        System.out.println("排序后位置：");
        show_order();
        System.out.println();//输出排序后位置
        shuffle();
        col_shuffle();
        set_place();//重新打乱位置
        System.out.println("排序前位置：");
        show_role();
        System.out.println();//输出排序前位置
        Binary_Sort.binarysort(huluwa,arr);//二分排序
        System.out.println();
        System.out.println("排序后位置：");
        show_role();//输出排序后位置
    }
    public void game_start(){
        System.out.println("Game start!!! ");
        init_huluwa();
        init_arr();
        init_col_arr();
        paixu();

    }
    public void game_over(){
        System.out.println("Game over!!! ");
    }



    //二分排序




}
