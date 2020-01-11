package types.src;

import java.util.*;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable.BinaryOp.Or;

import java.lang.Math;
//阵法类,阵法类负责将一个生物类的List编排布阵


//核心是更改生物类中的Map
enum Deployment{鹤翼,雁行,冲轭,长蛇,鱼鳞,方圆,偃月,锋矢};
public class Embattle{

    Deployment deployment;
    List <Organism>list;
    int right;  //0表示在左侧站队，1表示在右侧站队
    int x;      //长和宽
    int y;
    public Embattle(Deployment mydeployment,List<Organism> mylist,int myright,int myx,int myy)
    {
        this.deployment = mydeployment;
        this.list = mylist;
        this.right = myright;
        this.x = myx;
        this.y = myy;
    }
    public void changeDeployment(Deployment mydeployment)
    {
        this.deployment = mydeployment;
    }
    public void exec()
    {
        int centerx,centery;
        if(right==0){
             //左侧站队中心点为x/4,y/2
            centerx = x/4;centery = y/2;
        }
        else{
            centerx = 3*x/4;centery = y/2;
        }
        if(deployment==Deployment.鹤翼){
            int size = list.size()-1;
            int ii=0;
            for(int i=0;i<=size/2;i++){
                list.get(ii).changepos(centerx-i, centery-i);
                ii++;
            }
            for(int i=1;i<=(size+1)/2 ;i++){
                list.get(ii).changepos(centerx+i, centery-i);
                ii++;
            }
        }
        else if(deployment==Deployment.雁行){
            int ii=0;
            int size = list.size()-1;
            for(int i=0;i<=size/2;i++){
                list.get(ii).changepos(centerx-i, centery+i);
                ii++;
            }
            for(int i=1;i<=(size+1)/2 ;i++){
                list.get(ii).changepos(centerx+i, centery-i);
                ii++;
            }
        }
        else if(deployment==Deployment.冲轭){
            int size = list.size()-1;
            int ii=0;
            for(int i=0;i<=size/2;i++){
                list.get(ii).changepos((int)(centerx+((Math.pow(-1,i+1)+1)/2)), centery-i);
                ii++;
            }
            for(int i=1;i<=(size+1)/2 ;i++){
                list.get(ii).changepos((int)(centerx+((Math.pow(-1,i+1)+1)/2)), centery+i);
                ii++;
            }
        }
        else if(deployment==Deployment.长蛇){
            int size = list.size()-1;
            int ii=0;
            for(int i=0;i<=size/2;i++){
                list.get(ii).changepos(centerx, centery-i);
                ii++;
            }
            for(int i=1;i<=(size+1)/2 ;i++){
                list.get(ii).changepos(centerx, centery+i);
                ii++;
            }
        }
        else if(deployment==Deployment.鱼鳞){
            int size = list.size()-1;
            int i=0;
            int ii=0;
            while(true)
            {
                list.get(ii).changepos(centerx, centery+i);
                ii++;
                for(int j=1;j<=i;j++)
                {
                    list.get(ii).changepos(centerx+j, centery+i);
                    ii++;
                    if(ii==size)
                        break;
                    list.get(i).changepos(centerx-j, centery+i);
                    ii++;
                    if(ii==size)
                        break;
                }
                if(ii==size)
                        break;
                i++;
            }
            list.get(ii).changepos(centerx, centery+i+1);
        }
        else if(deployment == Deployment.方圆){
            int n = list.size()/4+1;    //n为边长
            int ii=0;
            list.get(ii).changepos(centerx-n, centery);
            ii++;
            for(int i=0;i<n;i++){
                list.get(ii).changepos(centerx-n+i, centery+i);
                ii++;
                if(ii==list.size())
                    break;
                list.get(ii).changepos(centerx-n+i, centery-i);
                ii++;
                if(ii==list.size())
                    break;
                list.get(ii).changepos(centerx+n-i, centery+i);
                ii++;
                if(ii==list.size())
                    break;
                list.get(ii).changepos(centerx+n-i, centery-i);
                ii++;
                if(ii==list.size())
                    break;
            }
        }
        else if(deployment == Deployment.偃月){

        }
        else if(deployment == Deployment.锋矢){
            int n = list.size()/4+1;    //n为边长,中间为2n
            int ii=0;
            for(int i=0;i<n;i++)
            {
                list.get(ii).changepos(centerx+i, centery+i);
                ii++;
                if(ii==list.size())
                    break;
                list.get(ii).changepos(centerx-i, centery-i);
                ii++;
                if(ii==list.size())
                    break;
                list.get(ii).changepos(centerx, centery+i);
                ii++;
                if(ii==list.size())
                    break;
                list.get(ii).changepos(centerx, centery+i+n);
                ii++;
                if(ii==list.size())
                    break;
            }
        }
    }

