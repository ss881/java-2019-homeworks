package nju.sfy.model.formation;

public class FormationHenge extends Formation{
    void initMap() {
        numberOfMembers = 8;
        for(int i = 1; i <= 4; i++){
            map[11 - 2 * i][7] = true;
            map[10 - 2 * i][8] = true;
        }
    }
}
