package com.sapient.factory;

import com.sapient.services.ProcessingFeeCalculator;
import com.sapient.services.ReadFile;
import com.sapient.services.WriteFile;
import com.sapient.services.impl.ProcessingFeeCalculatorImpl;
import com.sapient.services.impl.ReadCSVFileImpl;
import com.sapient.services.impl.WriteCSVFileImpl;

public class InstanceFactory {
	
    // Give the Object of ReadFile implement Class
	public static ReadFile getReadFileInstance(String str) {
		if(str==null || str=="")
			return null;
		if(str.equals("READFILECSV"))
			return new ReadCSVFileImpl();		
		return null;
	}
	
	// Give the Object of ProcessingFeeCalculator implement Class
	public static ProcessingFeeCalculator getProcessingFeeCalculatorInstance(String str) {
		if(str==null || str=="")
			return null;
		if(str.equals("PROCESSINGFEECALCULATOR"))
			return new ProcessingFeeCalculatorImpl();
		
		return null;
		
	}
	
	// Give the Object of WriteFile implement Class
	public static WriteFile getWriteFileInstance(String str) {
		if(str==null || str=="")
			return null;
		if(str.equals("WRITEFILECSV"))
			return new WriteCSVFileImpl();
		
		return null;	
	}
	
}
