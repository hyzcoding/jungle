package com.nightriver.jungle.common.config;

import com.nightriver.jungle.common.dto.JwtToken;
import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.service.SsoService;
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


/**
 * @Description:
 * @Author: hyz
 * @CreateDate: 2019/3/2
 * @Version: 1.0
 **/
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
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        logger.info("user对象："+user.toString());
        authorizationInfo.addRole(user.getUserRole());
        authorizationInfo.addStringPermission(user.getUserRole());
        return authorizationInfo;
    }


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String userEml = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = new User();
        user.setUserEml(userEml);
        User userDb = ssoService.login(user);
        logger.info("----->>userInfo=" + userDb.getUserPwd());
        if (userDb == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userDb,
                userDb.getUserPwd(),
                //salt=password
//                ByteSource.Util.bytes(userDb.getUserPwd()),
                //realm name
                getName()
        );
        return authenticationInfo;
    }
}
