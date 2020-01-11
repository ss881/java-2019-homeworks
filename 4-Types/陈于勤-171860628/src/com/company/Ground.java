package com.company;
import java.lang.reflect.Method;
import java.util.*;
import java.lang.*;

public class Ground<T extends Integer> {
    public static final int GROUBD_SIZE = 16;
    private Creature<T>[][] creatures = new Creature[GROUBD_SIZE][GROUBD_SIZE];
    private Position<Integer>[][] positions = new Position[GROUBD_SIZE][GROUBD_SIZE];
    private Random rand = new Random(new Date().getTime());

    //在给定范围内获得一个随机的空位置
    //返回的是一个Position<Integer>
    public Position<Integer> getNewAddress(int left, int righ, int up, int down) {
        //给定范围不能超出地图的边界
        if (left < 0 || righ > GROUBD_SIZE || up < 0 || down > GROUBD_SIZE) return null;
        //检查给定范围内的空间是否还有空余
        boolean judge = false;
        for (int i = left; i < righ; i++) {
            for (int j = up; j < down; j++) {
                if (creatures[i][j] == null) {
                    judge = true; break;
                }
            }
            if (judge == true) break;
        }
        if (judge == false) return null;
        //计算空闲的随机位置
        int x, y;
        do {
            x = left + rand.nextInt(righ - left);
            y = up + rand.nextInt(down - up);
        } while (creatures[x][y] != null);
        return new Position<Integer>(x, y);
    }

    public boolean checkAddressEmpty(int x, int y) {
        if (x < 0 || y < 0 || x >= GROUBD_SIZE || y >= GROUBD_SIZE) return false;
        if (creatures[x][y] != null) return false;
        return true;
    }

    //向地图中添加一个生物，生物的位置信息由生物对象自身携带
    public boolean addCreation( Creature creature) {
        //两个生物对象的位置不能重合
        Position<Integer> position = creature.getPosition().getIntegerPosition();
        int x = position.getX();
        int y = position.getY();
        if (creatures[x][y] != null) return false;
        //修改地图数组
        creatures[x][y] = creature;
        positions[x][y] = new Position<Integer>(x, y);
        return true;
    }
    public boolean deleteCreation(Creature creature) {
        //检查生物是否存在于地图中
        Position<Integer> position = creature.getPosition().getIntegerPosition();
        int x = position.getX();
        int y = position.getY();
        if (creatures[x][y] != creature) return false;
        creatures[x][y] = null;
        positions[x][y] = null;
        return true;
    }

    //修改某个生物对象在地图上的位置
    public boolean changeAddress(Creature creature, T tx, T ty ) {
        //如果生物自身记录的位置与地图记录的位置不一致
        //则说明出现了错误，不能进行下一步的位置修改操作
        Position<Integer> position = creature.getPosition().getIntegerPosition();
        int x = position.getX();
        int y = position.getY();
        if (creatures[x][y] != creature) return false;
        //检查修改的目的位置是否为空闲状态
        //若否，则无法完成修改的操作
        if (creatures[(Integer)tx][(Integer)ty] != null)
            return false;
        //修改地图数组
        creatures[x][y] = null;
        positions[x][y] = null;
        creatures[(Integer)tx][(Integer)ty] = creature;
        creature.setPosition(new Position<T>(tx, ty));
        positions[(Integer)tx][(Integer)ty] = new Position<Integer>((Integer)tx, (Integer)ty);
        return true;
    }

