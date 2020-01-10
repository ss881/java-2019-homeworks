package finalproject.utils;

public interface Logger {
    void do_logging();
    void add_log(String log);
    String getCurrentLogFileName();
    void clear();
}
