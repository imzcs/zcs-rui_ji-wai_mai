package com.zcs.ruijiwaimai;

import com.zcs.ruijiwaimai.constant.LoginType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.zcs.ruijiwaimai.util.RegexpTool.isInValidPhoneNumber;

public class SimpleTest {

    @Test
    public void test() {
        System.out.println(isInValidPhoneNumber("18851693525"));
    }
}
