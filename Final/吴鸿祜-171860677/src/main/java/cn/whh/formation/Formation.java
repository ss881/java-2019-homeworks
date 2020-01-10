package cn.whh.formation;

import cn.whh.creature.Creature;
import cn.whh.creature.Land;

public class Formation {
    final public int width=13;
    final public int height=10;
    protected Creature[][] creatures;

    public Formation()
    {
        creatures=new Creature[height][width];
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                creatures[i][j]=new Land();

            }
        }
    }

    public Creature[][] getCreatures()
    {
        return creatures;
    }
}
