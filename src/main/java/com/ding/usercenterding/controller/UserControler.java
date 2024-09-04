package com.ding.usercenterding.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ding.usercenterding.common.BaseResponse;
import com.ding.usercenterding.common.ResultUtils;
import com.ding.usercenterding.mapper.UserMapper;
import com.ding.usercenterding.model.domain.User;
import com.ding.usercenterding.model.domain.request.UserLoginRequest;
import com.ding.usercenterding.model.domain.request.UserRegisterRequest;
import com.ding.usercenterding.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户接口
 *
 * @author ding
 */

@RestController     //适用于编写restful 风格的api，返回默认值为Json类型
@RequestMapping("/user")
public class UserControler {
    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    /**
     *  用户注册
     * @param userRegisterRequest
     * @return  用户的id
     */

    @PostMapping("/register")
   public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        //@RequestBody 代表的是让SpringMVC框架与前端传过来的参数做一个关联
        if(userRegisterRequest == null){
                return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String plantCode = userRegisterRequest.getPlaneCode();
        //前端校验传过来的参数 不涉及业务逻辑校验
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,plantCode)) {
            return null;
        }

        long result = userService.userRegister(userAccount, userPassword, checkPassword, plantCode);
        return ResultUtils.success(result);

    }

    /**
     * 用户登录
     * @param userLoginRequest  采用一个类封装所有请求参数
     * @param request   请求参数 保存登录态
     * @return
     */

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        //@RequestBody 代表的是让SpringMVC框架与前端传过来的参数做一个关联
        if(userLoginRequest == null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        //前端校验传过来的参数 不涉及业务逻辑校验
        if(StringUtils.isAnyBlank(userAccount,userPassword)) {
            return null;
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        //@RequestBody 代表的是让SpringMVC框架与前端传过来的参数做一个关联
        if(request == null){
            return null;
        }
        int i = userService.userLogout(request);
        return ResultUtils.success(i);
    }


    @GetMapping("/current")
    public BaseResponse<User> getcCurrentUser(HttpServletRequest request){
        User currentUser = userService.getCurrentUser(request);
        return ResultUtils.success(currentUser);
    }


    /**
     * 查找用户
     * @param username
     * @param request
     * @return
     */

    @GetMapping("/search")
    BaseResponse<List<User>>  searchUsers(String username,HttpServletRequest request){
        List<User> userList = userService.searchUser(username, request);
        return ResultUtils.success(userList);
    }

    /**
     *
     * 删除用户
     */

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request){
        boolean b = userService.deleteUser(id, request);
         return ResultUtils.success(b);
    }


















}
