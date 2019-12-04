public class Confrontation {
    public static void main(String[] args) {
        initFight();
    }
    public static void initFight()
    {
        System.out.println("----------------------------------------------");
        for (int i = 1; i < 9; i++) {
            Map.initMap();
            CalabashBrothers cbs = new CalabashBrothers();
            BadGuys bgs = new BadGuys(11);
            Grandpa gp = new Grandpa();
            SerpentDemon sd = new SerpentDemon();

            Formations.setCharacter(cbs.getCalabashBrothers(), 4, 7, 10, 4, true);
            Formations.setCharacter(bgs.getBadGuys(), i, bgs.getNum(), 10, 11, false);
            Map.setMap(gp, 10, 0);
            Map.setMap(sd, 10, 19);

            Map.printMap();
            //System.out.println();
            System.out.println("----------------------------------------------");
        }
    }
}
