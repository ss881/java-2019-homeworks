import java.util.Random;


class World{
    /** size of the world, 15*15 */
    final static int worldSize = 15;
    /** move direction */
    enum Direction{STILL,LEFT,RIGHT,UP,DOWN};
    /** topography, only ground now */
    enum Topography{GROUND};
    /** the map */
    static Topography map[][] = new Topography[worldSize][worldSize];
    /** creature map, map of creatures */
    static Creature creatueMap[][] = new Creature[worldSize][worldSize];
    /** init world */
    World(){
        //init topograpghy and creature map
        for(int i = 0;i < worldSize;++i){
            for(int j = 0;j < worldSize;++j){
                map[i][j] = Topography.GROUND;
                creatueMap[i][j] = null;
            }
        }
    }
    /** 
     * this metohd should be called by a creature;
     * accept request to set one creature's position,if success return true,else return false;
     * the range of first and second has been checked before;
     * this method is uesd in the beginning, so don't need to care creature's old position.
     */
    static boolean setCreaturePosition(Creature one,int first,int second){
        if(creatueMap[first][second] == null){
            //int oldPositionFirst = one.position[0];
            //int oldPositionSecond = one.position[1];
            creatueMap[first][second] = one;
            one.position[0] = first;
            one.position[1] = second;
            return true;
        }
        else{
            System.out.printf("Error:this palce(%d,%d) already stands a creature%n",first,second);
            return false;
        }
    }

    /** similar to {@code setCreaturePosition}, but this method will change original position */
    static boolean changeCreaturePosition(Creature one,int first,int second){
        if(creatueMap[first][second] == null){
            int oldPositionFirst = one.position[0];
            int oldPositionSecond = one.position[1];
            creatueMap[oldPositionFirst][oldPositionSecond] = null;
            creatueMap[first][second] = one;
            one.position[0] = first;
            one.position[1] = second;
            return true;
        }
        else{
            System.out.printf("Error:this palce(%d,%d) already stands a creature%n",first,second);
            return false;
        }
    }

    static void showMap(){
        for(int i = 0;i < World.worldSize;++i){
            System.out.printf("==");
        }
        System.out.println("");
        for(int i = 0;i < worldSize;++i){
            for(int j = 0;j < worldSize;++j){
                if(creatueMap[i][j] == null){
                    //switch(map[i][j]){
                    //    case GROUND:
                            System.out.printf(" ");
                    //}
                }
                else
                   creatueMap[i][j].showOne();
                System.out.print(' ');
            }
            System.out.printf("%n");
        }
    }
}

class Creature{
    /** identity, calabashbrother or monster */
    enum CreatureType{HUMAN,CALABASH,MONSTER,NOTHING};
    CreatureType identity = CreatureType.NOTHING;
    /** position in the map of world */
    int position[] = new int[2];
    boolean inMap;
    ///** move speed */
    //int speed = 0;
    //not for now

    World.Direction moveDirection = World.Direction.STILL;
    
