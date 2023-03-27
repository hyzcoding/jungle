package com.hyzcoding.jungle.gateway;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @date 2019/1/31
 * @since 1.0.0
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("user接口", "/user/v2/api-docs", "0.0.1-SNAPSHOT"));
        resources.add(swaggerResource("article接口", "/article/v2/api-docs", "0.0.1-SNAPSHOT"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
