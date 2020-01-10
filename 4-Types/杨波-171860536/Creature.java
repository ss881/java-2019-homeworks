import java.util.ArrayList;
import java.util.Random;

abstract class Creature{
    enum CreatureType{HUMAN,CALABASH,MONSTER,NOTHING};/** identity, calabashbrother or monster */
	//这个枚举类型可以考虑用泛型取代
    CreatureType identity = CreatureType.NOTHING;
    int position[] = new int[2]; /** position in the map of world */
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
    boolean setPosition(int first, int second){
        boolean res = false;
        if(first >= 0 && first < World.worldSize && second >= 0 && second < World.worldSize){
            if(res = World.setCreaturePosition(this, first, second)){
                this.inMap = true;//after setposition,"this" is in map
            }
        }
        else{
            System.out.printf("Error:position range is 0~%d%n",World.worldSize-1);
        }
        return res;
    }
   
    abstract void showOne();/** according to identity, print something */
    
    
    boolean walkTo(int first, int second){/** walk to a certain place */
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
    
    void swap(int first,int second,World.Direction direction){/** swap */
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
    //void setLineFormation(CalabashBrother[] calabashBrotherList){
    //    boolean changed;
    //    changed = FightFormation.setLineFormation(calabashBrotherList, World.worldSize/2, World.worldSize/2-1 , World.Direction.RIGHT);
    //    while(changed){
    //        changed = FightFormation.setLineFormation(calabashBrotherList, World.worldSize/2, World.worldSize/2-1 , World.Direction.RIGHT);
    //    }
    //}
    /** override @{code showOne} */
    void showOne(){
        System.out.print('F');
    }
}

class CalabashBrother extends Creature{
    static String[] statusName = {"老大","老二","老三","老四","老五","老六","老七"};
    static String[] ownColor = {"赤","橙","黄","绿","青","蓝","紫"};

    private int idInCalabash;/** id for statusName and color */
    static int newId = 0;/** set idInCalabash according to creating time */

    static Formation<CalabashBrother> formation = null;

    CalabashBrother(){
        identity = CreatureType.CALABASH;
        //speed = 10;
        idInCalabash = newId++;
    }
    static boolean prepareFormation(CalabashBrother[] list,Formation<CalabashBrother> oneFormation){
        try{
            formation = oneFormation;
            formation.setLeader(list[0]);
            for(int i = 1;i < list.length;++i){
                formation.addMember(list[i]);
            }
            return true;
        }
        catch(Throwable e){
            System.err.println(e);
        }
        return false;
    }
    static void startFormation(){
        try {
            while(formation.setFormation());
        } catch (Throwable e) {//formation may be null
            System.err.println(e);
        }
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
    //static final int numOfSmallMonster = 6;
    //private Monster[] monsterList = new Monster[numOfSmallMonster+1];
    private ArrayList<Monster> monsterList = new ArrayList<Monster>();
    Formation<ScorpionMonster> formation = null;
    ScorpionMonster(){
        monsterIdentity = MonsterType.SCORPION;
    }
    /** 
     * @param num :num of monsters to be leaded
     */
    void leadMonsters(int num){
        setPosition(0, World.worldSize-1);//own place
        monsterList.clear();
        for(int i = 1;i <= num;++i){
            Monster one = new Monster();
            one.setPosition(i%World.worldSize,World.worldSize-1-i/World.worldSize);//这里是认为位置都能正常设置
            monsterList.add(one);
        }
    }
    boolean prepareFormation(Formation<ScorpionMonster> oneFormation){
        try{
            this.formation = oneFormation;
            formation.setLeader(this);
            for(Monster one:monsterList){
                formation.addMember(one);
            }
            return true;
        }
        catch(Throwable e){
            System.err.println(e);
        }
        return false;
    }
    void startFormation(){
        try{
            while(formation.setFormation());
        }
        catch(Throwable e){
            System.err.println(e);
        }
    }
    /*
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
    */
    /** override {@code showOne} */
    void showOne(){
        System.out.print('P');
    }
}