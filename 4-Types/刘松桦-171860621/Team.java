import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class Team {
    int num;
    Map map;
    Formation formation;
    List<GameObject> members;
    abstract void changeFormation();
}

class GourdTeam extends Team {
    enum GOURDS {
        老大, 老二, 老三, 老四, 老五, 老六, 老七
    }

    GourdTeam(int centerX, int centerY, Map map) {
        num = 8;
        this.map = map;
        members = new ArrayList<GameObject>();
        for (int i = 0; i < 7; i++) {
            members.add(new GameObject(GOURDS.values()[i].toString(), (char) ((int) '1' + i), map));
        }
        Collections.shuffle(members);
        members.add(new GameObject("老爷爷", 'e', map));
        formation=new LongFormation(map,members,centerX,centerY);
        formation.putFormation();
    }

    @Override
    void changeFormation() {
        formation.regulation();
    }
}

class MonsterTeam extends Team{
    MonsterTeam(int num,int centerX, int centerY, Map map){
        this.num=num%2==1?num+1:num;
        this.map=map;
        members=new ArrayList<GameObject>();
        members.add(new GameObject("蝎子精",'x',centerX,centerY,map));
        for(int i=1;i<=this.num-2;i++){
            members.add(new GameObject("小喽啰",'o',map));
        }
        members.add(new GameObject("蛇精",'s',centerX,centerY+2,map));
        formation=new ReuillyFormation(map,members,centerX,centerY);
        formation.putFormation();
    }
    @Override
    void changeFormation() {
        formation.regulation();
    }
}


