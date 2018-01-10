package com.zjs.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.zjs.contant.Constant;
import com.zjs.dto.AlipayDto;
import com.zjs.util.NumberUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 支付宝
 * 
 * @author hosiang 
 * @Company: ZJS 
 * @datetime 2016年11月8日 下午4:31:10 
 *
 */
@Controller
@RequestMapping(value = {"/alipay-demo"}, produces = {"application/json"})
@Api(value = "/index", description = "测试查询支付宝例子")
public class AlipayController  {
	Logger logger = LoggerFactory.getLogger(AlipayController.class);

		
	@RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}, produces = {"application/json"}
    )
    @ApiOperation( 
            value = "支付宝",
            notes = "返回支付宝",
            response = String.class
    )
    @ApiResponses({@ApiResponse( 
            code = 400,
            message = "请求处理失败"
    ), @ApiResponse(
            code = 404,
            message = "未能根据传入参数得到支付宝信息"
    )})
	public String index(Model model,HttpServletRequest request,@ModelAttribute("msg") String msg,@ModelAttribute("tradeStatus") String tradeStatus,
			@ModelAttribute("outTradeNo") String outTradeNo,@ModelAttribute("tradeNo") String tradeNo,@ModelAttribute("totalAmount") String totalAmount) throws Exception {
		 JSONArray json = new JSONArray();
		 
		 HttpSession session = request.getSession(); 
			
		 JSONArray sesJson = (JSONArray)session.getAttribute("sesJson");
		
		 if (null == sesJson) {
			 sesJson = new JSONArray();
		}
		 
			//生成支付宝WEB订单列表
	    	 for (int i = 0; i < 10; i++) {
	    		 
	    		 Map<String, String> map = new HashMap<String, String>();
	    		 
	    	         ++NumberUtil.boCount;  
	    	         NumberUtil.boID = NumberUtil.boCount; 
	    	         
	    		     String id = NumberUtil.getCustomerID(NumberUtil.boID);
	    		     String boOutTradeNo = NumberUtil.getOrderIdByUUId();
	    		     String boTotalAmount = NumberUtil.getRandom();
	    		     
	    		     map.put("id", id);
	    		     map.put("outTradeNo", boOutTradeNo);
	    		     map.put("totalAmount", boTotalAmount);
	    		     map.put("subject", "测试-"+id);
	    		     map.put("body", "测试支付接口-"+id);
	    	       
	                 json.add(map);
			}
	    	 
			
    	 //生成付款成功订单列表
    	 if (null != msg&&!("").equals(msg)) {
			
    		Map<String, String> sesMap = new HashMap<String, String>();
	         
	        ++NumberUtil.toCount;  
	        NumberUtil.toID = NumberUtil.toCount; 
	        
    		 //ID
    		String sesid = NumberUtil.getCustomerID(NumberUtil.toID);
    		 
			//session记录不能重复
			if (sesJson.size() == 0) {
				sesMap.put("sesid", sesid);
				sesMap.put("outTradeNo", outTradeNo);
				sesMap.put("tradeNo", tradeNo);
				sesMap.put("tradeStatus", tradeStatus);
				sesMap.put("totalAmount", totalAmount);
				sesJson.add(sesMap);
			}else {
				for(int j=0;j<sesJson.size();j++){
					JSONObject obj = sesJson.getJSONObject(j);
					if (null != obj && !outTradeNo.equals(obj.get("outTradeNo"))) {
						sesMap.put("sesid", sesid);
						sesMap.put("outTradeNo", outTradeNo);
						sesMap.put("tradeStatus", tradeStatus);
						sesMap.put("tradeNo", tradeNo);
						sesMap.put("totalAmount", totalAmount);
						sesJson.add(sesMap);
					}
				}
				
			}
		
        }
    	 
    	session.setAttribute("sesJson", sesJson);
    	 
    	model.addAttribute("result", json);
    	
		return "index";
	}
	
	
	/**
	 * 支付宝交易查询
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = {"/query"}, method = {RequestMethod.POST}, produces = {"application/json"})
	@ResponseBody 
	public String AlipayQuery(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		//Map<String, String> map = new HashMap<String, String>();
		Map<String,Object> map = new HashMap<String,Object>(); 
		
		JSONArray json = new JSONArray();
		
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(Constant.gatewayUrl, Constant.app_id, 
					Constant.merchant_private_key, "json", Constant.charset, Constant.alipay_public_key, Constant.sign_type);
			
			//设置请求参数
			AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
			
			//商户订单号，商户网站订单系统中唯一订单号
			String out_trade_no = new String(request.getParameter("WIDTQout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号
			String trade_no = new String(request.getParameter("WIDTQtrade_no").getBytes("ISO-8859-1"),"UTF-8");
			//请二选一设置
			
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","+"\"trade_no\":\""+ trade_no +"\"}");
			
			//请求
			String result = alipayClient.execute(alipayRequest).getBody();
			
             map.put("status", "Y");
             map.put("info", result);
             
		} catch (Exception e) {
			map.put("status", "N");
			map.put("info", e.getMessage());
		}
		
		String jsonResult = JSON.toJSONString(map);
		
		 return jsonResult;  
	}
	

	/**
	 * 支付宝付款
	 * @param model
	 * @param dto
	 * @return
	 * @throws AlipayApiException
	 */
	@RequestMapping(value = {"/pay"}, method = {RequestMethod.GET}, produces = {"application/json"})
    public String AlipayPay(Model model,@ApiParam(value = "查询条件", required = true) AlipayDto dto) throws Exception {
		
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(Constant.gatewayUrl, Constant.app_id, 
		Constant.merchant_private_key, "json", Constant.charset, Constant.alipay_public_key, Constant.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(Constant.return_url);
		alipayRequest.setNotifyUrl(Constant.notify_url);
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = dto.getOutTradeNo(); 
		//付款金额，必填
		String total_amount = dto.getTotalAmount(); 
		//订单名称，必填
		String subject = dto.getSubject();
		//商品描述，可空
		String body = dto.getBody();
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		
		model.addAttribute("result",result);
		
		return "load";
    }
	
	/**
	 * 支付宝服务器同步通知页面
	 * @throws Exception 
	 */
	@RequestMapping(value = {"/return"}, method = {RequestMethod.GET}, produces = {"application/json"})
    public String AlipayReturn(Model model,HttpServletResponse response,HttpServletRequest request,RedirectAttributes attr) throws Exception {
		
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, Constant.alipay_public_key, Constant.charset, Constant.sign_type); //调用SDK验证签名

		//商户订单号
		String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
	
		//支付宝交易号
		String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
		//付款金额
		String totalAmount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
		
		if(signVerified) {
			String msg = "success";
			attr.addFlashAttribute("msg", msg); 
			attr.addFlashAttribute("outTradeNo", outTradeNo);
			attr.addFlashAttribute("tradeNo", tradeNo);
			attr.addFlashAttribute("totalAmount", totalAmount);
			attr.addFlashAttribute("tradeStatus", "付款成功");
		}else {
			String msg = "fail";
			attr.addFlashAttribute("msg", msg); 
			attr.addFlashAttribute("outTradeNo", outTradeNo);
			attr.addFlashAttribute("tradeNo", tradeNo);
			attr.addFlashAttribute("totalAmount", totalAmount);
			attr.addFlashAttribute("tradeStatus", "付款失败");
		}
		
		 return "redirect:/alipay-demo/index";
	}
	
	/**
	 * 支付宝服务器异步通知页面
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = {"/notify"}, method = {RequestMethod.POST}, produces = {"application/json"})
    public String AlipayNotify(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 
		   String result = "";
		
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map<String,String[]> requestParams = request.getParameterMap();
			
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			boolean signVerified = AlipaySignature.rsaCheckV1(params, Constant.alipay_public_key, Constant.charset, Constant.sign_type); //调用SDK验证签名

			/* 实际验证过程建议商户务必添加以下校验：
			1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
			2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
			3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
			4、验证app_id是否为该商户本身。
			*/
			if(signVerified) {//验证成功
				//商户订单号
				String out_trade_no =new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
				//支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
				//交易状态
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
				
				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
						
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				}else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
					//注意：
					//付款完成后，支付宝系统发送该交易状态通知
				}
				
				result = "success";
				
			}else {//验证失败
				result = "fail";
			
				//调试用，写文本函数记录程序运行情况是否正常
				String sWord = AlipaySignature.getSignCheckContentV1(params);
				logResult(sWord);
			}
			
			model.addAttribute("result",result);
			
			return "notify";
    }
	
	@RequestMapping(value = {"/refund"}, method = {RequestMethod.GET}, produces = {"application/json"})
    public String AlipayRefund(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception {
    
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(Constant.gatewayUrl, Constant.app_id, 
		Constant.merchant_private_key, "json", Constant.charset, Constant.alipay_public_key, Constant.sign_type);
		
		//设置请求参数
		AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
		
		//商户订单号，商户网站订单系统中唯一订单号
		String out_trade_no = new String(request.getParameter("outTradeNo").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号
		String trade_no = new String(request.getParameter("tradeNo").getBytes("ISO-8859-1"),"UTF-8");
		//请二选一设置
		//需要退款的金额，该金额不能大于订单金额，必填
		String refund_amount = new String(request.getParameter("refundAmount").getBytes("ISO-8859-1"),"UTF-8");
		//退款的原因说明
		String refund_reason = new String(request.getParameter("refundReason").getBytes("ISO-8859-1"),"UTF-8");
		//标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
		String out_request_no = new String(request.getParameter("outRequestNo").getBytes("ISO-8859-1"),"UTF-8");
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"trade_no\":\""+ trade_no +"\"," 
				+ "\"refund_amount\":\""+ refund_amount +"\"," 
				+ "\"refund_reason\":\""+ refund_reason +"\"," 
				+ "\"out_request_no\":\""+ out_request_no +"\"}");
		
		//请求
		String result = alipayClient.execute(alipayRequest).getBody();
		
		JSONObject jsStr = JSONObject.parseObject(result); 
		
		model.addAttribute("result",jsStr);
		
		return "refund";
	}
	
	/** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(Constant.log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
}
