import java.util.Random;

import sun.security.pkcs11.wrapper.CK_AES_CTR_PARAMS;

public class Tile{
    public CalabashBoy calabashBoy;
    public int pX;
    public boolean isTaken;
    
    Tile(){
        pX = 0;
        calabashBoy = new CalabashBoy();
        isTaken = false;
    }

    public void setTile(int pX){
        this.pX = pX;
    }

}