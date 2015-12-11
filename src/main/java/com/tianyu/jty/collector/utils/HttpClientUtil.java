package com.tianyu.jty.collector.utils;

import com.tianyu.jty.common.utils.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Map;


/**
 * Created by xtao on 2015/11/9.
 */
public class HttpClientUtil {

    private HttpClient httpClient;

    private HttpClientUtil(){
        httpClient = HttpClients.createDefault();
    }

    public static HttpClientUtil getInctence(){
        return new HttpClientUtil();
    }

    public String doGet(URI uri){
        HttpResponse response = null;
        String result = "";
        HttpGet httpGet = new HttpGet(uri);
        httpGet.addHeader("Content-Type", "text/html;charset=UTF-8");
        try {
            response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            httpGet.releaseConnection();
        }
        return result;
    }

    public String doGet(String url){
        return doGet(url, null);
    }

    public String doGet(String url, Header header) {
       return doGet(url, header ,null);
    }

    public String doGet(String url, Header header, Map params) {
        HttpResponse response = null;
        String result = "";
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-Type", "text/html;charset=UTF-8");
        if(header != null) {
            httpGet.setHeader(header);
        }
        if(params!=null){
            httpGet.setConfig(RequestConfig.DEFAULT);
        }
        try {
            response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            httpGet.releaseConnection();
        }
        return result;
    }
}
