package com.design.factory;

/**
 * @author CWP
 * @version 1.0
 * @Title: AppleFactory
 * @Package com.design.factory
 * @Description: TODO
 * @date 2023/8/5 16:15
 */
public class AppleFactory extends FruitFactory<Apple> {

    @Override
    public Apple getFruit() {
        return new Apple();
    }
}