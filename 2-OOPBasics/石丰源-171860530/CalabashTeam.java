import java.util.ArrayList;
import java.util.Collections;
class CalabashTeam{
    private ArrayList<Calabash> row = new ArrayList<Calabash>();

    public CalabashTeam(){

    }

    public void addMember(Calabash c){
        row.add(c);
    }

    public void randomRow(){            //随意站队
        Collections.shuffle(row);
        for(int i = 0; i < row.size(); i++){
            row.get(i).setPosition(i);
        }
    }
    public void bubbleSort(){           //冒泡排序
        for(int i = 1; i <= row.size() - 1; i++){
            for(int j = 0; j <= row.size() - 1 - i; j++){
                if(row.get(j).getOrder() > row.get(j + 1).getOrder()){
                    row.get(j).move(j + 1);
                    row.get(j + 1).move(j);
                    Calabash temp = row.get(j + 1);
                    row.set(j + 1, row.get(j));
                    row.set(j, temp);
                }
            }
        }
    }
    public void binarySort(){           //二分排序
        for(int i = 1; i < row.size(); i++){
            int place = binarySearch(i);
            if(place != i){
                row.get(i).move(place);
                for(int j = place; j < i; j++){
                    row.get(j).move(j + 1);
                }
            }
            Calabash temp = row.get(i);
            row.remove(i);
            row.add(place, temp);
        }
        
    }
    private int binarySearch(int i){    //二分查找
        int first = 0, last = i - 1;
        while(first <= last){
            int mid = (first + last) / 2;
            if(row.get(mid).getOrder() < row.get(i).getOrder()){
                first = mid + 1;
                if(first > last)
                    return first;
            }
            else{
                last = mid - 1;
                if(last < first){
                    return mid;
                }
            }
        }
        return -1;
    }
    public void numberOffName(){        //队伍按照名字报数
        for(int i = 0; i < 7; i++){
            row.get(i).numberOffName();
        }
        System.out.println();
    }
    public void numberOffColor(){       //队伍按照颜色报数
        for(int i = 0; i < 7; i++){
            row.get(i).numberOffColor();
        }
        System.out.println();
    }
};