    //搜索某生物对象到某个目的地的路线
    //返回一个Position<Integer>数组，记录了路线上的每一个点
    public Position<Integer>[] searchPath(Creature creature, int targetx, int targety) {
        //首先检查生物是否在目的位置上
        if (creatures[targetx][targety] == creature) {
            Position<Integer>[] ret = new Position[1];
            ret[0] = new Position<Integer>(targetx, targety);
            return ret;
        }
        //目的地的位置必须为空闲状态
        if (creatures[targetx][targety] != null) return null;
        //其次检查生物的位置信息是否一致
        Position<Integer> originpos = creature.getPosition().getIntegerPosition();
        int addrx = originpos.getX();
        int addry = originpos.getY();
        if (creatures[addrx][addry] != creature) return null;
        //用队列来记录遍历路径中的没一个节点
        //其中原二位数组中 存放的是上一个节点和长度的信息
        //所以从终点处的节点可回推出整条路径
        LinkedList<Position<Integer>> queue = new LinkedList<Position<Integer>>();
        queue.addFirst(positions[addrx][addry]);
        //用栈来存放所有使用过的节点，在结束时进行清理
        Stack<Position<Integer>> stack = new Stack<Position<Integer>>();
        while (queue.isEmpty() == false) {
            Position<Integer> newposition = queue.removeLast();
            int x = newposition.getX();
            int y = newposition.getY();
            if (x == targetx && y == targety) break;
            if (x + 1 < GROUBD_SIZE && positions[x + 1][y] == null) {
                Position<Integer> nextposition = new Position<Integer>(x+1, y, newposition.getPath() + 1);
                Position<Integer> prevposition = new Position<Integer>(x, y, newposition.getPath() + 1);
                positions[x + 1][y] = prevposition;
                queue.addFirst(nextposition);
                stack.push(nextposition);
            }
            if (x - 1 >= 0 && positions[x - 1][y] == null) {
                Position<Integer> nextposition = new Position<Integer>(x-1, y, newposition.getPath() + 1);
                Position<Integer> prevposition = new Position<Integer>(x, y, newposition.getPath() + 1);
                positions[x - 1][y] = prevposition;
                queue.addFirst(nextposition);
                stack.push(nextposition);
            }
            if (y + 1 < GROUBD_SIZE && positions[x][y + 1] == null) {
                Position<Integer> nextposition = new Position<Integer>(x, y+1, newposition.getPath() + 1);
                Position<Integer> prevposition = new Position<Integer>(x, y, newposition.getPath() + 1);
                positions[x][y+1] = prevposition;
                queue.addFirst(nextposition);
                stack.push(nextposition);
            }
            if (y - 1 >= 0 && positions[x][y - 1] == null) {
                Position<Integer> nextposition = new Position<Integer>(x, y-1, newposition.getPath() + 1);
                Position<Integer> prevposition = new Position<Integer>(x, y, newposition.getPath() + 1);
                positions[x][y-1] = prevposition;
                queue.addFirst(nextposition);
                stack.push(nextposition);
            }
        }
        Position<Integer>[] ret = null;
        //回溯路径
        if (positions[targetx][targety] != null) {
            int pathlength = positions[targetx][targety].getPath();
            ret = new Position[pathlength];
            ret[--pathlength] = new Position<Integer>(targetx, targety);
            while (pathlength-- > 0) {
                int x = ret[pathlength + 1].getX();
                int y = ret[pathlength + 1].getY();
                Position<Integer> nextpos = positions[x][y];
                ret[pathlength] = nextpos;
            }
        }
        //清理广度优先遍历中使用到的二维数组场地
        while (stack.empty() == false) {
            Position<Integer> pos = stack.pop();
            int x = pos.getX();
            int y = pos.getY();
            positions[x][y] = null;
        }
        return ret;
    }

    public void showGround() {
        for (int i = 0 ; i < GROUBD_SIZE; i++) {
            System.out.print(i + "\t\t");
        }
        for (int j = 0; j < GROUBD_SIZE; j++) {
            for (int i = 0; i < GROUBD_SIZE; i++) {
//                int length = namelength;
                if (creatures[i][j] != null) {
                    String creation = creatures[i][j].toString();
                    System.out.printf( "%s\t", creation);
//                    length -= creation.length() * 2;
                }
                else System.out.print("\t\t");
//                System.out.print("|");
            }
            System.out.print("|" + j + "\n");
        }
        for (int i = 0; i < GROUBD_SIZE; i++)
            System.out.print("********");
        System.out.print("\n");
    }

