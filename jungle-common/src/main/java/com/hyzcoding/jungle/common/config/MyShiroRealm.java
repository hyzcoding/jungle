package com.hyzcoding.jungle.common.config;

import com.hyzcoding.jungle.common.dto.JwtToken;
import com.hyzcoding.jungle.common.pojo.User;
import com.hyzcoding.jungle.common.service.SsoService;
import com.hyzcoding.jungle.common.util.JwtUtil;
import com.hyzcoding.jungle.common.util.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * @author hyz
 * @date 2019/3/2
 * @version 1.0
 **/
@Service
public class MyShiroRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    @Autowired
    SsoService ssoService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

        String token = (String) principalCollection.getPrimaryPrincipal();
        String userEml = JwtUtil.getEml(token);
        User user = new User();
        user.setUserEml(userEml);
        user = ssoService.login(user);
        SimpleAuthorizationInfo authorizationInfo = addRoles(Role.valueOf(user.getUserRole()));
        return authorizationInfo;
    }


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 认证
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        logger.info("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String token = (String) auth.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        String userEml = JwtUtil.getEml(token);
        if (userEml == null) {
            return null;
        }
        User user = new User();
        user.setUserEml(userEml);
        User userDb = ssoService.login(user);
        logger.info("----->>userInfo=" + userDb.getUserPwd());
        if (userDb == null) {
            return null;
        }
        if (!JwtUtil.verify(token, userEml, userDb.getUserPwd())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                token, token,
                //salt=password
//                ByteSource.Util.bytes(userDb.getUserPwd()),
                //realm name
                getName()
        );
        return authenticationInfo;
    }

    private SimpleAuthorizationInfo addRoles(Role role) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (Role.ADMIN.equals(role)) {
            for (Role admin : Role.values()) {
                authorizationInfo.addRole(admin.toString());
            }
        } else if (Role.MODERATOR.equals(role)) {
            authorizationInfo.addRole(role.toString());
            authorizationInfo.addRole(Role.GUEST.toString());
            authorizationInfo.addRole(Role.USER.toString());
        } else if (Role.USER.equals(role)) {
            authorizationInfo.addRole(Role.GUEST.toString());
            authorizationInfo.addRole(Role.USER.toString());
        }
        return authorizationInfo;
    }

}
