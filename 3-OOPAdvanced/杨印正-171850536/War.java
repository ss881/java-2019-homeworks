public class War {
    final static int MAX = 20,HULUWA=7,LITTLEMONSTER=7,FIRE=6,SM=9;
    //数组空间，葫芦娃数量，小喽啰数量,葫芦娃长蛇阵头部大娃位置locationx，蝎子精位置locationy
    public static void main(String[] args) {//葫芦娃
        LivingBody[][] war = new LivingBody[MAX][MAX];
        war[0][0] = new CalabashBrother(1, 0, 0, "红","老大", "红色");//七个葫芦娃
        war[0][1] = new CalabashBrother(2, 0, 1, "橙","老二", "橙色");
        war[0][2] = new CalabashBrother(3, 0, 2, "黄","老三", "黄色");
        war[0][3] = new CalabashBrother(4, 0, 3, "绿","老四", "绿色");
        war[0][4] = new CalabashBrother(5, 0, 4, "青","老五", "青色");
        war[0][5] = new CalabashBrother(6, 0, 5, "蓝","老六", "蓝色");
        war[0][6] = new CalabashBrother(7, 0, 6, "紫","老七", "紫色");
        String random1 = "3576412";//随机坐标
        for (int i = 0; i < HULUWA; i++)
            swap(war, 0, i, 0, random1.charAt(i)-'1');//初始葫芦娃为乱序
        for (int i = 0; i < HULUWA; i++)
           System.out.print(war[0][i].name);//葫芦娃初始乱序
       for(int h=1;h<HULUWA+1;h++) {//葫芦娃冒泡排序为正序
            for (int i = 0; i < HULUWA + 1 - h; i++) {
              if(war[0][i]!=null&&war[0][i+1]!=null&&war[0][i].locationy>war[0][i+1].locationy) {
               swap(war,0,i,0,i+1);
              }
            }
        }
       for(int i=0;i<MAX;i++)
           for(int j=0;j<MAX;j++)
           {if(war[i][j]!=null) {
               war[i][j].locationx = i;
               war[i][j].locationy = j;
           }
           }
       System.out.println();
        for (int i = 0; i < HULUWA; i++)
        System.out.print(war[0][i].name);//输出葫芦娃位置

       for(int i=HULUWA-1;i>=0;i--)//葫芦娃在第FIRE行摆长蛇阵
        {  swap(war,0,i,FIRE,HULUWA-i-1);
                  /*  swaplocation(war,0,i,FIRE,HULUWA-i-1);*/
                }
        war[2][5] = new GrandFather(2, 5,"爷");//老爷爷
        war[2][10] = new SnakeMonster(2, 10,"蛇");//蛇精

Location warr=new Location();//鹤翼阵
        warr.heyi(war);

        for(int i=0;i<MAX;i++) {
            for (int j = 0; j < MAX; j++) {
                if(war[i][j]!=null)
                System.out.print(war[i][j].name);
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        warr.deleteheyi(war);
        warr.yanxing(war);//雁行阵
        for(int i=0;i<MAX;i++) {
            for (int j = 0; j < MAX; j++) {
                if(war[i][j]!=null)
                    System.out.print(war[i][j].name);
                else
                    System.out.print(" ");
            }
            System.out.println();
        }

    }
    public static void swap (LivingBody[][] a,int locationx1, int locationy1, int locationx2, int locationy2)//交换位置函数
    {

LivingBody temp1= a[locationx1][locationy1];
       LivingBody temp2= a[locationx2][locationy2];
       a[locationx1][locationy1] = temp2;
        a[locationx2][locationy2] = temp1;
    }



}

