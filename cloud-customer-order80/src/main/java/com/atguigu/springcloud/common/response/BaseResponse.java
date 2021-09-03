package com.atguigu.springcloud.common.response;

import com.atguigu.springcloud.common.enums.ResponseCodeEnum;

/**
 * @author wangqc
 * @version V2.0
 * @Description:
 * @date 2020/2/7
 * @time 21:44
 */
public class BaseResponse {
    private int code = ResponseCodeEnum.SUCCESS.getCode();
    private String msg = ResponseCodeEnum.SUCCESS.getDesc();

    public BaseResponse() {
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
