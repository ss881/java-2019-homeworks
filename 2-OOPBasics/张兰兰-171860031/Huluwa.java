package com.company;

public class Huluwa {
    private int col;
    private int row;
    private String role;
    private String order;
    private int age;

    public Huluwa(String set_role,int set_age){
        role=set_role;
        age=set_age;
    }
    public Huluwa(){
        row=0;
        col=0;
    }

    public void init_info(String set_role,int set_age,String set_order){
        role=set_role;
        age=set_age;
        order=set_order;
    }//初始化信息
    public void set_place(int set_col,int set_row){
        col=set_col;
        row=set_row;
    }

    public int transfer_col(){
        return col;
    }//返回信息
    public int transfer_row(){return row;}
    public String transfer_order(){return order;}
    public String transfer_role(){return role;}


    public void change_place(int new_row,int new_col){
        System.out.println(role+": From "+row +"行"+col +"列 To "+new_row +"行"+new_col +"列");
        col=new_col;
        row=new_row;
    }//两种改变位置
    public void change_place_order(int new_row,int new_col){
        System.out.println(order+": From "+row +"行"+col +"列 To "+new_row +"行"+new_col +"列");
        col=new_col;
        row=new_row;
    }
}
