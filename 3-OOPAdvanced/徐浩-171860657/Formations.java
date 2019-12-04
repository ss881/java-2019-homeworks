public class Formations
{
    public static void setCharacter(Creature[] obj,int mode,int n,int centerX,int centerY,boolean camp)
    {
        switch (mode)
        {
            case 1:{
                inHeYi(obj,n,centerX,centerY,camp);
                break;
            }
            case 2:{
                inYanXing(obj,n,centerX,centerY,camp);
                break;
            }
            case 3:{
                inChongE(obj,n,centerX,centerY,camp);
                break;
            }
            case 4:{
                inChangShe(obj,n,centerX,centerY,camp);
                break;
            }
            case 5:{
                inYuLin(obj,n,centerX,centerY,camp);
                break;
            }
            case 6:{
                inFangYuan(obj,n,centerX,centerY,camp);
                break;
            }
            case 7:{
                inYanYue(obj,n,centerX,centerY,camp);
                break;
            }
            case 8:{
                inFengShi(obj,n,centerX,centerY,camp);
                break;
            }

        }
    }

    public static void inHeYi(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    {
        Map.setMap(obj[0],centerX,centerY);
        for (int i = 1; i < n; i++) {
            if (i % 2 == 1) {
                Map.setMap(obj[i],centerX+ ((i + 1) / 2),centerY+ ((i + 1) / 2));
            } else {
                Map.setMap(obj[i],centerX- ((i + 1) / 2),centerY+ ((i + 1) / 2));
            }
        }
    }
    public static void inYanXing(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    {
        for (int i = 0; i < n; i++) {
            if(i%2==0)
            {
                Map.setMap(obj[i],centerX+ ((i + 1) / 2),centerY+ ((i + 1) / 2));
            }
            else
            {
                Map.setMap(obj[i],centerX- ((i + 1) / 2),centerY- ((i + 1) / 2));
            }
        }
    }
    public static void inChongE(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    {
        for (int i = 0; i < n; i++)
        {
            if(((i+1)/2)%2==1)
            {//右方放点
                if(i%2==1) { //上方(i+1)/2处
                    Map.setMap(obj[i],centerX- ((i + 1) / 2),centerY+ 1);
                }
                else
                {
                    Map.setMap(obj[i],centerX+ ((i + 1) / 2),centerY+ 1);
                }
            }
            else
            {
                //左方
                if(i%2==1) { //上方(i+1)/2处
                    Map.setMap(obj[i],centerX- ((i + 1) / 2),centerY);
                }
                else
                {
                    Map.setMap(obj[i],centerX+ ((i + 1) / 2),centerY);
                }
            }
        }
    }
    public static void inChangShe(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    {
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                Map.setMap(obj[i],centerX+ ((i + 1) / 2),centerY);
            } else {
                Map.setMap(obj[i],centerX- ((i + 1) / 2),centerY);
            }
        }
    }
    public static void inYuLin(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    {
        if(n!=10&&!camp)
        {
            n=10;
            obj=new BadGuy[n];
            for (int i = 0; i < n; i++) {
                obj[i]=new BadGuy();
            }
        }
        int x = centerX;
        int y = centerY;
        Map.setMap(obj[0],x, y);
        Map.setMap(obj[1],x - 1, y + 1);
        Map.setMap(obj[2],x - 2, y + 2);
        Map.setMap(obj[3],x, y + 2);
        Map.setMap(obj[4],x + 1, y + 2);
        Map.setMap(obj[5],x - 3, y + 3);
        Map.setMap(obj[6],x - 1, y + 3);
        Map.setMap(obj[7],x + 1, y + 3);
        Map.setMap(obj[8],x + 2, y + 3);
        Map.setMap(obj[9],x, y + 4);
    }
    public static void inFangYuan(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    {
        if(n!=8&&!camp)
        {
            n=8;
            obj=new BadGuy[n];
            for (int i = 0; i < n; i++) {
                obj[i]=new BadGuy();
            }
        }
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 1) {
                Map.setMap(obj[i],centerX + ((i + 1) / 2), centerY + ((i + 1) / 2));
            } else {
                Map.setMap(obj[i],centerX - ((i + 1) / 2), centerY + ((i + 1) / 2));
            }
        }
        Map.setMap(obj[5],centerX-1, centerY+3);
        Map.setMap(obj[6],centerX+1, centerY+3);
        Map.setMap(obj[7],centerX, centerY+4);
    }
    public static void inYanYue(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    {
        int x = centerX;
        int y = centerY;
        if (n <= 9) {
            for (int i = 0; i < n; i++) {
                if (i % 3 == 0) {
                    Map.setMap(obj[i],x,y+i/3);
                }
                if (i % 3 == 1) {
                    Map.setMap(obj[i],x-1,y+i/3);
                }
                if (i % 3 == 2) {
                    Map.setMap(obj[i],x+1,y+i/3);
                }
            }
        } else {
            n=19;
            obj=new BadGuy[n];
            for (int i = 0; i < n; i++) {
                obj[i]=new BadGuy();
            }
            for (int i = 0; i < 9; i++) {
                if (i % 3 == 0) {
                    Map.setMap(obj[i],x,y+i/3);
                }
                if (i % 3 == 1) {
                    Map.setMap(obj[i],x-1,y+i/3);
                }
                if (i % 3 == 2) {
                    Map.setMap(obj[i],x+1,y+i/3);
                }
            }

            Map.setMap(obj[9],x + 2, y + 1);
            Map.setMap(obj[10],x + 2, y + 2);
            Map.setMap(obj[11],x - 2, y + 1);
            Map.setMap(obj[12],x - 2, y + 2);
            Map.setMap(obj[13],x + 3, y + 2);
            Map.setMap(obj[14],x + 3, y + 3);
            Map.setMap(obj[15],x - 3, y + 2);
            Map.setMap(obj[16],x - 3, y + 3);
            Map.setMap(obj[17],x + 4, y + 3);
            Map.setMap(obj[18],x - 4, y + 3);

        }

    }
    public static void inFengShi(Creature[] obj,int n, int centerX,int centerY,boolean camp)
    {
        if(n!=12&&!camp)
        {
            n=12;
            obj=new BadGuy[n];
            for (int i = 0; i < n; i++) {
                obj[i]=new BadGuy();
            }
        }
        Map.setMap(obj[0],centerX,centerY);
        for (int i = 1; i < 7; i++) {
            if (i % 2 == 1) {
                Map.setMap(obj[i],centerX+ ((i + 1) / 2),centerY+ ((i + 1) / 2));
            } else {
                Map.setMap(obj[i],centerX- ((i + 1) / 2),centerY+ ((i + 1) / 2));
            }
        }
        for (int i = 7; i < 12; i++) {
            Map.setMap(obj[i],centerX,centerY+ i-6);
        }
    }


}