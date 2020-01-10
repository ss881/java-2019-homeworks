import java.util.*;
import java.lang.reflect.*;

abstract class Formation<T extends Creature>{
    private T leader;//不同的队长可以拥有不同的阵型,这里没有什么区别
    protected ArrayList<Creature> members = new ArrayList<Creature>();
    int startFirst,startSecond;
    World.Direction faceDirection;

    void setLeader(T boss){
        leader = boss;
    }
    T getLeader(){
        return leader;
    }
    void addMember(Creature member){
        members.add(member);
    }
    void setDirection(World.Direction direction){
        faceDirection = direction;
    }
    boolean setStartPoint(int first,int second){
        if(first < 0 || second < 0 || first >= World.worldSize || second >= World.worldSize){
            System.out.printf("Error:the range of position is 0~%d%n",World.worldSize-1);
            return false;
        }
        this.startFirst = first;
        this.startSecond = second;
        return true;
    }
    abstract boolean setFormation();
}

/** 长蛇 */
class LineFormation<T extends Creature> extends Formation<T>{
    LineFormation(int first,int second,World.Direction direction){
        setStartPoint(first, second);
        setDirection(direction);
    }
    boolean setFormation(){/**@override */
        boolean changed = false;
        int first = startFirst,second = startSecond;
        //only consider left and right
        if(faceDirection == World.Direction.LEFT){
            if(second + members.size() >= World.worldSize){
                System.out.println("Error:the queue is too long");
            }
            else{
                getLeader().walkTo(first, second++);
                for(Creature one:members){
                    if(one.walkTo(first, second++) == true)
                        changed = true;
                }
            }
        }
        else if(faceDirection == World.Direction.RIGHT){
            if(second - members.size() < 0){
                System.out.println("Error:the queue is too long");
            }
            else{
                getLeader().walkTo(first, second--);
                for(Creature one:members){
                    if(one.walkTo(first, second--) == true)
                        changed = true;
                }
            }
        }
        return changed;
    }
}
 /** 雁行 */
class WildGooseFormation<T extends Creature> extends Formation<T>{
    WildGooseFormation(int first,int second,World.Direction direction){
        setStartPoint(first, second);
        setDirection(direction);
    }
    boolean setFormation(){
        boolean changed = false;
        int first = startFirst,second = startSecond;
        if(World.Direction.LEFT == faceDirection){
            if(first + members.size() >= World.worldSize || second + members.size()>= World.worldSize){
                System.out.println("Error:the queue is too long");
            }
            else{
                getLeader().walkTo(first++, second++);
                for(Creature one:members){
                    if(one.walkTo(first++, second++) == true)
                        changed = true;
                }
            }
        }
        else if(World.Direction.RIGHT == faceDirection){
            if(first - members.size() < 0 || second - members.size() < 0){
                System.out.println("Error:the queue is too long");
            }
            else{
                 getLeader().walkTo(first--, second--);
                for(Creature one:members){
                    if(one.walkTo(first--, second--) == true)
                        changed = true;
                }
            }
        }
        return changed;
    }
    
}
/** 働軛 */
class MovePreventFormation<T extends Creature> extends Formation<T>{
    MovePreventFormation(int first,int second,World.Direction direction){
        setStartPoint(first, second);
        setDirection(direction);
    }
    boolean setFormation(){
        boolean changed = false;
        int first = startFirst,second = startSecond;
        if(World.Direction.LEFT == faceDirection){
            if(first + 1 >= World.worldSize || second + members.size() >= World.worldSize){
                System.out.println("Error:the queue is too long");
            }
            else{
                getLeader().walkTo(first, second++);
                int sign = 1;
                for(Creature one:members){
                    first += sign;
                    sign = -sign;
                    if(one.walkTo(first, second++) == true)
                        changed = true;
                }
            }
        }
        else if(World.Direction.RIGHT == faceDirection){
            if(first - 1 < 0 || second - members.size() < 0){
                System.out.println("Error:the queue is too long");
            }
            else{
                getLeader().walkTo(first, second--);
                int sign = 1;
                for(Creature one:members){
                    first -= sign;
                    sign = -sign;
                    if(one.walkTo(first, second--) == true)
                        changed = true;
                }
            }
        }
        return changed;
    }
}
/** 鹤翼 */
class WingsFormation<T extends Creature> extends Formation<T>{
    WingsFormation(int first,int second,World.Direction direction){
        setStartPoint(first, second);
        setDirection(direction);
    }
    boolean setFormation(){
        boolean changed = false;
        int first = startFirst,second = startSecond;
        if(faceDirection == World.Direction.LEFT){
            if(second - (members.size()+1)/2 < 0 || first+(members.size()+1)/2 >= World.worldSize || first - (members.size()+1)/2 < 0){
                System.out.println("Error:the queue is too long");
            }
            else{
                getLeader().walkTo(first, second--);
                int sign = 1,count = 1;
                for(Creature one:members){
                    if(sign == -1){
                        if(one.walkTo(first+count++, second--)==true)
                            changed = true;
                    }
                    else{
                        if(one.walkTo(first-count, second) == true)
                            changed = true;
                    }
                    sign = -sign;
                }
            }
        }
        else if(faceDirection == World.Direction.RIGHT){
            if(second + (members.size()+1)/2 >= World.worldSize || first + (members.size()+1)/2 >= World.worldSize || first - (members.size()+1)/2 < 0){
                System.out.println("Error:the queue is too long");
            }
            else{
                getLeader().walkTo(first, second--);
                int sign = 1,count = 1;
                for(Creature one:members){
                    if(sign == -1){
                        if(one.walkTo(first-count++, second--) == true)
                            changed = true;
                    }
                    else{
                        if(one.walkTo(first+count, second) == true)
                            changed = true;
                    }
                    sign = -sign;
                }
            }
        }
        return changed;

    }
}
public class FightFormation{
    //static void randomizePlace(int[] place){/** randomize the given list */
    //    int len = place.length;
    //    Random randomNumber = new Random();
    //    for(int i = 0;i < len;++i){
    //        int tempRan = randomNumber.nextInt(len);
    //        int tempPlace = place[i];
    //        //swap place[i] with place[temp]
    //        place[i] = place[tempRan];
    //        place[tempRan] = tempPlace;
    //    }
    //}

