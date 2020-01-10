import annotation.Description;
import gamectrl.Formation;
import gamectrl.MainConfig;
import objinfo.XPoint2D;
import static org.junit.Assert.*;
import org.junit.Test;
// 在保证最小行数的前提下，根据分辨率调整人物大小
// 所以生成的阵型的高度不可以超过行数
@Description(comment = "边界测试，任何阵型不应该超过屏幕高度设定")
public class BorderTest implements MainConfig {
    @Test public void testBorderSafety(){
        Formation f = new Formation();
        for(int i=0;i<7;i++){
            XPoint2D size=f.load(i);
            System.out.println((size.getY()+1)+" vs" +MainConfig.ROW);
            assertEquals(lessThan(size.getY()+1, MainConfig.ROW),true);
        }
    }
    private  boolean lessThan(int a,int b){
        return a<b;
    }
}
