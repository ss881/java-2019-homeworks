public class Location {//阵法类
    void heyi(LivingBody a[][]){
        a[6][8]=new ScorpionMonster(6,8,"蝎");
        a[3][15]=new LittleMonster(3,15,"1");
        a[4][14]=new LittleMonster(4,14,"2");
        a[5][13]=new LittleMonster(5,13,"3");
        a[7][13]=new LittleMonster(7,13,"4");
        a[8][14]=new LittleMonster(8,14,"5");
        a[9][15]=new LittleMonster(9,15,"6");

    }
    void yanxing(LivingBody a[][]){
a[6][10]=new ScorpionMonster(6,10,"蝎");
        a[4][19]=new LittleMonster(4,19,"1");
        a[5][18]=new LittleMonster(5,18,"2");
        a[6][11]=new LittleMonster(6,12,"3");
        a[7][16]=new LittleMonster(7,16,"4");
        a[8][15]=new LittleMonster(8,15,"5");

    }
    void deleteheyi(LivingBody a[][]){
        a[6][8]=null;
        a[3][15]=null;
        a[4][14]=null;
        a[5][13]=null;
        a[7][13]=null;
        a[8][14]=null;
        a[9][15]=null;

    }
    void deleteyanxing(LivingBody a[][]){
        a[6][10]=null;
        a[4][19]=null;
        a[5][18]=null;
        a[6][11]=null;
        a[7][16]=null;
        a[8][15]=null;

    }
}

