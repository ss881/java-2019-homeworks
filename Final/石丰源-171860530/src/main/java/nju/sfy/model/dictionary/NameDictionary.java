package nju.sfy.model.dictionary;

public class NameDictionary{
    private static String []names = {"", "老大", "老二", "老三", "老四", "老五", "老六", "老七"};
    public static String getName(int i){
        if(i > 0 && i < names.length)
            return names[i];
        else
            return "";
    }
};