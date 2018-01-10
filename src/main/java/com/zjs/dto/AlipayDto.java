package com.zjs.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
public class AlipayDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("ID")
	private String id;
	
	//商户订单号，商户网站订单系统中唯一订单号，必填
	@ApiModelProperty("商户订单号")
	private String outTradeNo;
	
	//支付宝交易号
	@ApiModelProperty("支付宝交易号")
	private String tradeNo;
	
	//付款金额，必填
	@ApiModelProperty("付款金额")
	private String totalAmount;
	
	//订单名称，必填
	@ApiModelProperty("订单名称")
	private String subject;
	
	//商品描述，可空
	@ApiModelProperty("商品描述")
	private String body;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}
