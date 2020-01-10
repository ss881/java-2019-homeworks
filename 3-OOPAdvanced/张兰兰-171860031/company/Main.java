package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        int N = 18;
       Processor elf = new Processor(N);
        elf.start(16);//开始游戏，布置人物
        elf.swap_eormation();//完成转换阵型
        elf.end();//游戏结束

    }
}
