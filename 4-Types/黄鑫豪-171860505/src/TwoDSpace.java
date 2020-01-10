import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwoDSpace {
    private static int n = 15;
    private static Object[][] ground = new Object[n][n];
    //private static GourdEva[] theGroup = new GourdEva[7];
    //private static GourdEva[] theLine = new GourdEva[7];
    //private static EvilSon[] theGroup_ES = new EvilSon[15];
    private static List<GourdEva> theGroup = new ArrayList<GourdEva>();
    private static List<GourdEva> theLine = new ArrayList<GourdEva>();
    private static List<EvilSon> theGroup_ES = new ArrayList<EvilSon>();
    private static Grandpa gpa;
    private static EvilFather elf;
    private static EvilMother elm;
    private static String[] colorRecord = {"红色", "橙色", "黄色", "绿色", "青色", "蓝色", "紫色"};
    private static String[] nameRecord = {"老大", "老二", "老三", "老四", "老五", "老六", "老七"};

    public static void main(String[] args) {

        for (int i = 0; i < 7; i++)
            theGroup.add(new GourdEva(i, colorRecord[i], nameRecord[i]));

        for (int i = 0; i < 15; i++)
            theGroup_ES.add(new EvilSon());

        elf = new EvilFather();
        elm = new EvilMother();
        gpa = new Grandpa();

        randomSetInLine();
        bubbleSort();

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                ground[i][j] = new Nothing();
            }
        }

        GetImage();
        SetInGoundEva();
        SetInEvil(1);
        SetInAudience();
        GetImage();

        ClearGround();
        SetInGoundEva();
        SetInEvil(2);
        SetInAudience();
        GetImage();
    }

    public static void ClearGround(){
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                ground[i][j].getMypos().setPos(-1, -1);
                ground[i][j] = new Nothing();
            }
    }

    public static void GetImage() {
        System.out.println("二维空间中的情况如下图：");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
               describe(ground[i][j]);
            System.out.println();
        }
    }

    public static void describe(Object x){
        Class type = x.getClass();
        if (type.getName().equals("Nothing"))
            System.out.print("口");
        if (type.getName().equals("EvilSon"))
            System.out.print("兵");
        if (type.getName().equals("EvilMother"))
            System.out.print("蛇");
        if (type.getName().equals("EvilFather"))
            System.out.print("蝎");
        if (type.getName().equals("GourdEva"))
            System.out.print("娃");
        if (type.getName().equals("Grandpa"))
            System.out.print("爷");
    }

    private static void SetInGoundEva() {
        int basicx = 0, basicy = n / 2;
        Position[] tmppos = Formation.longsnake();
        for (int i = 0; i < 7; i++) {
            ground[basicx + tmppos[i].getPosX()][basicy + tmppos[i].getPosy()] = theLine.get(i);
            theLine.get(i).setPos(basicx + tmppos[i].getPosX(),basicy + tmppos[i].getPosy());
        }
    }

    private static void SetInEvil(int p) {
        int basicx = n - 6, basicy = n / 2 - 4;
        Position[] tmppos = Formation.get(p);
        int l = tmppos.length;

        ground[basicx + tmppos[0].getPosX()][basicy + tmppos[0].getPosy()] = elf;
        elf.getMypos().setPos(basicx + tmppos[0].getPosX(),basicy + tmppos[0].getPosy());
        for (int i = 1; i < l; i++) {
            ground[basicx + tmppos[i].getPosX()][basicy + tmppos[i].getPosy()] = theGroup_ES.get(i);
            theGroup_ES.get(i).getMypos().setPos(basicx + tmppos[i].getPosX(),basicy + tmppos[i].getPosy());
        }
    }

    private static void SetInAudience() {
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (!flag) break;
            for (int j = 0; j < n; j++) {
                if (ground[i][j].isnothing()) {
                    ground[i][j] = gpa;
                    gpa.getMypos().setPos(i,j);
                    flag = false;
                    break;
                }
            }
        }

        flag = true;
        for (int i = n-1; i >= 0; i--) {
            if (!flag) break;
            for (int j = 0; j < n; j++) {
                if (ground[i][j].isnothing()) {
                    ground[i][j] = elm;
                    elm.getMypos().setPos(i,j);
                    flag = false;
                    break;
                }
            }
        }
    }

    private static List<Integer> getRandomNumList() { // 生成长度为7的随机数列
        List<Integer> list = new ArrayList<Integer>();

        Random r = new Random();
        while (list.size() != 7) {
            int num = r.nextInt(7);
            if (!list.contains(num)) {
                list.add(num);
            }
        }

        return list;
    }

    private static void whatInLine(){
        for (int i = 0; i < 7; i++) {
            theLine.get(i).saymyname();
            if (i == 6)
                System.out.print('\n');
            else
                System.out.print('，');
        }
    }

    private static void randomSetInLine() {
        List<Integer> tmpList = getRandomNumList();
        for (int i = 0; i < 7; i++) {
            theGroup.get(i).setPositionWithOutTalk(tmpList.get(i));
            theLine.add(theGroup.get(tmpList.get(i)));
        }
        System.out.println("【随机站队】");
        whatInLine();
    }

    private static void exchange(int a, int b) {
        theLine.get(a).setPositionAndTalk(b);
        theLine.get(b).setPositionAndTalk(a);
    }

    private static void bubbleSort() {
        System.out.println("【开始冒泡排队】");
        int n = 7;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (theLine.get(j).getrank() > theLine.get(j+1).getrank()) {
                    exchange(j, j + 1);
                    GourdEva tmp = theLine.get(j);
                    theLine.set(j, theLine.get(j + 1));
                    theLine.set(j + 1, tmp);
                }
            }
        }
        System.out.println("【冒泡排队后队中站位】");
        whatInLine();
    }

}
