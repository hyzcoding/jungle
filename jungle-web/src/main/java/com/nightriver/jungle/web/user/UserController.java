package com.nightriver.jungle.web.user;

import com.nightriver.jungle.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/11
 * @since 1.0.0
 */
@RestController
public class UserController {
    @RequestMapping(value = "index")
    public String index() {
        return "freemarker/index";
    }
    @RequestMapping(value = "login")
    public String login() {
        return "freemarker/login";
    }

}
