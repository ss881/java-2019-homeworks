package history;

import controller.MyMap;

public class Init{ //保存的初始化的战场
    private int[][]map = new int[10][20];

    public Init(int [][]init){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){
                map[i][j] = init[i][j];
            }
        }
    }
    public int[][] getMap(){
        return this.map;
    }
}
