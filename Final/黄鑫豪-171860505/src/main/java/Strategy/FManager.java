package Strategy;

import Field.TwoDSpace;

public class FManager {
    private TwoDSpace tds;
    private Formation f;

    public int[] getEvilX() {
        return f.evilX;
    }

    public int[] getEvilY() {
        return f.evilY;
    }

    public int[] getGourdX() {
        return f.gourdX;
    }

    public int[] getGourdY() {
        return f.gourdY;
    }

    public int getgpax(){
        return f.gpax;
    }

    public int getgpay(){
        return f.gpay;
    }

    public int getemx(){
        return f.emx;
    }

    public int getemy(){
        return f.emy;
    }

    public int getefx(){
        return f.efx;
    }

    public int getefy(){
        return f.efy;
    }


    public FManager(TwoDSpace tds){
        this.tds = tds;
        f = new Formation();
    }

    public void apply(){
        f.applyGourd(tds);
        f.applyEvil(tds);
    }
}
