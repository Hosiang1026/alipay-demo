package com.zjs.contant;

/**
 * 常量
 * Company: ZJS 
 * @author hosiang
 * @date 2016-11-14
 */
public class Constant {  
	
   /**
    * 支付宝接口参数
    **/
	
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016082000292794";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCuIQjc2HE3y2Mxm+Yvc+D4Lcmk1CX+XR3VkSbCYAWYdy5H2kJFUFTCCsM1NXMS1vlyMe4C5gwJEBBKz3uzbn8clng7tRFL8VajNXAWK541AsZLNKehmjeVHeAnFnEVlwRn0RUSxO8DxHkuH4FrBioiLUlcWY13oqcyIuX4kb/1ke4o6aXX1D1Wavqq3D7sDeG07+hlEBQJtmyi/ccNYDmf4Fg6mJE1Vjjw5lLDDs3eLJlYC/OAJR7iz38arc/eRP2RXBYExtBsWveSYWRh7j7caH2C29m17lPPilKRvSzrUw8B3OWVKBXz7U51/OgUYCdYBZRflF5yO5+b/Hkt8NeNAgMBAAECggEAe8F6vQsPnGnu++kjNgKTl49jSsSEpD2l4I2jEG6SS4QY3nReuUiYlLh1eLGeMXucGe8mi+TYFPDXw3OnJSWZLcY7eaP8wQ6rVp+6I+eoL2Djcbvgj9g9PLvY/a7TfFayiN5urcJNZU4UGhXGMzxUYanyA7KZ+rcdQWLQM1PTLEH4bWdeU+Xbcm+At0Q+SzJ38chXSfJ0Y0LYkLux+gnL1t/ab7D5Vlj4OYoEBKqGihxRy5WdTHEnmmUNUDG68CCtYZEQdjk9fOvO17rLXIHH66fcbAU63hjuQTUj0LxJUXVSwgv+2d3I5v/mE/OvdST7RDZ51yVfIdyF5dcirTVRAQKBgQDgJWJpAHAQD59Nbg5XNj5pngjT9OgxOlNqc46wbxxcTH6cy7fPN4CUx/zaIYXI2NXjv3tTRNbbtIOOyIasRMTC6cvWVy5Q11ezB5lnumcmX+UMKl/blVv51gAdl2TgctTH/0wJ6bUxKNPmUbOsAzgcA/gHYPKRB4cz/0j8bvgRYQKBgQDG3/3CHcCJssSUkQh8Tq/1d2cRhggr2wglo0fPFOrZdrZ29uk8+i7zXh0MsZeOShR1jiXA92opIayCAZWgqPJ8ZfKpq39v/nzmJIWN6/LXB75IwG34/I0NHaGG6lFL20RFDo3oPuVt7d0QZuPKMW1s7Cp+RFxRhtMzPdTvHn65rQKBgQCPG+9YIRprdr38Ht9zGUQCBQ+X3I7/F0E4/yzEQzy1RXW1QHBMEiC7XXB4CN01HhR6A8CGqrYPspIocKljhcimtcUaRbFhlgv9V0W/r4i6PJGuEcQAG72MpvCN9CJJF6f5RWvUDG5OQ8X/EH0Jv0hswq/ybhiRNmBBd6Wu3l7uIQKBgE+gX3nZb72yVgMmDr+2wb0Gkb3ybFMi36AyQA4p0d5AKc54DercQPAwPnFWqdfO0WJAB927MkZFtUxYVhB9lJk6OG62u8+blWaQOOkaD6EI+KcoZN/MPq2Br6rVJre4kx0TNr5GcJYTJu/qQkoygB2ccWcc99msoeNiXl9fVl4BAoGAMhRHJO2mO7hYRufiv0l+yVC71+IDJa44JGpgLZ7PSMhO8qhGmG5BsYUuUUhVCOjL4upAmsjYgECJMaQgtIZ/m4f0+eGYSij870QDDr9xMYg7vro+nGhRzkJ3Lybzp1BIyN5w61h+7MBlLl8qIQXOyADLUTPGZgv0T7jZCUmU5ss=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7NSvkdBAcibWeX+oKqg967wxiE1X3WjJBCQFbi7iOOMpZIphOhah4LNIc53vulT8IPi+Rz1gxhXkif3HIQmUp6XPlBIVvNj5Q0ojZGrn3b+3gS7ekSWbIimwy3y6copzxwyWxHhFGEQSdZN7Qkys+wiwxyKm1vGRT2Zz9YJMTVHu5xsdS5nmE9h2AiN9ltI/hOaCzadS/xAt9kkbWsuWAZNsp4Zwh7KKiELErhivn8pT/5RlorQwzbKJlnDP5hDtcLZbOoCh5qrF+DMZ/LFzIqABtxn7wd+D3H3DPyiBAPvmwLmGIikctd+MUh/CGsN0Xz/b4aBnFtZdbtsAwdBVTwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:8080/alipay-demo/notify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1:8080/alipay-demo/return";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";
	
}
