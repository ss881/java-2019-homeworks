package command;

import space.Space;

public class AttackCommand extends Command {
    int x,y,dx,dy;
    public float damage,defence;
    String src_name,dest_name;
    public AttackCommand(Space s, int src_x, int src_y, int dest_x, int dest_y,float damage,String src_name,String dest_name)
    {
        super(s);
        x=src_x;
        y=src_y;
        dx=dest_x;
        dy=dest_y;
        this.damage=damage;
        this.src_name=src_name;
        this.dest_name=dest_name;
        defence=-1;
        receiver.lock();
    }

    @Override
    public void execute() {
//        System.out.println(this);
        if(defence==-1)
            defence=receiver.attack(x,y,dx,dy,damage,src_name,dest_name);
        else
            receiver.attack(x,y,dx,dy,damage,defence,src_name,dest_name);
        receiver.add_log(this);
        receiver.unlock();
    }
    @Override
    public String toString()
    {
        return "Attack "+src_name+"->>"+dest_name;
    }
}
