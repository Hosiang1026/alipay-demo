package com.zjs.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RestUtil {

    /**
     * Rest --> post请求方式 无参方式 （Object）
     * @param url
     * @return
     * @throws Exception
     */
	public static String restPost(String url)  throws Exception{
        String result;
        try {
            //创建restTemplate对象
            RestTemplate restTemplate = new RestTemplate();
            //创建请求头
            HttpHeaders headers = new HttpHeaders();
            //将请求头传递参数方式改为json
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            //创建Rest内部请求体
            org.springframework.http.HttpEntity<String> formEntity = new org.springframework.http.HttpEntity<String>(null, headers);
            //返回信息
            result = restTemplate.postForObject(url, formEntity, String.class);
        } catch (HttpClientErrorException e) {
                result =  e.getResponseBodyAsString();
        }
        return result;
    }


    /**
     * Rest --> post请求方式 有参方式 （Object）
     * @param url
     * @param jsonObj
     * @return
     * @throws Exception
     */
	public static String restPost(String url,String jsonObj) throws Exception{
        String result;
        try {
            // 创建restTemplate对象
            RestTemplate restTemplate = new RestTemplate();
            //创建请求头
            HttpHeaders headers = new HttpHeaders();
            //将请求头传递参数方式改为json
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            //创建Rest内部请求体
            org.springframework.http.HttpEntity<String> formEntity = new org.springframework.http.HttpEntity<String>(jsonObj.toString(), headers);
            //返回信息
            result = restTemplate.postForObject(url, formEntity, String.class);
        } catch (HttpClientErrorException e) {
            result =  e.getResponseBodyAsString();
        }
        return result;
    }


    /**
     * Rest --> get请求方式 有参方式 （Object）
     * @return
     * @throws Exception
     */
    public static String restGet(String url) throws Exception{
        // 创建restTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        //返回值
        String result;
        try {
            result = restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            result = e.getResponseBodyAsString();
        }

        return result;
    }


    /**
     * Rest --> Put请求方式 有参方式 （Object）
     * @param url
     * @return
     * @throws Exception
     */
    public static String restPut(String url) throws Exception{
        String result;
        try {
            //创建restTemplate对象
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(url,String.class);
            result = "200";
        } catch (HttpClientErrorException e) {
            result =  e.getResponseBodyAsString();
        }
        return result;
    }
}
