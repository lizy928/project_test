package com.dlion.testproject.controller;

import com.dlion.testproject.annotation.ChatClintPermission;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李正元
 * @date 2019/8/16
 */
@RestController
public class UserController {

    @RequestMapping("/getUser")
    @ChatClintPermission
    public Object getUser() {
        return "success";
    }


}
