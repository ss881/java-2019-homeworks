package hw3.Formation;
public class FormationYanyue extends Formation{
    void initMap() {
        numberOfMembers = 26;
        map[5][6] = true; map[4][6] = true;map[6][6] = true;
        map[5][7] = true; map[4][7] = true;map[6][7] = true;
        for(int i = 0; i <= 4; i++) map[3 + i][8] = true;
        for(int i = 0; i <= 6; i++) map[2 + i][8] = true;
        map[2][9] = true; map[3][9] = true;map[7][9] = true;map[8][9] = true;
        map[2][10] = true; map[8][10] = true;
        map[1][11] = true; map[9][11] = true;
    }
}