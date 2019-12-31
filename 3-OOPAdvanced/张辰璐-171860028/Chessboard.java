import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Chessboard {
    final static int numOfBros = 7;
    final String name[] = {"老大", "老二", "老三", "老四", "老五", "老六", "老七"};
    final String color[] = {"红色", "橙色", "黄色", "绿色", "青色", "蓝色", "紫色"};

    int N=0;

    List<Chessman>being;
    List<Integer>map;

    /*Constructors*/
    public Chessboard() {
        being=new ArrayList<Chessman>();
        map=new ArrayList<Integer>();
    }

    public static Chessboard newNxN(){
        Chessboard res = new Chessboard();
        System.out.println("Please input the size of the map:");
        Scanner read=new Scanner(System.in);
        int temp=read.nextInt();
        res.setN(temp);
        System.out.println("Has loaded a NxN map for CalabashBrothers successfully!");
        res.showAllMap();
        /*add characters*/
        //Calabash bros
        for (int i = 0; i < numOfBros; i++){
            res.being.add(new Chessman(res.name[i],res.color[i],new Position(i,0),i));
            res.setMap(i,0,i);
        }
        //res.being.add(new Chessman(res.name[i],res.color[i],new Position(i,0),i))
        res.setMap(numOfBros,0,numOfBros);
        res.setMap(res.getN()-1,0,-3);
        System.out.println("Has loaded the righteous characters successfully!");
        res.showAllMap();
        return res;
    }


    /*Methods*/

    private int getSiteBeingId(int tsite){
        int tindex=map.get(tsite);
        Chessman tempC=being.get(tindex);
        return tempC.getId();
    }

    private String getSiteBeingName(int tsite){
        int tindex=map.get(tsite);
        Chessman tempC=being.get(tindex);
        return tempC.getName();
    }

//    private void swapSiteBeing(int tsite0,int tsite1){
//        int tindex0=map.get(tsite0);
//        int tindex1=map.get(tsite1);
//        map.set(tsite0,tindex1);
//        map.set(tsite1,tindex0);
//        //update chessman site info
//        Chessman tempC0=being.get(tindex0);
//        tempC0.changeSite(new Position(tsite1));
//        Chessman tempC1=being.get(tindex1);
//        tempC1.changeSite(new Position(tsite0));
//        being.set(tindex0,tempC1);
//        being.set(tindex1,tempC0);
//    }
//

    void setN(int x){
        N=x;
        refreshMap();
    }

    int getN(){
        return N;
    }

    void refreshMap() {
        if (map.size() < N * N) {
            for (int i = 0; i < map.size(); i++) {
                map.set(i, -1);
            }
            for (int i = map.size(); i < N * N; i++) {
                map.add(-1);
            }
        } else {
            for (int i = 0; i < N * N; i++) {
                map.set(i, -1);
            }
        }
    }

    int getMap(int x,int y){
        return map.get(x*N+y);
    }

    void setMap(int x,int y,int val){
        map.set(x*N+y,val);
    }
    void showAllMap(){
        System.out.println("=============================================================");
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                if(getMap(i,j)==-1)
                    System.out.printf("%-5s","*");
                else if(getMap(i,j)>=0&&getMap(i,j)<numOfBros)
                    System.out.printf("%-5d",getMap(i,j));
                else if(getMap(i,j)==-2)
                    System.out.printf("%-5s","x");//蝎子精
                else if(getMap(i,j)==-3)
                    System.out.printf("%-5s","s");//蛇精
                else if(getMap(i,j)==-4)
                    System.out.printf("%-5s","s");//小喽啰
                else if(getMap(i,j)==numOfBros)
                    System.out.printf("%-5s","y");//爷爷
            }
            System.out.println("");
        }
        System.out.println("=============================================================");
    }

    void yanXing(){
        setMap(0,6,-4);setMap(1,5,-4);
        setMap(2,4,-4);setMap(3,3,-4);
        setMap(4,2,-4);setMap(5,1,-2);
    }

    void buHuiDU(){
        setMap(0,6,-1);setMap(1,5,-1);
        setMap(2,4,-1);setMap(3,3,-1);
        setMap(4,2,-1);setMap(5,1,-1);

        setMap(0,6,-4);setMap(1,6,-4);
        setMap(2,6,-4);setMap(1,5,-4);
        setMap(2,5,-4);setMap(3,5,-2);
    }

    public static void main(String[] args) {
        Chessboard cb= Chessboard.newNxN();
        cb.yanXing();
        cb.showAllMap();
        cb.buHuiDU();
        cb.showAllMap();
    }
}
