public class GrandFather extends Unit implements leader{
    private String name;
    GrandFather() { name = "爷爷"; }
    public String getName() { return name; }
    public void cheer() { System.out.println("爷爷：孩子们，加油！"); }
    public void instruction() { System.out.println("爷爷：孩子们，列队！"); }
}