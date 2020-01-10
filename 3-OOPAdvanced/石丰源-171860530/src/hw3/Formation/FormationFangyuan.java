package hw3.Formation;
public class FormationFangyuan extends Formation{
    void initMap() {
        numberOfMembers = 8;
        for(int i = 0; i <= 2; i++){
            map[5 - i][7 + i] = true;
            map[5 + i][7 + i] = true;
        }
        for(int i = 0; i <= 1; i++){
            map[5 - i][11 - i] = true;
            map[5 + i][11 - i] = true;
        }
    }
}