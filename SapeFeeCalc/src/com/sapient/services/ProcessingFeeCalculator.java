package com.sapient.services;

import java.util.List;

import com.sapient.vo.Transaction;
@FunctionalInterface
public interface ProcessingFeeCalculator {
	
   // it will take list of transaction object and provide processed object
	public List<Transaction> getProcessingFeeCalculatedList(List<Transaction> list);
	
}
