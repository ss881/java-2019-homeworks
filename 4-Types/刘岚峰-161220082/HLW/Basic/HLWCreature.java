package HLW.Basic;

import HLW.Creature.HLWXieZiJing;

import java.util.ArrayDeque;

public abstract class HLWCreature<T> {
    /* the ordinates of the creature */
    protected int x;
    protected int y;
    protected int z;
    /* The world which this creature belongs to */
    protected HLWWorld world;
    /* The formation which this creature belongs to
    * note that the world of the formation must be same as the creature's */
    protected HLWFormation formation;

    /* name of the creature */
    protected String name;

    /* queue used to store received messages*/
    protected ArrayDeque<HLWMessage> RcvMessagesQueue;

    protected ArrayDeque<HLWMessage> SendMessagesQueue;

    public abstract int getSeniority();

    public abstract void printName();

    public abstract void printAlias();

    public abstract String getName();

    /** brief: if this HLWCreature is larger(the seniority is defined by specific subclass than obj
     * return true, else return false. */
    public boolean isLarge(HLWCreature obj){
        if(this.getClass() != obj.getClass()){
            HLWLog.HLW_ERROR("Two different creature cannot be compared together.");
            return false;
        }
        return this.getSeniority() > obj.getSeniority();
    }

    /** brief:
     * constructor function of HLW.Basic.HLWCreature
     * param
     * name: the name of the HLW.Basic.HLWCreature, and it must be assigned while creating the HLW.Basic.HLWCreature objects */
    public HLWCreature(String name, HLWWorld world){
        int[]   loc = null;
        this.name = name;
        this.world = world;
        loc = world.getRandomEmptyLocation(2);
        x = loc[2];
        y = loc[1];
        z = 0;
        this.world.setLocation(this);
        /* TD: considering some handling processes when the initial location is occupied by others.*/
        RcvMessagesQueue = new ArrayDeque<HLWMessage>();
        SendMessagesQueue = new ArrayDeque<HLWMessage>();
        /* at the beginning this creature belongs to no formation */
        this.formation = null;
    }

    /* brief:
     * constructor function of HLW.Basic.HLWCreature
     * param
     * name: the name of the HLW.Basic.HLWCreature, and it must be assigned while creating the HLW.Basic.HLWCreature objects
     * x: the x ordinate of the HLUCreature
     * y: the y ordinate of the HLUCreature
     * z: the z ordinate of the HLUCreature
     */
    public HLWCreature(String name, HLWWorld world, int x, int y, int z){
        this.name = name;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        /* TD: considering some handling processes when the initial location is occupied by others.*/
        this.world.setLocation(this);
        RcvMessagesQueue = new ArrayDeque<HLWMessage>();
        SendMessagesQueue = new ArrayDeque<HLWMessage>();
        /* at the beginning this creature belongs to no formation */
        this.formation = null;
    }

    /* brief:
     * constructor function of HLW.Basic.HLWCreature
     * param
     * the meaning of parameters of this function is same with the last one in the above
     */
    public HLWCreature(String name, HLWWorld world, int x){
        this.name = name;
        this.world = world;
        this.z = 0;
        this.y = 0;
        this.x = x;
        /* TD: considering some handling processes when the initial location is occupied by others.*/

        this.world.setLocation(this);
        RcvMessagesQueue = new ArrayDeque<HLWMessage>();
        SendMessagesQueue = new ArrayDeque<HLWMessage>();
        /* at the beginning this creature belongs to no formation */
        this.formation = null;
    }

    /** brief:
     * constructor function of HLW.Basic.HLWCreature
     * param
     * the meaning of parameters of this function is same with the last one in the above */
    public HLWCreature(String name, HLWWorld world, int x, int y){
        this.z = 0;
        this.name = name;
        this.x = x;
        this.y = y;
        /* TD: considering some handling processes when the initial location is occupied by others.*/
        this.world = world;
        this.world.setLocation(this);
        RcvMessagesQueue = new ArrayDeque<HLWMessage>();
        SendMessagesQueue = new ArrayDeque<HLWMessage>();
        /* at the beginning this creature belongs to no formation */
        this.formation = null;
    }


    /** brief:
     * get the location of the creature, using a array to present the ordinate,
     * the first number of the array is x, second is y and the rest one is z */
    public int[] getLocation(){
        int[] rc = new int[3];
        rc[0] = z;
        rc[1] = y;
        rc[2] = x;
        return rc;
    }

    public HLWFormation getFormation(){
        return this.formation;
    }

    /* Using when print log of the world map. Get the representative string of the creature */
    public abstract String getClassAndName();

    private void setLocation(int[] newLoc){
        this.z = newLoc[0];
        this.y = newLoc[1];
        this.x = newLoc[2];
        this.world.setLocation(this);
    }

    /** brief:
     * prepare the message sending to others, the message is determined by HLWEvent(which was the main info passed by
     * HLWThread
     * params:
     * messageType: there are three type of message: "move"("m"), "move to" and switch("s")
     * location: if the messageType is "move" then it will be the moving vector;
     * if the ,messageType is "move to", then it will be the location of the destination;
     * if the messageType is "switch" then the location will be empty since the targets will give the location
     * targets: giving the target of the "switch" message.*/
    public boolean prepareMessage(String messageType, int[] location, HLWCreature[] targets){
        switch(messageType){
            case "move":
                if(location == null){
                    HLWLog.HLW_ERROR("The location(moving vector) can't be null for move!");
                    System.exit(-1);
                    return false;
                }
                this.SendMessagesQueue.addLast(new HLWMessage(messageType, this, targets, location));
                return true;
            case "move to":
                if(location == null){
                    HLWLog.HLW_ERROR("The location can't be null for move to!");
                    System.exit(-1);
                    return false;
                }
                this.SendMessagesQueue.addLast(new HLWMessage(messageType, this, targets, location));
                return true;
            case "switch":
                if(targets == null){
                    HLWLog.HLW_ERROR("The targets can't be null for switch!");
                    System.exit(-1);
                    return false;
                }
                if(targets.length != 1){
                    HLWLog.HLW_ERROR("There can't be multiple targets for switch!");
                    System.exit(-1);
                    return false;
                }
                this.SendMessagesQueue.addLast(new HLWMessage(messageType, this, targets, location));
                return true;
            default:
                HLWLog.HLW_ERROR("Wrong message type of " + messageType);
                System.exit(-1);
                return false;
        }
    }

