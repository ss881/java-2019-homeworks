package team;

import creature.*;
import exception.CharacterErrorException;
import exception.ZhenfaIDOutOfNumException;
import space.Space;
import zhenfa.HeroZhenfaBook;
import zhenfa.ZhenfaBook;


public class HeroTeam extends Team {
    HeroZhenfaBook book=new HeroZhenfaBook();
    public HeroTeam(Space s) {
        super(s);
    }

    @Override
    public void zhenfa(int id) throws ZhenfaIDOutOfNumException, CharacterErrorException {
        for(Creature i:this) {
            int[] res;
            if(i instanceof Laoyeye)
                res= book.search(-1,id,space.getN());
            else if(i instanceof Huluwa)
                res=book.search(((Huluwa) i).getId(),id,space.getN());
            else
                throw new CharacterErrorException();
            i.move_to(res[0],res[1]);
        }
    }
}
