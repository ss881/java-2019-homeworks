import java.util.Random;

/**
 * author Yuan Manjie
 * date 2019/9/14
 * java course homework2
 */

public class huluxiongdi {
    private class People{
        int id;
        String name,color;
        People(int id)
        {
            this.id=id;
            this.name=this.get_name();
            this.color=this.get_color();
        }
        public String get_name()
        {
            switch (this.id)
            {
                case 0:return "老大";
                case 1:return "老二";
                case 2:return "老三";
                case 3:return "老四";
                case 4:return "老五";
                case 5:return "老六";
                case 6:return "老七";
                default: return "???";
            }
        }
        public String get_color()
        {
            switch (this.id)
            {
                case 0:return "红色";
                case 1:return "橙色";
                case 2:return "黄色";
                case 3:return "绿色";
                case 4:return "青色";
                case 5:return "蓝色";
                case 6:return "紫色";
                default: return "??色";
            }
        }
    }

    public People[] huluwa;

    huluxiongdi()
    {
        this.huluwa=new People[7];
        for(int i=0;i<7;i++)
        {
            this.huluwa[i]=new People(i);
        }
    }

    private void shuffle(){
        Random r= new Random();
        for(int i=0;i<7;i++)
        {
            int val=r.nextInt(7);
            swamp(val,i,false);
        }
    }

    private void baoshu(int mode){// 0-name 1-color
        for(int i=0;i<7;i++){
            if(mode==0)
            {
                System.out.print(this.huluwa[i].name);
            }else if(mode==1){
                System.out.print(this.huluwa[i].color);
            }
            System.out.print(" ");
        }
        System.out.print("\n");
    }
    public void swamp(int x,int y,boolean speak){
        if(speak){
            System.out.print(this.huluwa[x].name+":"+x+"->"+y+";");
            System.out.println(this.huluwa[y].name+":"+y+"->"+x+"");
        }
        People temp=this.huluwa[x];
        this.huluwa[x]=this.huluwa[y];
        this.huluwa[y]=temp;
    }
    // Bubble Sort
    public void Bubble_Sort(){
        for(int i=0;i<7;i++)
        {
            for(int j=0;j<7-i-1;j++)
            {
                if(this.huluwa[j].id>this.huluwa[j+1].id)
                {
                    swamp(j,j+1,true);
                }
            }
        }
    }
    // Insert Sort with Binary Search, refer to BaiduBaike
    public void BinarySearchInsertSort(){
        for(int i=0;i<7;i++){
            int low=0,high=i-1,mid=0;
            while(low<=high) {
                mid = (high + low) / 2;
                if (this.huluwa[mid].id > this.huluwa[i].id) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            for(int j=i-1;j>=low;j--){
                swamp(j+1,j,true);
            }
        }
    }
    public static void main(String[] args) {
        huluxiongdi Huluteng=new huluxiongdi();
        Huluteng.shuffle();
        Huluteng.Bubble_Sort();
        Huluteng.baoshu(0);
        Huluteng.shuffle();
        Huluteng.BinarySearchInsertSort();
        Huluteng.baoshu(1);
    }

}
