package com.ycjw.service.util;

import com.alibaba.fastjson.JSON;
import com.ycjw.service.config.SSLSocketClient;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Map;

@Slf4j
public class HttpUtil {
    private static final OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager())
            .build();

    /**
     * Get连接网络
     *
     * @param url 网络URL
     * @return API返回值
     */
    public static String get(String url) {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String bodyPost(String url, Object body){
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(body));
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    /**
     * Post连接网络
     *
     * @param url    网络URL
     * @param params 参数集合
     * @return API返回值
     */
    public static String post(String url, Map<String, String> params) {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            //迭代参数集合
            params.forEach(builder::add);
            RequestBody requestBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return "";
    }
}
