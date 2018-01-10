package com.zjs.util;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class NumberUtil {
	
	public static int boID;
	public static int toID;
	public static int boCount = 0; 
	public static int toCount = 0; 
	 
     //ID递增生成
	 public static String getCustomerID(int customerID) {  
        DecimalFormat decimalFormat = new DecimalFormat("00"); 
        String id = decimalFormat.format(customerID);
        return id;  
       } 
	 
	 //订单号随机生成
	 public static String getOrderIdByUUId() {
         int machineId = 1;//最大支持1-9个集群机器部署
         int hashCodeV = UUID.randomUUID().toString().hashCode();
         if(hashCodeV < 0) {//有可能是负数
             hashCodeV = - hashCodeV;
         }
         // 0 代表前面补充0     
         // 4 代表长度为4     
         // d 代表参数为正数型
         return machineId + String.format("%015d", hashCodeV);
     }
	 
	 //金额随机生成
	 public static String getRandom() {
     	 int max=250;
         int min=185;
         Random random = new Random();

         String s = String.valueOf(random.nextInt(max)%(max-min+1) + min)+".00";
         
         return s;
     }

	

}
