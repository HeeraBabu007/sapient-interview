package com.sapient.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.sapient.constant.ProcessingFeeConstant;
import com.sapient.services.ProcessingFeeCalculator;
import com.sapient.vo.Transaction;

public class ProcessingFeeCalculatorImpl implements ProcessingFeeCalculator {
	
    // contains processed list
	private List<Transaction> transactionList=new ArrayList<Transaction>();
	
	@Override
	public List<Transaction> getProcessingFeeCalculatedList(List<Transaction> transList) {
		transList.forEach(this::addProcessinFeeCalculatedItem);
		transactionList.sort(Comparator.comparing(Transaction::getClientId).
		thenComparing(Transaction::getTransactionType).thenComparing(Transaction::getTransactionDate)
		.thenComparing(Transaction::isPriorityFlag));
		return transactionList;
	}
	
	// add the processed object in list
	private void addProcessinFeeCalculatedItem(Transaction trans ) {
		transactionList.add(calculateProcessingFee(trans));
	}
	
	// calculate the processing fee
	private Transaction calculateProcessingFee(Transaction trans) {
		   if(isIntraDayTransaction(trans)) {
			   trans.setProcessingFees(ProcessingFeeConstant.PROCESSING_FEE.TEN.getFee());
		   }else {
			   if(trans.isPriorityFlag()) {
				   trans.setProcessingFees(ProcessingFeeConstant.PROCESSING_FEE.FIVE_HUNDRED.getFee());
			   }else {
				   if(trans.getTransactionType()==ProcessingFeeConstant.TRANSACTION_TYPE.SELL.getType() ||
					  trans.getTransactionType()==ProcessingFeeConstant.TRANSACTION_TYPE.WITHDRAW.getType()	   
					 ) {
					   trans.setProcessingFees(ProcessingFeeConstant.PROCESSING_FEE.HUNDRED.getFee());
				   }else if(trans.getTransactionType()==ProcessingFeeConstant.TRANSACTION_TYPE.BUY.getType() ||
							trans.getTransactionType()==ProcessingFeeConstant.TRANSACTION_TYPE.DEPOSIT.getType()	   
							 ) {
							   trans.setProcessingFees(ProcessingFeeConstant.PROCESSING_FEE.FIFTY.getFee());
					}
			   }
		   }
		return trans;
	}
	
	// Check that whether transaction is Intraday transaction or not
	private boolean isIntraDayTransaction(Transaction trans) {
		boolean isIntraDayTransaction=false;
		
		if(transactionList.size()>0) {
			for(Transaction transactionListLocal :transactionList) {
				if(transactionListLocal.getClientId().equals(trans.getClientId()) &&  
				   transactionListLocal.getSecurityId().equals(trans.getSecurityId()) &&
				   transactionListLocal.getTransactionDate().equals(trans.getTransactionDate())
				  ) {
					if(transactionListLocal.getTransactionType()==ProcessingFeeConstant.TRANSACTION_TYPE.BUY.getType() &&
					   trans.getTransactionType()==ProcessingFeeConstant.TRANSACTION_TYPE.SELL.getType() || 
					   transactionListLocal.getTransactionType()==ProcessingFeeConstant.TRANSACTION_TYPE.SELL.getType() &&
					   trans.getTransactionType()==ProcessingFeeConstant.TRANSACTION_TYPE.BUY.getType() 
					   ) {
						isIntraDayTransaction=true;
						transactionListLocal.setProcessingFees(ProcessingFeeConstant.PROCESSING_FEE.TEN.getFee());
						break;
					}
				}	
			}
			
		}
		return isIntraDayTransaction;	
	}

}
