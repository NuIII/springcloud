package com.atguigu.springcloud.common.enums;

/**
 * @author wangqc
 * @version V2.0
 * @Description:
 * @date 2020/2/7
 * @time 21:40
 */
public enum ResponseCodeEnum {
    FAILED(0, "系统错误，请联系管理员"),
    SUCCESS(1, "请求成功"),

    NULL_TOKEN(2, "登录信息失效,请重新登录"),

    NULL_ACCESS_TOKEN(3, "ACCESS_TOKEN无效"),

    NOT_WHITE_IP(4, "ip未在白名单中"),

    SERVICE_EXCEPTION(1000, "自定义异常");


    private int code;
    private String desc;

    private ResponseCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
