package com.design.factory;

/**
 * @author CWP
 * @version 1.0
 * @Title: Fruit
 * @Package com.design.factory
 * @Description: TODO
 * @date 2023/8/5 15:59
 */
public abstract class Fruit {
    private final String name;

    public Fruit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "@" + hashCode();
    }
}