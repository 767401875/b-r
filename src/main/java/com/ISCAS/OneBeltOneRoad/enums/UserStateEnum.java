package com.ISCAS.OneBeltOneRoad.enums;

public enum  UserStateEnum {
    Check(0, "审核中"),
    OFFLINE(-1,"用户不满足要求"),
    SUCCESS(1, "操作成功"),
    PASS(2, "审核通过"),
    INNER_ERROR(-1001, "内部系统错误"),
    NULL_ID(-1002, "用户ID为空"),
    NULL_USER(-1003,"用户信息为空");
    private int state;
    private String stateInfo;
    UserStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static UserStateEnum stateOf(int state){
        for(UserStateEnum stateEnum : values()){
            if(stateEnum.getState() == state)
                return stateEnum;
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
