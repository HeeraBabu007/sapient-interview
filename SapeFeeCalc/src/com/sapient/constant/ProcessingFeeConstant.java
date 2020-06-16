package com.sapient.constant;
public class ProcessingFeeConstant {
	
	// Storing Processing fee 
	public enum PROCESSING_FEE {	
		TEN("TEN",10),
		FIFTY("FIFTY",50),
		HUNDRED("HUNDRED",100),
		FIVE_HUNDRED("FIVE_HUNDRED",500);
		private String name;
		private double fee;		
		PROCESSING_FEE(String name,double fee){
			this.name=name;
			this.fee=fee;
		}
		public String getName() {
			return name;
		}
		public double getFee() {
			return fee;
		}
	}
	// Storing Transaction type
	public enum TRANSACTION_TYPE {

			BUY("BUY",1),
			SELL("SELL",2),
			DEPOSIT("DEPOSIT",3),
			WITHDRAW("WITHDRAW",4);
			
		private String name;
		private int type;
			
		TRANSACTION_TYPE(String name,int type){
			this.name=name;
			this.type=type;
		}
			
		public String getName() {
			return name;
		}
			
		public int getType() {
			return type;
		}
	}
}
