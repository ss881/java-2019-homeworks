package zhenfa;

import exception.ZhenfaIDOutOfNumException;

public class HeroZhenfaBook extends ZhenfaBook {
    @Override
    public int[] search(int character_id, int zhenfa_id, int size) throws ZhenfaIDOutOfNumException {
        int[] res=super.search(character_id,zhenfa_id,size);
        res[0]=size-1-res[0];
        return res;
    }
}
