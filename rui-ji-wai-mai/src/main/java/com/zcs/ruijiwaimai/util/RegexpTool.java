package com.zcs.ruijiwaimai.util;

import java.util.regex.Pattern;

import static com.zcs.ruijiwaimai.constant.RegexpConstant.PHONE_REGEXP;

public class RegexpTool {

    public static boolean isInValidPhoneNumber(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        return Pattern.matches(PHONE_REGEXP, phone);
    }

}
