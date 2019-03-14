package com.nightriver.jungle.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/11
 * @since 1.0.0
 */
@Controller
public class UserController {
    @RequestMapping(value = "index")
    public String index() {
        return "freemarker/index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "/login";
    }

}
