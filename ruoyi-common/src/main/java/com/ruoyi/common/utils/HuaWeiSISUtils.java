package com.ruoyi.common.utils;
/**
 * @author huangjie
 * @date 2025/11/25
 */
import com.huaweicloud.sdk.core.auth.BasicCredentials;
import com.huaweicloud.sdk.core.auth.ICredential;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.sis.v1.SisClient;
import com.huaweicloud.sdk.sis.v1.model.*;
import com.huaweicloud.sdk.sis.v1.region.SisRegion;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanyang
 * @className
 * @date
 * @Version
 */

public class HuaWeiSISUtils {
    private static String ak = "HPUA1FQLEWTAABJMTWJN";
    private static String sk = "JGdbplBM9OEFUCiNcdyi3CHZERZ9xzFG9l7STzmV";

    private static final int SLEEP_TIME = 500;
    private static final int MAX_POLLING_NUMS = 1000;
    private static  String region = "cn-north-4";    // 区域，如cn-north-1、cn
    private static String projectId = "593fba396f1146b9a8f0719417337bc6"; // 项目id。登录管理控制台，鼠标移

    public static String doSubmit(String url){
        TranscriberConfig configbody = new TranscriberConfig();
        ICredential auth = new BasicCredentials()
                .withAk(ak)
                .withSk(sk);
        SisClient client = SisClient.newBuilder()
                .withCredential(auth)
                .withRegion(SisRegion.valueOf(region))
                .build();
        PushTranscriberJobsRequest request = new PushTranscriberJobsRequest();
        PostTranscriberJobs body = new PostTranscriberJobs();
        //TranscriberConfig configbody = new TranscriberConfig();

        configbody.withProperty(TranscriberConfig.PropertyEnum.fromValue("chinese_8k_common"));
                body.withDataUrl(url);
        body.withConfig(configbody);
        request.withBody(body);
        try {
            PushTranscriberJobsResponse response =
                    client.pushTranscriberJobs(request);
            return response.getJobId();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getRequestId());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
        return "";
    }

    public static Map<String, Object> getResult(String jobId){
        int count = 0;
        Map<String, Object> map = new HashMap<>();
        ICredential auth = new BasicCredentials()
                .withAk(ak)
                .withSk(sk);
        SisClient client = SisClient.newBuilder()
                .withCredential(auth)
                .withRegion(SisRegion.valueOf(region))
                .build();
        CollectTranscriberJobRequest request = new
                CollectTranscriberJobRequest();
        request.withJobId(jobId);
        try {
            while (count<MAX_POLLING_NUMS) {
                //发送请求
                CollectTranscriberJobResponse response =
                        client.collectTranscriberJob(request);
                System.out.println(response.toString());
                //根据请求状态进行判断
                if("FINISHED".equals(response.getStatus())){//调用结束
                    //语音识别内容
                    String text =
                            response.getSegments().get(0).getResult().getText();
                    //回答时长
                    Integer audioDuration = response.getAudioDuration();
                    map.put("text", text);
                    map.put("audioDuration", audioDuration);
                    return map;
                }
                Thread.sleep(SLEEP_TIME);
                count++;
            }
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getRequestId());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
    public static void main(String[] args) {
        String jobId= doSubmit("http://t69u16fyj.hn-bkt.clouddn.com/answer_3.mp3");
        Map<String, Object> result = getResult(jobId);
        System.out.println(result);
    }


}

