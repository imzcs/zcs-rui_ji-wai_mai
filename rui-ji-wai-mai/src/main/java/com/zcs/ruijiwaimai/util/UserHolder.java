package com.zcs.ruijiwaimai.util;

import com.zcs.ruijiwaimai.pojo.dto.UserDTO;

public class UserHolder {
    private static final ThreadLocal<UserDTO> threadLocal = new ThreadLocal<>();

    public static UserDTO getUser() {
        return threadLocal.get();
    }

    public static void set(UserDTO userDTO) {
        threadLocal.set(userDTO);
    }

    public static void remove() {
        threadLocal.remove();
    }

}
