package com.nightriver.jungle.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/28
 * @since 1.0.0
 */
@Configuration
public class JwtFeignInterceptor implements RequestInterceptor {

    private final String TOKEN_HEADER = "authorization";

    @Override
    public void apply(RequestTemplate template) {
        template.header(TOKEN_HEADER, getHeaders(getHttpServletRequest()).get(TOKEN_HEADER));
    }
    private javax.servlet.http.HttpServletRequest getHttpServletRequest() {
        try {
//            RequestContextHolder.getRequestAttributes().
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, String> getHeaders(javax.servlet.http.HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        System.out.println(map);
        return map;
    }
}
