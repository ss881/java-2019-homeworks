import java.util.Random;

//游戏地图
class Map {
    private final static int HULUWA_NUM=7;
    //一组地砖（一维）
    private Tile[] tiles;
    //临时位置，用于葫芦娃之间进行位置交换
    private Tile tempPosition;
    Map(){
        tempPosition=new Tile();
        tempPosition.huluwa=null;
        tiles=new Tile[HULUWA_NUM];
        for(int i=0;i<HULUWA_NUM;i++)
            tiles[i]=new Tile();
        tiles[0].huluwa=new Huluwa(Color.RED,Name.ONE);
        tiles[1].huluwa=new Huluwa(Color.ORANGE,Name.TWO);
        tiles[2].huluwa=new Huluwa(Color.YELLOW,Name.THREE);
        tiles[3].huluwa=new Huluwa(Color.GREEN,Name.FOUR);
        tiles[4].huluwa=new Huluwa(Color.CYAN,Name.FIVE);
        tiles[5].huluwa=new Huluwa(Color.BLUE,Name.SIX);
        tiles[6].huluwa=new Huluwa(Color.PUPPLE,Name.SEVEN);
    }
    //葫芦娃随机排序
    void shuffle(){
        Random rand=new Random();
        for(int i=HULUWA_NUM;i>1;i--){
            int randPos=rand.nextInt(i);
            if(randPos!=i-1)
                swap(randPos,i-1);
        }
    }
    //冒泡排序
    void bubbleSort(){
        for(int i=0;i<HULUWA_NUM-1;i++){
            for(int j=0;j<HULUWA_NUM-1-i;j++){
                if(tiles[j].huluwa.decideToChange(tiles[j+1].huluwa))
                    swap(j,j+1);
            }
        }
    }
    //二分法插入排序
    void binarySort(){
        int start,end,mid=-1,insertPos;
        for(int i=1;i<HULUWA_NUM;i++){
            tiles[i].huluwa.runToTempPosition(tempPosition,i);
            start=0;
            end=i-1;
            while(start<=end){
                mid=(start+end)/2;
                if(tiles[mid].huluwa.decideToChange(tiles[i].huluwa))
                    end=mid-1;
                else
                    start=mid+1;
            }
            if(!tiles[mid].huluwa.decideToChange(tiles[i].huluwa))
                insertPos=mid+1;
            else
                insertPos=mid;
            for(int j=i;j>insertPos;j--)
                tiles[j-1].huluwa.runTo(tiles,j-1,j);
            tempPosition.huluwa.returnFromTemp(tiles,insertPos);
        }
    }
    //葫芦娃报数
    void count(CountKind kind){
        for(int i = 0; i< HULUWA_NUM; i++){
            tiles[i].huluwa.count(kind);
            System.out.print("\n");
        }
    }
    //交换两个葫芦娃的位置
    private void swap(int p1,int p2){
        tiles[p2].huluwa.runToTempPosition(tempPosition,p2);
        tiles[p1].huluwa.runTo(tiles,p1,p2);
        tempPosition.huluwa.returnFromTemp(tiles,p1);
    }
}
