package com.zcs.ruijiwaimai;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    @Test
    public void test() {
        System.out.println(DigestUtil.bcrypt("111222"));
    }
}
