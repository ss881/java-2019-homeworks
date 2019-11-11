import java.util.Random;
public class hulu1 {
    public static int[] makeShuffle()
    {
        int[] startArr=new int[7];
        int curf=0;
        while(curf<7)
        {
            startArr[curf]=(int)(Math.random()*7);
            if(curf==0)
            {
                curf++;
            }
            else
            {
                curf++;
                for(int i=0;i<curf-1;i++)
                {
                    if(startArr[i]==startArr[curf-1])
                    {
                        curf--;
                        break;
                    }
                }
            }
        }
        return startArr;
    }
    public static void main(String args[])
    {
        Boys[] huluboy;
        Boys outerSpace;
        huluboy=new Boys[7];
        //sort1
        int[] Shuffle;
        Shuffle=makeShuffle();
        for(int i=0;i<7;i++)
        {
            huluboy[Shuffle[i]]=new Boys(i+1);
        }
        for(int i=0;i<7;i++)
        {
            huluboy[i].setCurPlace(i+1);
        }
        //for(int i=0;i<7;i++) huluboy[i].speakRank();
        for(int i=0;i<6;i++)
        {
            for (int j = 0; j < 7 - i - 1; j++) {
                if (huluboy[j].getRank() > huluboy[j + 1].getRank()) {
                    huluboy[j].emitPlaceChange(j + 2);
                    huluboy[j + 1].recvPlaceChange(j+1);
                    outerSpace = huluboy[j];
                    huluboy[j] = huluboy[j + 1];
                    huluboy[j + 1] = outerSpace;
                }
            }
        }
        for(int i=0;i<7;i++) huluboy[i].speakRank();
        //sort2
        Shuffle=makeShuffle();
        for(int i=0;i<7;i++)
        {
            huluboy[Shuffle[i]].setRank(i+1);
            huluboy[Shuffle[i]].setColor(i+1);
        }
        for(int i=0;i<7;i++)
        {
            huluboy[i].setCurPlace(i+1);
        }
        int begin,end,middle;
        for(int i=1;i<7;i++)
        {
            outerSpace=huluboy[i];
            begin=0;
            end=i-1;
            while(end>begin)
            {
                middle=(begin+end)/2;
                if(huluboy[middle].getColor()>outerSpace.getColor()) end=middle-1;
                else begin=middle+1;
            }
            for(int j=i-1;j>=begin;j--)
            {
                huluboy[j+1]=huluboy[j];
            }
            if(end!=i)
            {
                huluboy[end + 1].emitPlaceChange(outerSpace.getCurPlace());
                outerSpace.recvPlaceChange(end + 2);
                huluboy[end + 1] = outerSpace;
            }
        }
        for(int i=0;i<7;i++) huluboy[i].speakColor();
    }
}
