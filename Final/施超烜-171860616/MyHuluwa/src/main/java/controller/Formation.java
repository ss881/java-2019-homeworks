package controller;

import creature.CalabashBrother;
import creature.Wannabe;
import creature.ScorpionEssence;

enum FormationName {
    HEYI("鹤翼阵"),YANXING("雁行阵"),CHONGE("衝軛阵"),
    CHANGSHE("长蛇阵"), YULIN("鱼鳞阵"), FANG("方円阵"),
    YANYUE("偃月阵"), FENGSHI("锋矢阵");
    private String name;
    FormationName(String name) {
        this.name = name;
    }
}

public class Formation {

    public void changeMonsterFormation(int index, ScorpionEssence scorpion, Wannabe[] wannabes, MyMap map) {
        switch (index) {
            case 0: {
                map.moveCreatureTo(scorpion, 4, 10);
                map.moveCreatureTo(wannabes[0], 5, 11);
                map.moveCreatureTo(wannabes[1], 6, 12);
                map.moveCreatureTo(wannabes[2], 7, 13);
                map.moveCreatureTo(wannabes[3], 7, 14);
                map.moveCreatureTo(wannabes[4], 6, 15);
                map.moveCreatureTo(wannabes[5], 5, 16);
                map.moveCreatureTo(wannabes[6], 4, 17);
                break;
            }
            case 1: {
                map.moveCreatureTo(scorpion,2,17);
                map.moveCreatureTo(wannabes[0],3,16);
                map.moveCreatureTo(wannabes[1],4,15);
                map.moveCreatureTo(wannabes[2],5,14);
                map.moveCreatureTo(wannabes[3],6,13);
                map.moveCreatureTo(wannabes[4],7,12);
                map.moveCreatureTo(wannabes[5],8,11);
                map.moveCreatureTo(wannabes[6],9,10);
                break;
            }
            case 2: {
                map.moveCreatureTo(scorpion,1,12);
                map.moveCreatureTo(wannabes[0],2,13);
                map.moveCreatureTo(wannabes[1],3,12);
                map.moveCreatureTo(wannabes[2],4,13);
                map.moveCreatureTo(wannabes[3],5,12);
                map.moveCreatureTo(wannabes[4],6,13);
                map.moveCreatureTo(wannabes[5],7,12);
                map.moveCreatureTo(wannabes[6],8,13);
                break;
            }
            case 3: {
                map.moveCreatureTo(scorpion,1,12);
                map.moveCreatureTo(wannabes[0],2,12);
                map.moveCreatureTo(wannabes[1],3,12);
                map.moveCreatureTo(wannabes[2],4,12);
                map.moveCreatureTo(wannabes[3],5,12);
                map.moveCreatureTo(wannabes[4],6,12);
                map.moveCreatureTo(wannabes[5],7,12);
                map.moveCreatureTo(wannabes[6],8,12);
                break;
            }
            case 4: {
                map.moveCreatureTo(scorpion,4,13);
                map.moveCreatureTo(wannabes[0],8,13);
                map.moveCreatureTo(wannabes[1],5,14);
                map.moveCreatureTo(wannabes[2],6,11);
                map.moveCreatureTo(wannabes[3],6,13);
                map.moveCreatureTo(wannabes[4],6,15);
                map.moveCreatureTo(wannabes[5],7,12);
                map.moveCreatureTo(wannabes[6],7,14);
                break;
            }
            case 5: {
                map.moveCreatureTo(scorpion,3,13);
                map.moveCreatureTo(wannabes[0],4,12);
                map.moveCreatureTo(wannabes[1],4,14);
                map.moveCreatureTo(wannabes[2],5,11);
                map.moveCreatureTo(wannabes[3],5,15);
                map.moveCreatureTo(wannabes[4],6,12);
                map.moveCreatureTo(wannabes[5],6,14);
                map.moveCreatureTo(wannabes[6],7,13);
                break;
            }
            case 6: {
                map.moveCreatureTo(scorpion,2,15);
                map.moveCreatureTo(wannabes[0],3,14);
                map.moveCreatureTo(wannabes[1],4,13);
                map.moveCreatureTo(wannabes[2],5,12);
                map.moveCreatureTo(wannabes[3],6,12);
                map.moveCreatureTo(wannabes[4],7,13);
                map.moveCreatureTo(wannabes[5],8,14);
                map.moveCreatureTo(wannabes[6],9,15);
                break;
            }
            case 7: {
                map.moveCreatureTo(scorpion,4,13);
                map.moveCreatureTo(wannabes[0],5,12);
                map.moveCreatureTo(wannabes[1],5,13);
                map.moveCreatureTo(wannabes[2],5,14);
                map.moveCreatureTo(wannabes[3],6,11);
                map.moveCreatureTo(wannabes[4],6,13);
                map.moveCreatureTo(wannabes[5],6,15);
                map.moveCreatureTo(wannabes[6],7,13);
                break;
            }
            default:
                System.out.println("无法移动");
        }
    }
    public void changeCalabashFormation(int index, CalabashBrother[] brothers,MyMap map) {
        int dis = 10;
        int up = 1;
        switch (index) {
            case 0: {
                map.moveCreatureTo(brothers[0], 5 - up, 11-dis);
                map.moveCreatureTo(brothers[1], 6- up, 12-dis);
                map.moveCreatureTo(brothers[2], 7- up, 13-dis);
                map.moveCreatureTo(brothers[3], 7- up, 14-dis);
                map.moveCreatureTo(brothers[4], 6- up, 15-dis);
                map.moveCreatureTo(brothers[5], 5- up, 16-dis);
                map.moveCreatureTo(brothers[6], 4- up, 17-dis);
                break;
            }
            case 1: {
                map.moveCreatureTo(brothers[0],3- up,16-dis);
                map.moveCreatureTo(brothers[1],4- up,15-dis);
                map.moveCreatureTo(brothers[2],5- up,14-dis);
                map.moveCreatureTo(brothers[3],6- up,13-dis);
                map.moveCreatureTo(brothers[4],7- up,12-dis);
                map.moveCreatureTo(brothers[5],8- up,11-dis);
                map.moveCreatureTo(brothers[6],9- up,10-dis);
                break;
            }
            case 2: {
                map.moveCreatureTo(brothers[0],2- up,13-dis);
                map.moveCreatureTo(brothers[1],3- up,12-dis);
                map.moveCreatureTo(brothers[2],4- up,13-dis);
                map.moveCreatureTo(brothers[3],5- up,12-dis);
                map.moveCreatureTo(brothers[4],6- up,13-dis);
                map.moveCreatureTo(brothers[5],7- up,12-dis);
                map.moveCreatureTo(brothers[6],8- up,13-dis);
                break;
            }
            case 3: {
                map.moveCreatureTo(brothers[0],2- up,12-dis);
                map.moveCreatureTo(brothers[1],3- up,12-dis);
                map.moveCreatureTo(brothers[2],4- up,12-dis);
                map.moveCreatureTo(brothers[3],5- up,12-dis);
                map.moveCreatureTo(brothers[4],6- up,12-dis);
                map.moveCreatureTo(brothers[5],7- up,12-dis);
                map.moveCreatureTo(brothers[6],8- up,12-dis);
                break;
            }
            case 4: {
                map.moveCreatureTo(brothers[0],8- up,13-dis);
                map.moveCreatureTo(brothers[1],5- up,14-dis);
                map.moveCreatureTo(brothers[2],6- up,11-dis);
                map.moveCreatureTo(brothers[3],6- up,13-dis);
                map.moveCreatureTo(brothers[4],6- up,15-dis);
                map.moveCreatureTo(brothers[5],7- up,12-dis);
                map.moveCreatureTo(brothers[6],7- up,14-dis);
                break;
            }
            case 5: {
                map.moveCreatureTo(brothers[0],4- up,12-dis);
                map.moveCreatureTo(brothers[1],4- up,14-dis);
                map.moveCreatureTo(brothers[2],5- up,11-dis);
                map.moveCreatureTo(brothers[3],5- up,15-dis);
                map.moveCreatureTo(brothers[4],6- up,12-dis);
                map.moveCreatureTo(brothers[5],6- up,14-dis);
                map.moveCreatureTo(brothers[6],7- up,13-dis);
                break;
            }
            case 6: {
                map.moveCreatureTo(brothers[0],3- up,14-dis);
                map.moveCreatureTo(brothers[1],4- up,13-dis);
                map.moveCreatureTo(brothers[2],5- up,12-dis);
                map.moveCreatureTo(brothers[3],6- up,12-dis);
                map.moveCreatureTo(brothers[4],7- up,13-dis);
                map.moveCreatureTo(brothers[5],8- up,14-dis);
                map.moveCreatureTo(brothers[6],9- up,15-dis);
                break;
            }
            case 7: {
                map.moveCreatureTo(brothers[0],5- up,12-dis);
                map.moveCreatureTo(brothers[1],5- up,13-dis);
                map.moveCreatureTo(brothers[2],5- up,14-dis);
                map.moveCreatureTo(brothers[3],6- up,11-dis);
                map.moveCreatureTo(brothers[4],6- up,13-dis);
                map.moveCreatureTo(brothers[5],6- up,15-dis);
                map.moveCreatureTo(brothers[6],7- up,13-dis);
                break;
            }
            default:
                System.out.println("无法移动");
        }
    }
}