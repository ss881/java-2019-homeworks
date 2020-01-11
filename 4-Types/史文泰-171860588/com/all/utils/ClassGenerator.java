package com.all.utils;

import java.lang.reflect.Constructor;

public class ClassGenerator {
    /**
     * 由反射获得构造器
     * @param classPath
     * @param parameterTypes
     * @return
     * @throws Exception
     */
    public static Constructor<?> generateClassConstructor(String classPath, Class<?>... parameterTypes) throws Exception {
        Class<?> NMapClass = Class.forName(classPath);
        Constructor<?> constructor = NMapClass.getConstructor(parameterTypes);
        return constructor;
    }

    /**
     * 由反射获得对象
     * @param constructor
     * @param initargs
     * @return
     * @throws Exception
     */
    public static Object generateClassObject(Constructor<?> constructor, Object ... initargs) throws Exception {
        Object object = constructor.newInstance(initargs);
        return object;
    }

//    /**
//     * 由反射获得属性
//     * @param object
//     * @param fatherMethodLevel
//     * @param fieldName
//     * @return
//     * @throws NoSuchFieldException
//     */
//    public static Field generateClassField(Object object, int fatherMethodLevel, String fieldName) throws NoSuchFieldException {
//        Class clazz = object.getClass();
//        for(int i = 0; i < fatherMethodLevel; ++i){
//            clazz = clazz.getSuperclass();
//        }
//        Field field = clazz.getDeclaredField(fieldName);
//        field.setAccessible(true);
//        return field;
//    }
//
//    /**
//     * 由对象反射获得其已经置为 无需检查 的Method，加快程序速度
//     * @param object
//     * @param fatherMethodLevel         父类层数(若需要调用父类方法)
//     * @param methodName
//     * @param parameterTypes
//     * @return
//     * @throws NoSuchMethodException
//     */
//    public static Method getAccessibleMethod(Object object, int fatherMethodLevel, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
//        Class clazz = object.getClass();
//        for(int i = 0; i < fatherMethodLevel; ++i){
//            clazz = clazz.getSuperclass();
//        }
//        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
//        method.setAccessible(true);
//        return method;
//    }
}