    public static void main(String[] args){
        Grandfather grandfather = new Grandfather();
        CalabashBrother[] calabashBrotherList = new CalabashBrother[7];
        SnakeMonster snakeMonster = new SnakeMonster();
        ScorpionMonster scorpionMonster = new ScorpionMonster();

        //int[] tempPlace = {4,5,6,7,8,9,10};
        //FightFormation.randomizePlace(tempPlace);
        //for(int i = 0; i < 7; ++i){
        //    calabashBrotherList[i] = new CalabashBrother();
        //    calabashBrotherList[i].setPosition(tempPlace[i], 0);//stand in a line at random
        //}

        //init
        for(int i = 0;i < 7;++i){
            int wantedPlace = new Random().nextInt(7);
            calabashBrotherList[i] = new CalabashBrother();
            while(calabashBrotherList[i].setPosition(wantedPlace, 0) == false)
                wantedPlace = (wantedPlace+1)%7;
        }
        scorpionMonster.leadMonsters(6);
        World.showMap();

        //grandfather.setLineFormation(calabashBrotherList);
        //scorpionMonster.setMovePreventFormation();
        CalabashBrother.prepareFormation(calabashBrotherList,new LineFormation<CalabashBrother>(World.worldSize/2, World.worldSize/2-1,World.Direction.RIGHT));
        scorpionMonster.prepareFormation(new WildGooseFormation<ScorpionMonster>(0, World.worldSize/2, World.Direction.LEFT));
        CalabashBrother.startFormation();
        scorpionMonster.startFormation();
        grandfather.choosePlaceToSit();
        snakeMonster.choosePlaceToSit();
        World.showMap();
        
        scorpionMonster.prepareFormation(new WingsFormation<ScorpionMonster>(World.worldSize/2, World.worldSize-1, World.Direction.LEFT));
        scorpionMonster.startFormation();
        grandfather.choosePlaceToSit();
        snakeMonster.choosePlaceToSit();
        World.showMap();

        System.out.println("Reflect Test");
        try{
            Class test = Class.forName("LineFormation");
            Method[] methods = test.getDeclaredMethods();
            Field[] fields = Class.forName("Formation").getDeclaredFields();
            System.out.println("LineFormation methods:");
            for(Method one:methods){
                System.out.println(one.toString());
            }
            System.out.println("Formation fields:");
            for(Field one:fields){
                System.out.println(one.toString());
            }
        }
        catch(ClassNotFoundException e){
            System.out.println(e);
        }
        //scorpionMonster.setWildGooseFormation();
        //grandfather.choosePlaceToSit();
        //snakeMonster.choosePlaceToSit();
        //World.showMap();
        //scorpionMonster.setWingsFormation();
        //grandfather.choosePlaceToSit();
        //snakeMonster.choosePlaceToSit();
        //World.showMap();
    }
}