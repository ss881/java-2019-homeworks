public class GoodTeam {
    private GrandPa grandPa;
    private Huluwa[]huluwas;

    public GoodTeam(){
        grandPa=new GrandPa();
        huluwas=grandPa.initialize();
    }

    public void sortHuluwa(){
        grandPa.sortHuluwa(huluwas);
    }

    public Huluwa[]getHuluwas(){
        return huluwas;
    }

    public GrandPa getGrandPa(){
        return grandPa;
    }
}
