package com.company;

public interface SwapEormation {
    public static void swap_method(Block block[][],Eorm pre_eormation,Eorm later_eormation,int num,int N) {
        System.out.println("队伍撤退");
        withdraw(block,pre_eormation,num,N);
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {

                block[i][j].show();
            }
            System.out.println(" ");
        }
        System.out.println("队伍进入");
        forward(block,pre_eormation,later_eormation,num,N);
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {

                block[i][j].show();
            }
            System.out.println(" ");
        }
    }
    public static void forward(Block block[][],Eorm pre_eormation,Eorm later_eormation,int num,int N){
        Eormation later=new Eormation(num);
        Eormation pre=new Eormation(num);
        later.self_init_(later_eormation,num,N);
        pre.self_init_(pre_eormation,num,N);
        for(int i=0;i<num;i++){
            int row=later.ret_row(i);
            int col=later.ret_col(i);
            int row2=pre.ret_row(i);
            int col2=pre.ret_col(i);
            block[row][col].Change_place(block[row2][col2]);
        }
        int row=later.ret_s_row();
        int col=later.ret_s_col();
        int row2=pre.ret_s_row();
        int col2=pre.ret_s_col();
        block[row][col].Change_place(block[row2][col2]);

    }
    public static void withdraw(Block block[][],Eorm later_eormation,int num,int N){
        Eormation later=new Eormation(num);
        later.self_init_(later_eormation,num,N);
        for(int i=0;i<num;i++){
            int row=later.ret_row(i);
            int col=later.ret_col(i);
            block[row][col].withdraw_state();
        }
        int row=later.ret_s_row();
        int col=later.ret_s_col();
        block[row][col].withdraw_state();

    }


}
