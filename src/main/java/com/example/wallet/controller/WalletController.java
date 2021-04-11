package com.example.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wallet.exception.InsufficeintBalanceException;
import com.example.wallet.service.WalletService;

@RestController
public class WalletController {

	@Autowired
	WalletService walletService;
	
	@GetMapping(value="/coins")
	public List<Integer> getCoins(){
		
		return walletService.getCoins();
	}
	
	@PostMapping(value="/initalize")
	public String initalizeWallet(@RequestBody List<Integer> coins) {
		return walletService.updateWallet(coins);
	}
	
	@PostMapping(value="/payMoney")
	public List<Integer> payMoney(@RequestBody Integer coins) throws InsufficeintBalanceException {
		try {
			return walletService.payMoney(coins);
		} catch (InsufficeintBalanceException e) {
			throw e;
		}
		
	}
	
	@GetMapping(value="/test")
	public String testMethod() {
		return "test success";
	}
}