    public int getGroupNumbers(String group) throws Exception {
        //获得所需查询的类别
        Class<?> targetclass = null;
        try {
            targetclass = Class.forName("com.company" + group);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
        //遍历二维数组计算对应对象的个数
        int ret = 0;
        for (int i = 0; i < GROUBD_SIZE; i++) {
            for (int j = 0; j < GROUBD_SIZE; j++) {
                if (targetclass.isInstance(creatures[i][j]) == true)
                    ret++;
            }
        }
        return ret;
    }

    //命令特定类型特定数量的对象以特定阵型排列在地图上
    //group指定对象的类型名称
    //leader指定关键点上的角色（可为null）
    //strategy指定特定的阵型
    //position指定关键点的位置
    //members指定阵型中的生物总量
    public void setGroupStrategyFormation(String group, Creature<T> leader,
                                          StrategyFormation strategy,
                                          Position<T> position, int members)
    throws Exception {
        //获得所需查询的类别
        Class<?> targetclass = null;
        try {
            targetclass = Class.forName("com.company." + group);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        if (members <= 0)
            return;

        //获得并调用相应的阵法函数
        Method strategymethod = strategy.getClass().getDeclaredMethod(
                "get"+strategy, Position.class, Integer.class);
        Object ret = strategymethod.invoke(null, position, members);
        Position<Integer>[] formation = new Position[1];
        if (formation.getClass().isInstance(ret))
            formation = (Position<Integer>[])ret;
        else
            return;

        boolean[][] repeatcheck = new boolean[GROUBD_SIZE][GROUBD_SIZE];
        for (int i = 0; i < GROUBD_SIZE; i++) {
            for (int j = 0; j < GROUBD_SIZE; j++) {
                repeatcheck[i][j] = false;
            }
        }
        for (int i = 0; i < formation.length; i++) {
            if (formation[i] == null)
                continue;
            int x = formation[i].getX();
            int y = formation[i].getY();
            if (x >= 0 && x < GROUBD_SIZE && y >= 0 && y < GROUBD_SIZE)
                repeatcheck[x][y] = true;
        }

        //首先确定关键点上的领导者
        if (leader != null) {
            Position<Integer> leaderpos = leader.getPosition();
            int leaderx = leaderpos.getX();
            int leadery = leaderpos.getY();
            int x = position.getX();
            int y = position.getY();
            if (x >= 0 && x < GROUBD_SIZE && y >= 0 && y < GROUBD_SIZE) {
                if (leader.goSomewhere(x, y) == true) {
                    creatures[x][y] = creatures[leaderx][leadery];
                    positions[x][y] = positions[leaderx][leadery];
                    creatures[leaderx][leadery] = null;
                    positions[leaderx][leadery] = null;
                }
            }
        }
        for (int counter = 0; counter < formation.length; counter++) {
            if (formation[counter] == null)
                continue;
            Position<Integer> target = formation[counter];
            boolean complete = false;
            int targetx = target.getX();
            int targety = target.getY();

            //检查边界条件
            if (targetx < 0 || targetx >= GROUBD_SIZE ||
                targety < 0 || targety >= GROUBD_SIZE)
                continue;
            if (creatures[targetx][targety] != null) {
                if (targetclass.isInstance(creatures[targetx][targety])) {
                    creatures[targetx][targety].goSomewhere(targetx,targety);
                    complete = true;
                }
            }

            //遍历生物数组查找能到达目的位置的生物对象
            for (int i = 0; i < GROUBD_SIZE; i++) {
                if (complete == true)
                    break;
                for (int j = 0; j < GROUBD_SIZE; j++) {
                    if (complete == true)
                        break;
                    //检查位置上是否存在生物
                    if (creatures[i][j] == null)
                        continue;
                    //检查位置上的生物是否是目的对象
                    if (!targetclass.isInstance(creatures[i][j]))
                        continue;
                    //检查此位置是否已经作为目标地点之一
                    if (repeatcheck[i][j] == true)
                        continue;
                    //命令生物前往目标地点，并获得生物对象是否能前往目标地点的返回值
                    if (creatures[i][j].goSomewhere(targetx, targety) == true) {
                        complete = true;
                        creatures[targetx][targety] = creatures[i][j];
                        positions[targetx][targety] = positions[i][j];
                        creatures[i][j] = null;
                        positions[i][j] = null;
                    }
                }
            }
            if (complete == false) {
                System.out.println("Warning!" + target + "can not get to!");
            }
        }
    }
}








