package com.wars;

///import com.sun.javaws.exceptions.ExitException;

public class Formation {
    public int Cmid,Rmid;//should be 7,7
    public int N;//N of War
    //Hulubros h=new Hulubros();
    //Enemys e=new Enemys();

    public Formation(int N){
        Cmid=N/2;
        Rmid=N/2;
        this.N=N;
    }

    public <T> void setFormation(T x, String str) {
        if (x.getClass().getSimpleName().equals("Hulubros")) {//Hulu
            Hulubros h = (Hulubros) x;
            h.sort(Rmid + 3, Cmid / 2);
        } else if (x.getClass().getSimpleName().equals("Enemys")) {//Enemys
            Enemys e = (Enemys) x;
            System.out.println("Str = " + str);
            if (str.equals("HeYi")) {
                e.louluoDefaultPos();
                e.Xiezi.moveto((N - 4) / 2 + 4, Cmid + Cmid / 2 + 1);
                for (int i = 0; i < 3; i++) {
                    e.Louluo[i].setPos(e.Xiezi.x - i - 1, e.Xiezi.y - i - 1);
                }
                for (int i = 3; i < 6; i++) {
                    e.Louluo[i].setPos(e.Xiezi.x - i + 2, e.Xiezi.y + i - 2);
                }
            } else if (str.equals("YanXing")) {
                e.louluoDefaultPos();
                //xiezi's pos: ((N-5)/2+4,N-1-4)
                e.Xiezi.moveto((N - 5) / 2 + 4, N - 1 - 4);//9,10
                for (int i = 0; i < 4; i++) {
                    if (e.Xiezi.x - i - 1 >= 0 && e.Xiezi.y + i + 1 <= 14)
                        e.Louluo[i].setPos(e.Xiezi.x - i - 1, e.Xiezi.y + i + 1);
                    else {
                        System.out.println("x=" + (e.Xiezi.x - i - 1) + "y=" + (e.Xiezi.y + i + 1));
                        System.out.println("Error");
                    }
                }
            } else if (str.equals("ChongE")) {
                e.louluoDefaultPos();
                //xiezi's pos : (Rmid+1,N-1-2-1)
                e.Xiezi.moveto(Rmid + 1, N - 1 - 2 - 1);//9,10
                e.Louluo[0].setPos(e.Xiezi.x - 2, e.Xiezi.y);
                e.Louluo[1].setPos(e.Xiezi.x - 3, e.Xiezi.y + 1);
                e.Louluo[2].setPos(e.Xiezi.x - 1, e.Xiezi.y + 1);
                e.Louluo[3].setPos(e.Xiezi.x + 1, e.Xiezi.y + 1);
                e.Louluo[4].setPos(e.Xiezi.x + 2, e.Xiezi.y);
            } else if (str.equals("YuLin")) {
                e.louluoDefaultPos();
                //xiezi's pos :(Rmid,Cmid+4);
                e.Xiezi.moveto(Rmid, Cmid + 4);
                e.Louluo[0].setPos(e.Xiezi.x - 2, e.Xiezi.y);
                e.Louluo[1].setPos(e.Xiezi.x - 1, e.Xiezi.y + 1);
                e.Louluo[2].setPos(e.Xiezi.x, e.Xiezi.y - 2);
                e.Louluo[3].setPos(e.Xiezi.x, e.Xiezi.y + 2);
                e.Louluo[4].setPos(e.Xiezi.x + 1, e.Xiezi.y - 3);
                for (int i = 0; i < 3; i++) {
                    e.Louluo[4 + i + 1].setPos(e.Louluo[4 + i].x, e.Louluo[4 + i].y + 2);
                }
                e.Louluo[8].setPos(e.Xiezi.x + 2, e.Xiezi.y);
            } else if (str.equals("FangYuan")) {
                e.louluoDefaultPos();
                //Xiezo's pos: (Rmid,Cmid+2)
                e.Xiezi.moveto(Rmid, Cmid + 2);
                e.Louluo[0].setPos(e.Xiezi.x - 1, e.Xiezi.y + 1);
                e.Louluo[1].setPos(e.Xiezi.x - 2, e.Xiezi.y + 2);
                e.Louluo[2].setPos(e.Xiezi.x - 1, e.Xiezi.y + 3);
                e.Louluo[3].setPos(e.Xiezi.x, e.Xiezi.y + 4);
                e.Louluo[4].setPos(e.Xiezi.x + 1, e.Xiezi.y + 1);
                e.Louluo[5].setPos(e.Xiezi.x + 2, e.Xiezi.y + 2);
                e.Louluo[6].setPos(e.Xiezi.x + 1, e.Xiezi.y + 3);
            } else if (str.equals("YanYue")) {
                e.louluoDefaultPos();
                //xiezi's pos: (Rmid,Cmid+3)
                e.Xiezi.moveto(Rmid, Cmid + 2);
                e.Louluo[0].setPos(e.Xiezi.x - 1, e.Xiezi.y);
                e.Louluo[1].setPos(e.Xiezi.x + 1, e.Xiezi.y);
                for (int i = 0; i < 2; i++) {//2,3
                    e.Louluo[2 + i].setPos(e.Louluo[0].x, e.Louluo[0].y + i + 1);
                }
                for (int i = 0; i < 2; i++) {//4,5
                    e.Louluo[4 + i].setPos(e.Xiezi.x, e.Xiezi.y + i + 1);
                }
                for (int i = 0; i < 2; i++) {//6,7
                    e.Louluo[6 + i].setPos(e.Louluo[1].x, e.Louluo[1].y + i + 1);
                }
                for (int i = 0; i < 2; i++) {//8,9
                    e.Louluo[8 + i].setPos(e.Louluo[2].x - 1 - i, e.Louluo[2].y + i + 1);
                }
                for (int i = 0; i < 2; i++) {//10,11
                    e.Louluo[10 + i].setPos(e.Louluo[6].x + i + 1, e.Louluo[6].y + i + 1);
                }
                for (int i = 0; i < 3; i++) {//12,13,14
                    e.Louluo[12 + i].setPos(e.Louluo[3].x - i - 1, e.Louluo[3].y + i + 1);
                }
                for (int i = 0; i < 3; i++) {//15,16,17
                    e.Louluo[15 + i].setPos(e.Louluo[7].x + i + 1, e.Louluo[7].y + i + 1);
                }
            } else if (str.equals("FengShi")) {
                e.louluoDefaultPos();
                //Xiezi's pos: (Rmid-2,Cmid+4)
                e.Xiezi.moveto(Rmid - 2, Cmid + 4);
                for (int i = 0; i < 5; i++) {//0,1,2,3,4
                    e.Louluo[i].setPos(e.Xiezi.x + i + 1, e.Xiezi.y);
                }
                e.Louluo[5].setPos(e.Xiezi.x + 1, e.Xiezi.y - 1);
                e.Louluo[6].setPos(e.Xiezi.x + 2, e.Xiezi.y - 2);
                e.Louluo[7].setPos(e.Xiezi.x + 1, e.Xiezi.y + 1);
                e.Louluo[8].setPos(e.Xiezi.x + 2, e.Xiezi.y + 2);
            } else {
                System.err.println("Unknown Str in setFormation");
                System.exit(-1);
            }
        } else {
            //System.out.println();
            System.err.println("Unknown Type in setFormation");
            System.exit(-1);
        }
    }

}
