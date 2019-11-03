public class Creature {
    String name = "";
    int x = -1;
    int y = -1;
    Creature(){}
    Creature(String name){ this.name = name;}
    boolean walk(World world, int x, int y){
        int n = world.getSize();
        if(x<0 || y<0 || x>=n || y>= n){
            System.out.println("目的坐标不在已知空间中，无法移动至目的坐标");
            return false;
        }
        if (world.map[x][y] != null) {
            System.out.println("目的坐标存在生物，无法移动至目的坐标");
            world.map[x][y].tellName();
            return false;
        }
        world.map[x][y] = this;
        if (this.x >= 0 && this.y >= 0) {
            //该生物已经存在于坐标空间中
            world.map[this.x][this.y] = null;
        }
        if(this.x<0 || this.y<0 || this.x>=n || this.y>= n){
            System.out.println(name+"->(" + x + "," + y + ")");
        }
        else {
            System.out.println(name + "(" + this.x + "," + this.y + ")->(" + x + "," + y + ")");
        }
        this.x = x;
        this.y = y;
        return true;
    }
    void setName(String name){
        this.name = name;
    }
    String tellName(){ return name; }
}
