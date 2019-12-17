
final class SnakeEssence extends Creature {
    // 按照目前的剧情，没蝎子精什么事
    SnakeEssence(){
        name="Ss";
        setPosition(new XPoint2D(7,0));
    }
    public void sayComeOn(){
        System.out.println("（画外音）"+name+": 我是蛇精，我在给我的蝎子精加油");
    }
}