    //static int newId = 0;
    //int id;
    Creature(){
        position[0] = position[1] = 0;
        inMap = false;
    }
    void setPosition(int first, int second){
        if(first >= 0 && first < World.worldSize && second >= 0 && second < World.worldSize){
            World.setCreaturePosition(this, first, second);
            this.inMap = true;//after setposition,"this" is in map
        }
        else
            System.out.printf("Error:position range is 0~%d%n",World.worldSize-1);
    }
    /** according to identity, print something */
    void showOne(){
        System.out.print('N');
    }
    /** walk to a certain place */
    boolean walkTo(int first, int second){
        boolean retValue = false;
        if(first < 0 || second < 0 || first >= World.worldSize || second >= World.worldSize){
            System.out.printf("Error:the range of position is 0~%d%n",World.worldSize-1);
            return false;
        }

        World.Direction xDirection,yDirection;
        boolean changed = false;
        if(position[0] < first)
            xDirection = World.Direction.DOWN;
        else if(position[0] > first)
            xDirection = World.Direction.UP;
        else
            xDirection = World.Direction.STILL;

        if(position[1] < second)
            yDirection = World.Direction.RIGHT;
        else if(position[1] > second)
            yDirection = World.Direction.LEFT;
        else
            yDirection = World.Direction.STILL;
        
        while(position[0] != first || position[1] != second){
            //改一下，先走没有障碍物的地方
            //如果遇到人交换的话会改变别人的位置
            if(position[0] == first)
                xDirection = World.Direction.STILL;
            if(xDirection == World.Direction.DOWN){
                if(World.creatueMap[position[0]+1][position[1]] == null){
                    moveDirection = World.Direction.DOWN;
                    World.changeCreaturePosition(this, position[0]+1,position[1]);
                    changed = true;
                   
                }
            }
            else if(xDirection == World.Direction.UP){
                if(World.creatueMap[position[0]-1][position[1]] == null){
                    moveDirection = World.Direction.UP;
                    World.changeCreaturePosition(this, position[0]-1, position[1]);
                    changed = true;
                    
                }
            }
            if(position[1] == second)
                yDirection = World.Direction.STILL;
            if(yDirection == World.Direction.RIGHT){
                if(World.creatueMap[position[0]][position[1]+1] == null){
                    moveDirection = World.Direction.RIGHT;
                    World.changeCreaturePosition(this, position[0],position[1]+1);
                    changed = true;
                    
                }
                else if(changed == false){
                    moveDirection = World.Direction.RIGHT;
                    swap(position[0], position[1], World.Direction.RIGHT);
                    changed = true;
                }
            }
            else if(yDirection == World.Direction.LEFT){
                if(World.creatueMap[position[0]][position[1]-1] == null){
                    moveDirection = World.Direction.LEFT;
                    World.changeCreaturePosition(this, position[0],position[1]-1);
                    changed = true;
                }
                else if(changed == false){
                    moveDirection = World.Direction.LEFT;
                    swap(position[0], position[1], World.Direction.LEFT);
                    changed = true;
                }
            }

            if(changed == false){
                if(xDirection == World.Direction.UP){
                    moveDirection = World.Direction.UP;
                    swap(position[0], position[1], World.Direction.UP);
                    changed = true;
                }
                else if(xDirection == World.Direction.DOWN){
                    moveDirection = World.Direction.DOWN;
                    swap(position[0], position[1], World.Direction.DOWN);
                    changed = true;
                }
            }
            if(changed == true)
                retValue = true;
            changed = false;
            moveDirection = World.Direction.STILL;
        }
        return retValue;
    }
    /** swap */
    void swap(int first,int second,World.Direction direction){
        //consider easy situation, one creature won't be surrounded
        if(World.Direction.RIGHT == direction || World.Direction.LEFT == direction){
            if(first-1 >= 0 && World.creatueMap[first-1][second] == null){
                //leave out the place
                walkTo(first-1, second);
                if(direction == World.Direction.RIGHT){
                    World.creatueMap[first][second+1].walkTo(first, second);
                    walkTo(first, second+1);
                }
                else{
                    World.creatueMap[first][second-1].walkTo(first, second);
                    walkTo(first, second-1);
                }
            }
            else if(first+1 < World.worldSize && World.creatueMap[first+1][second] == null){
                walkTo(first+1, second);
                if(direction == World.Direction.RIGHT){
                    World.creatueMap[first][second+1].walkTo(first, second);
                    walkTo(first, second+1);
                }
                else{
                    World.creatueMap[first][second-1].walkTo(first, second);
                    walkTo(first, second-1);
                }
            }
            else{
                System.out.println("Error:can't swap place");
            }
        }
        else{//swap direction is up or down
            if(second-1 >= 0 && World.creatueMap[first][second-1] == null){
                //leave out the place
                walkTo(first, second-1);
                if(direction == World.Direction.DOWN){
                    World.creatueMap[first+1][second].walkTo(first, second);
                    walkTo(first+1, second);
                }
                else{
                    World.creatueMap[first-1][second].walkTo(first, second);
                    walkTo(first-1, second);
                }
            }
            else if(second+1 < World.worldSize && World.creatueMap[first][second+1] == null){
                walkTo(first, second+1);
                if(direction == World.Direction.DOWN){
                    World.creatueMap[first+1][second].walkTo(first, second);
                    walkTo(first+1, second);
                }
                else{
                    World.creatueMap[first-1][second].walkTo(first, second);
                    walkTo(first-1, second);
                }
            }
            else{
                System.out.println("Error:can't swap place");
            }
        }
    }
}

