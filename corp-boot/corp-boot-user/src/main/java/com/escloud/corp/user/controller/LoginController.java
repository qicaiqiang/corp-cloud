package com.escloud.corp.user.controller;

import com.escloud.corp.user.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 登陆接口测试
 * @Author qicaizhao
 */
@RestController
@RequestMapping(value = "**",method = {RequestMethod.POST, RequestMethod.GET})
@Api(value = "用户登陆登出操作", description = "用户登陆登出操作")
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ApiImplicitParam(name = "userEntity", value = "用户登录实体", required = true, dataType = "UserEntity")
    @ApiOperation(value="用户登录", notes="用户登录",httpMethod ="POST" )
    public UserEntity login(@RequestBody UserEntity userEntity) {

        if (userEntity != null)
            return userEntity;
        else
            return null;
    }
}
