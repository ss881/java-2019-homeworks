abstract class Creature{
    /** position in the map of world */
    int position = 0;
    /** after a creature has been {@code setPosition()}, it's in map */
    boolean inMap = false; 
    ///** move speed */
    //int speed = 0;
    //not for now
    World.Direction moveDirection = World.Direction.STILL;

    /** set position in creatureMap, if succeed set "inMap" = true */
    void setPosition(int place){
        if(place >= 0 && place <World.worldSize){
            World.setCreaturePosition(this, place);
            inMap = true;
        }
        else{
            System.out.printf("Error:position range is 0~%d%n",World.worldSize-1);
        }
    }

    /** walk to some place
     * @return if move,return true;else return false
     */
    boolean walkTo(int place){
        boolean retVal = false;
        if(place < 0 || place >= World.worldSize){
            System.out.printf("Error:position range is 0~%d%n",World.worldSize-1);
            return false;
        }

        World.Direction direction;
        if(position > place)
            direction = World.Direction.FORWARD;
        else
            direction = World.Direction.BACKWARD;
        
        while(position != place){
            if(direction == World.Direction.FORWARD){
                if(World.creatureMap[position-1] == null)
                    World.changeCreaturePosition(this, place);
                else
                    swap(position,direction);
                retVal = true;
            }
            else{
                if(World.creatureMap[position+1] == null)
                    World.changeCreaturePosition(this, place);
                else
                    swap(position,direction);
                retVal = true;
            }
        }
        return retVal;
    }

    void swap(int place,World.Direction direction){
        if(direction == World.Direction.FORWARD){
            if(place - 1 < 0){
                System.out.println("Error:forward is nothing");
                return;
            }
            World.exchangePlace(this, World.creatureMap[place-1]);
        }
        else{
            if(place + 1 >= World.worldSize){
                System.out.println("Error:backward is nothing");
                return;
            }
            World.exchangePlace(this, World.creatureMap[place+1]);
        }
    }

    /** after send request to World, if ok, World will call {@code getTo()} to change one's place */
    void getTo(int place){
        if(place < 0 || place >= World.worldSize){
            System.out.printf("Error:position range is 0~%d%n",World.worldSize-1);
            return;
        }
        position = place;
    }

    Creature lookForward(){
        if(position - 1 >= 0){
            return World.whoIsInfront(position);
        }
        else//already in first place
            return null;
    }

    abstract int compareTo(Creature one);
    /** show one accroding to the mode */
    abstract void showOne(int mode);
    abstract void toBubbleSort();
}

class CalabashBrother extends Creature{
    static String[] statusName = {"老大","老二","老三","老四","老五","老六","老七"};
    static String[] colors = {"赤","橙","黄","绿","青","蓝","紫"};

    int idInCalabashBrothers;
    static int newId = 0;

    CalabashBrother(){
        idInCalabashBrothers = newId++;
    }
    /** override, after {@code getTO()}, show the process */
    void getTo(int place){
        if(place < 0 || place >= World.worldSize){
            System.out.printf("Error:position range is 0~%d%n",World.worldSize-1);
            return;
        }
        System.out.println(statusName[idInCalabashBrothers]+":"+position+"->"+place);
        position = place;
    }

    

    void showName(){
        System.out.println(statusName[idInCalabashBrothers]);
    }
    void showColor(){
        System.out.println(colors[idInCalabashBrothers]);
    }

    CalabashBrother lookForward(){
        if(position - 1 >= 0){
            Creature theOne = World.whoIsInfront(position);
            if(theOne instanceof CalabashBrother)
                return (CalabashBrother) theOne;
            else
                return null;
        }
        else
            return null;
    }

    void toBubbleSort(){
        CalabashBrother theOneInfront = lookForward();
        while(theOneInfront != null && idInCalabashBrothers < theOneInfront.idInCalabashBrothers){
            swap(position, World.Direction.FORWARD);
            theOneInfront = lookForward();
        }   
        if(theOneInfront != null)//inform the one in front to bubble sort
            theOneInfront.toBubbleSort();
    }

    void showOne(int mode){
        if(mode == 1)
            showName();
        else if(mode == 2)
            showColor();
    }

    int compareTo(Creature one){
        CalabashBrother theOne;
        if(one instanceof CalabashBrother){
            theOne = (CalabashBrother)one;
            if(idInCalabashBrothers < theOne.idInCalabashBrothers)
                return -1;
            else if(idInCalabashBrothers > theOne.idInCalabashBrothers)
                return 1;
            else
                return 0;
        }
        return 0;
    }
}