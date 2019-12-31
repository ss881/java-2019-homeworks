package HLW.Formation;

import HLW.Basic.*;


/* The YanXing formation is begin from the top left corner and the center is like it of ChangShe Formation */
public class HLWFormationYanXing extends HLWFormation{

    public  HLWFormationYanXing(String name, HLWWorld world, int[] center, int size){
        super(world, center, name, size);
        int radius;

        /* check boundary conditions */
        if(this.size % 2 == 0){
            radius = (this.size - 2) / 2;
            if(this.center[1] - radius < 0 || this.center[2] - radius < 0 ||
                    this.center[2]  + 1 + radius > world.getBoundary(2) - 1 ||
                    this.center[1] + 1 + radius > world.getBoundary(1) - 1){
                HLWLog.HLW_ERROR(String.format("The %s formation has been out of the world boundary.\n", this.name));
                System.exit(-1);
            }
        }else{
            radius = (this.size - 1) / 2;
            if(this.center[1] - radius < 0 || this.center[2] - radius < 0 ||
                    this.center[2]  + radius > world.getBoundary(2) - 1 ||
                    this.center[1]  + radius > world.getBoundary(1) - 1){
                HLWLog.HLW_ERROR(String.format("The %s formation has been out of the world boundary.\n", this.name));
                System.exit(-1);
            }
        }
    }

    @Override
    protected void findNextPositionInFormation(int[] loc){
        loc[1] += 1;
        loc[2] += 1;
    }

    @Override
    protected int[] getBeginPosition() {
        int[]   loc = new int[3];
        int radius = 0;
        this.world.copyLocation(this.center, loc);
        if(this.size%2 == 0){
            radius = (this.size - 2) / 2;
        }else{
            radius = (this.size - 1) / 2;
        }
        loc[2] -= radius;
        loc[1] -= radius;
        return loc;
    }
}
