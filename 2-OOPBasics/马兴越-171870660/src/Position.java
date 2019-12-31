public class Position {
    private int pos;//1-7
    public Position(int pos){
        this.pos=pos;
    }

    /*
     * 向前移动一个位置（一步），同时报告。
     */
    public void moveForward(){
        --pos;
        System.out.println(""+(pos+1)+"->"+(pos));
    }

    public void moveBackward(){
        ++pos;
        System.out.println(""+(pos-1)+"->"+(pos));
    }

    public int currentPosition(){
        return pos;
    }
}
