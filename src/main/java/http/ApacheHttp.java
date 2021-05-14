package http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ApacheHttp {

    public static String post(String url, Map<String, String> params) {
 
        String respCtn = "";
        // 1、创建 client 实例
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 2、创建 post 实例
        HttpPost post = new HttpPost(url);
        
        ArrayList<NameValuePair> reqParams = null;
        if (params != null && !params.isEmpty()) {
            reqParams = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> e : params.entrySet()) {
                reqParams.add(new BasicNameValuePair(e.getKey(), e.getValue()));
            }
        }

        HttpResponse response = null;
        try {
            if (reqParams != null)
                // 3、设置 post 参数
                post.setEntity(new UrlEncodedFormEntity(reqParams, "UTF-8"));
            // 4、发送请求
            response = httpclient.execute(post);
            respCtn = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
     
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                }
            }
        }
        return respCtn;
    }

/**
     * http get 请求
     * 
     * @param String
     *            url
     * 
     */
    public static String doGet(String url) {
    	
        String respCtn = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpResponse response = null;
            HttpGet get = new HttpGet(url);
            response = httpclient.execute(get);
            //response.setHeader("Content-Type", "text/html; charset=gb2312");
            respCtn = EntityUtils.toString(response.getEntity(),Charset.forName("gb2312"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                }
            }
        }
        return respCtn;
    }
}
