package com.hyzcoding.jungle.common.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈邮件工具〉<br>
 * 〈 〉
 *
 * @author hyz
 * @date 2019/3/7
 * @since 1.0.0
 */
public class MailUtil {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.from}")
    private String from;


    public static String random(int length) {

        String random = "";
        /*随机数函数*/
        java.util.Random r = new java.util.Random();
        for (int i = 0; i < length; i++) {
            /*生成36以内的随机数，不含36，并转化为36位*/
            random += Integer.toString(r.nextInt(10));
        }
        return random;
    }

    public static String html(String code) throws Exception {
        String mailTemplatesPath = "/mail";
        String fileName = "/register.ftl";
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassLoaderForTemplateLoading(MailUtil.class.getClassLoader(), mailTemplatesPath);
        Template template = configuration.getTemplate(fileName);
        Map<String, String> model = new HashMap<>(1);
        model.put("code", code);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        return html;
    }
}
