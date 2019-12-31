import java.util.*;

class CalabashBrother {
    private int position;
    private int rank;
    private String name;
    private String color;

    public CalabashBrother(String c, int r, String n) {
        color = c;
        rank = r;
        name = n;
    }

    void setPosition(int p) {
        position = p;
    }

    int getPosition() {
        return position;
    }

    int getRank() {
        return rank;
    }

    String getColor() {
        return color;
    }

    String getName() {
        return name;
    }

    void moveTo(int dst) {
        position = dst;
    }
}

class Output {
    void outputContent(String content) {
        System.out.print(content);
    }

    void outputMove(CalabashBrother cala, int src, int dst) {
        System.out.print(cala.getName() + ": " + src + "->" + dst + "\t");
    }
    
    void outputAllColors(CalabashBrother calas[]) {
        for(int i = 0; i < calas.length; i++) {
            System.out.print(calas[i].getColor() + " ");
        }
        System.out.print("\n");
    }

    void outputAllNames(CalabashBrother calas[]) {
        for(int i = 0; i < calas.length; i++) {
            System.out.print(calas[i].getName() + " ");
        }
        System.out.print("\n");
    }
}

class Sort {
    //生成随机序列
    void randomSeq(CalabashBrother calas[]) {
        Random random = new Random();
        for(int i = 0; i < calas.length; i++) {
            calas[i].setPosition(i);
        }
        for(int i = 0; i < calas.length; i++) {
            int r = random.nextInt(i + 1);
            calas[i].setPosition(r);
            calas[r].setPosition(i);
            CalabashBrother temp = calas[i];
            calas[i] = calas[r];
            calas[r] = (temp);
        }
    }

    void bubbleSort(CalabashBrother calas[]) {
        Output output = new Output();
        boolean flag = true;
        for(int i = calas.length - 1; i > 0; i--) {
            flag = true;
            for(int j = 0; j < i; j++) {
                if(calas[j].getRank() > calas[j + 1].getRank()) {
                    flag = false;
                    calas[j].moveTo(j + 1);
                    output.outputMove(calas[j], j, j + 1);
                    calas[j + 1].moveTo(j);
                    output.outputMove(calas[j + 1], j + 1, j);
                    CalabashBrother temp = calas[j];
                    calas[j] = calas[j + 1];
                    calas[j + 1] = temp;
                }
            }
            if(flag) {
                break;
            }
        }
        output.outputContent("\n");
    }

    void binSort(CalabashBrother calas[]) {
        Output output = new Output();
        int begin, end, mid;
        for(int i = 1; i < calas.length; i++) {
            begin = 0;
            end = i - 1;
            while(begin <= end) {
                mid = (begin + end) / 2;
                if(calas[mid].getRank() <= calas[i].getRank()) {
                    begin = mid + 1;
                }
                else {
                    end = mid - 1;
                }
            }
            if(begin != i) {
                CalabashBrother temp = calas[i];
                for(int j = i; j > begin; j--) {
                    calas[j - 1].moveTo(j);
                    output.outputMove(calas[j - 1], j - 1, j);
                    calas[j] = calas[j - 1];
                }
                calas[i].moveTo(begin);
                output.outputMove(calas[i], i, begin);
                calas[begin] = temp;
            }
        }
        output.outputContent("\n");
    }
}

public class SortCalabashBrothers {
    public static void main(String[] arg) {
        CalabashBrother calas[] = {
            new CalabashBrother("红色", 1, "老大"), 
            new CalabashBrother("橙色", 2, "老二"), 
            new CalabashBrother("黄色", 3, "老三"), 
            new CalabashBrother("绿色", 4, "老四"), 
            new CalabashBrother("青色", 5, "老五"), 
            new CalabashBrother("蓝色", 6, "老六"), 
            new CalabashBrother("紫色", 7, "老七")
        };
        Sort sort = new Sort();
        Output output = new Output();

        sort.randomSeq(calas);
        output.outputContent("冒泡排序：\n");
        output.outputContent("原序列：");
        output.outputAllNames(calas);
        sort.bubbleSort(calas);
        output.outputAllNames(calas);

        output.outputContent("\n");

        sort.randomSeq(calas);
        output.outputContent("二分排序：\n");
        output.outputContent("原序列：");
        output.outputAllNames(calas);
        sort.binSort(calas);
        output.outputAllColors(calas);
    }
}