    public void printEmbattle(){
        int flag = 0;
        //将目前list的阵法打印出来
        for(int j=0;j<y;j++)
        {
            for(int i=0;i<x;i++)
            {
                flag = 0;
                int ii=0;
                for(ii=0;ii<list.size();ii++)
                {
                    MyMap temp;
                    temp = list.get(ii).Getpos();
                    if(temp.x==i&&temp.y==j)
                    {
                        flag = 1;
                        System.out.print(list.get(ii).name+"\t");
                        break;
                    }
                }
                if(flag == 0)
                {
                    System.out.print("\t");
                }
            }
            System.out.print("\n");
        }
    }
    public static void main(String []args){
        //System.out.println("hello world");
        //Organism brother0 = new Organism(new MyMap(2,3),"大娃");
        //System.out.println(Color.红色.ordinal());
        Brothers brother1 = new Brothers(new MyMap(2,3),Color.红色);
        Brothers brother2 = new Brothers(new MyMap(3,3),Color.橙色);
        Brothers brother3 = new Brothers(new MyMap(4,3),Color.黄色);
        Brothers brother4 = new Brothers(new MyMap(5,3),Color.绿色);
        Brothers brother5 = new Brothers(new MyMap(6,3),Color.青色);
        Brothers brother6 = new Brothers(new MyMap(7,3),Color.蓝色);
        Brothers brother7 = new Brothers(new MyMap(8,3),Color.紫色);
        Organism grandpa = new Organism(new MyMap(0,0),"老爷爷");
        //grandpa.cheer();
        
        List<Organism> badmanlist = new ArrayList<Organism>();
        Monsters scorpion = new Monsters(new MyMap(10,1), "蝎子精");
        Monsters snake = new Monsters(new MyMap(10,0), "蛇精");
        badmanlist.add(scorpion);
        badmanlist.add(snake);
        for(int i=0;i<7;i++)
        {
            badmanlist.add(new Monsters(new MyMap(10,i+2),"小喽啰"));
        }

        List<Organism> godmanlist = new ArrayList<Organism>();
        godmanlist.add(brother1);
        godmanlist.add(brother2);
        godmanlist.add(brother3);
        godmanlist.add(brother4);
        godmanlist.add(brother5);
        godmanlist.add(brother6);
        godmanlist.add(brother7);
        Embattle godmanEmbattle = new Embattle(Deployment.长蛇, godmanlist, 0, 20, 20);
        
        Embattle badmanEmbattle = new Embattle(Deployment.雁行, badmanlist, 1, 20, 20);
        //godmanEmbattle.printEmbattle();
        godmanEmbattle.exec();
        badmanEmbattle.exec();
        
        
        List<Organism> allmanlist = new ArrayList<Organism>();
        allmanlist.addAll(godmanlist);
        allmanlist.addAll(badmanlist);
        Embattle allmanEmbattle = new Embattle(Deployment.雁行, allmanlist, 0, 20, 20); //虽然这里是雁形阵，但是只要不exec()就不会改变位置

        allmanEmbattle.printEmbattle();
        snake.cheer();
        grandpa.cheer();


        badmanEmbattle.changeDeployment(Deployment.鹤翼);   //鹤翼阵
        badmanEmbattle.exec();
        allmanEmbattle.printEmbattle();
        snake.cheer();
        grandpa.cheer();

        // System.out.println(grandpa.godorbad(grandpa));
        
        // System.out.println(grandpa.godorbad(snake));
        // //godmanEmbattle.printEmbattle();
        //System.out.println(brother1.Getpos().x);
    }
}
    