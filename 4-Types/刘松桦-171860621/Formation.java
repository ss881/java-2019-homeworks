import java.util.List;

abstract class Formation<T extends GameObject> {
    Map map;
    List<GameObject> members;
    int centerX,centerY;
    Formation(Map map,List<GameObject> members,int x,int y){
        this.map=map;
        this.members=members;
        this.centerX=x;
        this.centerY=y;
    }
    abstract void putFormation();
    abstract void regulation();
}

class LongFormation extends Formation<GameObject>{
    //长蛇型阵型，List中，最后一位是队伍的头头，会站在中心偏左两个单位，其余的围绕中心纵向展开

    LongFormation(Map map,List<GameObject> members,int x,int y){
        super(map,members,x,y);
    }

    private void swap(int i, int j) {
        int tempX = members.get(j).x, tempY = members.get(j).y;
        members.get(j).setPosition(members.get(i).x, members.get(i).y);
        members.get(i).setPosition(tempX, tempY);
        GameObject temp=members.get(j);
        members.set(j, members.get(i));
        members.set(i,temp);
        map.printMap();
    }

    private void quickSort(int l, int r) {
        if (l >= r)
            return;
        int i = l;
        int j = r;
        GameObject key = members.get(l);
        while (i < j) {
            while (i < j && members.get(j).symbol >= key.symbol)
                j--;
            if (i < j) {
                swap(i,j);
                i++;
            }
            while (i < j && members.get(i).symbol < key.symbol)
                i++;
            if (i < j) {
                swap(j,i);
                j--;
            }
        }
        int tempX = members.get(j).x, tempY = key.y;
        key.setPosition(members.get(i).x, members.get(i).y);
        members.get(i).setPosition(tempX, tempY);
        members.set(i,key);
        quickSort(l, i - 1);
        quickSort(i + 1, r);
    }

    @Override
    void putFormation() {
        for (int i = 0; i < members.size()-1; i++) {
            members.get(i).setPosition(centerX - (members.size()-1)/2 + i, centerY);
        }
        members.get(members.size()-1).setPosition(centerX,centerY-2);
        map.printMap();
    }

    void regulation(){
        quickSort(0,6);
        map.printMap();
    }
}

class ReuillyFormation extends Formation<GameObject>{
    //鹤翼型，List第一个是将军（boss），最后一个是皇帝（督战的），中间的都是士兵，将军站在中心，皇帝站在中心靠右两个单位

    ReuillyFormation(Map map,List<GameObject> members,int x,int y){
        super(map,members,x,y);
    }

    @Override
    void putFormation() {
        members.get(0).setPosition(centerX,centerY);
        for(int i=1;i<=(members.size()-2)/2;i++){
            members.get(i*2-1).setPosition(centerX-i,centerY+i);
            members.get(i*2).setPosition(centerX+i,centerY+i);
        }
        members.add(new GameObject("蛇精",'s',centerX,centerY+2,map));
        members.get(members.size()-1).setPosition(centerX,centerY+2);
        map.printMap();
    }

    void regulation(){
        for(int i=1;i<=(members.size()-2)/2;i++){
            members.get(i*2-1).changePosition(centerX-i,i%2==1?centerY+1:centerY);
            members.get(i*2).changePosition(centerX+i,i%2==1?centerY+1:centerY);
        }
        map.printMap();
    }
}

