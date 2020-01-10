package command;

import space.Space;

public class WaitCommand extends Command {
    long time;
    public WaitCommand(Space receiver,long time) {
        super(receiver);
        this.time=time;
    }

    @Override
    public void execute() {
        try {
            Thread.sleep(time);
        }catch (InterruptedException e)
        {

        }
    }
}
