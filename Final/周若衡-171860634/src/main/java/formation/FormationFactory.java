package formation;


public class FormationFactory{
    public static Formation  create(int i,int arg1,int arg2,int type) {
        i=i%8;
        switch (i) {
            case 0:return new FormationYanYi(arg1,arg2,type);
            case 1:return new FormationYanXing(arg1,arg2,type);
            case 2:return new FormationHengChe(arg1,arg2,type);
            case 3:return new FormationChangShe(arg1,arg2,type);
            case 4:return new FormationYuLin(arg1,arg2,type);
            case 5:return new FormationFangMen(arg1,arg2,type);
            case 6:return new FormationYanYue(arg1,arg2,type);
            case 7:return new FormationFengShi(arg1,arg2,type);
            default:
                break;
        }
        return null;
    }
}

