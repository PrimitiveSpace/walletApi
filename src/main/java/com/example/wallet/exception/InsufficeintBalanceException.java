package com.example.wallet.exception;

import java.util.List;

public class InsufficeintBalanceException extends Exception {


	private String message;
	private Integer inputMoney;
	private List<Integer> availableMoney;
	
	public InsufficeintBalanceException() {

	}
	
	public InsufficeintBalanceException(String message, Integer inputMoney, List<Integer> availableMoney) {
		this.message=message;
		this.inputMoney=inputMoney;
		this.availableMoney=availableMoney;
	}

	public InsufficeintBalanceException(String message) {
		super(message);
	}

	public InsufficeintBalanceException(Throwable cause) {
		super(cause);
	}

	public InsufficeintBalanceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public String toString() {
		return message+""+inputMoney+"My current coins are"+availableMoney;
	}


}
