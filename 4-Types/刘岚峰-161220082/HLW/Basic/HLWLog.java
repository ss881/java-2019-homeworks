package HLW.Basic;

public class HLWLog {
    public static void HLW_NOTICE(String info){
        System.out.println( "HLW NOTICE: " + Thread.currentThread().getStackTrace()[2].getFileName() + ", " +
                Thread.currentThread().getStackTrace()[2].getLineNumber() + ": " + info);
    }

    public static void HLW_ERROR(String info){
        System.err.println( "HLW ERROR: " + Thread.currentThread().getStackTrace()[2].getFileName() + ", " +
                Thread.currentThread().getStackTrace()[2].getLineNumber() + ": " + info);
    }

}
