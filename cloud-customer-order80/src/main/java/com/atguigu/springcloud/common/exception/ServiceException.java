package com.atguigu.springcloud.common.exception;

import com.atguigu.springcloud.common.enums.ResponseCodeEnum;
import lombok.Data;

/**
 * @Description: 业务逻辑异常
 */
@Data
public class ServiceException extends RuntimeException {

    private Integer code = ResponseCodeEnum.SERVICE_EXCEPTION.getCode();
    private String msg;
    private Object data;

    public ServiceException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ServiceException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(Object data,Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
