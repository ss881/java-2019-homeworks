package hw4.Formation;

public class FormationChangshe extends Formation{
    public void initMap() {
        numberOfMembers = 7;
        for(int i = 2; i <= 8; i++){
            map[i][2] = true;
        }
    }
}