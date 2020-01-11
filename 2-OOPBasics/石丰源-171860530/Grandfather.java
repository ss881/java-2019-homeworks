public class Grandfather{
    private Calabash giveBirth(int i){
        String name = NameDictionary.getName(i);
        String color = ColorDictionary.getColor(i);
        return new Calabash(name, color, i);
    }
    public Grandfather(){

    }
    public static void main(String[] args){
        Grandfather g = new Grandfather();
        Calabash one = g.giveBirth(1);
        Calabash two = g.giveBirth(2);
        Calabash three = g.giveBirth(3);
        Calabash four = g.giveBirth(4);
        Calabash five = g.giveBirth(5);
        Calabash six = g.giveBirth(6);
        Calabash seven = g.giveBirth(7);

        CalabashTeam t = new CalabashTeam();
        t.addMember(one);
        t.addMember(two);
        t.addMember(three);
        t.addMember(four);
        t.addMember(five);
        t.addMember(six);
        t.addMember(seven);

        //冒泡排序
        t.randomRow();
        t.numberOffName();
        t.bubbleSort();
        t.numberOffName();

        System.out.println();

        //二分排序
        t.randomRow();
        t.numberOffColor();
        t.binarySort();
        t.numberOffColor();
    }
};

/*
为什么
javac *.java
java Grandfather
可以正常运行
但是
javac Grandfather.java
就会找不到NameDictionary和ColorDictionary
*/