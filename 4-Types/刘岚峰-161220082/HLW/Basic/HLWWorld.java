package HLW.Basic;

import HLW.Creature.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.lang.System.exit;

public class HLWWorld {
    /* the name of the HLW.Basic.HLWWorld */
    private String name;
    /* The map of the world */
    private HLWCreature[][][] worldMap;
    /* The list of all HuLuWas and their grandpa */
    private ArrayList<HLWCreature>  HuLuWaList;
    /* The list of all Minions ,SheJing and XieZiJing */
    private ArrayList<HLWCreature>  MonsterList;
    /* Formations' list, used to sign each formation
    protected ArrayList<HLWFormation> formationList;*/
    /* Number of the max creature in the World */
    private int maxCreature;

    /* brief:construction function of HLW.Basic.HLWWorld, the default size of HLW.Basic.HLWWorld will be
     * 30 in x, 30 in y, and 2 in z
     * and note that the 3-dimension array from left to right correspond to z, y, x
     */
    public HLWWorld(String name, int maxCreature){
        this.name = name;
        /* all position should be null at the beginning */
        worldMap = new HLWCreature[2][30][30];
        this.maxCreature = maxCreature;
        HuLuWaList = new ArrayList<HLWCreature>();
        MonsterList = new ArrayList<HLWCreature>();
    }
    public HLWWorld(String name, int xSize, int maxCreature){
        this.name = name;
        worldMap = new HLWCreature[2][30][xSize];
        this.maxCreature = maxCreature;
        HuLuWaList = new ArrayList<HLWCreature>();
        MonsterList = new ArrayList<HLWCreature>();
    }
    public HLWWorld(String name, int xSize, int ySize, int maxCreature){
        this.name = name;
        worldMap = new HLWCreature[2][ySize][xSize];
        this.maxCreature = maxCreature;
        HuLuWaList = new ArrayList<HLWCreature>();
        MonsterList = new ArrayList<HLWCreature>();
    }
    public HLWWorld(String name, int xSize, int ySize, int zSize, int maxCreature){
        /* Unlikely to use this function */
        this.name = name;
        worldMap = new HLWCreature[zSize][ySize][xSize];
        this.maxCreature = maxCreature;
        HuLuWaList = new ArrayList<HLWCreature>();
        MonsterList = new ArrayList<HLWCreature>();
    }

    public void addHuLuWa(HLWCreature c){
        if(c.getClass() != HLWHuLuWa.class && c.getClass() != HLWYeYe.class){
            HLWLog.HLW_ERROR("HuLuWa add into wrong list!");
            System.exit(-1);
        }
        this.HuLuWaList.add(c);
    }

    public void addMonster(HLWCreature c){
        if(c.getClass() != HLWMinion.class && c.getClass() != HLWXieZiJing.class &&
            c.getClass() != HLWSheJing.class){
            HLWLog.HLW_ERROR("Monster add into wrong list!");
            System.exit(-1);
        }
        this.MonsterList.add(c);
    }

    public int[] getRandomEmptyLocation(int dimension){
        int[]   rc = new int[3];
        Random rd = new Random();
        Arrays.fill(rc, 0);
        while(!this.checkLocation(rc)){
            switch (dimension){
                case 1:
                    rc[2] = rd.nextInt(this.worldMap[0][0].length);
                    break;
                case 2:
                    rc[1] = rd.nextInt(this.worldMap[0].length);
                    rc[2] = rd.nextInt(this.worldMap[0][0].length);
                    break;
                default:
                    HLWLog.HLW_ERROR("The dimension must be less than 3 and more larger than 0.");
                    exit(-1);
            }
        }
        return rc;
    }



    public static String getStringLocation(int[] loc){
        if(loc.length != 3){
            System.err.println("The given location must be 3-dimension!");
            exit(-1);
        }
        return "(x:" + loc[2] + ", y:" + loc[1] + ", z:" + loc[0] + ")";
    }

    /* brief: print the movement of HLW.Basic.HLWCreature */
    public void printMovement(HLWCreature crt, int[] src, int[] dst){
        System.out.println(crt.getName() + ": " + getStringLocation(src) + " -> " + getStringLocation(dst));
    }

    /* brief: check the given location's state, if it is occupied then return false, else return true */
    public boolean checkLocation(int[] lct){
        if(lct == null) {
            HLWLog.HLW_ERROR("The location is null!");
            exit(-1);
        }
        if(lct.length != 3){
            HLWLog.HLW_ERROR("The given location must be 3-dimension!");
            exit(-1);
        }
        return worldMap[lct[0]][lct[1]][lct[2]] == null;
    }

    /** brief: Set the location in the world map of this given HLW.Basic.HLWCreature. The location information is
     * contained in the HLW.Basic.HLWCreature */
    public void setLocation(HLWCreature crt){
        int[] lct = crt.getLocation();
        if(!checkLocation(lct)){
            HLWLog.HLW_ERROR("The given location of the HLWCreature has been occupied!");
            exit(-1);
        }
        worldMap[lct[0]][lct[1]][lct[2]] = crt;
    }

    public HLWCreature getCreature(int[] location){
        HLWCreature retCreature = worldMap[location[0]][location[1]][location[2]];
        if(retCreature == null){
            HLWLog.HLW_ERROR("The given location has no HLW.Basic.HLWCreature!");
            exit(-1);
            return null;
        }
        return retCreature;
    }

    public HLWCreature removeCreature(int[] location){
        HLWCreature retCreature = getCreature(location);
        worldMap[location[0]][location[1]][location[2]] = null;
        return retCreature;
    }



    /* Get the next location of the assigned axis. '0' means 'z' axis; '1' means 'y' axis; and '2' means 'z' axis.
     * the 'step' means how many unit length it will go .*/
    public boolean moveCoordinate(int[] location, int axis, int step){
        if(axis > 2 || axis < 0){
            HLWLog.HLW_ERROR("There is no axis other than x,y and z");
            return false;
        }
        if(location[axis] + step > getBoundary(axis) || location[axis] + step < 0){
            HLWLog.HLW_ERROR("Next destination is out of boundary.");
            return false;
        }else{
            location[axis] += step;
            return true;
        }
    }

    public void copyLocation(int[] src, int[] dst){
        if(src.length != 3 || dst.length != 3){
            HLWLog.HLW_ERROR("The number of dimensions of location is not three!");
            exit(-1);
        }
        System.arraycopy(src, 0, dst, 0, 3);
    }

    /* Get the boundary of the assigned axis */
    public int getBoundary(int axis){
        switch (axis){
            case 0:
                return worldMap.length;
            case 1:
                return worldMap[0].length;
            case 2:
                return worldMap[0][0].length;
            default:
                HLWLog.HLW_ERROR("There is no axis other than x,y and z");
                return -1;
        }
    }

    public boolean checkPointInBoundary(int[] point){
        if(point.length != 3){
            HLWLog.HLW_ERROR("The dimension of a point must be 3.");
            return false;
        }
        if(point[0] >= getBoundary(0) || point[1] >= getBoundary(1) || point[2] >= getBoundary(2)){
            return false;
        }
        return true;
    }

    public void printWorld(){
        for(int i = 0; i < worldMap[0].length; i++){
            for(int j = 0; j < worldMap[0][0].length; j++){
                if(worldMap[0][i][j] == null){
                    System.out.print(" ");
                }else {
                    System.out.print(worldMap[0][i][j].getClassAndName());
                }
            }
            System.out.print("\n");
        }
    }
}
