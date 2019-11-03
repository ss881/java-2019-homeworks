import Creatures.CalabashBoy;

public class Sort {

        void BubbleSort(CalabashBoy[] boys) {
            for(int i=6;i>0;i--)
            {
                for(int j=0;j<i;j++)
                {
                    if(boys[j].getName_rank()>boys[j+1].getName_rank())
                    {
                        CalabashBoy temp=boys[j];
                        boys[j]=boys[j+1];
                        boys[j+1]=temp;
                    }
                }
            }
        }
}
