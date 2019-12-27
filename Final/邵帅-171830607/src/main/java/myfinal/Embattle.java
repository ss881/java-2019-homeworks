package myfinal;

import java.util.*;
//import javafx.application.*;
//import org.graalvm.compiler.core.common.type.ArithmeticOpTable.BinaryOp.Or;

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
   
}
    