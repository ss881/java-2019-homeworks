import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jdk.javadoc.internal.doclets.formats.html.resources.standard;
import sun.net.www.content.text.plain;

public class Main{
    public static CalabashBoy []calabashBoys = new CalabashBoy[7];
    //static Ground ground = new Ground();
    public static void InitializeCalabashboys(){
        calabashBoys[0] = new CalabashBoy("红色", 1); 
        calabashBoys[1] = new CalabashBoy("橙色", 2);
        calabashBoys[2] = new CalabashBoy("黄色", 3); 
        calabashBoys[3] = new CalabashBoy("绿色", 4); 
        calabashBoys[4] = new CalabashBoy("青色", 5); 
        calabashBoys[5] = new CalabashBoy("蓝色", 6); 
        calabashBoys[6] = new CalabashBoy("紫色", 7); 
    }

    public static void randomStand(){
        System.out.println("\n\nRandomly stand: ");

        Ground.ClearAll();
        for (CalabashBoy cala : calabashBoys){
            cala.randomStand();
        }
        Ground.PrintRank();
    }

    public static void BubbleSort(){
        System.out.println("\n\nBubble Sort: ");
        for(int i = 0; i < Ground.tiles.length; i++){
            for(int j = 0; j < Ground.tiles.length - i - 1; j++){
                Ground.tiles[j].calabashBoy.SwapWithNext_bubble();
            }
        }
        System.out.println("\nBubble sorting result:");
        Ground.PrintRank();
    }

    public static int BinarySearch(int l, int r, int pivot){
        int insertLoc = -1;

        while(l <= r){
            insertLoc = (l+r)/2;
            if(Ground.tiles[insertLoc].calabashBoy.getRank()>pivot)r = insertLoc-1;
            else if(Ground.tiles[insertLoc].calabashBoy.getRank()<pivot)l=insertLoc+1;
            else break;
        }
        if(Ground.tiles[insertLoc].calabashBoy.getRank() <= pivot)return insertLoc+1;
        else return insertLoc;
    }

    public static void BinarySort(){
        System.out.println("\n\nBinary Sort: ");

        for(int i = Ground.tiles.length-2; i >= 0; i--){
            int pivot = Ground.tiles[i].calabashBoy.getRank();
            int insertLoc = BinarySearch(i+1, Ground.tiles.length-1, pivot);

            for(int j = i; j < insertLoc; j++){
                //System.out.println(insertLoc);
                Ground.tiles[j].calabashBoy.SwapWithNext_binary();
            }
        }

        System.out.println("\nBinary sorting result:");
        Ground.PrintColor();
    }

    public static void main(String[] args) {
        //ground.Print();
        
        InitializeCalabashboys();
        
        randomStand();
        BubbleSort();

        randomStand();
        BinarySort();
    }
}