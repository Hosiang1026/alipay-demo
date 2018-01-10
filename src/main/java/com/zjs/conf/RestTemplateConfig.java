package com.zjs.conf;

import javax.annotation.Resource;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Resource
    private RestTemplate restTemplate;
    /**
     * Rest --> post请求方式 有参方式 （Object）
     * @param url
     * @param jsonObj
     * @return
     * @throws Exception
     */
	public String restPost(String url,String jsonObj) throws Exception{
        String result;
        try {
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
    public String restGet(String url) throws Exception{
        //返回值
        String result;
        try {
            result = restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            result = e.getResponseBodyAsString();
        }

        return result;
    }
}
