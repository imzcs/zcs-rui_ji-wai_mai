package com.zcs.ruijiwaimai;

import org.junit.jupiter.api.Test;

public class SimpleTest {

    @Test
    public void test() {
        try {
            f1();
        } catch (Exception e) {
            e.printStackTrace();
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                System.out.println(stackTraceElement);
                System.out.println();
            }
        }
    }

    public void f1() {
        f2();
    }

    private void f2() {
        throw new RuntimeException("异常了");
    }
}
