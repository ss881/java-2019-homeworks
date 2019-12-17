import java.util.*;

class Huluwa {
    private String name, color;
    private int nameRank, colorRank;
    private int position;
    private boolean isInLine;

    Huluwa(String n, String c, int nr, int cr) {
        name = n;
        color = c;
        nameRank = nr;
        colorRank = cr;
        position = 0;
        isInLine = false;
    }

    public void shoutName() {
        System.out.println(name + ":" + name);
    }

    public void shoutColor() {
        System.out.println(name + ":" + color);
    }

    public void shoutPosMove(String dest, String src) {
        System.out.println(name + ":" + src + "->" + dest);
    }

    public void shoutPosMove(int dest, int src) {
        shoutPosMove("" + dest, "" + src);
    }

    public boolean isBiggerThan(Huluwa bro, String key) {
        if (key == "color") {
            return colorRank > bro.colorRank;
        } else if (key == "name") {
            return nameRank > bro.nameRank;
        } else {
            throw new IllegalArgumentException("Undefined key!");
        }
    }
}

class Cheque {
    private ArrayList<Huluwa> line;
    private int length;

    private Huluwa kickout(int pos, boolean shouting) {
        Huluwa outsider = line.get(pos);
        if (shouting) outsider.shoutPosMove("outside", "" + pos);
        return outsider;
    }

    private void setPos(Huluwa guy, int dest, int src, boolean shouting) {
        line.set(dest, guy);
        if (shouting) guy.shoutPosMove(dest, src);
    }

    private void swap(int pos1, int pos2, boolean shouting) {
        Huluwa outsider1 = kickout(pos1, shouting);
        Huluwa outsider2 = kickout(pos2, shouting);
        setPos(outsider1, pos2, pos1, shouting);
        setPos(outsider2, pos1, pos2, shouting);
    }

    private void move(int dest, int src) {
        if (dest == src) {
            line.get(src).shoutPosMove("stay", "" + src);
            return;
        }

        Huluwa outsider = kickout(src, true);
        if (dest > src) {
            for (int i = src + 1; i <= dest; i += 1) {
                Huluwa guy = line.get(i);
                guy.shoutPosMove(i - 1, i);
            }
        } else { //dest < src
            for (int i = src - 1; i >= dest; i -= 1) {
                Huluwa guy = line.get(i);
                guy.shoutPosMove(i + 1, i);
            }
        }

        outsider.shoutPosMove(dest, src);
        line.remove(src);
        line.add(dest, outsider);
    }

    private int binarySearch(Huluwa guy, String key, int low, int high) {
        if (high <= low)
            return (guy.isBiggerThan(line.get(low), key)) ? (low + 1) : low;

        int mid = (low + high) / 2;

        //considering huluwa cannot duplicate theirselves, the following block is hidden.
        //if (guy == line.get(mid))
        //    return mid + 1;

        if (guy.isBiggerThan(line.get(mid), key)) return binarySearch(guy, key, mid + 1, high);
        else return binarySearch(guy, key, low, mid - 1);
    }

    Cheque(int len) {
        line = new ArrayList<Huluwa>(len);
        length = len;
    }

    public void addHuluwa(Huluwa guy) {
        line.add(guy);
    }

    public void sortBubble(String key) {
        for (int i = 0; i < length - 1; i += 1) {
            for (int j = 0; j < length - 1 - i; j += 1) {
                if (line.get(j).isBiggerThan(line.get(j + 1), "name")) {
                    swap(j, j + 1, true);
                }
            }
        }
    }

    public void sortBi(String key) {
        for (int i = 1; i < length; i += 1) {
            Huluwa guyToSort = line.get(i);
            int dest = binarySearch(guyToSort, key, 0, i - 1);
            move(dest, i);
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < 100; i += 1) {
            int pos1, pos2;
            pos1 = random.nextInt(7);
            pos2 = random.nextInt(7);
            while (pos1 == pos2) pos2 = random.nextInt(7);
            swap(pos1, pos2, false);
        }
    }

    public void report(String key) {
        System.out.println("----Reporting!----");
        for (Huluwa i : line) {
            if (key == "name")
                i.shoutName();
            else
                i.shoutColor();
        }
        System.out.println("------------------");
    }
}

public class Homework2 {
    public static void main(String[] args) {
        Cheque cheque = new Cheque(7);
        cheque.addHuluwa(new Huluwa("老大", "红色", 1, 1));
        cheque.addHuluwa(new Huluwa("老二", "橙色", 2, 2));
        cheque.addHuluwa(new Huluwa("老三", "黄色", 3, 3));
        cheque.addHuluwa(new Huluwa("老四", "绿色", 4, 4));
        cheque.addHuluwa(new Huluwa("老五", "青色", 5, 5));
        cheque.addHuluwa(new Huluwa("老六", "蓝色", 6, 6));
        cheque.addHuluwa(new Huluwa("老七", "紫色", 7, 7));
        System.out.println("Cheque addtion done!");
        //冒泡
        cheque.shuffle();
        cheque.report("name");
        cheque.sortBubble("name");
        cheque.report("name");
        System.out.println("Bubblesort done!");
        //二分
        cheque.shuffle();
        cheque.report("color");
        cheque.sortBi("color");
        cheque.report("color");
        System.out.println("Binarysort done!");
    }
}
