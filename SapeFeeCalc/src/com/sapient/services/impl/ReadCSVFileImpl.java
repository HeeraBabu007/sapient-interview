package com.sapient.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sapient.services.ReadFile;
import com.sapient.utils.ProcessingFeeUtils;
import com.sapient.vo.Transaction;

public class ReadCSVFileImpl implements ReadFile {

	// we are providing csv read implementation for method
	@Override
	public List<Transaction> readFile(String path) {
		List<Transaction> transactionList=new ArrayList<Transaction>();
		Stream<String> lines=null;
		try {
			lines=Files.lines(Paths.get(path)).skip(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<List<String>> transList=lines.map(i->Arrays.asList(i.split(","))).collect(Collectors.toList());
		for(List<String> localList : transList) {
			transactionList.add(ProcessingFeeUtils.getTransaction(localList));
		}
		
		return transactionList;
	}

}
