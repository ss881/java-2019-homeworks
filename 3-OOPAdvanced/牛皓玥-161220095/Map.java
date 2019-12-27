public class Map {
    final static int N = 22;                            //地图大小
    Position[][] positions = new Position[N][N];        //地图位置

    Map(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                positions[i][j] = new Position(i,j);
            }
        }
    }

    // 打印阵型
    public void printMap(){
        for(int i=0;i<N;i++) {
            System.out.print('|');
            for (int j=0; j<N; j++) {
                //如何显示出整体的地图，有无生物的对齐
                if(positions[i][j].flag == false){
                    System.out.print('\t');
                    System.out.print('|');
                }
                else{
                    positions[i][j].creature.tellname();
                    System.out.print('|');
                }
            }
            System.out.print('\n');
            if(i==10){
                System.out.print("—————————————————————————————————————————————————————————————————————————————————————————\n");
            }
        }
    }
}
