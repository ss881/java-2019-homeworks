import java.io.*;
public class LogRead{
    String name;
    FileInputStream in;
    boolean reading;
    public LogRead(){}
    public void readInit(String name){
        try{
            this.name=name;
            in=new FileInputStream(name+".calalog");
        }catch(Exception e){
            System.err.println(e);
        }
        reading=true;
    }
    public int intRead(){
        int i=0;
        try{
            i=in.read();
        }catch(Exception e){
            System.err.println(e);
        }
        return i;
    }
    public boolean boolRead(){
        int i=0;
        try{
            i=in.read();
        }catch(Exception e){
            System.err.println(e);
        }
        if(i==1){
            return true;
        }
        else{
            return false;
        }
    }
    public String stringRead(){
        int length=0;
        byte[]bs;
        String str="";
        try{
            length=in.read();
            bs=new byte[length];
            in.read(bs);
            str=new String(bs);
        }catch(Exception e){
            System.err.println(e);
        }
        return str;
    }
}