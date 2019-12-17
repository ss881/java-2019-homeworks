//爷爷类
class Grandpa extends Creature{
    private CalabashBrother[] calabashBrothers;
    Grandpa(String name,CalabashBrother[] calabashBrothers) {
        super(name,false);
        this.calabashBrothers=calabashBrothers;
    }
    //变换阵型
    void setCalabashBrothers(){
        Formation.changeFormation(GameMap.battleField,calabashBrothers,FormationKind.SNAKE);
    }
}

//葫芦娃类
class CalabashBrother extends Creature{
    CalabashBrother(String name){
        super(name,false);
    }
}