/** grandfather manage 7 brothers */
class Grandfather extends Creature{
    private CalabashBrother[] calabashBrotherList = new CalabashBrother[7];
    Grandfather(){
        identity = CreatureType.HUMAN;
    }
    void choosePlaceToSit(){
        for(int i = 0;i < World.worldSize;++i){
            for(int j = 0;j <World.worldSize;++j){
                if(World.creatueMap[i][j] == null){
                    if(inMap == false){
                        setPosition(i, j);
                    }
                    else
                        walkTo(i, j);
                    return;
                }
            }
        }
    }
    void createCalabashBrothers(){
        int[] tempPlace = {4,5,6,7,8,9,10};

        FightFormation.randomizePlace(tempPlace);
        //create 7 brothers
        for(int i = 0;i < 7;++i){
            calabashBrotherList[i] = new CalabashBrother();
            //stand in a line at random
            calabashBrotherList[i].setPosition(tempPlace[i], 0);
        }
    }
    void setLineFormation(){
        boolean changed;
        changed = FightFormation.setLineFormation(calabashBrotherList, World.worldSize/2, World.worldSize/2-1 , World.Direction.RIGHT);
        while(changed){
            changed = FightFormation.setLineFormation(calabashBrotherList, World.worldSize/2, World.worldSize/2-1 , World.Direction.RIGHT);
        }
    }
    /** override @{code showOne} */
    void showOne(){
        System.out.print('F');
    }
}

class CalabashBrother extends Creature{
    static String[] statusName = {"老大","老二","老三","老四","老五","老六","老七"};
    static String[] ownColor = {"赤","橙","黄","绿","青","蓝","紫"};

    /** id for statusName and color */
    private int idInCalabash;
    /** set idInCalabash according to creating time */
    static int newId = 0;

    CalabashBrother(){
        identity = CreatureType.CALABASH;
        //speed = 10;
        idInCalabash = newId++;
    }
    /*
    void setId(int id){
        if(id >= 0 && id < 7)
            idInCalabash = id;
        else
            System.out.println("Error:id range is 0~6");
    }*/

    /** override {@code showOne} */
    void showOne(){
        System.out.printf("%d",idInCalabash);
    }
}

class Monster extends Creature{
    enum MonsterType{SNAKE,SCORPION,OTHER};
    MonsterType monsterIdentity;
    Monster(){
        identity = CreatureType.MONSTER;
        monsterIdentity = MonsterType.OTHER;
    }
    /** override {@code showOne} */
    void showOne(){
        System.out.print('M');
    }
}

class SnakeMonster extends Monster{
    SnakeMonster(){
        monsterIdentity = MonsterType.SNAKE;
    }
    void choosePlaceToSit(){
        for(int i = World.worldSize-1;i >= 0;--i){
            for(int j = World.worldSize-1;j >= 0;--j){
                if(World.creatueMap[i][j] == null){
                    if(inMap == false){
                        setPosition(i, j);
                    }
                    else
                        walkTo(i, j);
                    return;
                }
            }
        }
    }
    /** override {@code showOne} */
    void showOne(){
        System.out.print('S');
    }
}

