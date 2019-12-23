package nju.sfy.model.formation;

public class FormationFengshi extends Formation{
    void initMap() {
        numberOfMembers = 13;
        for(int i = 0; i <= 5; i++) map[5][6 + i] = true;
        map[4][7] = true;map[6][7] = true;
        map[3][8] = true;map[7][8] = true;
        map[2][9] = true;map[8][9] = true;
    }
}