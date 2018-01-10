<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/static/css/comm.css"/>
<link rel="stylesheet" type="text/css" href="/static/css/index.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/comm.js"></script>
<script type="text/javascript" src="/static/js/index.js"></script>
<title>WEB支付测试</title>
</head>
<script type="text/javascript">
$(function(){
	 
	var sesJson = ${sessionScope.sesJson};
    
    //隐藏交易列表DIV
	hideDiv(sesJson);
	
	$("#btn_center").click(function () {
        popCenterWindow();
    });
	
    tabs = document.getElementsByName('tab');
	contents = document.getElementsByName('divcontent');
	
	(function changeTab(tab) {
	    for(var i = 0, len = tabs.length; i < len; i++) {
	        tabs[i].onmouseover = showTab;
	    }
	})();
	
})
 
</script>
<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4>
	<header class="am-header">
	<h1>支付宝WEB订单列表</h1>
	</header>
    <div id="main">
		<div>
    		<form name=tradequery action='http://localhost:8080/alipay-demo/query' method=post target="_parent" align="center">
				<dl class="content">
					<dd>
					    <span>商户订单号 ：</span>
						<input id="WIDTQout_trade_no" name="WIDTQout_trade_no" />
						<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<span>支付宝交易号 ：</span>
						<input id="WIDTQtrade_no" name="WIDTQtrade_no" />
						<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<span>退款请求号 ：</span>
						<input id="WIDRQout_request_no" name="WIDRQout_request_no" />
						<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<span class="new-btn-login-sp">
							<button class="new-btn-login" type="button" id="btn-dd" onclick="queryDetailed();"
								style="text-align: center;">交 易 查 询</button>
						</span>
						<span class="new-btn-login-sp">
							<button class="new-btn-login" type="button" id="btn_center"
								style="text-align: center;">退 款 查 询</button>
						</span>
						<span class="new-btn-login-sp">
							<button class="new-btn-login" type="button" id="btn_center"
								style="text-align: center;">交 易 关 闭</button>
						</span>
					</dd>
				</dl>
				
				 <div class="window" id="center"> 
				   <div id="title" class="title"><img src="#" alt="关闭" />查询详情</div> 
				   <div class="msg_content">测试内容</div> 
				 </div>
			<hr class="one_line">
		  </form>
    </div>
    
    <div id="tabhead" class="tab-head">
			<h2 id="tab1" class="selected" name="tab">订 单 列 表</h2>
			<h2 id="tab2" name="tab">交 易 列 表</h2>
	</div></br>
		
	<div id="body1" class="tab-content" name="divcontent">
	<hr class="one_line">
	<div class="content">
	<table class="hovertable">
		<tr>
		    <th>序号</th>
			<th>商户订单号</th>
			<th>订单名称 </th>
			<th>付款金额</th>
			<th>商品描述</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${result}" var="bill">
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
			<td>${bill.id}</td>
			<td>${bill.outTradeNo}</td>
			<td>${bill.subject}</td>
			<td>${bill.totalAmount}</td>
			<td>${bill.body}</td>
			<td>
			<a class="sel_btn" href="http://localhost:8080/alipay-demo/pay?outTradeNo=${bill.outTradeNo}&subject=${bill.subject}&totalAmount=${bill.totalAmount}&body=${bill.body}">付款</a>
			</td>
		</tr>
		</c:forEach> 
	</table>
  </div>
 </div>
 
 <div id="body2" class="tab-content" name="divcontent">
   <hr class="one_line">
     	<div class="content">
	     	<table class="hovertable">
			   		<tr>
			   		<th>序号</th>
			   		<th>商户订单号</th>
			   		<th>支付宝交易号 </th>
			   		<th>交易状态</th>	
			   		<th>交易金额</th>	
			   		<th>操作</th>
			   		</tr>
			   		<c:forEach items="${sessionScope.sesJson}" var="sesJ">
			   		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
			   		<td>${sesJ.sesid}</td>
			   		<td>${sesJ.outTradeNo}</td>
			   		<td>${sesJ.tradeNo}</td>
			   		<td>${sesJ.tradeStatus}</td>
			   		<td>${sesJ.totalAmount}</td>
			   		<td>
			   		<a  class="sel_btn" href="http://localhost:8080/alipay-demo/refund?outTradeNo=${sesJ.outTradeNo}&tradeNo=${sesJ.outTradeNo}&refundAmount=${sesJ.totalAmount}">退款</a>
			   		</td>
			   		</tr>
		   		</c:forEach>
	   	    </table>
        </div>

   </div>
   		<div id="foot">
			<ul class="foot-ul">
				<li>狂欢马克思@版权所有 2017-2018 WWW.HOSIANG.CN</li>
			</ul>
		</div>
 </div>  
</body>

</html>