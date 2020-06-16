package com.sapient.services.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.sapient.constant.ProcessingFeeConstant;
import com.sapient.services.WriteFile;
import com.sapient.utils.ProcessingFeeUtils;
import com.sapient.vo.Transaction;

public class WriteCSVFileImpl implements WriteFile {
	
   // write the processed data in csv file
	@Override
	public void writeFile(List<Transaction> list, String path) {
		FileWriter fileWriter=null;
		final String HEADER="Client Id,Transaction Type,Transaction Date,Priority,Processing Fee"; 
		final String COMMA_DELIMETR=",";
		final String NEWLINE_DELIMETR="\n";
		SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
	    try {
			fileWriter=new FileWriter(path);
			fileWriter.append(HEADER);
			fileWriter.append(NEWLINE_DELIMETR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    for(Transaction transactionList:list) {
			try {
				fileWriter.append(transactionList.getClientId()+COMMA_DELIMETR);
				fileWriter.append(ProcessingFeeUtils.getTransactionName(transactionList.getTransactionType())+COMMA_DELIMETR);
				fileWriter.append(sd.format(transactionList.getTransactionDate())+COMMA_DELIMETR);
				fileWriter.append((transactionList.isPriorityFlag() ? "Y":"N")+COMMA_DELIMETR);
				fileWriter.append(transactionList.getProcessingFees()+NEWLINE_DELIMETR);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    try {
	    	fileWriter.flush();
	    	fileWriter.close();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }

	}

}
