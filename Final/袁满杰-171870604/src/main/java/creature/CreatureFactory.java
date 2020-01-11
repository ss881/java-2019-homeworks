package creature;

import exception.HuluwaOutOfNumException;
import space.Space;

import java.io.*;
import java.net.URI;

public abstract class CreatureFactory {
    static float[][] info=new float[12][5];
    Space space;
    public CreatureFactory(Space s)
    {
        space=s;
    }
    abstract Creature create() throws HuluwaOutOfNumException;
    public static void load_config(URI filepath)
    {
        File conf=new File(filepath);
        BufferedReader in=null;
        try {
            in=new BufferedReader(new InputStreamReader(new FileInputStream(conf)));
            String line="";
            for (int i = 0; i <12 ; i++) {
                line=in.readLine();
                String[] lines= line.split(",");
                for (int j = 0; j <5 ; j++) {
                    info[i][j]=Float.parseFloat(lines[j]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
