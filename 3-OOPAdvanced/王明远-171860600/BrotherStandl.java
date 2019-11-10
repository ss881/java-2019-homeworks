public class BrotherStandl extends Creature{

    private int rank_of_brother;// this represents the rank, First rank 1, second rank 2 and so on
    private int color;// use int to represent color , 1->red 2->orange 3->yellow 4->green 5->cyan 6->blue 7->purple
    BrotherStandl(int initial_rank,int initial_color)// born with its color and rank set
    {
        born(Identity.BrotherStandl);
        rank_of_brother=initial_rank;
        color=initial_color;
    }

    int report_color()// return one's color
    {
        return color;
    }
    int report_rank()// return one's rank
    {
        return rank_of_brother;
    }
    void provoke()//  provoke in brotherstandl version
    {
        System.out.println("Hey, Monster! Don't try to kid me! I'm not fool!");
    }

 
    void follow_orders(Identity id,int x,int y)// follow orther's order to goto someplace
    {
        if(id==Identity.Grandpa||id == Identity.BrotherStandl)// only if grandpa or one's brother give the order,
                                                              // then the order will be followed
                                                            
        {
            rush_to_somespace(x, y);
        }
        else provoke();
    }

    void follow_orders(Identity id,BrotherStandl brother)// follow orther's order to exchange space with one's brother
    {
        if(id==Identity.Grandpa||id == Identity.BrotherStandl)
        {
            if(brother.report_rank()==this.rank_of_brother)
                return;
            else
            {
                int brother_x=look_around_x(brother);
                int brother_y=look_around_y(brother);
                brother.rush_to_somespace(this.current_space_x,this.current_space_y);
                this.rush_to_somespace(brother_x,brother_y);
            }
        }
        else provoke();
    }

    int look_around_x(BrotherStandl brother)// get one's brother's coordinate_x
    {
        if(brother.report_color()==this.color)
            return this.get_current_x();
        else
        return brother.get_current_x();
    }
    int look_around_y(BrotherStandl brother)//get one's brother's coordinate_y
    {
        if(brother.report_color()==this.color)
             return this.get_current_y();
        else
        return brother.get_current_y();
    }
};