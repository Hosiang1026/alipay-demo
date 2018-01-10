/**
 * 支付宝WEB支付首页JS
 */

//获取窗口的高度 
var windowHeight; 
//获取窗口的宽度 
var windowWidth; 
//获取弹窗的宽度 
var popWidth; 
//获取弹窗高度 
var popHeight; 
function init(){ 
   windowHeight=$(window).height(); 
   windowWidth=$(window).width(); 
   popHeight=$(".window").height(); 
   popWidth=$(".window").width(); 
} 
//关闭窗口的方法 
function closeWindow(){ 
    $(".title img").click(function(){ 
        $(this).parent().parent().hide("slow"); 
        });
    } 
    //定义弹出居中窗口的方法 
    function popCenterWindow(){ 
    	debugger;
        init(); 
        //计算弹出窗口的左上角Y的偏移量 
    var popY=(windowHeight-popHeight)/2; 
    var popX=(windowWidth-popWidth)/2; 
    //alert('jihua.cnblogs.com'); 
    //设定窗口的位置 
    $("#center").css("top",popY).css("left",popX).slideToggle("slow");  
    closeWindow();

    } 
    
function getResult(){
	$("#body1").empty();
	$("#body2").empty();

}

function queryDetailed(){
	debugger;
	var  outTradeNo = $('#WIDTQout_trade_no').val();
	var  tradeNo = $('#WIDTQtrade_no').val();
	
    $.ajax({
		type : 'POST',
		url:'http://localhost:8080/alipay-demo/query',
		dataType: 'json',
		data:{
			outTradeNo:outTradeNo,
			tradeNo:tradeNo
		},
		success : function(data) {
			debugger;
  		   if(data.status == 'Y'){
  			 alert('提示',data.info);
  		   }else{
  			 alert('提示',data.info);
  		   }
		},
 	     onLoadError:function(){
      	   	 alert('提示','连接服务器错误');
      	}
	});
}
