package team;

import creature.*;
import exception.CharacterErrorException;
import exception.ZhenfaIDOutOfNumException;
import space.Space;
import zhenfa.ZhenfaBook;


public class VillainTeam extends Team {
    ZhenfaBook book=new ZhenfaBook();
    public VillainTeam(Space s) {
        super(s);
    }
    @Override
    public void zhenfa(int id) throws ZhenfaIDOutOfNumException, CharacterErrorException {
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
            XiaolouluoFactory f=new XiaolouluoFactory(space,this.size()-1);
            for(int i=this.size();i<n;i++)
            {
                Creature t=f.create();
                t.born();
                this.add(t);
            }
        }
        for(Creature i:this) {
            int[] res;
            if(i instanceof Shejing)
                res= book.search(-1,id,space.getN());
            else if(i instanceof Xiezijing)
                res=book.search(0,id,space.getN());
            else if(i instanceof Xiaolouluo)
                res=book.search(((Xiaolouluo) i).getid(),id,space.getN());
            else
                throw new CharacterErrorException();
            i.swap_to(res[0],res[1]);
        }
    }
}
