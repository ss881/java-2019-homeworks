package com.company;

public class Eormation {
    private int col[];
    private int row[];
    private int s_c;
    private int s_r;
    private int num;
    private int N;
    public Eormation(int num){col=new int[num];row=new int[num];}
    public void self_init_(Eorm eorm,int num,int N){
        this.num=num;
        this.N=N;
        switch(eorm){
            case Heyi:
                init_heyi();
                break;
            case Yanxing:
                init_yanxing();
                break;
            case Hengzhi:
                init_hengzhi();
                break;
            case Yanyue:
                init_yanyue();
                break;
            case Fangmen:
                init_fangmen();
                break;
            case Fengshi:
                init_fengshi();
                break;
            case Yulin:
                init_yulin();
                break;
            default:
                break;

        }
    }
    public void init_yanxing() {
        int no = 0;
        for (int i = 1; i <= num / 2; i++, no++) {
            row[no] = i;
            col[no] = N - i;
        }
        for (int i = 1; i <= num / 2; i++, no++) {
            row[no] = i;
            col[no] = N - 1 - i;
        }
        s_c=N-1-num/2-1;
        s_r=num/4;
    }
    public void init_hengzhi() {
        int no = 0;
        for (int i = 1; i <= num / 2; i++, no++) {
            row[no] = i;
            col[no] = N - 1;
        }
        for (int i = 1; i <= num / 2; i++, no++) {
            row[no] = i+1;
            col[no] = N - 2;
        }
        s_r=num/4;
        s_c=N-3;
    }
    public void init_fengshi() {
        int no = 0;
        int lie=num-8;
        for(int i=0;i<lie;i++,no++)
        {
            row[no]=5;
            col[no]=N-1-i;
        }
        for(int j=0;j<4;j++,no++)
        {
            row[no]=4-j;
            col[no]=N-lie+1+j;
        }
        for(int j=0;j<4;j++,no++)
        {
            row[no]=6+j;
            col[no]=N-lie+1+j;
        }
        s_r=5;
        s_c=N-2-lie;
    }
    public void init_yanyue(){
        int lie=(num-10)/2;
        int no=0;
        for(int i=0;i<lie;i++,no++){
            row[no]=4;
            col[no]=N-3-2*i;
        }
        for(int i=0;i<lie;i++,no++){
            row[no]=5;
            col[no]=N-3-2*i;
        }
        for(int i=0;i<3;i++,no++)
        {
            row[no]=1+i;
            col[no]=N-1-i;
        }
        for(int i=0;i<3;i++,no++)
        {
            row[no]=6+i;
            col[no]=N-3+i;
        }
        for(int i=0;i<2;i++,no++)
        {
            row[no]=2+i;
            col[no]=N-3-i;
        }
        for(int i=0;i<2;i++,no++)
        {
            row[no]=6+i;
            col[no]=N-4+i;
        }
        s_c=N-3-2*lie-1;
        s_r=5;
    }
    public void init_fangmen(){
        int no=0;
      for(int i=0;i<num/4;i++,no++) {
          row[no]=1+i;
          col[no]=N-1-num/4+i;
      }
      for(int i=0;i<num/4;i++,no++) {
            row[no]=num/4+1+i;
            col[no]=N-1-i;
      }
      for(int i=0;i<num/4;i++,no++) {
            row[no]=num/2+1-i;
            col[no]=N-1-num/4-i;
      }
      for(int i=0;i<num/4;i++,no++) {
            row[no]=num/4+1-i;
            col[no]=N-1-num/2+i;
        }
      s_c=N-1-num/2;
      s_r=num/4+1;
    }
    public void init_yulin(){
        row[0]=5;
        col[0]=N-11;
        row[1]=4;
        col[1]=N-9;
        row[2]=3;
        col[2]=N-7;
        row[3]=5;
        col[3]=N-7;
        row[4]=7;
        col[4]=N-7;
        row[5]=2;
        col[5]=N-5;
        row[6]=4;
        col[6]=N-5;
        row[7]=6;
        col[7]=N-5;
        row[8]=8;
        col[8]=N-5;
        row[9]=1;
        col[9]=N-3;
        row[10]=3;
        col[10]=N-3;
        row[11]=5;
        col[11]=N-3;
        row[12]=7;
        col[12]=N-3;
        row[13]=9;
        col[13]=N-3;
        row[14]=4;
        col[14]=N-1;
        row[15]=6;
        col[15]=N-1;
        s_c=N-13;
        s_r=5;

    }
    public void init_heyi() {
        int no = 0;
        for (int i = 1; i <= num / 2; i++, no++) {
            row[no] = i;
            col[no] = N - i;
        }
        for (int i = 1; i <= num / 2; i++, no++) {
            row[no] = num/2+1+i;
            col[no] = N - num/2 -1 + i;
        }
        s_r=num/2+1;
        s_c=N-num/2-1;
    }
    public int ret_row(int n){
        return row[n];
    }
    public int ret_col(int n){
        return col[n];
    }
    public int ret_s_row(){return s_r;}
    public int ret_s_col(){return s_c;}
}