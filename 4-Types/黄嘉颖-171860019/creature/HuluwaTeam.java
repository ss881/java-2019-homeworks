package creature;

import javafx.scene.image.Image;
import queuepattern.*;
import space.*;

public class HuluwaTeam implements HuluwaTeamQueuePattern {
    private Huluwa brothers[];
    private Grandpa cheerleader;
    public HuluwaTeam(){
        brothers=new Huluwa[7];
        cheerleader=new Grandpa();
        randomQueue();
    }

    public Grandpa getCheerleader() {
        return cheerleader;
    }
    public Huluwa getTheSpecificHuluwa(int i){
        return brothers[i];
    }
    public Huluwa[] getBrothers(){
        return brothers;
    }

    public void randomQueue(){
        //brothers=new Huluwa[7];
        int rank;
        String color,name,picturePath;
        rank=0;
        color="";
        name="";
        picturePath="";
        Image picture;
        picture=new Image("image/");
        boolean flag;
        for (int i=0;i<brothers.length;i++){
            flag=false;
            while(flag==false) {
                int rand = (int) Math.round(Math.random() * 6 + 1);
                for (int j = 0; j < i; j++) {
                    if (brothers[j].getRank() == rand) {
                        flag = true;
                        break;
                    }
                }
                if (flag==true)flag=false;
                else{
                    switch(rand) {
                        case 1:
                            rank = 1;
                            color = "红色";
                            name = "老大";
                            picture=new Image("image/Redwa.png");
                            picturePath = "image/Redwa.png";
                            break;
                        case 2:
                            rank = 2;
                            color = "橙色";
                            name = "老二";
                            picture=new Image("image/Orangewa.png");
                            picturePath = "/image/Orangewa.png";
                            break;
                        case 3:
                            rank = 3;
                            color = "黄色";
                            name = "老三";
                            picture=new Image("image/Yellowwa.png");
                            picturePath = "/image/Yellowwa.png";
                            break;
                        case 4:
                            rank= 4;
                            color = "绿色";
                            name = "老四";
                            picture=new Image("image/Greenwa.png");
                            picturePath = "/image/Greenwa.png";
                            break;
                        case 5:
                            rank = 5;
                            color = "青色";
                            name = "老五";
                            picture=new Image("image/Bluewa.png");
                            picturePath = "/image/Bluewa.png";
                            break;
                        case 6:
                            rank = 6;
                            color = "蓝色";
                            name = "老六";
                            picture=new Image("image/Cyanwa.png");
                            picturePath = "/image/Cyanwa.png";
                            break;
                        case 7:
                            rank = 7;
                            color = "紫色";
                            name = "老七";
                            picture=new Image("image/Purplewa.png");
                            picturePath = "/image/Purplewa.png";
                            break;
                        default:
                            System.out.println("wrong huluwa number!");
                            break;
                    }
                    brothers[i]=new Huluwa(rank,color,name,picture,picturePath);
                    flag=true;
                }
            }
        }
    }
    public void bubbleSort(){
        //System.out.println();
        for (int i=0;i<brothers.length-1;i++) {
            for (int j = 0; j < brothers.length - i - 1; j++) {
                if (brothers[j].getRank() > brothers[j + 1].getRank()) {
                    //brothers[j].tellChange(j,j+1);
                    //queue[j + 1].tellChange(j+1,j);
                    Huluwa temp = brothers[j];
                    brothers[j] = brothers[j + 1];
                    brothers[j + 1] = temp;
                }
            }
        }
    }

    public void generateTheCheerPattern(Space battleground, int x, int y){
        cheerleader.moveTo(battleground,x,y);
    }

    public void generateChangshePattern(Space battleground, int x, int y){
        for (int i=0;i<brothers.length;i++){
            brothers[i].moveFrom(battleground);
        }
        randomQueue();
        if (x==0){
            bubbleSort();
        }
        if (!battleground.isExceedTheBattleField(x,y)&&!battleground.isExceedTheBattleField(x,y+5)){
            brothers[0].moveTo(battleground, x, y);
            for (int i=1;i<brothers.length;i++){
                y += 1;
                brothers[i].moveTo(battleground, x, y);
            }
        }
        else
        {
            System.out.println("changshe pattern move wrong!!!");
        }
    }
}
