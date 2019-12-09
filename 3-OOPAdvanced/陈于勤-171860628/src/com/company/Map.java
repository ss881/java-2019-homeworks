package com.company;
import  java.util.*;

public class Map {
    public static final int MAP_SIZE = 16;
    private Creature[][] creaturemap = new Creature[MAP_SIZE][MAP_SIZE];
    private int[][] pathmap = new int[MAP_SIZE][MAP_SIZE];
    private Random rand = new Random(new Date().getTime());
//    private int namelength = 6;

    //检查生物对象的位置信息与地图中的记录是否一致
//    public boolean checkAddress(Creation creation) {
//        int x = creation.getAddressx();
//        int y = creation.getAddressy();
//        return creationmap[x][y] == creation;
//    }
    //在给定范围内获得一个随机的空位置
    //返回的是一个长度为2的一维数组
    public int[] getNewAddress(int left, int righ, int up, int down) {
        //给定范围不能超出地图的边界
        if (left < 0 || righ > MAP_SIZE || up < 0 || down > MAP_SIZE) return null;
        //检查给定范围内的空间是否还有空余
        boolean judge = false;
        for (int i = left; i < righ; i++) {
            for (int j = up; j < down; j++) {
                if (creaturemap[i][j] == null) {
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
        } while (creaturemap[x][y] != null);
        int[] ret = {x, y};
        return ret;
    }
    public boolean checkAddressEmpty(int x, int y) {
        if (x < 0 || y < 0 || x >= MAP_SIZE || y >= MAP_SIZE) return false;
        if (creaturemap[x][y] != null) return false;
        return true;
    }
    //向地图中添加一个生物，生物的位置信息由生物对象自身携带
    public boolean addCreation( Creature creature) {
        //两个生物对象的位置不能重合
        int x = creature.getAddressx();
        int y = creature.getAddressy();
        if (creaturemap[x][y] != null) return false;
        //修改地图数组
        creaturemap[x][y] = creature;
        pathmap[x][y] = -1;
        return true;
    }
    public boolean deleteCreation(Creature creature) {
        //检查生物是否存在于地图中
        int x = creature.getAddressx();
        int y = creature.getAddressy();
        if (creaturemap[x][y] != creature) return false;
        creaturemap[x][y] = null;
        pathmap[x][y] = 0;
        return true;
    }
    //修改某个生物对象在地图上的位置
    public boolean changeAddress(Creature creature, int x, int y ) {
        //如果生物自身记录的位置与地图记录的位置不一致
        //则说明出现了错误，不能进行下一步的位置修改操作
        int addrx = creature.getAddressx();
        int addry = creature.getAddressy();
        if (creaturemap[addrx][addry] != creature) return false;
        //检查修改的目的位置是否为空闲状态
        //若否，则无法完成修改的操作
        if (creaturemap[x][y] != null)
            return false;
        //修改地图数组
        creaturemap[addrx][addry] = null;
        pathmap[addrx][addry] = 0;
        creaturemap[x][y] = creature;
        pathmap[x][y] = -1;
        return true;
    }
    //搜索某生物对象到某个目的地的路线
    //返回一个二维数组，记录了路线上的每一个点
    public int[][] searchPath(Creature creature, int targetx, int targety) {
        //首先目的地的位置必须为空闲状态
        if (creaturemap[targetx][targety] != null) return null;
        //其次检查生物的位置信息是否一致
        int addrx = creature.getAddressx();
        int addry = creature.getAddressy();
        if (creaturemap[addrx][addry] != creature) return null;
        //用数组来代替一个临时的伪队列
        //数组中存放点的位置以及路径中上一个节点的索引（便于回溯路线）
        int[][] array = new int[MAP_SIZE * MAP_SIZE][3];
        //记录目的地位置点存放在数组中的索引
        int targetindex = -1;
        //分别表示遍历的当前节点，存放新节点的下一个位置
        int index = 0, next = 1;
        //将生物对象当前的位置加入队列
        array[0][0] = addrx; array[0][1] = addry; array[0][2] = -1;
        //广度优先遍历查找路径
        for (; index != next; index++) {
            int x = array[index][0], y = array[index][1];
            if (x == targetx && y == targety) {
                targetindex = index; break;
            }
            //计算相邻位置点距离初始点的距离
            int length = pathmap[x][y] < 0 ? 1 : pathmap[x][y] + 1;
            //检查相邻位置节点
            //记录相邻点的位置和上一个节点（即当前节点）的索引
            //存储位置后移，同时修改地图上的距离信息
            if (x + 1 < MAP_SIZE && pathmap[x + 1][y] == 0) {
                array[next][0] = x + 1; array[next][1] = y;
                array[next][2] = index; next++;
                pathmap[x + 1][y] = length;
            }
            if (x - 1 >= 0 && pathmap[x - 1][y] == 0) {
                array[next][0] = x - 1; array[next][1] = y;
                array[next][2] = index; next++;
                pathmap[x - 1][y] = length;
            }
            if (y + 1 < MAP_SIZE && pathmap[x][y + 1] == 0) {
                array[next][0] = x; array[next][1] = y + 1;
                array[next][2] = index; next++;
                pathmap[x][y + 1] = length;
            }
            if (y - 1 >= 0 && pathmap[x][y - 1] == 0) {
                array[next][0] = x; array[next][1] = y - 1;
                array[next][2] = index; next++;
                pathmap[x][y - 1] = length;
            }
        }
        //没有找到到达目的地的路径
        if (targetindex < 0) return null;
        //路径的长度
        int length = pathmap[targetx][targety];
        //返回的路径节点数组
        int[][] ret = new int[length][2];
        for (index = targetindex; length > 0; length--) {
            ret[length - 1][0] = array[index][0];
            ret[length - 1][1] = array[index][1];
            index = array[index][2];
        }
        for (int i = next - 1; i > 0; i--) {
            int x = array[i][0], y = array[i][1];
            pathmap[x][y] = 0;
        }
        return ret;
    }
    //更新地图中生物的名字最长长度
/*    public int renewNameLength() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (creaturemap[i][j] != null) {
                    int newlength = creaturemap[i][j].toString().length();
                    namelength = (namelength > newlength) ? namelength : newlength;
                }
            }
        }
        return namelength;
    }
*/
public void showMap() {
        for (int j = 0; j < MAP_SIZE; j++) {
            System.out.print("|");
            for (int i = 0; i < MAP_SIZE; i++) {
//                int length = namelength;
                if (creaturemap[i][j] != null) {
                    String creation = creaturemap[i][j].toString();
                    System.out.printf( "%-3s", creation);
//                    length -= creation.length() * 2;
                }
                else System.out.print("     ");
//                System.out.print("|");
            }
            System.out.print("\n");
        }
        for (int i = 0; i < MAP_SIZE; i++)
            System.out.print("*****");
        System.out.print("\n");
    }
}
