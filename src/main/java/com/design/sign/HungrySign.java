package com.design.sign;

/**
 * @author CWP
 * @version 1.0
 * @Title: HungrySign
 * @Package com.design.sign
 * @Description: TODO
 * @date 2023/8/5 16:32
 */
public class HungrySign {
    private final static HungrySign INSTANCE = new HungrySign();

    private HungrySign(){};

    public static HungrySign getINSTANCE() {
        return INSTANCE;
    }
}