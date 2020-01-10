package hw4.Formation;

public class FormationYulin extends Formation{
    void initMap() {
        numberOfMembers = 10;
        map[5][7] = true;
        map[4][8] = true;
        for(int i = 0; i <= 2; i++){
            map[3 + 2 * i][9] = true;
        }
        for(int i = 0; i <= 3; i++){
            map[2 + 2 * i][10] = true;
        }
    }
}
