package nju.sfy.model.formation;

public class FormationHeyi extends Formation{
    void initMap() {
        numberOfMembers = 7;
        map[5][7] = true;
        for(int i = 1; i <= 3; i++){
            map[5 - i][7 + i] = true;
            map[5 + i][7 + i] = true;
        }
    }
}
