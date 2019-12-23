package homework4;

import java.io.IOException;

public class Admin {
    private static Checkerboard theboard;

    public static void main(String args[]) throws IOException, InterruptedException {
        theboard = new Checkerboard();
        while (true) {
            theboard.print();
            System.out.print("输入[Y/n]继续……");
            char input=(char)System.in.read();
            if (input == 'n')
                break;
            else {
                theboard.clear();
            }
        }
    }
}

