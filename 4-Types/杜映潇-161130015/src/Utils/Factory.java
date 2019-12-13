package Utils;

public interface Factory {
    <T> T generate(String input);
}