    private void receiveMessage(HLWMessage msg){
        this.RcvMessagesQueue.addLast(msg);
    }

    /* brief:
     * send the message to other creature(s).
     * Get one message from the message queue to send. If there is no message in the queue, then inform the user that
     * there is no message in the queue.
     */
    public void sendOneMessage(){
        if(this.SendMessagesQueue.isEmpty()){
            HLWLog.HLW_NOTICE("There is no message in the queue to be sent.");
        }

        HLWMessage info = this.SendMessagesQueue.getFirst();
        HLWCreature[] tgts;

        this.SendMessagesQueue.removeFirst();
        tgts = info.getTargets();
        for(HLWCreature c: tgts){
            c.receiveMessage(info);
        }
    }

    private boolean _moveTo(int[] destination){
        int[] originLocation = this.getLocation();
        boolean rc = true;

        /* if this HLW.Basic.HLWCreature will move to its origin location */
        for(int i=0; i < 3; i++){
            if(originLocation[i] != destination[i]){
                rc = false;
                break;
            }
        }
        if(rc){
            return true;
        }
        /* if the location has been occupied*/
        if(!this.world.checkLocation(destination)){
            HLWLog.HLW_ERROR(String.format("The location (z:%d, y:%d, x:%d) has been occupied.", destination[0], destination[1],
                destination[2]));
            System.exit(-1);
        }
        this.world.removeCreature(this.getLocation());
        this.setLocation(destination);
        this.world.printMovement(this, originLocation, destination);
        return true;
    }

    private void moveUpward(){
        this.world.removeCreature(this.getLocation());
        this.z++;
        this.world.setLocation(this);
    }

    /** A switch action is divided into these three steps:
     * 1. The target, or to say the handler HLW.Basic.HLWCreature will first move upward or downward. Since we only consider
     * a 2-D space, we can move the target upward for 1 unit length
     * 2. The source, the other HLW.Basic.HLWCreature in the process, will then move to the target HLW.Basic.HLWCreature's location
     * 3. The target HLW.Basic.HLWCreature will finally move into the other HLW.Basic.HLWCreature's original location
     * param:
     * source: The sender of the message, the HLW.Basic.HLWCreature who request a switch, and mention that the target of the
     * switch is always "this" HLW.Basic.HLWCreature itself who handles the switch message*/
    private boolean _switch(HLWCreature source){
        int[] tgtOriginalLocation = this.getLocation();
        int[] srcOriginalLocation = source.getLocation();

        this.moveUpward();
        source._moveTo(tgtOriginalLocation);
        this._moveTo(srcOriginalLocation);
        return true;
    }

    private boolean _move(int[] vector){
        int[] dst = new int[3];

        world.copyLocation(getLocation(), dst);
        for(int i = 0; i < 3; i++){
            dst[i] += vector[i];
        }
        /* The destination has been occupied */
        if(!this.world.checkLocation(dst)){
            HLWLog.HLW_ERROR(" The destination has been occupied by other HLWCreatures!");
            System.exit(-1);
            return false;
        }
        this.world.removeCreature(this.getLocation());
        this.z += vector[0];
        this.y += vector[1];
        this.x += vector[2];
        this.world.setLocation(this);
        this.world.printMovement(this, this.getLocation(), dst);

        return true;
    }

    /** brief: This function will handle one message in the queue, then the HLW.Basic.HLWCreature will act according to the
     * message type and the information contained in the message. Besides, after finishing handling the message,
     * the HLW.Basic.HLWCreature will remove the handled message from the queue */
    public void handleOneMessage(){
        HLWMessage msg = RcvMessagesQueue.getFirst();
        if(msg == null){
            HLWLog.HLW_NOTICE("There is no message to process.");
            return;
        }
        boolean rc = false;

        switch (msg.getType()){
            case "move":
                rc = _move(msg.getCoordinate());
            case "move to":
                rc = _moveTo(msg.getCoordinate());
                break;
            case "switch":
                rc = _switch(msg.getSource());
                break;
            default:
                HLWLog.HLW_ERROR("Wrong message type of" + msg.getType());
                System.exit(-1);
        }

        if(!rc){
            /* TD: In the future, there can be some call back function here */
            HLWLog.HLW_ERROR("One message are failed to handle!");
            System.exit(-1);
        }
        else{
            RcvMessagesQueue.removeFirst();
        }
    }

    /** brief: Get the world which this creature belongs to, used by the HLWFormation class */
    HLWWorld getWorld(){
        return this.world;
    }
    /** brief: Set the formation of the HLW.Basic.HLWCreature.
     * params:
     * f: the formation assigned to the HLW.Basic.HLWCreature */
    public void setFormation(HLWFormation f){
        if(f.getWorld() != this.world){
            System.err.println("The formation and the member creature must be in the same HLW.Basic.HLWWorld!");
            System.exit(-1);
        }
        this.formation = f;
    }

}
