package test;

import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Map<Integer,String> map = new HashMap<>(2);
//        map.put(3,"A");
//        map.put(5,"B");
//        map.put(7,"C");
        Integer a = Integer.valueOf("0");
        Integer b = Integer.parseInt("0");
        System.out.println(a);
        System.out.println(a==b);
        /*Class<?> clazz = Integer.class.getDeclaredClasses()[0];
        Field field = clazz.getDeclaredField("cache");
        field.setAccessible(true);
        Integer[] cache = (Integer[])field.get(clazz);
        cache[132] = cache[133];
        int a = 2;
        int b = a + a;
        System.out.printf("%d+%d=%d", a, a, b);
        System.out.println(a + "+" + a + "=" + b);*/
    }
}
