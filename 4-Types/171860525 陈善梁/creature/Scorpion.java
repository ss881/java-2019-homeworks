package creature;

public class Scorpion extends Leader<Evial>{
    static final int N=12;//size of map
    static final int NUM_EVILS=20;//max number of evils

    public Evial[] initialize(){//add Snake to evials?
        Evial[]evials = new Evial[NUM_EVILS];
        for(int i=0;i<evials.length;++i){
            evials[i]=new Evial();
        }
        changeToCrane(evials);
        return evials;
    }

    @Override
    public void changeForm(Evial[] evials) {
        int nextForm=(int)(Math.random()*6)+1;//[1-6]
        switch (nextForm){
            case 1:changeToGoose(evials);break;
            case 2:changeToCar(evials);break;
            case 3:changeToFishScale(evials);break;
            case 4:changeToSquare(evials);break;
            case 5:changeToMoon(evials);break;
            default:changeToArrow(evials);break;
        }
    }

    public void changeToCrane(Evial[]evials){
//        resetCurrentPositions(evials);
        //use 7 evils (include Scorpion)
        //set Scorpion position
        this.moveTo(7,7);
        int count=0;
        for(int i=1;i<=3;++i){
            evials[count].moveTo(7-i,7-i);
            evials[count+1].moveTo(7-i,7+i);
            count+=2;
        }
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(-1,-1);
        }
    }

    public void changeToGoose(Evial[]evials){
//        resetCurrentPositions(evials);
        //use 5 evils (include Scorpion)
        //set Scorpion position
//        setPreviousPosition(currentPosition.x,currentPosition.y);//self
        this.moveTo(6,7);
        int count=0;
        for(int i=1;i<=2;++i){
            evials[count].moveTo(6-i,7+i);
            evials[count+1].moveTo(6+i,7-i);
            count+=2;
        }
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(-1,-1);
        }
    }

    public void changeToCar(Evial[]evials){
        this.moveTo(3,7);
        int count=0;
        for(int i=0;i<3;++i){
            evials[count].moveTo(3+2*i+1,6);
            ++count;
        }
        for(int i=0;i<2;++i){
            evials[count].moveTo(3+2*i+2,7);
            ++count;
        }
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(-1,-1);
        }
    }

    public void changeToFishScale(Evial[]evials){
        this.moveTo(4,7);
        int count=1;
        evials[0].moveTo(5,8);
        for(int i=0;i<3;++i){
            evials[count].moveTo(6,5+2*i);
            ++count;
        }
        for(int i=0;i<4;++i){
            evials[count].moveTo(7,4+2*i);
            ++count;
        }
        evials[count].moveTo(8,7);
        ++count;
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(-1,-1);
        }
    }

    public void changeToSquare(Evial[]evials){
        this.moveTo(4,7);
        int count=0;
        for(int i=0;i<2;++i){
            evials[count].moveTo(4+2*i+1,6);
            evials[count+1].moveTo(4+2*i+1,8);
            count+=2;
        }
        evials[count].moveTo(6,5);
        evials[count+1].moveTo(6,9);
        evials[count+2].moveTo(8,7);
        count+=3;
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(-1,-1);
        }
    }

    public void changeToMoon(Evial[]evials){
        this.moveTo(5,5);
        int count=0;
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                if(i==0&&j==0){
                    continue;
                }
                else{
                    evials[count].moveTo(5+i,5+j);
                    ++count;
                }
            }
        }
        for(int i=0;i<2;++i){
            evials[count].moveTo(5-i-1,6+i+1);
            evials[count+1].moveTo(7+i+1,6+i+1);
            count+=2;
        }
        for(int i=0;i<3;++i){
            evials[count].moveTo(5-i-1,7+i+1);
            evials[count+1].moveTo(7+i+1,7+i+1);
            count+=2;
        }
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(-1,-1);
        }
    }

    public void changeToArrow(Evial[]evials){
        this.moveTo(3,7);
        int count=0;
        for(int i=1;i<=6;++i){
            evials[count].moveTo(3+i,7);
            ++count;
        }
        for(int i=1;i<=2;++i){
            evials[count].moveTo(3+i,7-i);
            evials[count+1].moveTo(3+i,7+i);
            count+=2;
        }
        for(;count<evials.length;++count){
            //currently-not-used evial move disappear
            evials[count].moveTo(-1,-1);
        }
    }

//    @Override
//    public char getSymbol(){
//        return 'X';
//    }
}
