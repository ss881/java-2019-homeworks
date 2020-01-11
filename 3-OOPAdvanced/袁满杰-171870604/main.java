import java.util.Random;

/**
 * author Yuan Manjie
 * date 2019/9/123
 * java course homework3
 */

abstract class creature{
    String name;
    int locx,locy;
    int map_size;
    void show(){
        System.out.print(this.name);
    };
    void born(tile[][] floor)
    {
        this.map_size=floor.length;
        Random r=new Random();
         while(true){
             int x=r.nextInt(this.map_size);
             int y=r.nextInt(this.map_size);
             if(floor[x][y].sth==null){
                 floor[x][y].sth=this;
                 this.locx=x;
                 this.locy=y;
                 break;
             }
         }
    }
    void move_to(tile[][] board,int x,int y){
        if(board[x][y].sth!=null)
        {
            creature temp=board[x][y].sth;
            board[x][y].sth=this;
            board[this.locx][this.locy].sth=temp;
            board[this.locx][this.locy].sth.locx=this.locx;
            board[this.locx][this.locy].sth.locy=this.locy;
        }else
        {
            board[x][y].sth=this;
            board[this.locx][this.locy].sth=null;
        }
        this.locx=x;
        this.locy=y;
    }
    void leave(tile[][] board)
    {
        board[this.locx][this.locy].sth=null;
    }
    abstract void zhenfa(tile[][] board,int num);
}

class huluwa extends creature {
    int id;
    huluwa(int id,String name){
        this.name=name;
        this.id=id;
    }
    void zhenfa(tile[][] board,int num){
        int target_x=this.map_size/4+4;
        int target_y=this.map_size/2;
        this.move_to(board,target_x-this.id,target_y);
    }
}

class laoyeye extends creature {
    laoyeye()
    {
        this.name="爷";
    }
    void zhenfa(tile[][] board,int num){
        int target_x=0;
        int target_y=this.map_size/2;
        this.move_to(board,target_x,target_y);
    }
}

class shejing extends creature {
    shejing()
    {
        this.name="蛇";
    }
    void zhenfa(tile[][]board,int num){
        int target_x=this.map_size-1;
        int target_y=this.map_size/2;
        this.move_to(board,target_x,target_y);
    }
}

class xiezijing extends creature {
    creature louluos[];
    xiezijing()
    {
        louluos=new xiaolouluo[1];
        this.name="蝎";
    }
    void summon(int n,tile[][] board){
        if(this.louluos.length>1)
        {
            for(creature i:louluos)
                i.leave(board);
        }
        this.louluos=new xiaolouluo[n];
        for(int i=0;i<n;i++)
        {
            this.louluos[i]=new xiaolouluo(i+1);
            this.louluos[i].born(board);
        }
    }
    void zhenfa(tile[][]board,int num){
        int target_x=this.map_size/2+1;
        int target_y=this.map_size/2;
        int n=0;
        switch (num){
            case 1:n=6;target_x+=3;break;
            case 2:n=4;target_x+=2;break;
            case 3:n=5;break;
            case 4:n=9;break;
            case 5:n=7;break;
            case 6:n=18;target_x+=2;target_y+=1;break;
            case 7:n=11;break;
        }
        this.move_to(board,target_x,target_y);
        this.summon(n,board);
        for(creature i:this.louluos)
        {
            i.zhenfa(board,num);
        }
    }
}

