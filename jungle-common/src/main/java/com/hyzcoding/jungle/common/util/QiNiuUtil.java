package com.hyzcoding.jungle.common.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @date 2019/3/27
 * @since 1.0.0
 */
@Component
public class QiNiuUtil {

    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private String secretKey;
    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.path}")
    private String path;

    /**
     * 将图片上传到七牛云
     */
    public String uploadQNImg(FileInputStream file, String key) {
        // 构造一个带指定Zone对象的配置类,zone2华南
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String returnPath = path + "/" + putRet.key;
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
