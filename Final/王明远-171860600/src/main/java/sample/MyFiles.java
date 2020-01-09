package  sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyFiles {
    static  String parpath="replay/";
    static String input=new String();
    static PrintWriter mywriter;
    public static long output;
    static int point=0;
    static int []myarry;
    static ArrayList<Integer> buffer;
    static void load(int [][]tmp,int height,int width)
    {
        for(int i=0;i<width;++i)
            for (int j=0;j<height;++j)
                buffer.add(tmp[i][j]);
    }
    static  void open(boolean flag) throws Exception {
        if(flag)//true means read
        {
            BufferedReader mybufferedReader=new BufferedReader(new FileReader(input));
            String mydata="";
            while(true)
            {
                String mystringtmp=mybufferedReader.readLine();

                if(mystringtmp==null)
                    break;
                mydata+=mystringtmp;
                point=0;
            }
            String []mydatastirng=mydata.split(" ");
            //System.out.println(mydatastirng.length);
            myarry=new int[mydatastirng.length];
            for(int i=0;i<myarry.length;++i)
                myarry[i]=Integer.parseInt(mydatastirng[i]);
        }
        else
        {
            String tmp=parpath+output+".txt";
            mywriter=new PrintWriter(tmp);
            buffer=new ArrayList<>();
        }
    }
    static void close()
    {
        for(int i=0;i<buffer.size()-1;++i)
        {
            mywriter.print(buffer.get(i).intValue());
            mywriter.print(" ");
        }
        mywriter.println(buffer.get(buffer.size()-1));
        mywriter.close();
    }

   static boolean read(int [][]tmp,int height,int width)
    {
        if(point==myarry.length)
            return false;
        for(int i=0;i<width;++i)
            for (int j=0;j<height;++j)
            {
                tmp[i][j]=myarry[point];
                point++;
            }

        return true;

    }








}
