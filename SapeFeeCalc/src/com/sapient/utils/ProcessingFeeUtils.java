package com.sapient.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sapient.constant.ProcessingFeeConstant;
import com.sapient.vo.Transaction;

public class ProcessingFeeUtils {
    
	// provide double value
	public static Double parseDouble(String str) {
		Double dbl=null;
		if(str!=null && str!="")
			dbl=Double.parseDouble(str);
		return dbl;
		
	}
	
	// to get transaction name
	public static String getTransactionName(int i) {
		String transactionName=null;
		if(i==1) 
			transactionName=ProcessingFeeConstant.TRANSACTION_TYPE.BUY.getName();
		else if(i==2) 
			transactionName=ProcessingFeeConstant.TRANSACTION_TYPE.SELL.getName();
		else if(i==3) 
			transactionName=ProcessingFeeConstant.TRANSACTION_TYPE.DEPOSIT.getName();
		else if(i==4) 
			transactionName=ProcessingFeeConstant.TRANSACTION_TYPE.WITHDRAW.getName();
		else
			transactionName="Invalid Transaction";
		
		return transactionName;
		
	}
	
	// providing transaction type on the value
	public static Integer getTransactionType(String str) {
		
		if(str!=null && str!="") {
			switch(str) {
			case "BUY":
				return ProcessingFeeConstant.TRANSACTION_TYPE.BUY.getType();
			case "SELL":
				return ProcessingFeeConstant.TRANSACTION_TYPE.SELL.getType();
			case "DEPOSIT":
				return ProcessingFeeConstant.TRANSACTION_TYPE.DEPOSIT.getType();
			case "WITHDRAW":
				return ProcessingFeeConstant.TRANSACTION_TYPE.WITHDRAW.getType();	
			default :
				return 0;
			}
		}
		return 0;
		
	} 
	// Give the data object using string
	public static Date parseDate(String str) {
		 Date date=null;
		 SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
		 try {
			 date=sd.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	// converting priority into boolean
	public static boolean getPriority(String str) {
		boolean flag=false;
		if(str!=null && str!=""){
			if(str.equalsIgnoreCase("Y"))
				flag=true;
		}
			
		return flag;
	}
	
	// Creating object and putting all values
	public static Transaction getTransaction(List<String> list) {
		Transaction transaction =new Transaction();
		transaction.setExternalTransactionId(list.get(0));
		transaction.setClientId(list.get(1));
		transaction.setSecurityId(list.get(2));
		transaction.setTransactionType(getTransactionType(list.get(3)));
		transaction.setTransactionDate(parseDate(list.get(4)));
		transaction.setMarketValue(parseDouble(list.get(5)));
		transaction.setPriorityFlag(getPriority(list.get(6)));
		return transaction;
	}
	
}
