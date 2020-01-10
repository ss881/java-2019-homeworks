package finalproject.utils;

public interface Factory {
    <T> T generate(String input);
}
