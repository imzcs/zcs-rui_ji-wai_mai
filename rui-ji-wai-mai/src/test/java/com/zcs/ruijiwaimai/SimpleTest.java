package com.zcs.ruijiwaimai;

import com.zcs.ruijiwaimai.constant.LoginType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SimpleTest {

    @Test
    public void test() {
        Arrays.stream(LoginType.values()).forEach(a -> System.out.println(a.ordinal()));
    }
}
