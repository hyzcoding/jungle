package com.hyzcoding.jungle.common.dto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 〈RESTFUL返回标准格式DTO〉<br>
 * 〈 〉
 *
 * @author hyz
 * @date 2019/3/7
 * @since 1.0.0
 */
@Component
public class Result<T> {
    private HttpStatus code = HttpStatus.OK;
    private String message;
    private T data;

    public static Result fail(HttpStatus code,String message) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code:" + code +
                ", message:'" + message + '\'' +
                ", data:" + data +
                '}';
    }
}
