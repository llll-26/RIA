package com.ruoyi.common.utils;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import java.io.InputStream;

/**
 *
 * @date
 * @Version
 */
public class QINiuUtils {
    private static Configuration cfg;
    private static UploadManager uploadManager ;
    private static String accessKey = "jMof0ofUCR3i2p6nKI1B4ZcVONOixvYtToZ7LjD8";//
    private static String secretKey = "iCodhEnim6Gk6EQVy5GlD3RBa0NfDxNrspki_aoJ";//
    private static String bucket = "cqust";//
    //构造一个带指定Region对象的配置类
    static {
        cfg = Configuration.create(Region.autoRegion());
        cfg.resumableUploadAPIVersion =
                Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        // 设置请求为http
        cfg.useHttpsDomains = false;
        uploadManager = new UploadManager(cfg);
    }
    /**
     *
     * <p>
     * 说明:
     * </p>
     * @param inputStream 输入流
     * @param key 文件名
     * @author tanyang at
     */
    public static void upload(InputStream inputStream,String key){
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream,key,upToken,null,
                    null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),
                    DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);
                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {
                }
            }
        }
    }
}
