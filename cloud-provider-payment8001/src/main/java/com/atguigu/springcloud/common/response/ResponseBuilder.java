package com.atguigu.springcloud.common.response;

import com.atguigu.springcloud.common.enums.ResponseCodeEnum;
import com.github.pagehelper.PageInfo;


public class ResponseBuilder {
    public ResponseBuilder() {
    }

    public static <T> DataResponse<T> ok() {
        return dataResponse(null, ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getDesc());
    }

    public static <T> DataResponse<T> ok(T data) {
        return dataResponse(data, ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getDesc());
    }

    public static <T> DataResponse<T> ok(T data, String message) {
        return dataResponse(data, ResponseCodeEnum.SUCCESS.getCode(), message);
    }

    public static <T> DataResponse<T> failed() {
        return dataResponse(null, ResponseCodeEnum.FAILED.getCode(), ResponseCodeEnum.FAILED.getDesc());
    }

    public static <T> DataResponse<T> failed(int code, String msg) {
        return dataResponse(null, code, msg);
    }

    public static <T> DataResponse<T> failed(T data, int code, String msg) {
        return dataResponse(data, code, msg);
    }

    /**
     * 自定义异常返回
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> DataResponse<T> failed(String msg) {
        return dataResponse(null, ResponseCodeEnum.SERVICE_EXCEPTION.getCode(), msg);
    }


    private static BaseResponse baseResponse(int code, String msg) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        return baseResponse;
    }


    public static <T> PageResponse<T> page(PageInfo<T> pageInfo) {
        return pageResponse(pageInfo);
    }

    private static <T> DataResponse<T> dataResponse(T data, int code, String msg) {
        DataResponse<T> dataResponse = new DataResponse<>();
        dataResponse.setCode(code);
        dataResponse.setData(data);
        dataResponse.setMsg(msg);
        return dataResponse;
    }

    private static <T> PageResponse<T> pageResponse(PageInfo<T> pageInfo) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setData(pageInfo.getList());
        pageResponse.setTotal(pageInfo.getTotal());
        pageResponse.setPages(pageInfo.getPages());
        pageResponse.setPageNum(pageInfo.getPageNum());
        pageResponse.setStartRow(pageInfo.getStartRow());
        pageResponse.setEndRow(pageInfo.getEndRow());
        pageResponse.setPrePage(pageInfo.getPrePage());
        pageResponse.setNextPage(pageInfo.getNextPage());
        pageResponse.setFirstPage(pageInfo.isIsFirstPage());
        pageResponse.setLastPage(pageInfo.isIsLastPage());
        pageResponse.setHasNextPage(pageInfo.isHasNextPage());
        pageResponse.setHasPreviousPage(pageInfo.isHasPreviousPage());
        pageResponse.setNavigatepageNums(pageInfo.getNavigatepageNums());
        pageResponse.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
        pageResponse.setNavigateLastPage(pageInfo.getNavigateLastPage());
        return pageResponse;
    }
}
