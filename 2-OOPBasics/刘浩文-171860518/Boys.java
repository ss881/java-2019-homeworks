public class Boys {
    int rank;
    int color;
    int curPlace;
    Boys(int givenRank){rank=givenRank;color=rank;}
    void setRank(int recvRank){rank=recvRank;}
    int getRank(){return rank;}
    void setColor(int recvColor){color=recvColor;}
    int getColor(){return color;}
    void setCurPlace(int recvPlace){curPlace=recvPlace;}
    int getCurPlace(){return curPlace;}
    void emitPlaceChange(int targ)//发出交换位置的请求
    {
        switch (rank)
        {
            case 1:System.out.println("老大:"+curPlace); break;
            case 2:System.out.println("老二:"+curPlace); break;
            case 3:System.out.println("老三:"+curPlace); break;
            case 4:System.out.println("老四:"+curPlace); break;
            case 5:System.out.println("老五:"+curPlace); break;
            case 6:System.out.println("老六:"+curPlace); break;
            case 7:System.out.println("老七:"+curPlace); break;
            default: break;
        }
        curPlace=targ;
    }
    void recvPlaceChange(int targ)//接收位置交换的请求
    {
        System.out.println("->"+curPlace+'\n');
        curPlace=targ;
    }
    void speakRank()
    {
        switch (rank)
        {
            case 1:System.out.println("老大"); break;
            case 2:System.out.println("老二"); break;
            case 3:System.out.println("老三"); break;
            case 4:System.out.println("老四"); break;
            case 5:System.out.println("老五"); break;
            case 6:System.out.println("老六"); break;
            case 7:System.out.println("老七"); break;
            default: break;
        }
        System.out.println('\n');
    }
    void speakColor()
    {
        switch (color)
        {
            case 1:System.out.println("红色"); break;
            case 2:System.out.println("橙色"); break;
            case 3:System.out.println("黄色"); break;
            case 4:System.out.println("绿色"); break;
            case 5:System.out.println("青色"); break;
            case 6:System.out.println("蓝色"); break;
            case 7:System.out.println("紫色"); break;
            default: break;
        }
        System.out.println('\n');
    }
}
