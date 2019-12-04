import myorganism.*;
public class Formation {
    public static void main(String[] arg) {
        BattleMap map=new BattleMap(20);//选择地图尺寸
        map.initmap();//初始化地图
        map.createMonsters(31);//选择敌军数量
        map.crescentMoon();//偃月
        map.printMap();//打印地图
        map.craneWing();//鹤翼
        map.printMap();
        map.wildGoose();//雁行
        map.printMap();
        map.chongE();//冲轭
        map.printMap();
        map.fishScale();//鱼鳞
        map.printMap();
        map.HouEn();//方円
        map.printMap();
        map.sharpArrow();//锋矢
        map.printMap();
    }

}
