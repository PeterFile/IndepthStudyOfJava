package com.design.factory;

/**
 * @author CWP
 * @version 1.0
 * @Title: FactoryTest
 * @Package com.design.factory
 * @Description: TODO
 * @date 2023/8/5 16:06
 */
public class FactoryTest {
    public static void main(String[] args) {
        test(new AppleFactory().getFruit());
    }

    public static void test(Fruit fruit) {
        System.out.println(fruit.toString());
    }
}