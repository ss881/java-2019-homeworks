
class HuLuWa {
    String name;
    String color;
    int no;

    HuLuWa(String na, String c, int n) {
        name = na;
        color = c;
        no = n;
    }

}

class XiongDi {
    HuLuWa[] bro;

    public boolean init(String[] names, String[] colors) {
        int x1 = names.length;
        int x2 = colors.length;
        if (x1 != x2) {
            System.out.println("错误：names与colors数量不同！");
            return false;
        }
        bro = new HuLuWa[x1];
        for (int i = 0; i < names.length; i++)
            bro[i] = new HuLuWa(names[i], colors[i], i);
        return true;
    }

    public void report(int state) {
        for (int i = 0; i < bro.length; i++) {
            if (state == 0) {
                System.out.println("我是" + bro[i].name + "，我的颜色为" + bro[i].color + "，我的编号为" + bro[i].no + "。");
            }//print all
            else if (state == 1) {
                System.out.println("我是" + bro[i].name +/*"，我的颜色为"+bro[i].color+*/"。");
            }//print name
            else if (state == 2) {
                System.out.println(/*"我是" + bro[i].name + "，*/"我的颜色为" + bro[i].color + "。");
            }//print color
            else {
                System.out.println("参数错误！");
                return;
            }
        }
    }

    public static void swap(HuLuWa[] b, int x1, int x2, int flag) {
        if (flag == 1) {
            System.out.println(b[x1].name + "：" + x1 + "->" + x2);
            System.out.println(b[x2].name + "：" + x2 + "->" + x1);
        }
        HuLuWa t = b[x1];
        b[x1] = b[x2];
        b[x2] = t;
    }

    public void randomIt() {
        int x1 = (int) (1000 * Math.random() + 1);
        int x2, x3;
        for (int i = 0; i < x1; i++) {
            x2 = (int) (bro.length * Math.random());
            x3 = (int) (bro.length * Math.random());
            swap(bro, x2, x3, 0);
        }
    }

    public void bubbleSortIt() {
        int n = bro.length;
        if (n <= 1) return;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (bro[j].no > bro[j + 1].no) {
                    swap(bro, j, j + 1, 1);
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    public void binaryInsertSortIt() {
        for (int i = 0; i < bro.length; i++) {
            HuLuWa t = bro[i];
            int le = 0;
            int ri = i - 1;
            int mid = 0;
            while (le <= ri) {
                mid = (le + ri) / 2;
                if (bro[i].no < bro[mid].no)
                    ri = mid - 1;
                else
                    le = mid + 1;
            }
            for (int j = i - 1; j >= le; j--)
                swap(bro, j, j + 1, 1);
        }
    }

    public void swapTest() {
        swap(bro, 0, 1, 0);
    }
}


public class Solution {
    public static void main(String[] args) {
        //System.out.println("123");
        String[] namesText = new String[]{"老大", "老二", "老三", "老四", "老五", "老六", "老七"};
        String[] colorsText = new String[]{"赤", "橙", "黄", "绿", "青", "蓝", "紫"};
        XiongDi a = new XiongDi();
        a.init(namesText, colorsText);
        System.out.println("进行随机化");
        a.randomIt();
        a.report(1);
        System.out.println("进行冒泡排序");
        a.bubbleSortIt();
        a.report(1);
        System.out.println("\n\n进行随机化");
        a.randomIt();
        a.report(2);
        System.out.println("进行二分排序");
        a.binaryInsertSortIt();
        a.report(2);
        /*
        a.report(0);
        a.randomIt();
        a.report(0);
        */
        /*
        a.report(0);
        a.swapTest();
        a.report(0);
        */
        /*
        a.report(0);
        System.out.println("\n以下测试state==1");
        a.report(1);
        System.out.println("\n以下测试state==2");
        a.report(2);
        */
        /*
        HuLuWa[] temp = new HuLuWa[namesText.length];
        for(int i=0;i<temp.length;i++)
            temp[i]=new HuLuWa(namesText[i],colorsText[i],i);
        */
        /*
        temp[0] = new HuLuWa("老大", "赤", 1);
        temp[1] = new HuLuWa("老大", "赤", 1);
        temp[2] = new HuLuWa("老大", "赤", 1);
        temp[3] = new HuLuWa("老大", "赤", 1);
        temp[4] = new HuLuWa("老大", "赤", 1);
        temp[5] = new HuLuWa("老大", "赤", 1);
        temp[6] = new HuLuWa("老大", "赤", 1);
        */

    }
}
