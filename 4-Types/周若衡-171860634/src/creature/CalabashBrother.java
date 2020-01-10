package creature;

import java.util.Comparator;

public class CalabashBrother extends GoodCreature{
    enum Color{
        红,橙,黄,绿,青,蓝,紫
    }
    enum Seniority {
        大娃,二娃,三娃,四娃,五娃,六娃,七娃
    }

    private Color color;
    private Seniority seniority;

    public CalabashBrother(){
        super();
    }
    public CalabashBrother(int rank) {
        super();
        seniority=Seniority.values()[rank];
        color=Color.values()[rank];
    }
    public CalabashBrother(int rank,int x,int y) {
        super(x,y);
        seniority=Seniority.values()[rank];
        color=Color.values()[rank];
    }
    public Seniority getSeniority(){
        return this.seniority;
    }
    @Override
    public String toString(){
        return this.seniority.toString();
    }
}

class CalabashBrotherComparator implements Comparator<CalabashBrother>{
    @Override
    public int compare(CalabashBrother bro1,CalabashBrother bro2){
        return bro1.getSeniority().ordinal()-bro2.getSeniority().ordinal();
    }
}


