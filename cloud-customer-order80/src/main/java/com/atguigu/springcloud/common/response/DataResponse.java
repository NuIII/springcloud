package com.atguigu.springcloud.common.response;

/**
 * @author wangqc
 * @version V2.0
 * @Description:
 * @date 2020/2/7
 * @time 21:44
 */
public class DataResponse<T> extends BaseResponse {
    private T data;

    public DataResponse() {
    }

    public DataResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}