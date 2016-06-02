package com.novelot.lib;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }



    private static void m4() throws Exception {
        String f = "com.novelot.reflect.Foo";
        Object object = Reflect.on(f, "m4").call("outInfo").get();
        System.out.println(object.toString());
    }

    private static void m3() throws Exception {
        String f = "com.novelot.reflect.Foo";
        Object object = Reflect.on(f).call("setMsg", "新").call("outInfo").get();
        System.out.println(object.toString());
    }

    private static void m1() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        String f = "com.novelot.reflect.Foo";
        Class<?> cla = Class.forName(f);
        Object instance = cla.newInstance();
        Method method = cla.getDeclaredMethod("setMsg", String.class);
        method.setAccessible(true);
        method.invoke(instance, "msg");

        Method method2 = cla.getDeclaredMethod("outInfo", null);
        method2.setAccessible(true);
        Object object = method2.invoke(instance, null);
        System.out.println(object.toString());
    }

    private static void m2() {
        Foo f = new Foo("这是一个foo对象");
        Reflect.on(f).call("outInfo");
        Reflect.on(f).call("setMsg", "新");
        Reflect.on(f).call("outInfo");
        Reflect.on(f).set("info", "field 新");
        Reflect.on(f).call("outInfo");
        Object object = Reflect.on(f).get("info");
        System.out.println(object.toString());
    }
}