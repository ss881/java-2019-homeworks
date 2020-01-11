package team;

import creature.*;
import exception.CharacterErrorException;
import exception.HuluwaOutOfNumException;
import exception.ZhenfaIDOutOfNumException;
import space.Space;
import zhenfa.HeroZhenfaBook;


public class HeroTeam extends Team {
    HeroZhenfaBook book=new HeroZhenfaBook();
    public HeroTeam(Space s) {
        super(s);
    }

    @Override
    public void zhenfa(int id) throws ZhenfaIDOutOfNumException, CharacterErrorException, HuluwaOutOfNumException {
        int n=num_map[id];
        if(this.size()>n)
        {
            for(int i=this.size()-1;i>=n;i--)
            {
                this.get(i).leave();
                this.remove(i);
            }
        }else
        {
            HuluwaFactory ff=new HuluwaFactory(space,this.size());
            for (int i = this.size(); i <8 ; i++) {
                Creature t=ff.create();
                t.born();
                this.add(t);
            }
            ChuanshanjiaFactory f=new ChuanshanjiaFactory(space,this.size()-1);
            for(int i=this.size();i<n;i++)
            {
                Creature t=f.create();
                t.born();
                this.add(t);
            }
        }
        for(Creature i:this) {
            int[] res;
            if(i instanceof Laoyeye)
                res= book.search(-1,id,space.getN());
            else if(i instanceof Huluwa)
                res=book.search(((Huluwa) i).getid()-1,id,space.getN());
            else if(i instanceof Chuanshanjia)
                res=book.search(((Chuanshanjia)i).getid(),id,space.getN());
            else
                throw new CharacterErrorException();
            i.swap_to(res[0],res[1]);
        }
    }
}
