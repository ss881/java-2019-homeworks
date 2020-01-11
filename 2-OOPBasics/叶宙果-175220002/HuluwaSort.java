

public class HuluwaSort
{
    Huluwa[] huluwas;
    
    void initialize()
    {
        Huluwa wa1 = new Huluwa(1,"红色","老大");
        Huluwa wa2 = new Huluwa(2,"橙色","老二");
        Huluwa wa3 = new Huluwa(3,"黄色","老三");
        Huluwa wa4 = new Huluwa(4,"绿色","老四");
        Huluwa wa5 = new Huluwa(5,"青色","老五");
        Huluwa wa6 = new Huluwa(6,"蓝色","老六");
        Huluwa wa7 = new Huluwa(7,"紫色","老七");

        huluwas = new Huluwa[7];
        huluwas[3] = wa1;
        huluwas[6] = wa2;
        huluwas[4] = wa3;
        huluwas[0] = wa4;
        huluwas[5] = wa5;
        huluwas[1] = wa6;
        huluwas[2] = wa7;
    }

    void showposition()
    {
        for(int i = 0; i < this.huluwas.length; i++)
        {
            System.out.print(this.huluwas[i].seniority + " ");
        }
        System.out.print("\n");
        for(int i = 0; i < this.huluwas.length; i++)
        {
            System.out.print(this.huluwas[i].color + " ");
        }
        System.out.print("\n");
    }

    void switchplace(int j)
    {
        Huluwa temp = this.huluwas[j];
        this.huluwas[j] = this.huluwas[j+1];
        this.huluwas[j+1] = temp;

    }

    void jump(Huluwa[] huluwas, int i, Huluwa j)
    {
        huluwas[i] = j;
        //return huluwas;
    }

    void bubblereport(int j)
    {
        System.out.println(this.huluwas[j].seniority + ":" + j + "->" + (j + 1));   //交换时报告交换动作
        System.out.println(this.huluwas[j+1].seniority + ":" + (j + 1) + "->" + j);
    }

    void binaryreport1(int i, int j)
    {
        System.out.println(this.huluwas[i].seniority + ":" + i + "->" + j);
    }

    void binaryreport2(int i, int j)
    {
        System.out.println(this.huluwas[j].seniority + ":" + i + "->" + j);
    }

    void bubbleSort()
    {
        for (int i = 0; i < this.huluwas.length - 1; i++) //外循环每次将已排好的元素排除并继续循环
        {
            for (int j = 0; j < (this.huluwas.length - 1 - i); j++) //内循环每次将相邻的元素进行比较交换
            {
                if (this.huluwas[j].num > this.huluwas[j+1].num) 
                {
                    this.switchplace(j);
                    this.bubblereport(j);
                    this.showposition();
                }
            }
        }
        System.out.println();
    }

    void binarySort()
    {
        for (int i = 1; i < this.huluwas.length; i++) 
        {
            Huluwa temp = this.huluwas[i];
            int right = i - 1;
            int left = 0;
            int mid;
            int flag = 0;
            while (left <= right)   //找到中间元素并与temp比较交换
            {
                mid = (left + right) / 2;
                if (this.huluwas[mid].num > temp.num)
                {
                    right = mid - 1;
                }
                else if (this.huluwas[mid].num < temp.num)
                {
                    left = mid + 1;
                }
            }
            
            for (int j = i; j > left; j--)  //将数组向后移动
            {
                this.jump(this.huluwas, j, this.huluwas[j-1]);
                this.binaryreport1(j - 1, j);
                //System.out.println(this.huluwas[j-1].seniority + ":" + (j - 1) + "->" + j);
                flag = 1;   //仅当需要插入操作时才置flag为1，否则会有无效交换动作的报告
            }
            
            if(flag == 1)   //当flag为1时插入元素
            {
                this.jump(this.huluwas, left, temp);
                this.binaryreport2(i, left);
                //System.out.println(this.huluwas[left].seniority + ":" + i + "->" + left);
                this.showposition();
            }
            
        }
        System.out.println();
    }

    
    public static void main(String[] args) 
    {
        HuluwaSort sort=new HuluwaSort();
        
        //冒泡排序
          
        sort.initialize();   //初始化（随意站队）
        System.out.println("初始位置：");
        sort.showposition();
        System.out.println("排序过程："); 
        sort.bubbleSort();
        System.out.println("报数：");
        for(int i = 0; i < sort.huluwas.length; i++)
        {
            System.out.println(sort.huluwas[i].seniority);
        }
        System.out.println();
        
        //二分排序
        
        sort.initialize();   //再次初始化（随意站队）
        System.out.println("初始位置：");
        sort.showposition();
        System.out.println("排序过程：");
        sort.binarySort();
        System.out.println("报颜色：");
        for(int i = 0; i < sort.huluwas.length; i++)
        {
            System.out.println(sort.huluwas[i].color);
        }
    }

}

class Huluwa
{
    int num;            //序号
    String color;       //颜色
    String seniority;   //排行
    int x;
    int y;
    

    Huluwa(int num,String color,String seniority)    //建立对象时初始化
    {
        this.num=num;
        this.color=color;
        this.seniority=seniority;
        
    }

    Huluwa()
    {
        this.num=0;
        this.color=" ";
        this.seniority=" ";
    }


}