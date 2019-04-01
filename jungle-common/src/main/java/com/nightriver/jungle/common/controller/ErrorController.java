package com.nightriver.jungle.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/15
 * @since 1.0.0
 */
@Controller
public class ErrorController {

    @GetMapping("/error")
    @ResponseBody
    public String error(){
        return "error";
    }
}
