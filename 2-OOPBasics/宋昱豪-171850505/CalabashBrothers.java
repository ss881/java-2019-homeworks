
public class CalabashBrothers {
    int Number;
    Color MyColor;
    double position;
    double Height;
    void GetMyColor()
    {
        switch(MyColor)
        {
            case RED:System.out.println("红色"); break;
            case ORANGE:System.out.println("橙色");break;
            case YELLOW:System.out.println("黄色");break;
            case GREEN:System.out.println("绿色");break;
            case CYAN:System.out.println("青色");break;
            case BLUE:System.out.println("蓝色");break;
            case PURPLE:System.out.println("紫色");break;
            default:System.out.println("妖怪放了我爷爷");break;
        }
    }
    void getWho()
    {
        switch(Number)
        {
            case 1:System.out.println("老大"); break;
            case 2:System.out.println("老二");break;
            case 3:System.out.println("老三");break;
            case 4:System.out.println("老四");break;
            case 5:System.out.println("老五");break;
            case 6:System.out.println("老六");break;
            case 7:System.out.println("老七");break;
            default:System.out.println("妖怪放了我爷爷");break;
        }
    }
    void ChangePosition(double dest)
    {
        double distance=Math.abs(dest-position);
        int Step=0;
        while(distance>(Height/2))
        {
            distance=distance-Height/2;
            Step++;
        }
        if(distance>0)
        {
            Step++;
        }
        switch(Number)
        {
            case 1:{
                System.out.println("老大:"+position+"->"+dest+"走了"+Step+"步");
            } break;
            case 2:{
                System.out.println("老二:"+position+"->"+dest+"走了"+Step+"步");
            }break;
            case 3:{
                System.out.println("老三:"+position+"->"+dest+"走了"+Step+"步");
            }break;
            case 4:{
                System.out.println("老四:"+position+"->"+dest+"走了"+Step+"步");
            }break;
            case 5:{
                System.out.println("老五:"+position+"->"+dest+"走了"+Step+"步");
            }break;
            case 6:{
                System.out.println("老六:"+position+"->"+dest+"走了"+Step+"步");
            }break;
            case 7:{
                System.out.println("老七:"+position+"->"+dest+"走了"+Step+"步");
            }break;
            default:System.out.println("妖怪放了我爷爷");break;
        }
        position=dest;
    }
    public CalabashBrothers(int Who,double ToPosition,Color x)
    {
        Number=Who;
        position=ToPosition;
        Height=1.8;
        MyColor=x;
    }

}
