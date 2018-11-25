package com.fh;



import com.google.gson.Gson;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpUilt {

    //delete请求
    public static String sendDelete(String url, Integer id, Map<String, String> headers, boolean isValidate){
        //打开浏览器
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //输入网址
        HttpDelete httpDelete = new HttpDelete(url + "/" + id+".jhtml");
        //设置请求头
        buildHeadrs(headers,httpDelete,isValidate);
        //敲回车
        CloseableHttpResponse response = null;
        String result="";
        try {
            response = client.execute(httpDelete);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(response!=null){
                    response.close();
                    response=null;
                }
                if(httpDelete!=null){
                    httpDelete.releaseConnection();
                }
                if(client!=null){
                    client.close();
                    client=null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    //put请求
    public static String sendPut(String url, Map<String,String> params, Map<String, String> headers, boolean isValidate){
        //打开浏览器
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //输入网址
        HttpPut httpPut = new HttpPut(url);
        //设置参数
        if(params!=null&&!params.isEmpty()){
            Gson gson = new Gson();
            String data = gson.toJson(params);
            try {
                StringEntity entity = new StringEntity(data);
                httpPut.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        httpPut.setHeader("Content-Type","application/json");
        //设置头信息
        buildHeadrs(headers, httpPut, isValidate);
        //敲回车
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = client.execute(httpPut);
            result  = EntityUtils.toString(response.getEntity() , "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != response) {
                    response.close();
                    response=null;
                }
                if(null != httpPut) {
                    httpPut.releaseConnection();
                }
                if(null != client) {
                    client.close();
                    client=null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }



    public static String sendPost(String url, Map<String,String> params, Map<String, String> headers,boolean isValidate){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        if(null!=headers && headers.size()>0) {
            //设置请求头
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> map = iterator.next();
                String key = map.getKey();
                String value = map.getValue();
                httpPost.addHeader(key, value);
            }
        }

        if(null!=params && params.size()>0) {
            //设置请求体
            List<NameValuePair> pairs = new ArrayList();
            Iterator<Map.Entry<String, String>> headersIterator = params.entrySet().iterator();
            while (headersIterator.hasNext()) {
                Map.Entry<String, String> map = headersIterator.next();
                String key = map.getKey();
                String value = map.getValue();
                pairs.add(new BasicNameValuePair(key, value));
            }
            UrlEncodedFormEntity entity = null;
            try {
                entity = new UrlEncodedFormEntity(pairs, "utf-8");
                httpPost.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != response) {
                    response.close();
                    response=null;
                }
                if(null != httpPost) {
                    httpPost.releaseConnection();
                }
                if(null != httpClient) {
                    httpClient.close();
                    httpClient=null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }


    public static String sendGet(String url, Map<String,String> params, Map<String, String> headers,boolean isValidate){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = null;

        if(null!=params && params.size()>0) {
            //设置请求体
            List<NameValuePair> pairs = new ArrayList();
            Iterator<Map.Entry<String, String>> headersIterator = params.entrySet().iterator();
            while (headersIterator.hasNext()) {
                Map.Entry<String, String> map = headersIterator.next();
                String key = map.getKey();
                String value = map.getValue();
                pairs.add(new BasicNameValuePair(key, value));
            }

            try {
                String str = EntityUtils.toString(new UrlEncodedFormEntity(pairs, "utf-8"));
                url = url+"?"+str;


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        httpGet = new HttpGet(url);

        //设置请求头
        buildHeadrs(headers, httpGet,isValidate);

        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != response) {
                    response.close();
                    response=null;
                }
                if(null != httpGet) {
                    httpGet.releaseConnection();
                }
                if(null != httpClient) {
                    httpClient.close();
                    httpClient=null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    //设置请求头
    private static void buildHeadrs(Map<String, String> headers, HttpUriRequest request, boolean isValidate) {
        if(headers!=null&&headers.size()>0){
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                request.addHeader(key,value);
            }
        }
        if(isValidate){

            Map<String,String> haders=new HashMap<String,String>();
            String time = new Date().getTime()+"";
            String nonce=RandomStringUtils.randomAlphabetic(4)+new Date().getTime();
            String sign = CheckSumBuilder.getCheckSum("ee441cd0-7ff9-43c0-a73e-baaa6cc8d699", nonce, time);
            haders.put("appkey","663fc1a7-1f44-4729-8bc2-6729a446102a");
            haders.put("time",time);
            haders.put("sign",sign);
            haders.put("nonce",nonce);
        }
    }


}