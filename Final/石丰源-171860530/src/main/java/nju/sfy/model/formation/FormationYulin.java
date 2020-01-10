package nju.sfy.model.formation;

public class FormationYulin extends Formation{
    void initMap() {
        numberOfMembers = 9;
        /*
        map[5][6] = true;
        map[4][7] = true;
        for(int i = 0; i <= 2; i++){
            map[3 + 2 * i][8] = true;
        }
        for(int i = 0; i <= 3; i++){
            map[2 + 2 * i][9] = true;
        }
        */
        map[6][5] = true;
        map[7][4] = true;
        for(int i = 0; i <= 2; i++){
            map[8][3 + 2 * i] = true;
        }
        for(int i = 0; i <= 3; i++){
            map[9][2 + 2 * i] = true;
        }
    }
}
