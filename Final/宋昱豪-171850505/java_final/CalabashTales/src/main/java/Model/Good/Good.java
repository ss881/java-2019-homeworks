package Model.Good;

import Model.World.Attributes;
import Model.World.Group;
import Model.World.Position;
import Model.World.Tile;

public class Good extends Group {
    Grandpa leader;
    public Good(Tile y[][],int num)
    {
        super();
        formnum=num;
        Position x=new Position(5,2);
        Attributes A=new Attributes(100,100,20,2,Attributes.livingStatus.live,Attributes.Group.good);
        A.SetURL("pic/GrandPa.png");
        leader=new Grandpa(x,A);
        members.add(leader);
        CalabashBrothers CB[]=new CalabashBrothers[7];
        leader.plantCalabash(CB,7);
        for(int i=0;i<7;i++)
        {
            members.add(CB[i]);
        }
        leader.StartCommand(7,CB);
        switch (formnum)
        {
            case 0:leader.T1();break;
            case 1:leader.T2();break;
            case 2:leader.T3();break;
            case 3:leader.T4();break;
            case 4:leader.T5();break;
            case 5:leader.T6();break;
            case 6:leader.T7();break;
            case 7:leader.T8();break;
            default:leader.T1();break;
        }

    }

}
