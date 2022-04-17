package handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.ResponseBuilder;

public class GlobalHandler {
    public static BaseResponse handler(BlockException exception) {
        return ResponseBuilder.failed("服务器过忙");
    }
}
