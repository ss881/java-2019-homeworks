package com.company;
import java.util.Random;
public class Main {
    private static Random rand = new Random();
    public static void shuffle(int[] arr) {
        int length = arr.length;
        for ( int i = length; i > 0; i-- ){
            int randInd = rand.nextInt(i);
            int temp = arr[randInd];
            arr[randInd] = arr[i-1];
            arr[i-1] = temp;
        }
    }//打乱位置顺序

    public static void init_huluwa(Huluwa []brothers){
        String set_role="赤";
        String set_order="老大";
        int set_age=0;
        brothers[0].init_info(set_role,set_age,set_order);
        set_role="橙";
        set_age=1;
        set_order="老二";
        brothers[1].init_info(set_role,set_age,set_order);
        set_role="黄";
        set_order="老三";
        set_age=2;
        brothers[2].init_info(set_role,set_age,set_order);
        set_role="绿";
        set_order="老四";
        set_age=3;
        brothers[3].init_info(set_role,set_age,set_order);
        set_role="青";
        set_order="老五";
        set_age=4;
        brothers[4].init_info(set_role,set_age,set_order);
        set_role="蓝";
        set_order="老六";
        set_age=5;
        brothers[5].init_info(set_role,set_age,set_order);
        set_role="紫";
        set_order="老七";
        set_age=6;
        brothers[6].init_info(set_role,set_age,set_order);
    }//初始化葫芦娃数据
    public static void init_arr(int []arr){
       for(int i=0;i<7;i++)
           arr[i]=i;
    }//初始化位置

    public static void set_place(Huluwa []brothers,int []arr,int []col_arr){
        for(int i=0;i<7;i++)
        {
            int order=arr[i];
            brothers[order].set_place(col_arr[order],i);
        }
    }//打乱顺序后，设定每个葫芦娃的位置
    public static void show_order(Huluwa []brothers,int arr[]){
        for(int i=0;i<7;i++){
            int place=arr[i];
            String order=brothers[place].transfer_order();
            int row=brothers[place].transfer_row();
            int col=brothers[place].transfer_col();
            System.out.println(order+"："+row+"行"+col+"列");
        }
    }//按照y轴从小到大顺序展示葫芦娃对应的名称和位置
    public static void show_role(Huluwa []brothers,int arr[]){
        for(int i=0;i<7;i++){
            int place=arr[i];
            String role=brothers[place].transfer_role();
            int row=brothers[place].transfer_row();
            int col=brothers[place].transfer_col();
            System.out.println(role+"： "+row+"行"+col+"列");
        }
    }//按照y轴从小到大顺序展示葫芦娃的颜色和对应的位置
    public static void main(String[] args) {
        Huluwa []brothers=new Huluwa[7];
        for(int i=0;i<7;i++)
            brothers[i]=new Huluwa();//初始化葫芦娃七兄弟
        int []arr=new int[7];//初始化y轴坐标
        int []col_arr=new int[7];//初始化x轴坐标
        Bubble_Sort sort1=new Bubble_Sort();//冒泡排序
        Binary_Sort sort2=new Binary_Sort();//二分排序
        init_huluwa(brothers);
       init_arr(arr);
       init_arr(col_arr);
        shuffle (arr);
        shuffle(col_arr);//打乱葫芦娃位置
        set_place(brothers,arr,col_arr);//确定位置
        System.out.println("排序前位置：");
        show_order(brothers,arr);
        System.out.println();//输出排序前位置
        sort1.bubbleSort(brothers,arr);//冒泡排序
        System.out.println();
        System.out.println("排序后位置：");
        show_order(brothers,arr);
        System.out.println();//输出排序后位置
        shuffle (arr);
        shuffle(col_arr);
        set_place(brothers,arr,col_arr);//重新打乱位置
        System.out.println("排序前位置：");
        show_role(brothers,arr);
        System.out.println();//输出排序前位置
        sort2.binarysort(brothers,arr);//二分排序
        System.out.println();
        System.out.println("排序后位置：");
        show_role(brothers,arr);//输出排序后位置
        return;
    }
}
