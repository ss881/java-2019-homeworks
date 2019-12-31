package HLW.Formation;

import HLW.Basic.*;

import java.util.Random;

public class HLWFormationChangShe extends HLWFormation {
    /* All HLWCreatures in the ChangSheFormation has the same y coordinate value*/

    public HLWFormationChangShe(String name, HLWWorld world, int[] center, int size){
        super(world, center, name, size);
        int radius;

        /* check boundary conditions */
        radius = this.size/2;
        if(this.size % 2 == 0){
            if(this.center[1] - (radius-1) < 0 || this.center[1] + radius > this.world.getBoundary(1) - 1){
                HLWLog.HLW_ERROR(String.format("The %s formation has been out of the world boundary.\n", this.name));
                System.exit(-1);
            }
        }else{
            if(this.center[1] - radius < 0 || this.center[1] + radius > this.world.getBoundary(1) - 1){
                HLWLog.HLW_ERROR(String.format("The %s formation has been out of the world boundary.\n", this.name));
                System.exit(-1);
            }
        }
    }

    /* Put the beginning position into the parameter array "loc" */
    protected int[] getBeginPosition(){
        int[]   loc = new int[3];
        int radius = this.size/2;
        this.world.copyLocation(this.center, loc);
        /* if the length of the formation is even, then the center is the half of the y coordinate*/
        if(this.size %2 == 0){
            loc[1] -= (radius - 1);
        }else{
            loc[1] -= radius;
        }
        return loc;
    }

    /* Get the member of the Formation from the beginning according to the index.
     * The index is begin from 0
     */
    protected HLWCreature getMemberFromIndex(int index){
        if(index < 0 || index > size - 1){
            HLWLog.HLW_ERROR("The index is beyond the boundary.");
            return null;
        }
        int[]   loc = getBeginPosition();


        world.moveCoordinate(loc, 1, index);
        return world.getCreature(loc);
    }

    @Override
    protected void findNextPositionInFormation(int[] loc) {
        loc[1] += 1;
    }

    /* bubble sort */
    public void bubbleSort(){
        HLWCreature[]   targets = new HLWCreature[1];
        int[]   loc = null;
        int[]   tmp = new int[3];

        loc = getBeginPosition();
        for(int i = 1; i < this.size; i++){
            for(int j = 0; j < this.size - i ; j++){
                if(getMemberFromIndex(j).isLarge(getMemberFromIndex(j+1))){
                    targets[0] = getMemberFromIndex(j+1);
                    getMemberFromIndex(j).prepareMessage("switch", null, targets);
                    getMemberFromIndex(j).sendOneMessage();
                    getMemberFromIndex(j+1).handleOneMessage();
                }
            }
        }

    }



    /* binary sort */
    public void binarySortByColor(){
        int low, high, mid;
        HLWCreature[] target = new HLWCreature[1];
        int[]   vector = new int[3];

        for(int i = 1; i < this.size ; i++){
            low = 0;
            high = i - 1;
            while(low <= high){
                mid = low + (high - low) / 2;
                if(getMemberFromIndex(i).isLarge(getMemberFromIndex(mid))){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }
            vector[0] = 0;
            vector[1] = 1;
            vector[2] = 0;
            for(int j = i-1; j >= low; j--) {
                target[0] = getMemberFromIndex(j);
                getMemberFromIndex(j).prepareMessage("move", vector, target);
                getMemberFromIndex(j).sendOneMessage();
                getMemberFromIndex(j).handleOneMessage();
            }
            vector = getBeginPosition();
            world.moveCoordinate(vector, 1, low);
            target[0] = getMemberFromIndex(i);
            getMemberFromIndex(i).prepareMessage("move to", vector, target);
            getMemberFromIndex(i).sendOneMessage();
            getMemberFromIndex(i).handleOneMessage();
        }
    }

    /* report by HuLuWa seniority */
    public void reportHuLuWaBySeniority(){
        HLWLog.HLW_NOTICE("葫芦兄弟用辈分来从头到尾报数：");
        for(int i = 0; i < size; i++){
            getMemberFromIndex(i).printName();
            System.out.print("  ");
        }
        System.out.print("\n");
    }

    /* report by HuLuWa color */
    public void reportHuLuWaByColor(){
        HLWLog.HLW_NOTICE("葫芦兄弟用代表色来从头到尾报数：");
        for(int i = 0; i < size; i++){
            getMemberFromIndex(i).printAlias();
            System.out.print("  ");
        }
        System.out.print("\n");
    }


}
