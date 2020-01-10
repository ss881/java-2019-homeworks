package command;

import space.Space;

public class MoveCommand extends Command {
    String name;
    int x,y,dx,dy;
    boolean force=false;
    public MoveCommand(Space s, int src_x, int src_y, int dest_x, int dest_y,String name)
    {
        super(s);
        x=src_x;
        y=src_y;
        dx=dest_x;
        dy=dest_y;
        receiver.lock();
        this.name=name;
    }
    public MoveCommand(Space s, int src_x, int src_y, int dest_x, int dest_y,String name,boolean force)
    {
        super(s);
        x=src_x;
        y=src_y;
        dx=dest_x;
        dy=dest_y;
        receiver.lock();
        this.name=name;
        this.force=force;
    }
    @Override
    public void execute() {
//        System.out.println(this);
        boolean res= receiver.move(x,y,dx,dy,name,force);
        if(res)
            receiver.add_log(this);
        receiver.unlock();
    }
    @Override
    public String toString()
    {
        return "Move "+name+"("+x+","+y+")->("+dx+","+dy+")";
    }
}
