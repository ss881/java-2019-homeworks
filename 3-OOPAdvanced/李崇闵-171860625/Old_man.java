public class Old_man extends Creature {
    Hulu []hulu;
    Old_man(int r,int c,String n){
        row=r;
        col=c;
        name=n;
    }
    void Creat(Ground ground){
        hulu=new Hulu[7];
        hulu[0]=new Hulu(8,9,"大");
        hulu[1]=new Hulu(2,4,"二");
        hulu[2]=new Hulu(4,6,"三");
        hulu[3]=new Hulu(4,7,"四");
        hulu[4]=new Hulu(8,2,"五");
        hulu[5]=new Hulu(3,6,"六");
        hulu[6]=new Hulu(8,1,"七");
        for(int i=0;i<7;i++)
            ground.Register(hulu[i]);
    }
    void Longsnake(Ground ground) {
        for (int i = 1; i < 7; i++) {
            if (hulu[i].Look_forward(hulu[0], i)) {
                ground.Logout(hulu[i]);
                hulu[i].Move_to(hulu[0].row+i,hulu[0].col);
                ground.Register(hulu[i]);
            }
        }
    }
}
