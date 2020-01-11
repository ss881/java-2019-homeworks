public class CalabashBrother extends LivingBody {//葫芦娃类
    public int number;//1-7,用来在颜色，排行时比较，大娃=1，二娃=2..
    public String seniority;//名称
    public String color;//颜色
public CalabashBrother(int number,int locationx,int locationy,String name,String seniority,String color)
{super(locationx,locationy,name);
    this.number=number;
    this.seniority=seniority;
this.color=color;
}

/*    void report(String command) {//报告
        switch (command) {
            case "seniority":
                System.out.print(this.seniority);
                break;
            case "color":
                System.out.print(this.color);
                break;
            case "location":
                System.out.print(this.locationx + this.locationy);
                break;
            default:
                break;
        }
    }*/
}