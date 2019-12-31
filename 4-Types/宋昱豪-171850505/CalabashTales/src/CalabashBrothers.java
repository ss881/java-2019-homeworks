public class CalabashBrothers extends Lives
{
    private color myColor;
    private int myNumber;
    // position myPosition;
    public void getMyColor()
    {
        switch(myColor)
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
    public void getMyNumber()
    {
        switch(myNumber)
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
    public CalabashBrothers(int Who,color how,Position where)
    {
        moving=false;
        myNumber=Who;
        myPosition=where;
        myColor=how;
    }
}
