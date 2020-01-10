//import java.util.Date;
import java.io.*;
public class LogWrite{
    String name;
    FileOutputStream out;
    PrintStream p;
    boolean writing;
    public LogWrite(){}
    public void writeInit(String name){
        try{
            this.name=name;
            File file=new File(name+".calalog");
            if(!file.exists())
                file.createNewFile();
            out=new FileOutputStream(file);
            p=new PrintStream(out);
        }catch(Exception e){
            System.err.println(e);
        }
        writing=true;
    }
    public void intWrite(int i){
        try{
            out.write(i);
        }catch(Exception e){
            System.err.println(e);
        }
    }
    public void boolWrite(boolean b){
        try{
            if(b){
                out.write(1);
            }
            else{
                out.write(0);
            }
        }catch(Exception e){
            System.err.println(e);
        }
    }
    public void stringWrite(String s){
        try{
            out.write(s.length());
            out.write(s.getBytes());
        }catch(Exception e){
            System.err.println(e);
        }
    }
    public void close(){
        try{
            out.close();
        }catch(Exception e){
            System.err.println(e);
        }
        writing=false;
    }
}