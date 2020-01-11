package hw3.Dictionary;
public class FormationDictionary{
    private static String []names = {"", "FormationHeyi", "FormationYanxing", "FormationHenge", "FormationChangshe", "FormationYulin", "FormationFangyuan", "FormationYanyue", "FormationFengshi"};
    public static String getName(int i){
        if(i > 0 && i < names.length)
            return names[i];
        else
            return "";
    }
}