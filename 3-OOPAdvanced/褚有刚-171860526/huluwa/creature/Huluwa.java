package huluwa.creature;

import javax.swing.*;

public class Huluwa extends Creature implements Comparable<Huluwa>{
   private int rank;

   public Huluwa(int rank, String name) {
      super(name,
              new ImageIcon("pics/" + name + ".jpg").getImage());
      this.rank = rank;
   }

   @Override
   public int compareTo(Huluwa o) {
       return Integer.compare(rank, o.rank);
   }
}
