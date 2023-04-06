package com.oysen.controller;

import com.oysen.bean.TblUserRecord;
import com.oysen.result.Permission;
import com.oysen.result.Permissions;
import com.oysen.result.R;
import com.oysen.result.UserInfo;
import com.oysen.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    //测试
    @RequestMapping("/test")
    public String test() {
        return "测试连接" ;
    }

    // 解决前端报错
    @RequestMapping("/auth/2step-code")
    public boolean step_code2() {
        System.out.println("此请求是前端框架带的默认请求，可以不做任何处理，也可以在前端将其删除");
        System.out.println("step_code2");
        return true;
    }

    //登录
    @RequestMapping("/auth/login")
    //@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
    public R login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpSession session) {
        System.out.println("收到前端请求!");
        TblUserRecord tblUserRecord = loginService.login(username, password);
        tblUserRecord.setToken(tblUserRecord.getUserName());
        //将用户信息存入Session
        session.setAttribute("userRecord",tblUserRecord);

        R r = new R(200,"ok",tblUserRecord);
        System.out.println(username + ":" + password);
        System.out.println(tblUserRecord);
        return r;
    }

    //处理登录业务
    @RequestMapping("/user/info")
    public R getInfo(HttpSession session) {
        //获取用户信息
        TblUserRecord tblUserRecord = (TblUserRecord) session.getAttribute("userRecord");
        //获取用户对应功能
        String[] rolePrivileges = tblUserRecord.getTblRole().getRolePrivileges().split("-");
        //拼接需要返回的数据对象格式
        Permissions permissions = new Permissions();
        List<Permission> permissionList = new ArrayList<>();
        for (String s : rolePrivileges) {
            permissionList.add(new Permission(s));
        }
        permissions.setPermissions(permissionList);
        UserInfo userInfo = new UserInfo(tblUserRecord.getUserName(),permissions);
        return new R(userInfo);
    }
    //处理登出业务
    @RequestMapping("/auth/logout")
    public void logOut(HttpSession session) {
        System.out.println("logout");
        session.invalidate();//将Session设置失效
    }
}