class ScorpionMonster extends Monster{
    static final int numOfSmallMonster = 6;
    private Monster[] monsterList = new Monster[numOfSmallMonster+1];
    ScorpionMonster(){
        monsterIdentity = MonsterType.SCORPION;
    }
    void leadMonsters(){
        monsterList[0] = this;
        setPosition(0, World.worldSize-1);
        for(int i = 1;i < numOfSmallMonster+1;++i){
            monsterList[i] = new Monster();
            monsterList[i].setPosition(i%World.worldSize,World.worldSize-1-i/World.worldSize);
        }
    }
    void setLineFormation(){
        boolean changed;
        changed = FightFormation.setLineFormation(monsterList, World.worldSize/2, World.worldSize/2 , World.Direction.LEFT);
        while(changed){
            changed = FightFormation.setLineFormation(monsterList, World.worldSize/2, World.worldSize/2 , World.Direction.LEFT);
        }
    }
    void setWingsFormation(){
        boolean changed;
        changed = FightFormation.setWingsFormation(monsterList, World.worldSize/2,World.worldSize-1,World.Direction.LEFT);
        while(changed){
            changed = FightFormation.setWingsFormation(monsterList, World.worldSize/2,World.worldSize-1,World.Direction.LEFT);
        }
    }
    void setMovePreventFormation(){
        boolean changed;
        changed = FightFormation.setMovePreventFormation(monsterList, World.worldSize/2, World.worldSize/2, World.Direction.LEFT);
        while(changed){
            changed = FightFormation.setMovePreventFormation(monsterList, World.worldSize/2, World.worldSize/2, World.Direction.LEFT);
        }
    }
    void setWildGooseFormation(){
        boolean changed;
        changed = FightFormation.setWildGooseFormation(monsterList, 0, World.worldSize/2,World.Direction.LEFT);
        while(changed){
            changed = FightFormation.setWildGooseFormation(monsterList, 0, World.worldSize/2,World.Direction.LEFT);
        }
    }
    /** override {@code showOne} */
    void showOne(){
        System.out.print('P');
    }
}

public class FightFormation{
    /** randomize the given list */
    static void randomizePlace(int[] place){
        int len = place.length;
        Random randomNumber = new Random();
        for(int i = 0;i < len;++i){
            int tempRan = randomNumber.nextInt(len);
            int tempPlace = place[i];
            //swap place[i] with place[temp]
            place[i] = place[tempRan];
            place[tempRan] = tempPlace;
        }
    }

    /** 长蛇 
     * @param startFirst first place of the formation(row)
     * @param startSecond first place of the formation(col)
     */
    static boolean setLineFormation(Creature[] creatureList,int startFirst,int startSecond,World.Direction faceDirection){
        int len = creatureList.length;
        boolean changed = false;
        if(len <= 0){
            return false;
        }
        else if(startFirst < 0 || startSecond < 0 || startFirst >= World.worldSize || startSecond >= World.worldSize){
            System.out.printf("Error:the range of position is 0~%d%n",World.worldSize-1);
        }
        //only consider left and right
        if(faceDirection == World.Direction.LEFT){
            if(startSecond + len - 1 >= World.worldSize){
                System.out.println("Error:the queue is too long");
            }
            else{
                for(int i = 0;i < len;++i){
                    if(creatureList[i].walkTo(startFirst, startSecond++) == true)
                        changed = true;
                }
            }
        }
        else if(faceDirection == World.Direction.RIGHT){
            if(startSecond - len + 1 < 0){
                System.out.println("Error:the queue is too long");
            }
            else{
                for(int i = 0;i < len;++i){
                    if(creatureList[i].walkTo(startFirst, startSecond--) == true)
                        changed = true;
                }
            }
        }
        return changed;
    }

    /**
     * 雁行
     * @param startFirst first place of the formation(row)
     * @param startSecond first place of the formation(col)
     */
    static boolean setWildGooseFormation(Creature[] creatureList,int startFirst,int startSecond,World.Direction faceDirection){
        int len = creatureList.length;
        boolean changed = false;
        if(len <= 0){
            return false;
        }
        else if(startFirst < 0 || startSecond < 0 || startFirst >= World.worldSize || startSecond >= World.worldSize){
            System.out.printf("Error:the range of position is 0~%d%n",World.worldSize-1);
        }
        if(World.Direction.LEFT == faceDirection){
            if(startFirst + len - 1 >= World.worldSize || startSecond + len - 1>= World.worldSize){
                System.out.println("Error:the queue is too long");
            }
            else{
                for(int i = 0;i < len;++i){
                    if(creatureList[i].walkTo(startFirst++, startSecond++) == true)
                        changed = true;
                }
            }
        }
        else if(World.Direction.RIGHT == faceDirection){
            if(startFirst - len + 1 < 0 || startSecond - len + 1 < 0){
                System.out.println("Error:the queue is too long");
            }
            else{
                for(int i = 0;i < len;++i){
                    if(creatureList[i].walkTo(startFirst--, startSecond--) == true)
                        changed = true;
                }
            }
        }
        return changed;
    }

