package com.han.gp.domain.enums;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum UserStatusEnum implements Serializable {

    Enable(true, "启用"),
    Disable(false, "禁用");

    boolean code;
    String name;

    UserStatusEnum(boolean code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<Boolean, UserStatusEnum> keyMap = new HashMap<>();

    static {
        for (UserStatusEnum item : UserStatusEnum.values()) {
            keyMap.put(item.getCode(), item);
        }
    }

    public static UserStatusEnum fromCode(Boolean code) {
        return keyMap.get(code);
    }

    public boolean getCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
