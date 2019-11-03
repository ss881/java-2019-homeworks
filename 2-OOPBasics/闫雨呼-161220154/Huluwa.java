//葫芦娃
class Huluwa {
    private Name name;
    private Color color;
    Huluwa(Color color,Name name){
        this.color=color;
        this.name=name;
    }
    //葫芦娃位置移动
    void runTo(Tile[] tiles,int src,int dst){
        count(CountKind.NAME);
        System.out.println(":"+(src+1)+"->"+(dst+1));
        tiles[dst].huluwa=this;
    }
    //葫芦娃达到空闲的中间位置，这个位置不属于葫芦娃队列中的位置
    void runToTempPosition(Tile tempPosition,int src){
        count(CountKind.NAME);
        System.out.println(":"+(src+1)+"->temp");
        tempPosition.huluwa=this;
    }
    //葫芦娃从空闲的中间位置归位
    void returnFromTemp(Tile[] tiles,int dst){
        count(CountKind.NAME);
        System.out.println(":temp->"+(dst+1));
        tiles[dst].huluwa=this;
    }
    //两个葫芦娃进行交流，决定是否变换位置
    boolean decideToChange(Huluwa h){
        return name.compareTo(h.name) > 0;
    }
    //葫芦娃进行报数
    void count(CountKind kind){
        if(kind==CountKind.NAME){
            switch (name){
                case ONE:System.out.print("大娃");break;
                case TWO:System.out.print("二娃");break;
                case THREE:System.out.print("三娃");break;
                case FOUR:System.out.print("四娃");break;
                case FIVE:System.out.print("五娃");break;
                case SIX:System.out.print("六娃");break;
                case SEVEN:System.out.print("七娃");break;
            }
        }
        else{
            switch (color){
                case RED:System.out.print("红色");break;
                case ORANGE:System.out.print("橙色");break;
                case YELLOW:System.out.print("黄色");break;
                case GREEN:System.out.print("绿色");break;
                case CYAN:System.out.print("青色");break;
                case BLUE:System.out.print("蓝色");break;
                case PUPPLE:System.out.print("紫色");break;
            }
        }
    }
}
