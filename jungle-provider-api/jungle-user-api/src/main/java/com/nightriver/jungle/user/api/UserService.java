package com.nightriver.jungle.user.api;

import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * userService API
 */
@FeignClient("jungle-user")
public interface UserService {
    /**
     * 用户登录
     * @param user
     * @return
     */
    @GetMapping("/login")
    Result login(User user);
}
