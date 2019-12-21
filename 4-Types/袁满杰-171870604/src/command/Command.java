package command;

import space.Space;

public abstract class Command {
    protected Space receiver;
    public Command(Space receiver)
    {
        this.receiver=receiver;
    }
    abstract public void execute();
}
