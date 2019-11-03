package hw3.creature;

import javax.swing.*;

public class Huluwa extends Creature implements Comparable<Huluwa>{
   private int rank;
   private static final String[] HULUWA_NAMES= {
           "大娃", "二娃", "三娃", "四娃", "五娃", "六娃", "七娃",
   };

   public Huluwa(int rank) {
      super(HULUWA_NAMES[rank - 1],
              new ImageIcon("pics/" + HULUWA_NAMES[rank-1] + ".jpg").getImage());
      this.rank = rank;
//      setImage();
   }

   @Override
   public int compareTo(Huluwa o) {
       return Integer.compare(rank, o.rank);
   }
}
