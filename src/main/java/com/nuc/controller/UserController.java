package com.nuc.controller;

import com.nuc.pojo.User;
import com.nuc.service.UserService;
import com.nuc.utils.JwtHelper;
import com.nuc.utils.Result;
import com.nuc.utils.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin//跨域
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result=userService.login(user);
        return result;
    }


    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result result= userService.getUserInfo(token);
        return result;
    }


    @PostMapping("checkUserName")
    public Result checkUserName(String username){
        Result result=userService.checkUserName(username);
        return result;
    }


    @PostMapping("regist")
    public Result regist(@RequestBody User user){
        Result result=userService.regist(user);
        return result;
    }


    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration){
            //已经过期
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }
}
