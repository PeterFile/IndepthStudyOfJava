package com.design.sign;

/**
 * @author CWP
 * @version 1.0
 * @Title: IdlerSign
 * @Package com.design.sign
 * @Description: TODO
 * @date 2023/8/5 16:50
 */
public class IdlerSign {

    private IdlerSign(){};

    private static final class InstanceHolder {
        private static final IdlerSign INSTANCE = new IdlerSign();
    }

    public static IdlerSign getINSTANCE() {

        return InstanceHolder.INSTANCE;
    }
}