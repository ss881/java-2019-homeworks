package command;

import space.Space;

import java.io.Serializable;

public abstract class Command implements Serializable {
    protected transient Space receiver;
    public Command(Space receiver)
    {
        this.receiver=receiver;
    }
    public void set_receiver(Space r){receiver=r;}
    abstract public void execute();
    public String to_String(){
        return "Command";
    }
}
