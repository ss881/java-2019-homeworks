package generator;
public interface Generator<T>{
    T generate(String classname);
}