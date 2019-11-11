class World{
    final static int worldSize = 15;/** size of the world, 15*15 */
    enum Direction{STILL,LEFT,RIGHT,UP,DOWN};/** move direction */
    enum Topography{GROUND};/** topography, only ground now */
    static Topography map[][] = new Topography[worldSize][worldSize];/** the map */
    static Creature creatueMap[][] = new Creature[worldSize][worldSize];/** creature map, map of creatures */
    
    World(){/** init world */
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
            //System.out.printf("Error:this palce(%d,%d) already stands a creature%n",first,second);
            //以后考虑用throw
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
