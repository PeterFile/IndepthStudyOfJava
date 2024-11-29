package com.design.factory;

/**
 * @author CWP
 * @version 1.0
 * @Title: FruitFactory
 * @Package com.design.factory
 * @Description: 水果抽象工厂
 * @date 2023/8/5 16:07
 */
public abstract class FruitFactory<T extends Fruit> {
    public abstract T getFruit();

}