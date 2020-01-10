class NameDictionary{
    private static String []names = {"", "老大", "老二", "老三", "老四", "老五", "老六", "老七"};
    public static String getName(int i){
        if(i > 0 && i < names.length)
            return names[i];
        else
            return "";
    }
};

class ColorDictionary{
    private static String []colors = {"", "红色", "橙色", "黄色", "绿色", "青色", "蓝色", "紫色"};
    public static String getColor(int i){
        if(i > 0 && i < colors.length)
            return colors[i];
        else
            return "";
    }
}