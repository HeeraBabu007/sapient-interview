package com.sapient.main;

import java.util.List;

import com.sapient.factory.InstanceFactory;
import com.sapient.services.ProcessingFeeCalculator;
import com.sapient.services.ReadFile;
import com.sapient.services.WriteFile;
import com.sapient.vo.Transaction;

public class ProcessingFeeCalculatorEngine {

	public static void main(String[] args) {
		// Read file and Find the list of objects
		ReadFile readFile=InstanceFactory.getReadFileInstance("READFILECSV");
		List<Transaction> transactionList=readFile.readFile(System.getProperty("user.dir")+"\\src\\resource\\sapient PS_FeeCalculator.csv");
		
		// Processed list of objects
		ProcessingFeeCalculator processingFeeCalculetor=InstanceFactory.getProcessingFeeCalculatorInstance("PROCESSINGFEECALCULATOR");
		List<Transaction> processedList=processingFeeCalculetor.getProcessingFeeCalculatedList(transactionList);		
		
		// Now, write list of objects into the csv file
		WriteFile writeFile=InstanceFactory.getWriteFileInstance("WRITEFILECSV");
		writeFile.writeFile(processedList, System.getProperty("user.dir")+"\\src\\resource\\ProcessingFeeCalculateFile.csv");
	}
}
