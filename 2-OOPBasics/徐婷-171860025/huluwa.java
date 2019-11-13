import java.util.*;

class Huluwa{
    int rank;   //葫芦兄弟中的排行

    public Huluwa(int r){
        Scanner sc=new Scanner(System.in);
        while(r<1||r>7){
            System.out.println("非法输入,请重新输入1-7之间的整数");
            //exit(-1);
         
            r=sc.nextInt();
        }
       // sc.close();
        rank=r;
    }
    public String getColor(){
        switch(rank){
            case 1:return "红色";
            case 2:return "橙色";
            case 3:return "黄色";
            case 4:return "绿色";
            case 5:return "青色";
            case 6:return "蓝色";
            case 7:return "紫色";
            default:return null;
        }
    }
    public String getName(){
        switch(rank){
            case 1: return "老大";
            case 2: return "老二";
            case 3: return "老三";
            case 4: return "老四";
            case 5: return "老五";
            case 6: return "老六";
            case 7: return "老七";
            default: return null;
        }
    }
    public int getRank(){
        return rank;
    }
}

class GroupHulu{
    private Huluwa brothers[];
    public GroupHulu(){
        brothers=new Huluwa[7];
    }
    public void bubbleSort(){
        System.out.println("请输入初始葫芦娃的站位");
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<7;i++){
            int r=sc.nextInt();
            //System.out.println(r);
            brothers[i]=new Huluwa(r);
        }
        //sc.close();
        for(int i=6;i>=0;i--){
            for(int j=0;j<i;j++){
                if(brothers[j].getRank()>brothers[j+1].getRank()){
                    Huluwa tmp=brothers[j];
                    brothers[j]=brothers[j+1];
                    brothers[j+1]=tmp;
                }
            }
        }
        for(int i=0;i<7;i++){
            System.out.println(brothers[i].getName());
        }
    }
    public void binarySort(){
        System.out.println("请输入初始葫芦娃的站位");
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<7;i++){
            brothers[i]=new Huluwa(sc.nextInt());
        }
        //sc.close();
        System.out.println("输出BinarySort过程");
        for(int i=1;i<7;i++){
            int l=0,r=i-1;
            Huluwa last=brothers[i];
            int tmp=brothers[i].getRank();
            int mid=(l+r)/2;
            
            while(l<=r){
                mid=(l+r)/2;
                if(brothers[mid].getRank()<tmp){
                    l=mid+1;
                }
                else{
                    r=mid-1;
                }
            }
            for(int j=i-1;j>=l;j--){
                System.out.println(brothers[j].getName()+":"+(j)+"->"+(j+1));
                brothers[j+1]=brothers[j];
            }
            if(l!=i){
                brothers[l]=last;
                System.out.println(last.getName()+":"+i+"->"+l);
            }
        }
        System.out.println("排序结束报颜色");
        for(int i=0;i<7;i++){
            System.out.println(brothers[i].getName()+":"+brothers[i].getColor());
        }
    }
}
class Console{
    public static void main(String args[]){
        GroupHulu bros=new GroupHulu();
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("输入以下数字使用相应操作");
            System.out.println("1：Bubble sort\n2:Binary Sort\n-1:Exit");
            
            int op=sc.nextInt();
            //sc.close();
            switch(op){
                case 1:bros.bubbleSort();break;
                case 2:bros.binarySort();break;
                case -1: return;
                default:sc.close();System.out.println("错误输入，请重新输入");
            }
            System.out.println("-------------------------------------------");
        }
    }
}