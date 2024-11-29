package com.design.factory;

/**
 * @author CWP
 * @version 1.0
 * @Title: OrangeFactory
 * @Package com.design.factory
 * @Description: 橘子工厂类
 * @date 11/28/2024 5:37 PM
 */
public class OrangeFactory extends FruitFactory<Orange> {

    @Override
    public Orange getFruit() {
        return new Orange();
    }
}