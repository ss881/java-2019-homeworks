/*
请用面向对象编程方法，以Java语言编写程序，实现以下过程:

假设存在一个NxN的二维空间（N>10)，该空间中的任意一个位置坐标上可站立一个生物体（葫芦娃、老爷爷、蛇精、蝎子精、小喽啰均属于生物体）；
请让初始乱序的七个兄弟按下图所示阵型中的长蛇形依序（老大至老七）站队；
请在图中选择一个阵型（长蛇除外）让蝎子精领若干小喽啰站队；
将葫芦兄弟的长蛇阵营和蝎子精小喽啰阵营放置于二位空间中，形成对峙局面；
请选择合适位置将老爷爷和蛇精放置于空间中，为各自一方加油助威；
将上述对峙局面打印输出；
请让蝎子精小喽啰阵营变换一个阵法（长蛇除外），重复4-6步。

*/
public  class Main{
    //the concrete explain to main represents in readme.md
    public static void main(String[] args) {
        final int size_of_current_space=20;
        SpaceForBattle space=new SpaceForBattle(size_of_current_space);
        MonsterScorpion<MonsterSaltFish> monsterScorpion=new MonsterScorpion<>(MonsterSaltFish.class);
        MonsterSnake monsterSnake=new MonsterSnake();
        Grandpa grandpa=new Grandpa();
        monsterScorpion.summon_monstersaltfish();// in present circumstances, we suppose that monsterscorpion summon 15 saltfish
        grandpa.plant_standl();
        monsterScorpion.measure_space_for_battle(space);
        monsterScorpion.decide_direction_of_attack(Direction.WEST);
        monsterScorpion.study_military_for_monster();
        grandpa.measure_space_for_battle(space);
        grandpa.decide_direction_of_attack(Direction.EAST);
        grandpa.study_military_for_standl();
        grandpa.decide_start_coordinate(1,3);
        grandpa.choose_stand_type(StandType.SnakeShape);
        monsterScorpion.decide_self_coordinate(space, 8, 6);
        monsterScorpion.choose_stand_type(StandType.MoonShape);
        grandpa.embattle_for_battle(space);
        monsterScorpion.embattle_for_battle(space);
        grandpa.choose_stand_place(space);
        monsterSnake.choose_stand_place(space);
        grandpa.cheer_for_battle();
        monsterSnake.cheer_for_battle();
        space.overlook_space();
        monsterSnake.leave_for_some_time(space);
        monsterScorpion.decide_self_coordinate(space, 11, 2);
        monsterScorpion.choose_stand_type(StandType.YokeShape);
        monsterScorpion.change_type_for_battle(space);
        monsterSnake.choose_stand_place(space);
        monsterSnake.cheer_for_battle();
        grandpa.cheer_for_battle();
        space.overlook_space();
        space.rebulid_space();

    }
}