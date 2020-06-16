package com.sapient.services;

import java.util.List;

import com.sapient.vo.Transaction;
@FunctionalInterface
public interface ReadFile {
	
	// Read files and provide list contains the object of transaction
	public List<Transaction> readFile(String path);
}
