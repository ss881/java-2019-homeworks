package Formation;

import javafx.util.Pair;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Formation {        //阵型
    private static final String filename = "src/main/resources/formation.conf";

    private static Type[] formationStatus = Type.values();

    private static List<Pair<Integer, Integer>> fill(BufferedReader bufferedReader) throws IOException {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        String patternFormationNum = "!(\\d+)";
        //String patternCoordinate = "(\\d+)\\s+(\\d+)";
        Pattern pattern = Pattern.compile(patternFormationNum);
        String string = bufferedReader.readLine();
        //while ((string = bufferedReader.readLine()) != null){
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()){
                int num = Integer.parseInt(matcher.group(1));
                //pattern = Pattern.compile(patternCoordinate);
                for (int i = 0;i < num;i++){
                    string = bufferedReader.readLine();
                    String[] coors = string.split("\\s+");
                    list.add(new Pair<Integer, Integer>(Integer.parseInt(coors[0]), Integer.parseInt(coors[1])));
                }
            }
        //}
        return list;
    }

    public static Type[] getFormationStatus(){
        return formationStatus;
    }

    public static void init(){
        for (Type type : formationStatus){
            if (type.isExist())
                return;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            String string;
            String patternFormationName = "@([a-zA-Z]+)";
            Pattern pattern = Pattern.compile(patternFormationName);
            while ((string = bufferedReader.readLine()) != null){
                Matcher matcher = pattern.matcher(string);
                if (matcher.find()){//类名
                    Class<?> formationClass = Class.forName("Formation." + matcher.group(1));
                    Method[] methods = formationClass.getDeclaredMethods();
                    for (Method method : methods){
                        if (method.getName().equals("load")){
                            for (Type type : formationStatus){
                                if (type.name().equals(matcher.group(1))){
                                    string = bufferedReader.readLine();
                                    if (string.equals("-1")) {
                                        type.setCharacter(true);
                                    }
                                    type.setExist(true);
                                    break;
                                }
                            }
                            method.invoke(formationClass.newInstance(), fill(bufferedReader));
                            break;
                        }
                    }
                }else {
                    System.out.println("error");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static List<Pair<Integer, Integer>> getFormation(Type type) {
        try {
            Class<?> mclass = Class.forName("Formation." + type.name());
            Method method = mclass.getMethod("getFormation", (Class<?>[]) null);
            return (List<Pair<Integer, Integer>>)method.invoke(mclass.newInstance(), null);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
        /*Class<?> mclass = Formation.class;

        try {
            Method[] methods = mclass.getMethods();
            for (Method m : methods) {
                if (m.getName().equals("get" + type.name())) {
                    position = (List<Pair<Integer, Integer>>) m.invoke(mclass, (Object[]) null);
                    break;
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }*/
    }

}

