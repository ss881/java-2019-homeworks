package team;

import creature.Creature;
import exception.CharacterErrorException;
import exception.HuluwaOutOfNumException;
import exception.ZhenfaIDOutOfNumException;
import space.Space;

import java.util.ArrayList;

public abstract class Team extends ArrayList<Creature> {
    Space space;
    public Team(Space s)
    {
        space=s;
    }
    public abstract void zhenfa(int id) throws HuluwaOutOfNumException, ZhenfaIDOutOfNumException, CharacterErrorException;
    public void born(){
        for(Creature i:this)
            i.born();
    }
}
