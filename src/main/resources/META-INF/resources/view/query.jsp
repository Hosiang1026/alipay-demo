<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/static/css/comm.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/static/js/comm.js"></script>
<title>交易详情</title>
</head>
<script type="text/javascript">
$(function(){

	var result = ${result.alipay_trade_query_response};
    //动态填充表单内容
	addContent(result);
})
 
</script>

<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4>
	<header class="am-header">
	<h1>查询详情</h1>
	</header>

    <div>
     <form name=tradequery action='http://localhost:8080/alipay-demo/index' method=get target="_parent">
			<div id="query_body"></div>
	 </form>
    </div>

</body>
<script language="javascript">

</script>
</html>