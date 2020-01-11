package record;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OthersTest {

    @Test
    public void otherTest(){
        Calendar cale = Calendar.getInstance();
        int tempHH = cale.get(Calendar.HOUR);
        int HH = cale.get(Calendar.HOUR_OF_DAY);
        int MM = cale.get(Calendar.MINUTE);
        int SS = cale.get(Calendar.SECOND);
        String curTime = String.valueOf(HH)+'-'+String.valueOf(MM)+'-'+String.valueOf(SS);

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd HHmmss");
        String fileName = sf.format(new Date()).substring(9);
        System.out.println(fileName);
        System.out.println(tempHH);
        System.out.println(curTime);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String curTime2 = sdf.format(new Date()).substring(4);
        System.out.println("curTime2: "+curTime2);
    }
}
