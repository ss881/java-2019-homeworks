package command;

import space.Space;

public class LeaveCommand extends Command {
    int x,y;
    String name;
    public LeaveCommand(Space s, int src_x, int src_y,String name){
        super(s);
        x=src_x;
        y=src_y;
        this.name=name;
        receiver.lock();

    }
    @Override
    public void execute() {
//        System.out.println(this);
        receiver.leave(x,y,name);
        receiver.add_log(this);
        receiver.unlock();
    }
    @Override
    public String toString()
    {
        return "Leave "+name;
    }
}
