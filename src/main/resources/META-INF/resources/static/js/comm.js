/**
 * 支付宝WEB支付测试JS
 */
//全局变量
var tabs;
var contents;

function showTab() {
    for(var i = 0, len = tabs.length; i < len; i++) {
    	
        if(tabs[i] === this) {
            tabs[i].className = 'selected';
            contents[i].className = 'show';
        } else {
            tabs[i].className = '';
            contents[i].className = 'tab-content';
        }
    }
}

function addContent(result){

	var code = result.code;
	
	//请求正常
    if(code == "10000"){
       var trade_no = result.trade_no;
	   var out_trade_no = result.out_trade_no;
	   var buyer_user_id = result.buyer_user_id;
       var buyer_logon_id = result.buyer_logon_id;
       var total_amount = result.total_amount;
       var msg = result.msg;
       var send_pay_date = result.send_pay_date;
       
       var content = 
       "<dl class='content'>"+
	   "<dd>"+
	   "<span>支付宝交易号：</span>"+
	   "<input style='border:0;' name='tradeNo' value="+trade_no+"></input>"+
	   "</dd>"+
	   "<hr class='one_line'>"+
	   "<dd>"+
	   "<span>商家订单号：</span>"+
	   "<input style='border:0;' name='outTradeNo' value="+out_trade_no+"></input>"+
	   "</dd>"+
	   "<hr class='one_line'>"+
	   "<dd>"+
	   "<span>买家在支付宝的用户ID：</span>"+
	   "<a>"+buyer_user_id+"</a>"+
	   "</dd>"+
	   "<hr class='one_line'>"+
	   "<dd>"+
	   "<span>买家支付宝账号：</span>"+
	   "<a>"+buyer_logon_id+"</a>"+
	   "</dd>"+
	   "<hr class='one_line'>"+
	   "<dd>"+
	   "<span>交易金额：</span>"+
	   "<input style='border:0;' name='totalAmount' value="+total_amount+"></input>"+
	   "</dd>"+
	   "<hr class='one_line'>"+
	   "<dd>"+
	   "<span>交易状态：</span>"+
	   "<a>"+msg+"</a>"+
	   "</dd>"+
	   "<hr class='one_line'>"+
	   "<dd>"+
	   "<span>交易时间：</span>"+
	   "<a>"+send_pay_date+"</a>"+
	   "</dd>"+
	   "<hr class='one_line'>"+
	   "<dd>"+
	   "<span class='new-btn-login-sp'>"+
	   "<button class='new-btn-login' type='button' id='btn-dd' onclick='history.go(-1)' style='text-align: center;'>返回</button>"+
	   "</span>"+
	   "</dd>"+
       "</dl>";
      
    $('#query_body').html(content);  
    
 }
    
    //请求错误
    if(code == "40004"){
    	var out_trade_no = result.out_trade_no;
    	var sub_msg = result.sub_msg;
    	
        var content = 
            "<dl class='content'>"+
     	   "<dd>"+
     	   "<span>商家订单号：</span>"+
     	   "<a>"+out_trade_no+"</a>"+
     	   "</dd>"+
     	   "<hr class='one_line'>"+
     	   "<dd>"+
     	   "<span>交易状态：</span>"+
     	   "<a>"+sub_msg+"</a>"+
     	   "</dd>"+
     	   "<hr class='one_line'>"+
     	   "<dd>"+
     	   "<span class='new-btn-login-sp'>"+
     	   "<button class='new-btn-login' type='button' id='btn-dd' onclick='history.go(-1)' style='text-align: center;'>返回</button>"+
     	   "</span>"+
     	   "</dd>"+
            "</dl>";
           
         $('#query_body').html(content);  
	}
	
    //请求异常
    if(code == "20000"){
    	var sub_msg = result.sub_msg;
    	
        var content = 
            "<dl class='content'>"+
     	   "<dd>"+
     	   "<span>交易状态：</span>"+
     	   "<a>"+sub_msg+"</a>"+
     	   "</dd>"+
     	   "<hr class='one_line'>"+
     	   "<dd>"+
     	   "<span class='new-btn-login-sp'>"+
     	   "<button class='new-btn-login' type='button' id='btn-dd' onclick='history.go(-1)' style='text-align: center;'>返回</button>"+
     	   "</span>"+
     	   "</dd>"+
            "</dl>";
           
         $('#query_body').html(content); 
	}
    
}    

function hideDiv(sesJson){

    if(null != sesJson&&sesJson.length == 0){
    	$("#body2").hide();
      }
        
}
