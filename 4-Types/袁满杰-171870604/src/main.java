import creature.*;
import exception.CharacterErrorException;
import exception.HuluwaOutOfNumException;
import exception.ZhenfaIDOutOfNumException;
import space.Space;

import java.util.Random;

public class main {
    public static void main(String[] args) throws HuluwaOutOfNumException, ZhenfaIDOutOfNumException, CharacterErrorException {
        Random r=new Random();
        Space chessboard=new Space(19);

        chessboard.heros.zhenfa(0);

        int r1=r.nextInt(6)+1;
        chessboard.villains.zhenfa(r1);

        chessboard.show();
        int r2=r1;
        while((r2=r.nextInt(6)+1)==r1);
        chessboard.villains.zhenfa(r2);

        chessboard.show();
    }
}
