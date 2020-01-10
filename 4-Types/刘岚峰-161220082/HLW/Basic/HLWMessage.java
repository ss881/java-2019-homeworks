package HLW.Basic;

import java.util.HashSet;

public class HLWMessage {
    /* There are only three types for now: "move" , "move to" and "switch".
    * Note that when use "move" and "move to", the target is the sender itself
    * 1. "move" means a HLW.Basic.HLWCreature moves a distance represented by a array, which will need a vector as a parameters.
    * 2. "move to" means a HLW.Basic.HLWCreature moves to a destination which is a coordinate represented by the parameters
    * 3. "switch" involves to HLWCreatures and one is who set up the "switch" and another is the one who reacts to
    * the "switch" */

    private String type;

    private HLWCreature src;

    private HLWCreature[] tgts;

    /* In different context the coordinate has different means.
     * IF the type is "move" then the coordinate is a moving vector;
     * if the type is "move to" then the coordinate is a destination coordinate;
     * if the type is "switch" then the coordinate should be null. */
    private int[] coordinate;

    /* All message types */
    private final static HashSet<String> HLWMessageType = new HashSet<String>(){
        {
            add("move");
            add("move to");
            add("switch");
        }
    };

    /** brief: This function will judge if the given type is contained in the HLWMessageType set */
    private static boolean ifMessageType(String type){
        return HLWMessageType.contains(type);
    }


    /* In different context the parameters has different means.
     * The "type" means the type of the message, all kinds of type are listed above; the "src" means the sender of the
     * message; the "tgts" means the receiver of the message, which can contain many receivers or non; the "crd" means
     * the target coordinate of the message.
     * IF the type is "move" then the 'crd' is a moving vector, 'tgts' is sender itself;
     * if the type is "move to" then the coordinate is a destination coordinate, 'tgts' is sender itself too;
     * if the type is "switch" then the coordinate should be null, the 'tgts' must only be one target HLWCreature.
     */
    public HLWMessage(String type, HLWCreature src, HLWCreature[] tgts, int[] crd){
        if(crd != null && crd.length != 3){
            System.err.println("The length of location array must be 3!");
            System.exit(-1);
        }
        this.type = type;
        this.src = src;
        this.tgts = tgts;
        if(crd == null){
            this.coordinate = null;
        }else {
            coordinate = new int[3];
            this.src.getWorld().copyLocation(crd, coordinate);
        }
    }


    public HLWCreature[] getTargets(){
        return this.tgts;
    }

    public HLWCreature getSource(){
        return this.src;
    }

    public int[] getCoordinate(){
        return this.coordinate;
    }

    public String getType() {
        return type;
    }
}
