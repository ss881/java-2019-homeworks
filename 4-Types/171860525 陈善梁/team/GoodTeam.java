package team;

import creature.*;

public class GoodTeam extends Team<Huluwa,GrandPa>{

    public GoodTeam(){
        leader=new GrandPa();
        members=leader.initialize();
    }

    @Override
    public void changeForm() {
        leader.changeForm(members);
    }

    @Override
    public Huluwa[] getMembers() {
        return members;
    }

    @Override
    public GrandPa getLeader() {
        return leader;
    }
}
