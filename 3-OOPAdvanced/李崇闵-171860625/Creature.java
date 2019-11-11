public class Creature {
    int row,col;
    String name;

    void Move_up(){
        row--;
    }
    void Move_down(){
        row++;
    }
    void Move_left(){
        col--;
    }
    void Move_right(){
        col++;
    }
    void Move_to(int r,int c){
        if(r>row){
            int temp=r-row;
            for(int i=0;i<temp;i++)
                Move_down();
        }
        else if(r<row){
            int temp=row-r;
            for(int i=0;i<temp;i++)
                Move_up();
        }
        if(c>col) {
            int temp=c-col;
            for(int i=0;i<temp;i++)
              Move_right();
        }
        else if(c<col){
            int temp=col-c;
            for(int i=0;i<temp;i++)
                Move_left();
        }
    }

}
