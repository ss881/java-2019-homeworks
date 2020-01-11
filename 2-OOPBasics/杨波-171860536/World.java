class World{
    /** size of the world, 1*7 */
    final static int worldSize = 7;
    /** direction */
    enum Direction{FORWARD,BACKWARD,STILL}
    /** topography, only ground now */
    enum Topography{GROUND};
    /** the map */
    static Topography map[] = new Topography[worldSize];
    /** creature map, map of creatures */
    static Creature creatureMap[] = new Creature[worldSize];
    /** init world */
    World(){
        //init topograpghy and creature map
        for(int i = 0;i < worldSize;++i){
                map[i] = Topography.GROUND;
                creatureMap[i] = null;
        }
    }
    /** 
     * this metohd should be called by a creature;
     * accept request to set one creature's position,if success return true,else return false;
     * the range of first and second has been checked before;
     * this method is uesd in the beginning, so don't need to care creature's old position.
     */
    static boolean setCreaturePosition(Creature one,int place){
        if(creatureMap[place] == null){
            //int oldPositionFirst = one.position[0];
            //int oldPositionSecond = one.position[1];
            creatureMap[place] = one;
            //one.getTo(place);
            one.position = place;
            return true;
        }
        else{
            System.out.printf("Error:this palce(%d) already stands a creature%n",place);
            return false;
        }
    }

    /** similar to {@code setCreaturePosition}, but this method will change original position */
    static boolean changeCreaturePosition(Creature one,int place){
        if(creatureMap[place] == null){
            int oldPosition = one.position;
            creatureMap[oldPosition] = null;
            creatureMap[place] = one;
            //one.position = place;
            one.getTo(place);
            //showMap();
            return true;
        }
        else{
            System.out.printf("Error:this palce(%d) already stands a creature%n",place);
            return false;
        }
    }
    /** exchange two creatures' position */
    static boolean exchangePlace(Creature one,Creature two){
        if(one.position - two.position == 1 || one.position - two.position == -1){
            creatureMap[one.position] = two;
            creatureMap[two.position] = one;
            int temp = one.position;
            one.getTo(two.position);
            two.getTo(temp);
            //one.position = two.position;
            //two.position = temp;
            return true;
        }
        else{
            System.out.println("Error:can't exchange because they are not adjoined");
            return false;
        }     
    }

    /** check who is in front of the given place */
    static Creature whoIsInfront(int place){
        if(place - 1 < 0 || place - 1 >= worldSize){
            System.out.println("Error:wrong range of place");
            return null;
        }
        return creatureMap[place-1];
    }

    static void showMap(int mode){
        for(int i = 0;i < worldSize;++i){
            if(creatureMap[i] != null)
                creatureMap[i].showOne(mode);
        }
    }

    static void bubbleSort(){
        //worldSize-1 rounds
        for(int i = 1; i <= worldSize-1; ++i){
            if(creatureMap[worldSize-1] != null)
                creatureMap[worldSize-1].toBubbleSort();
        }
    }

    static void binaryInsertSort(){
        for(int i = 1,left = 0,right = 0,mid = 0;i < World.worldSize;++i){
            left = 0;
            right = i-1;
            while(left < right){
                mid = (left+right)/2;
                if(creatureMap[i].compareTo(creatureMap[mid]) < 0){
                    right = mid;
                }
                else{
                    left = mid+1;
                }
            }
            //in last, left == right
            if(creatureMap[left].compareTo(creatureMap[i]) < 0)
                creatureMap[i].walkTo(left+1);
            else    
                creatureMap[i].walkTo(left);
            
        }
    }
    /*
    static void showMap(){
        for(int i = 0;i < World.worldSize;++i){
            System.out.printf("==");
        }
        System.out.println("");
        for(int i = 0;i < worldSize;++i){
            if(creatureMap[i] == null){
                //switch(map[i][j]){
                //    case GROUND:
                        System.out.printf(" ");
                //}
            
            }
            else
                creatureMap[i].showOne();
            System.out.print(' ');
        }
    }
    */
}