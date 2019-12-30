public class Snake extends Unit implements leader{
    private String name;
    Snake() { name = "蛇精"; }
    public String getName() { return name; }
    public void cheer() {
        System.out.println("蛇精：小的们，给我上！");
    }
    public void instruction() { System.out.println("蛇精：小的们，布阵！"); }
}