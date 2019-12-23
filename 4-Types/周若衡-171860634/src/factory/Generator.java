package factory;

public interface Generator<T> {
    T generate(String className,int rank);
}
