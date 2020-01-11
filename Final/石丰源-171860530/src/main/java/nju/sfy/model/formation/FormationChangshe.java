package nju.sfy.model.formation;

public class FormationChangshe extends Formation{
    public void initMap() {
        numberOfMembers = 7;
        /*
        for(int i = 2; i <= 8; i++){
            map[i][2] = true;
        }
        */
        for(int j = 2; j <= 8; j++){
            map[2][j] = true;
        }
    }
}