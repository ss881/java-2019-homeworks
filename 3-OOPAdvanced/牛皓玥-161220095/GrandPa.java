import java.util.Collections;

public class GrandPa extends Leaders {

    GrandPa()
    {
        this.name = "爷爷";
        this.pos_y = 5;
        this.center_x = 16;

        //创建葫芦娃兄弟
        for(int i = 0;i< 7;i++) {
            //创建一个葫芦娃，并赋予其位置
            this.kids.add(new Gourd(i));
        }
        Collections.shuffle(kids);
    }
}