    /** 
     * 働軛
     * @param startFirst first place of the formation(row)
     * @param startSecond first place of the formation(col)
     */
    static boolean setMovePreventFormation(Creature[] creatureList,int startFirst,int startSecond,World.Direction faceDirection){
        int len = creatureList.length;
        boolean changed = false;
        if(len <= 0){
            return false;
        }
        else if(startFirst < 0 || startSecond < 0 || startFirst >= World.worldSize || startSecond >= World.worldSize){
            System.out.printf("Error:the range of position is 0~%d%n",World.worldSize-1);
        }
        if(World.Direction.LEFT == faceDirection){
            if(startFirst + 1 >= World.worldSize || startSecond + len - 1 >= World.worldSize){
                System.out.println("Error:the queue is too long");
            }
            else{
                for(int i = 0,sign = 1;i < len;++i){
                    if(creatureList[i].walkTo(startFirst, startSecond++) == true)
                        changed = true;
                    startFirst += sign;
                    sign = -sign;
                }
            }
        }
        else if(World.Direction.RIGHT == faceDirection){
            if(startFirst - 1 < 0 || startSecond - len + 1 < 0){
                System.out.println("Error:the queue is too long");
            }
            else{
                for(int i = 0,sign = 1;i < len;++i){
                    if(creatureList[i].walkTo(startFirst, startSecond--) == true)
                        changed = true;
                    startFirst -= sign;
                    sign = -sign;
                }
            }
        }
        return changed;
    }

    /** 
     * 鹤翼
     * @param startFirst first place of the formation(row)
     * @param startSecond first place of the formation(col)
     */
    static boolean setWingsFormation(Creature[] creatureList,int startFirst,int startSecond,World.Direction faceDirection){
        int len = creatureList.length;
        boolean changed = false;
        if(len <= 0){
            return false;
        }
        else if(startFirst < 0 || startSecond < 0 || startFirst >= World.worldSize || startSecond >= World.worldSize){
            System.out.printf("Error:the range of position is 0~%d%n",World.worldSize-1);
        }
        //only consider left and right
        if(faceDirection == World.Direction.LEFT){
            if(startSecond - len/2 < 0 || startFirst+len/2 >= World.worldSize || startFirst - len/2 < 0){
                System.out.println("Error:the queue is too long");
            }
            else{
                for(int i = 0,sign = 1;i < len;++i){
                    if(sign == -1){
                        if(creatureList[i].walkTo(startFirst-(i+1)/2, startSecond-(i+1)/2)==true)
                            changed = true;
                    }
                    else{
                        if(creatureList[i].walkTo(startFirst+(i+1)/2, startSecond-(i+1)/2) == true)
                            changed = true;
                    }
                    sign = -sign;
                }
            }
        }
        else if(faceDirection == World.Direction.RIGHT){
            if(startSecond + len/2 >= World.worldSize || startFirst + len/2 >= World.worldSize || startFirst - len/2 < 0){
                System.out.println("Error:the queue is too long");
            }
            else{
                for(int i = 0,sign = 1;i < len;++i){
                    if(sign == -1){
                        if(creatureList[i].walkTo(startFirst-(i+1)/2, startSecond+(i+1)/2) == true)
                            changed = true;
                    }
                    else{
                        if(creatureList[i].walkTo(startFirst+(i+1)/2, startSecond+(i+1)/2) == true)
                            changed = true;
                    }
                    sign = -sign;
                }
            }
        }
        return changed;
    }
    public static void main(String[] args){
        Grandfather grandfather = new Grandfather();
        SnakeMonster snakeMonster = new SnakeMonster();
        ScorpionMonster scorpionMonster = new ScorpionMonster();

        grandfather.createCalabashBrothers();
        scorpionMonster.leadMonsters();
        World.showMap();

        grandfather.setLineFormation();
        scorpionMonster.setMovePreventFormation();
        grandfather.choosePlaceToSit();
        snakeMonster.choosePlaceToSit();
        World.showMap();
        scorpionMonster.setWildGooseFormation();
        grandfather.choosePlaceToSit();
        snakeMonster.choosePlaceToSit();
        World.showMap();
        scorpionMonster.setWingsFormation();
        grandfather.choosePlaceToSit();
        snakeMonster.choosePlaceToSit();
        World.showMap();
    }
}