package formation;

import java.util.ArrayList;

//进行阵型变换,修改Creature 的 pos,运用泛型
abstract public class Formation<T> {
    final  int N = 12;
    abstract public  void changeForm(T leader, ArrayList<T> followers);
}

