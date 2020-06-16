package com.sapient.services;

import java.util.List;

import com.sapient.vo.Transaction;

@FunctionalInterface
public interface WriteFile {
	
    // take list of object and write in file
	public void writeFile(List<Transaction> list,String path);
	
}
