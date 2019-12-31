import java.util.*;

class DollBrother
{
    private int rank;
    private int color;

    DollBrother(int rank,int color)
    {
        this.rank=rank;
        this.color=color;
    }


    public int getRank(){return rank;}

    public int getColor(){return color;}

    public String sayRank()
    {
        int rank=this.getRank();
        switch(rank)
        {
            case 1: return "老大";
            case 2: return "老二";
            case 3: return "老三";
            case 4: return "老四";
            case 5: return "老五";
            case 6: return "老六";
            case 7: return "老七";
        }

        return null;
    }

    public String sayColor()
    {
        int color=this.getColor();
        switch(color)
        {
            case 1: return "红色";
            case 2: return "橙色";
            case 3: return "黄色";
            case 4: return "绿色";
            case 5: return "青色";
            case 6: return "蓝色";
            case 7: return "紫色";
        }

        return null;
    }
}

public class GourdDoll
{
    public static DollBrother brothers[];

    GourdDoll(){};


    public void bubblesort()
    {
        brothers=new DollBrother[7];
        System.out.println("输入葫芦娃七兄弟的初次站位顺序：");
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<7;i++)
        {
            int rank=sc.nextInt();
            int color=rank;
            brothers[i]=new DollBrother(rank,color);
        }

        for(int i=0;i<6;i++)
        {
            for(int j=0;j<6-i;j++)
            {
                int currank=brothers[j].getRank();
                int nextrank=brothers[j+1].getRank();
                if(currank>nextrank)
                {
                    System.out.print(brothers[j].sayRank()+":"+j+"->"+(j+1)+" ");
                    System.out.println(brothers[j+1].sayRank()+":"+(j+1)+"->"+j);
                    DollBrother temp=brothers[j];
                    brothers[j]=brothers[j+1];
                    brothers[j+1]=temp;
                }
            }
        }

        System.out.println("排序结束开始报数：");
        for(int i=0;i<7;i++)
        {
            System.out.print(brothers[i].sayRank()+" ");
        }
        System.out.println('\n');
    }

    public void dichotomysort()
    {
        brothers=new DollBrother[7];
        System.out.println("再次输入葫芦娃七兄弟的站位顺序：");
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<7;i++)
        {
            int rank=sc.nextInt();
            int color=rank;
            brothers[i]=new DollBrother(rank,color);
        }

        for(int i=0;i<7;i++)
        {
            int start=0,end=i-1;
            int mid=-1;
            while(start<=end)
            {
                mid=(start+end)/2;
                if(brothers[mid].getColor()>brothers[i].getColor()) end=mid-1;
                else start=mid+1;
            }

            DollBrother temp=brothers[i];
            for(int j=i-1;j>end;j--)
            {
                //System.out.print(sayRank(brothers[j])+":"+j+"->"+(j+1)+" ");
                System.out.println(brothers[j].sayRank()+":"+j+"->"+(j+1));
                brothers[j+1]=brothers[j];
            }

            if(end+1!=i) System.out.println(temp.sayRank()+":"+i+"->"+(end+1));
            brothers[end + 1] = temp;

        }


        System.out.println("排序结束开始报数：");
        for(int i=0;i<7;i++)
        {
            System.out.print(brothers[i].sayColor()+" ");
        }
        System.out.println('\n');
    }

    public static  void main(String[] args)
    {
        GourdDoll family=new GourdDoll();
        family.bubblesort();
        family.dichotomysort();
    }
}
