package command;

import space.Space;

public class MoveCommand extends Command {
    int x,y,dx,dy;
    public MoveCommand(Space s, int src_x, int src_y, int dest_x, int dest_y)
    {
        super(s);
        x=src_x;
        y=src_y;
        dx=dest_x;
        dy=dest_y;
    }
    @Override
    public void execute() {
        receiver.move(x,y,dx,dy);
    }
}
