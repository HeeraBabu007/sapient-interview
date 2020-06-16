package com.sapient.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.sapient.factory.InstanceFactory;
import com.sapient.services.ProcessingFeeCalculator;
import com.sapient.services.ReadFile;
import com.sapient.utils.ProcessingFeeUtils;
import com.sapient.vo.Transaction;

class TestProcessingFeeCalculator {
    
	
	// we are checking all records is read or not from file .we are not including header
	@Test
	void testReadFileNoOfRecord() {
		ReadFile readFile=InstanceFactory.getReadFileInstance("READFILECSV");
		List<Transaction> transactionList=readFile.readFile(System.getProperty("user.dir")+"\\src\\resource\\sapient PS_FeeCalculator.csv");
		assertEquals(21, transactionList.size());
		
	}
	
	// we are taking one record from first row of  file and matching it with our expected value
	@Test
	void testReadFileValue() {
		ReadFile readFile=InstanceFactory.getReadFileInstance("READFILECSV");
		List<Transaction> transactionList=readFile.readFile(System.getProperty("user.dir")+"\\src\\resource\\sapient PS_FeeCalculator.csv");
		assertEquals("GS", transactionList.get(0).getClientId());
		
	}
	
	// we are adding one records and checking its processing fees
	@Test
	void testProcessedRecord() {
		Transaction transaction =new Transaction();
		transaction.setExternalTransactionId("SAPEXTXN1");
		transaction.setClientId("GS");
		transaction.setSecurityId("ICICI");
		transaction.setTransactionType(1);
		transaction.setTransactionDate(new Date());
		transaction.setMarketValue(101.9);
		transaction.setPriorityFlag(false);
		
		List<Transaction> transactionList=new ArrayList<Transaction>();
		transactionList.add(transaction);
		ProcessingFeeCalculator processingFeeCalculetor=InstanceFactory.getProcessingFeeCalculatorInstance("PROCESSINGFEECALCULATOR");
		List<Transaction> processedList=processingFeeCalculetor.getProcessingFeeCalculatedList(transactionList);
		assertEquals(50, processedList.get(0).getProcessingFees());

	}
	
	// we are passing transaction type and getting name
	@Test
	void testGetTransactionName() {
		assertEquals("DEPOSIT", ProcessingFeeUtils.getTransactionName(3));
	}
	
	// we are passing transaction name and getting type
	@Test
	void testGetTransactionType() {
		assertEquals(Integer.valueOf(3), ProcessingFeeUtils.getTransactionType("DEPOSIT"));
	}
	
	

}
