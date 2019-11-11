class Evial extends Creature{
    @Override
    public char getSymbol(){
        return 'E';
    }
}

class Scorpion extends Evial{
    static final int N=12;//size of map
    static final int NUM_EVILS=20;//max number of evils

    public Evial[] initialize(){
        Evial[]evials = new Evial[NUM_EVILS];
        for(int i=0;i<evials.length;++i){
            evials[i]=new Evial();
        }
        changeToCrane(evials);
        return evials;
    }

//    public void resetCurrentPositions(Evial []evials){
//        Position notInMap=new Position(-1,-1);
//        for(Evial evial:evials){
////            evial.previousPosition=evial.currentPosition;
//            evial.setPreviousPosition(evial.currentPosition.x,evial.currentPosition.y);
//            if(evial.currentPosition.myEualsTo(notInMap)==false){
//                //now this evial is in map
//                //set its previousPos
//                evial.setCurrentPosition(-1,-1);//remove it
//            }
//        }
//        //now all currentpos is(-1,-1)
//    }

//    public void changeToCrane(Evial[]evials){
//        resetCurrentPositions(evials);
//        //use 7 evils (include Scorpion)
//        //set Scorpion position
//        setPreviousPosition(currentPosition.x,currentPosition.y);//self
//        setCurrentPosition(7,7);
//        int count=0;
//        for(int i=1;i<=3;++i){
//            evials[count].setCurrentPosition(7-i,7-i);
//            evials[count+1].setCurrentPosition(7-i,7+i);
//            count+=2;
//        }
//    }

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

    @Override
    public char getSymbol(){
        return 'X';
    }
}
