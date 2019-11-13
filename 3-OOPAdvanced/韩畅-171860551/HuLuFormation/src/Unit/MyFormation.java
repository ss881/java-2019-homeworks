package Unit;

// 这个类其实应该直接放在unitcommander类的文件内，其权限本应该仅限于老爷爷和蛇精这样的具有单位操控权限的单位
// 其实也无非就是把其public属性去掉，放在UnitCommander类里面
// 单独拎出来写成单独的类，看起来比较清晰，如果老师觉得有违背编程思想的意思，我再调整一下就好
public class MyFormation {
    static OneMyFormation[] forms;

    MyFormation() {
        forms = new OneMyFormation[8];

        int[][] ar0 = { 
            { 0, -1, -1, -1, -1, -1, 6 }, 
            { -1, 1, -1, -1, -1, 5, -1 }, 
            { -1, -1, 2, -1, 4, -1, -1 },    
            { -1, -1, -1, 3, -1, -1, -1 } 
        };
        forms[0] = new OneMyFormation(4, 7, ar0, "鹤翼", 7);

        int[][] ar1 = { 
            { -1, -1, -1, -1, 0 }, 
            { -1, -1, -1, 1, -1 }, 
            { -1, -1, 2, -1, -1 }, 
            { -1, 3, -1, -1, -1 },
            { 4, -1, -1, -1, -1 } 
            };
        forms[1] = new OneMyFormation(5, 5, ar1, "雁行", 5);

        int[][] ar2 = { 
            { -1, 0 }, 
            { 1, -1 }, 
            { -1, 2 }, 
            { 3, -1 }, 
            { -1, 4 }, 
            { 5, -1 } 
        };
        forms[2] = new OneMyFormation(6, 2, ar2, "冲轭", 6);

        int[][] ar3 = { 
            { -1, -1, -1, 0, -1, -1, -1 }, 
            { -1, -1, -1, -1, 1, -1, -1 }, 
            { -1, 2, -1, 3, -1, 4, -1 },
            { 5, -1, 6, -1, 7, -1, 8 }, 
            { -1, -1, -1, 9, -1, -1, -1 } 
        };
        forms[3] = new OneMyFormation(5, 7, ar3, "鱼鳞", 10);

        int[][] ar4 = { 
            { -1, -1, 0, -1, -1 }, 
            { -1, 1, -1, 2, -1 }, 
            { 3, -1, -1, -1, 4 }, 
            { -1, 5, -1, 6, -1 },
            { -1, -1, 7, -1, -1 }
         };
        forms[4] = new OneMyFormation(5, 5, ar4, "方圆", 8);

        int[][] ar5 = { 
            { -1, -1, -1, -1, -1, -1, 0 }, 
            { -1, -1, -1, 2, -1, 1, -1 }, 
            { -1, -1, 3, -1, 4, -1, -1 },
            { 5, 6, -1, 7, -1, -1, -1 }, 
            { 8, 9, -1, 10, -1, -1, -1 }, 
            { 11, 12, -1, 13, -1, -1, -1 },
            { -1, -1, 14, -1, 15, -1, -1 }, 
            { -1, -1, -1, 16, -1, 17, -1 }, 
            { -1, -1, -1, -1, -1, -1, 18 } 
        };
        forms[5] = new OneMyFormation(9, 7, ar5, "偃月", 19);

        int[][] ar6 = { 
            { -1, -1, -1, 0, -1, -1, -1 }, 
            { -1, -1, 1, -1, 2, -1, -1 }, 
            { -1, -1, -1, 3, -1, -1, -1 },
            { -1, 4, -1, 5, -1, 6, -1 }, 
            { -1, -1, -1, 7, -1, -1, -1 }, 
            { 8, -1, -1, -1, -1, -1, 9 },
            { -1, -1, -1, 10, -1, -1, -1 }, 
            { -1, -1, -1, 11, -1, -1, -1 }, 
            { -1, -1, -1, 12, -1, -1, -1 }
        };
        forms[6] = new OneMyFormation(9, 7, ar6, "锋矢", 13);

        int[][] ar7 = { { 0 }, { 1 }, { 2 }, { 3 }, { 4 }, { 5 }, { 6 } };
        forms[7] = new OneMyFormation(7, 1, ar7, "长蛇", 7);

    }

    public void getLenWid(int i, int len, int wid) {
        len = forms[i].len;
        wid = forms[i].wid;
    }

    public OneMyFormation getMyFormation(int i) {
        return forms[i];
    }
}
// 每个阵型由于长宽是独特的属性，需要一个单独的类
class OneMyFormation {
    public int len;
    public int wid;
    public int size;
    public int[][] form;
    public String name;

    OneMyFormation(int l, int w, int ar[][], String n, int s) {
        name = n;
        size = s;
        form = new int[len][wid];
        form = ar;
        wid = w;
        len = l;
    }
}
