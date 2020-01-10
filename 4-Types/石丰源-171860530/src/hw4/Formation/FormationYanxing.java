package hw4.Formation;

public class FormationYanxing extends Formation{
    void initMap() {
        numberOfMembers = 7;
        for(int i = 1; i <= 7; i++){
            map[i + 1][12 - i] = true;
        }
    }
}