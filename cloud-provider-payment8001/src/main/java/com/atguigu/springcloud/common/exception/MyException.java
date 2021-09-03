package com.atguigu.springcloud.common.exception;

import com.atguigu.springcloud.common.response.BaseResponse;
import com.atguigu.springcloud.common.response.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 统一处理异常
 * @Author: wangQc
 * @Date: 2020/9/29
 * @Time: 20:16
 */
@ControllerAdvice
@Slf4j
public class MyException {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public BaseResponse exception(Exception e, HttpServletRequest request) {
		String uri = request.getRequestURI();
		StringBuilder stringBuilder = new StringBuilder();
		//自定义异常
		if (e instanceof ServiceException) {
			ServiceException se = (ServiceException) e;
			log.error(uri + "====异常：" + se.getMsg());
			return ResponseBuilder.failed(se.getData(), se.getCode(), se.getMsg());
		}
		//绑定异常
		else if (e instanceof BindException) {
			BindException bindException = (BindException) e;
			List<ObjectError> allErrors = bindException.getAllErrors();
			for (ObjectError allError : allErrors) {
				stringBuilder.append(allError.getDefaultMessage());
			}
			log.error(uri + "====异常：" + stringBuilder.toString());
			return ResponseBuilder.failed(stringBuilder.toString());
		}
		//参数校验异常
		else if (e instanceof MethodArgumentNotValidException){
			MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
			BindingResult result = methodArgumentNotValidException.getBindingResult();
			FieldError error = result.getFieldError();
			String code = error != null ? error.getDefaultMessage() : null;
			stringBuilder.append(code);
			log.error(uri+"..出现异常.."+ stringBuilder.toString());
			return ResponseBuilder.failed(stringBuilder.toString());
		}
		//参数缺失异常
		else if (e instanceof MissingServletRequestParameterException) {
			MissingServletRequestParameterException parameterException = (MissingServletRequestParameterException) e;
			log.error(uri + "====异常：" + parameterException.getMessage());
			return ResponseBuilder.failed(parameterException.getMessage());
		} else {
			log.error(uri + "====异常：" + e.getMessage(), e);
			return ResponseBuilder.failed();
		}
	}
}
