public class Xiezi extends Creature {
        Xiaoyao []xiaoyao;
        Xiezi(int x,int y,String n){
            row=x;
            col=y;
            name=n;
        }
        void Creat(Ground ground){
            xiaoyao=new Xiaoyao[8];
            xiaoyao[0]=new Xiaoyao(9,11,"喽");
            xiaoyao[1]=new Xiaoyao(3,11,"啰");
            xiaoyao[2]=new Xiaoyao(4,12,"喽");
            xiaoyao[3]=new Xiaoyao(4,12,"啰");
            xiaoyao[4]=new Xiaoyao(6,16,"喽");
            xiaoyao[5]=new Xiaoyao(6,19,"啰");
            xiaoyao[6]=new Xiaoyao(8,18,"喽");
            xiaoyao[7]=new Xiaoyao(5,17,"啰");
            for(int i=0;i<8;i++)
                ground.Register(xiaoyao[i]);
        }
        void Yanxin(Ground ground){
              for(int i=0;i<8;i++){
                  ground.Logout(xiaoyao[i]);
                  xiaoyao[i].Move_to(row-i-1,col+i+1);
                  ground.Register(xiaoyao[i]);
              }
         }
        void Heyi(Ground ground){
            for(int i=0;i<4;i++){
                ground.Logout(xiaoyao[i]);
                xiaoyao[i].Move_to(row-i-1,col+1+i);
                ground.Register(xiaoyao[i]);
            }
            for(int i=4;i<8;i++){
                ground.Logout(xiaoyao[i]);
                xiaoyao[i].Move_to(row+1+i-4,col+1+i-4);
                ground.Register(xiaoyao[i]);
            }
        }
}
