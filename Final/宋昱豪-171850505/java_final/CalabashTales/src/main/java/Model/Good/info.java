package Model.Good;

public enum info
{
    first("红色", "老大", 1), second("橙色", "老二", 2), third("黄色", "老三", 3), fourth("绿色", "老四", 4),
    fifth("青色", "老五", 5), sixth("蓝色", "老六", 6), seventh("紫色", "老七", 7);
    String name;
    String color;
    int index;
    info(String n, String c, int i)
    {
        this.name=n;
        this.color=c;
        this.index=i;
    }
}