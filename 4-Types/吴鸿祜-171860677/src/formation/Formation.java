package formation;
import organism.Organism;
import organism.OrganismGenerator;
import position.Position;

public class Formation {
     private  int  length;
     private  int  width;
     protected Organism organisms[][];
     protected OrganismGenerator generator=new OrganismGenerator();

     public Formation(int length,int width)
     {
         this.length=length;
         this.width=width;
         organisms=new Organism[length][width];

         for(int i=0;i<length;i++)
         {
             for(int j=0;j<width;j++)
             {
                 organisms[i][j]=generator.next("Land");
                 organisms[i][j].setPosition(new Position(i,j));
             }
         }
     }

     public void printFormation()
     {
         for(int i=0;i<length;i++)
         {
             for (int j = 0; j < width; j++)
             {
                System.out.print(organisms[i][j].getName()+" ");
                // System.out.print(organisms[i][j].getPosition().getX()+" "+organisms[i][j].getPosition().getY()+"  ");
             }
             System.out.print("\n");
         }
         System.out.println("\n");
     }

     public Organism[][] getOrganisms() {return organisms;}
}
