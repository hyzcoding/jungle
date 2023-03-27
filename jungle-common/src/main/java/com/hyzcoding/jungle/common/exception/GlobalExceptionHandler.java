package com.hyzcoding.jungle.common.exception;

import com.hyzcoding.jungle.common.dto.Result;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @date 2019/3/28
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public Result handle401() {
        return Result.fail(HttpStatus.UNAUTHORIZED, "您没有权限访问！");
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(HttpServletRequest request, Throwable ex) {
        if (ex instanceof NullPointerException) {
            return Result.fail(HttpStatus.BAD_REQUEST, "未填写参数");
        } else if (ex instanceof NumberFormatException || ex instanceof MethodArgumentTypeMismatchException) {
            return Result.fail(HttpStatus.BAD_REQUEST, "参数不合法");
        } else if (ex instanceof NotExistException) {
            return Result.fail(HttpStatus.NO_CONTENT,ex.getMessage());
        } else if(ex instanceof HttpRequestMethodNotSupportedException){
            return Result.fail(HttpStatus.BAD_REQUEST, "请求类型错误");
        } else {
            return Result.fail(HttpStatus.BAD_REQUEST, "未知错误");
        }
    }
}
