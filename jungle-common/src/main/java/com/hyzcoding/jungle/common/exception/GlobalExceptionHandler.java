package com.hyzcoding.jungle.common.exception;

import com.hyzcoding.jungle.common.dto.Result;
import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈全局异常捕捉〉<br>
 * 〈 〉
 *
 * @author hyz
 *  2019/3/28
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 捕捉shiro的异常
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public Result handle401() {
        return Result.fail(HttpStatus.UNAUTHORIZED, "您没有权限访问！");
    }

    /**
     * 捕捉其他所有异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(HttpServletRequest request, Throwable ex) {
        logger.warn(ex.getMessage());
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
