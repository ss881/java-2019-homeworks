package field;/*
 * N*N的场地。
 * 先行后列。x为横坐标。访问格式为map[x][y].
 */
import items.Living;
import java.util.Random;

public class Field {
    public static final int N=12;
    private Block[][] map;

    public Field(){
        map=new Block[N][N];
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                map[i][j]=new Block(i,j);
    }

    /*
     * 按Living指定的位置添加。用于初始化。
     */
    public boolean addLiving(Living living){
        if(livingAt(living.getPosition())!=null)
            return false;
        int x=living.getPosition().getX(),y=living.getPosition().getY();
        map[y][x].setLiving(living);
        return true;
    }

    /*
     * 将living添加到x,y指定的位置，返回是否成功。
     * 如果成功，修改living中的数据为x,y指定的位置。
     * 否则不做任何操作并返回false。
     */
    private boolean addLiving(Living living, int x, int y){
        if(livingAt(x,y)!=null)
            return false;
        map[y][x].setLiving(living);
        living.getPosition().setPos(x,y);
        return true;
    }

    public boolean addLiving(Living living, Position pos){
        return addLiving(living,pos.getX(),pos.getY());
    }

    private void removeLiving(Position position){
        map[position.getY()][position.getX()].removeLiving();
    }

    public Living livingAt(Position pos){
        return map[pos.getY()][pos.getX()].getLiving();
    }

    private Living livingAt(int x, int y){
        return map[y][x].getLiving();
    }

    /*
     * 移动指定生物到指定位置。此函数由Living调用。
     * （移动应该是生物体主动的动作，故由Living主动，此处只是维护数据，
     * 顺便充当检测是否冲突。如果目标位置有生物体了，返回false，不做任何操作。）
     * 后续维护工作由Living自行完成。
     */
    public boolean moveLiving(Living living, int dx, int dy){
        //保证两边的数据一致
        assert livingAt(living.getPosition())==living;
        int nx=living.getPosition().getX()+dx;
        int ny=living.getPosition().getY()+dy;
        Position oldPosition=living.getPosition().copy();
        if(livingAt(nx,ny)!=null)
            return false;
        if(!addLiving(living,nx,ny))
            return false;
        removeLiving(oldPosition);
        assert livingAt(living.getPosition())==living;
        return true;
    }

    /*
     * 交换两指定生物的位置，由Living调用，且已经保证位置相邻。
     */
    public boolean swapLiving(Living living1, Living living2){
        assert livingAt(living1.getPosition())==living1;
        assert livingAt(living2.getPosition())==living2;
        if(!living1.getPosition().adjacentWith(living2.getPosition()))
            return false;
        removeLiving(living1.getPosition());
        removeLiving(living2.getPosition());
        Position pos1=living1.getPosition().copy(),
                pos2=living2.getPosition().copy();
        addLiving(living1,pos2);
        addLiving(living2,pos1);
        assert livingAt(living1.getPosition())==living1;
        assert livingAt(living2.getPosition())==living2;
        return true;
    }

    /*
     * 画出当前的面板情况。每行表示一行，每列宽度一个制表符。
     * 各个生物调用toString接口显示。
     */
    public void draw(){
        System.out.println("------------------------------------------------------");
        System.out.print('\t');
        for(int i=0;i<N;i++){
            System.out.print(""+i+'\t');
        }
        System.out.print('\n');
        for(int i=0;i<N;i++){
            System.out.print(""+i+'\t');
            for(int j=0;j<N;j++) {
                Living l = livingAt(j,i);
                if(l!=null)
                    System.out.print(l.toString());
                System.out.print('\t');
            }
            System.out.print('\n');
        }
        System.out.println("------------------------------------------------------");
    }

    public boolean inside(Position pos){
        return 0 <= pos.getX() && pos.getX() < N && 0 <= pos.getY() && pos.getY() < N;
    }

    public Position randomPosition(){
        Random random=new Random();
        Position position=new Position(random.nextInt(Field.N),
                random.nextInt(Field.N));
        while (livingAt(position)!=null) {
            position.setPos(random.nextInt(Field.N),
                    random.nextInt(Field.N));
        }
        return position;
    }

    public Position leftRandomPosition(){
        Random random=new Random();
        Position position=new Position(random.nextInt(Field.N/2),
                random.nextInt(Field.N));
        while (livingAt(position)!=null) {
            position.setPos(random.nextInt(Field.N/2),
                    random.nextInt(Field.N));
        }
        return position;
    }

    public Position rightRandomPosition(){
        Random random=new Random();
        Position position=new Position(random.nextInt(Field.N/2)+N/2,
                random.nextInt(Field.N));
        while (livingAt(position)!=null) {
            position.setPos(random.nextInt(Field.N/2)+N/2,
                    random.nextInt(Field.N));
        }
        return position;
    }

    /*
     * 返回p指定的位置能否到达。
     * 不能到达是当且仅当该位置不在范围内，或者存在不可移动的对象。
     */
    public boolean unreachable(Position p){
        return !inside(p) || (livingAt(p) != null && !livingAt(p).isMovable());
    }
}
