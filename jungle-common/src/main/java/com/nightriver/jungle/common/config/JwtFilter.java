package com.nightriver.jungle.common.config;

import com.alibaba.fastjson.JSONObject;
import com.nightriver.jungle.common.dto.JwtToken;
import com.nightriver.jungle.common.dto.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JwtFilter，现在改为 JWT 认证
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/18
 * @since 1.0.0
 */
@Configuration
public class JwtFilter extends UserFilter {

    Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     */
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            throw new AuthenticationException();
        }
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }


    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (logger.isErrorEnabled()) {
            logger.error("account need login for: {}", ((HttpServletRequest) request).getServletPath());
            logger.info(((HttpServletRequest) request).getHeader("Authorization"));
            response.setContentType("application/json;charset=UTF-8");
            Result result = Result.fail(HttpStatus.UNAUTHORIZED,"没有权限");
            ((HttpServletResponse) response).setStatus(401);
            response.getWriter().write(JSONObject.toJSONString(result));
        }

        // 请求被拦截后直接返回json格式的响应数据
        response.getWriter().flush();
        response.getWriter().close();
        return false;
    }
}
