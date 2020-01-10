package nju.sfy.model.dictionary;

public class ColorDictionary{
    private static String []colors = {"", "红色", "橙色", "黄色", "绿色", "青色", "蓝色", "紫色"};
    public static String getColor(int i){
        if(i > 0 && i < colors.length)
            return colors[i];
        else
            return "";
    }
}