class xiaolouluo extends creature {
    int id;
    xiaolouluo(int id){
        this.id=id;
        this.name="卒";
    }
    void zhenfa(tile[][]board,int num){
        int target_x=this.map_size/2+1;
        int target_y=this.map_size/2;
        switch (num)
        {
            case 1://heyi
                if((this.id+1)%2==0)
                {
                    target_y+=(this.id+1)>>1;
                }else{
                    target_y-=(this.id+1)>>1;
                }
                target_x+=6-(this.id)>>1;
                this.move_to(board,target_x,target_y);
                break;
            case 2://yanxing
                target_y-=this.id-3;
                target_x-=this.id-5;
                if(this.id==3)
                {
                    target_y-=2;
                    target_x-=2;
                }
                this.move_to(board,target_x,target_y);
                break;
            case 3://chonge
                target_x+=this.id;
                target_y+=this.id%2;
                this.move_to(board,target_x,target_y);
                break;
            case 4://yuling
                if(this.id<4)
                {
                    target_x+=this.id;
                    target_y-=this.id;
                }else if(this.id<6)
                {
                    target_x+=this.id-2;
                    target_y-=this.id-4;
                }else if(this.id<9)
                {
                    target_x+=this.id-4;
                    target_y-=this.id-8;
                }else
                {
                    target_x+=3;
                    target_y+=3;
                }
                this.move_to(board,target_x,target_y);
                break;
            case 5://fangyen
                target_x+=(this.id+1)/2;
                int temp=(this.id+1)>>1;
                if(this.id%2==0)
                {
                    if(temp<2)
                        target_y+=temp;
                    else
                        target_y+=4-temp;
                }else
                {
                    if(temp<2)
                        target_y-=temp;
                    else
                        target_y-=4-temp;
                }
                this.move_to(board,target_x,target_y);
                break;
            case 6://yanyue
                target_x+=2;
                target_y+=1;
                if(this.id==18)
                {
                    target_x-=1;
                }else if(this.id==17)
                {
                    target_x+=1;
                }else if(this.id<=5)
                {
                    target_y-=1;
                    target_x+=this.id-3;
                }else if(this.id<=12)
                {
                    target_y-=2;
                    target_x+=this.id-9;
                }else if(this.id==13)
                {
                    target_x+=3;
                    target_y-=3;
                }else if(this.id==14)
                {
                    target_x-=3;
                    target_y-=3;
                }else if(this.id==15)
                {
                    target_x+=4;
                    target_y-=3;
                }else if(this.id==16)
                {
                    target_x-=4;
                    target_y-=3;
                }
                this.move_to(board,target_x,target_y);
                break;
            case 7://fengshi
                if(this.id<=6)
                {
                    if((this.id+1)%2==0)
                    {
                        target_y+=(this.id+1)>>1;
                    }else{
                        target_y-=(this.id+1)>>1;
                    }
                    target_x+=(this.id+1)>>1;
                }else
                {
                    target_x+=this.id-6;
                }
                this.move_to(board,target_x,target_y);
                break;
        }
    }
}

class tile{
    creature sth;
    tile(){
        this.sth=null;
    }
    void show(){
        if(this.sth!=null)
        {
            this.sth.show();
        }else
        {
            System.out.print("囗");
        }
    }
}

class space{
    int N;
    tile floor[][];
    creature hulus[];
    creature yeye,she,xiezi;
    space(int n){
        this.N=n;
        this.floor=new tile[n][n];
        for(int i=0;i<this.N;i++)
            for(int j=0;j<this.N;j++)
                this.floor[i][j]=new tile();
        this.hulus= new huluwa[]{new huluwa(1, "大"), new huluwa(2, "二"), new huluwa(3, "三"), new huluwa(4, "四"), new huluwa(5, "五"), new huluwa(6, "六"), new huluwa(7, "七")};
        this.yeye=new laoyeye();
        this.she=new shejing();
        this.xiezi=new xiezijing();

        for(int i=0;i<7;i++)
            this.hulus[i].born(this.floor);
        this.yeye.born(this.floor);
        this.she.born(this.floor);
        this.xiezi.born(this.floor);

    }
    void show()
    {
        for(int i=0;i<this.N;i++)
            System.out.print("--");
        System.out.println();
        for(int i=0;i<this.N;i++){
            for(int j=0;j<this.N;j++){
                this.floor[j][i].show();
            }
            System.out.print('\n');
        }
        for(int i=0;i<this.N;i++)
            System.out.print("--");
        System.out.println();
    }
}

public class main {
    public static void main(String[] args){
        Random r=new Random();
        space chessboard=new space(19);
        for(creature i:chessboard.hulus)
        {
            i.zhenfa(chessboard.floor,0);
        }
        chessboard.yeye.zhenfa(chessboard.floor,0);
        int r1=r.nextInt(6)+1;
        chessboard.xiezi.zhenfa(chessboard.floor,r1);
        chessboard.she.zhenfa(chessboard.floor,0);
        chessboard.show();
        int r2=r1;
        while((r2=r.nextInt(6)+1)==r1);
        chessboard.xiezi.zhenfa(chessboard.floor,r2);
        chessboard.show();
    }
}
