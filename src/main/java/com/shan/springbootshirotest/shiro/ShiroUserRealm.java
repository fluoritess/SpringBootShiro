package com.shan.springbootshirotest.shiro;

import com.shan.springbootshirotest.mapper.userMapper;
import com.shan.springbootshirotest.pojo.user;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class ShiroUserRealm extends AuthorizingRealm {
    @Autowired
    userMapper usermapper;
    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权...");

        user user = (user) principals.getPrimaryPrincipal();


        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        permsSet.add("admin");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("用户认证...");
        //用户信息获取
        String userNameInput=(String)token.getPrincipal();
        String passwordInput=new String((char[])token.getCredentials());
        //查询用户信息
        List<user> userList=usermapper.selectByExample(null);
        Iterator<user> iterator=userList.iterator();
        user targetuser=null;
        while(iterator.hasNext()){
            user user=iterator.next();
            if(user.getUsername().equals(userNameInput)){
                targetuser=user;
            }
        }
      /*  user targetuser=new user(1,"shan","123",1);*/
        //用户不存在
        if(targetuser==null){
            throw new UnknownAccountException("用户名或者密码错误！");
        }
        //密码错误
        if(!passwordInput.equals(targetuser.getUserpassword())){
            throw new IncorrectCredentialsException("用户名或者密码错误！");
        }

        System.out.println("用户登陆成功!");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(targetuser, targetuser.getUserpassword(), getName());
        return info;
    }
}