package com.nightriver.jungle.user.api.impl;

import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.pojo.UserInfo;
import com.nightriver.jungle.user.api.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/4/2
 * @since 1.0.0
 */
@Component
public class UserHystrix implements UserService {
    @Override
    public Result vCode(String email) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result register(String name, String password, String email, Integer sex, String vcode, String dbvcode) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result add(User user) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result<String> login(User user) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result logout(User user) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result<UserInfo> getInfo(Integer id) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result<User> get(Integer id) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result getList(String keyWords, Integer pageNum) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result modifyPwd(User user) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result<User> modify(User user) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result modifyInfo(UserInfo userInfo) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result remove(int id) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result upload(MultipartFile avatar) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }
}
