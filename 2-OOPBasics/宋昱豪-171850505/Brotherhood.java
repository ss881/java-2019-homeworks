import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Brotherhood {
    CalabashBrothers[] CB;
    void initialize()
    {
        CB=new CalabashBrothers[7];
        Integer[] random=Sf();
        for(int i=1;i<8;i++)
        {
            CB[i-1]=new CalabashBrothers(random[i-1],i);
        }
    }
    void disorder()
    {
        Integer[] random=Sf();
        for(int i=0;i<7;i++)
        {
            CB[i].ChangeMyColorByNumber(random[i]);
        }
    }
    public static  void main(String[] args)
    {
        Brotherhood m=new Brotherhood();
        m.initialize();
        m.Bubble();
        m.disorder();
        m.division();
    }
    void Bubble()
    {
        for(int i=0; i< 7; i++)
        {
            for(int j=0;j<6;j++)
            {
                if(CB[j].Number>CB[j+1].Number)
                {
                    CalabashBrothers temp = CB[j];
                    CB[j] = CB[j + 1];
                    CB[j].ChangePosition(j+1);//attention
                    CB[j + 1] = temp;
                    CB[j+1].ChangePosition(j+2);
                }
            }
        }
        for(int i=0;i<7;i++)
        {
            CB[i].getWho();
        }
    }
    void division()
    {
        for(int i=1;i<7;i++)
        {
            CalabashBrothers temp = CB[i];
            int low = 0, high = i - 1;
            int mid = -1;
            while (low <= high) {
                mid = low + (high - low) / 2;
                if (CB[mid].Number > temp.Number) {
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
            for(int j = i - 1; j >= low; j--) {
                CB[j + 1] = CB[j];//attention
                CB[j+1].ChangePosition(j+2);
            }
            CB[low] = temp;
            CB[low].ChangePosition(low+1);
        }
        for(int i=0;i<7;i++)
        {
            CB[i].GetMyColor();
        }
    }
    private Integer[] Sf() {
        Integer solutionArr[] = new Integer[7];
        List list=new ArrayList<Integer>();
        for (int i = 0; i < 7; i++)
            list.add(i+1);
        Collections.shuffle(list);
        list.toArray(solutionArr);
        return solutionArr;
    }
}
