package creatures;

public class Scorpion extends Evial{
    static final int N=12;//size of map
    static final int NUM_EVILS=20;//max number of evils

    public Evial[] initialize(Snake snake){
        Evial[]evials = new Evial[NUM_EVILS];
        for(int i=0;i<evials.length;++i){
            evials[i]=new Evial();
        }
        changeToCrane(evials,snake);
        return evials;
    }

    public void changeToCrane(Evial[]evials,Snake snake){
//        resetCurrentPositions(evials);
        //use 7 evils (include Scorpion)
        //set Scorpion position
        System.out.println("\n transform to 鹤翼:\n--------------------------------------------\n");
        this.moveTo(new Position (7,7));
        int count=0;
        for(int i=1;i<=3;++i){
            evials[count].moveTo(new Position (7-i,7-i));
            evials[count+1].moveTo(new Position (7-i,7+i));
            count+=2;
        }
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(new Position (-1,-1));
        }
        snake.moveTo(new Position (5,7));
    }

    public void changeToGoose(Evial[]evials,Snake snake){
//        resetCurrentPositions(evials);
        //use 5 evils (include Scorpion)
        //set Scorpion position
//        setPreviousPosition(currentPosition.x,currentPosition.y);//self
        System.out.println("\n transform to 雁行:\n--------------------------------------------\n");

        this.moveTo(new Position (6,7));
        int count=0;
        for(int i=1;i<=2;++i){
            evials[count].moveTo(new Position (6-i,7+i));
            evials[count+1].moveTo(new Position (6+i,7-i));
            count+=2;
        }
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(new Position (-1,-1));
        }
        snake.moveTo(new Position (4,7));
    }

    public void changeToCar(Evial[]evials,Snake snake){
        System.out.println("\n transform to 衡轭:\n--------------------------------------------\n");
        this.moveTo(new Position (3,7));
        int count=0;
        for(int i=0;i<3;++i){
            evials[count].moveTo(new Position (3+2*i+1,6));
            ++count;
        }
        for(int i=0;i<2;++i){
            evials[count].moveTo(new Position (3+2*i+2,7));
            ++count;
        }
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(new Position (-1,-1));
        }
        snake.moveTo(new Position (6,5));
    }

    public void changeToFishScale(Evial[]evials,Snake snake){
        System.out.println("\n transform to 鱼鳞:\n--------------------------------------------\n");
        this.moveTo(new Position (4,7));
        int count=1;
        evials[0].moveTo(new Position (5,8));
        for(int i=0;i<3;++i){
            evials[count].moveTo(new Position (6,5+2*i));
            ++count;
        }
        for(int i=0;i<4;++i){
            evials[count].moveTo(new Position (7,4+2*i));
            ++count;
        }
        evials[count].moveTo(new Position (8,7));
        ++count;
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(new Position (-1,-1));
        }
        snake.moveTo(new Position (2,7));
    }

    public void changeToSquare(Evial[]evials,Snake snake){
        System.out.println("\n transform to 方:\n--------------------------------------------\n");
        this.moveTo(new Position (4,7));
        int count=0;
        for(int i=0;i<2;++i){
            evials[count].moveTo(new Position (4+2*i+1,6));
            evials[count+1].moveTo(new Position (4+2*i+1,8));
            count+=2;
        }
        evials[count].moveTo(new Position (6,5));
        evials[count+1].moveTo(new Position (6,9));
        evials[count+2].moveTo(new Position (8,7));
        count+=3;
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(new Position (-1,-1));
        }
        snake.moveTo(new Position (6,7));
    }

    public void changeToMoon(Evial[]evials,Snake snake){
        System.out.println("\n transform to 偃月:\n--------------------------------------------\n");
        this.moveTo(new Position (5,5));
        int count=0;
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                if(i==0&&j==0){
                    continue;
                }
                else{
                    evials[count].moveTo(new Position (5+i,5+j));
                    ++count;
                }
            }
        }
        for(int i=0;i<2;++i){
            evials[count].moveTo(new Position (5-i-1,6+i+1));
            evials[count+1].moveTo(new Position (7+i+1,6+i+1));
            count+=2;
        }
        for(int i=0;i<3;++i){
            evials[count].moveTo(new Position (5-i-1,7+i+1));
            evials[count+1].moveTo(new Position (7+i+1,7+i+1));
            count+=2;
        }
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(new Position (-1,-1));
        }
        snake.moveTo(new Position (6,4));
    }

    public void changeToArrow(Evial[]evials,Snake snake){
        System.out.println("\n transform to 锋矢:\n--------------------------------------------\n");
        this.moveTo(new Position (3,7));
        int count=0;
        for(int i=1;i<=6;++i){
            evials[count].moveTo(new Position (3+i,7));
            ++count;
        }
        for(int i=1;i<=2;++i){
            evials[count].moveTo(new Position (3+i,7-i));
            evials[count+1].moveTo(new Position (3+i,7+i));
            count+=2;
        }
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(new Position (-1,-1));
        }
        snake.moveTo(new Position (2,7));
    }

    public void changeRandomly(Evial[]evials,Snake snake){
        int nextForm=(int)(Math.random()*6)+1;//[1-6]
        switch (nextForm){
            case 1:changeToGoose(evials,snake);break;
            case 2:changeToCar(evials,snake);break;
            case 3:changeToFishScale(evials,snake);break;
            case 4:changeToSquare(evials,snake);break;
            case 5:changeToMoon(evials,snake);break;
            default:changeToArrow(evials,snake);break;
        }

    }

    @Override
    public char getSymbol(){
        return 'X';
    }
}
