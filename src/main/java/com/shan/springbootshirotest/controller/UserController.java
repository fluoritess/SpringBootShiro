package com.shan.springbootshirotest.controller;


import com.google.code.kaptcha.Constants;
import com.shan.springbootshirotest.util.R;
import com.shan.springbootshirotest.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @ResponseBody
    @RequestMapping("/login.action" )
    public R login(@RequestBody Map<String,String> map, HttpSession session){
        System.out.println("进入登录...");
        String username=map.get("name");
        String password=map.get("password");
        String code=map.get("code");

//        String kaptcha= ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//        if(!kaptcha.equalsIgnoreCase(code)){
//            return R.error("验证码不正确");
//        }
        try {
            Subject subject=ShiroUtils.getSubject();
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return R.error(e.getMessage());
        }catch (LockedAccountException e) {
            return R.error(e.getMessage());
        }catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }
        session.setAttribute("user",ShiroUtils.getUserEntity());
        session.setAttribute("id",ShiroUtils.getUserEntity().getId());
        Map<String,Object> msg=new HashMap<>();
        msg.put("code","0");
        msg.put("id",ShiroUtils.getUserEntity().getId());
        return R.ok().put("data",msg);
    }
}
