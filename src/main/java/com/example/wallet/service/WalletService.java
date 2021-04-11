package com.example.wallet.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InsufficientResourcesException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.wallet.dao.WalletDao;
import com.example.wallet.exception.InsufficeintBalanceException;
import com.example.wallet.model.Wallet;

@Service
public class WalletService {

	@Autowired
	private WalletDao walletDao;
	
	public WalletService() {
		
	}
	
	public String updateWallet(List<Integer> coins) {
		Wallet wallet=new Wallet();	
		wallet.setCoins(coins);	
		Collections.sort((List<Integer>)wallet.getCoins(), (coin1,coin2) -> coin1.compareTo(coin2));
		walletDao.save(wallet);
		return "Success";
	}
	
	
	public void saveAndUpdate(List<Integer> coins) {
		Wallet wallet=new Wallet();
		wallet.setCoins(coins);
		wallet.setWalletId(1L);
		walletDao.save(wallet);
	}
	
	public List<Integer> getCoins(){
	
		List<Wallet> wallets= walletDao.findAll();
		//Collections.sort((List<Integer>)wallets.get(0).getCoins(), (coin1,coin2) -> coin1.compareTo(coin2));
		return wallets.get(0).getCoins();
	}
	
	public List<Integer> payMoney(Integer coin) throws InsufficeintBalanceException {
	    
		List<Integer> availableCoins=getCoins();
	try {	
		Map<String, List<Integer>> coinsMap =getMatchCoins(coin,availableCoins);
		if(coinsMap != null) {
			for (Integer removeIndex : coinsMap.get("paidMoney") ) {
				availableCoins.remove(removeIndex);
			}			
			availableCoins.addAll(coinsMap.get("changeMoney"));
		}else {
			
		}
	}catch(InsufficeintBalanceException ex) {
		throw new InsufficeintBalanceException();
	}
		//deleteWallet();
		//updateWallet(availableCoins);
		Collections.sort(availableCoins, (coin1,coin2) -> coin1.compareTo(coin2));
		saveAndUpdate(availableCoins);
		return availableCoins;
	}
	
	public Map<String, List<Integer>> getMatchCoins(Integer inputCoin,List<Integer> walletCoins) throws InsufficeintBalanceException {
		
		Integer availableCoins = new Integer(0);
		Map<String,List<Integer>> coinsMap= new HashMap<String,List<Integer>>();
		List<Integer> paidMoneyList=new ArrayList<Integer>();
		List<Integer> changeMoneyList=new ArrayList<Integer>();
		
		for(Integer coin  : walletCoins) {
			
			/*
			 * if( availableCoins <1 && coin == inputCoin) {
			 * 
			 * return coin; }else if(availableCoins <1 && coin > inputCoin ) { return
			 * coin-inputCoin; }else { availableCoins=availableCoins+coin; if(availableCoins
			 * >= inputCoin) { return availableCoins-inputCoin; } }
			 */
			availableCoins += coin;
			
			//checkCoin = (availableCoins > 0)? availableCoins + coin : coin;
			if (availableCoins == inputCoin) {
				paidMoneyList.add(coin);
				coinsMap.put("paidMoney", paidMoneyList);
				coinsMap.put("changeMoney", changeMoneyList);
				return coinsMap;
			}else if (availableCoins >inputCoin) {
				paidMoneyList.add(coin);
				changeMoneyList.add(availableCoins -inputCoin);
				coinsMap.put("paidMoney", paidMoneyList);
				coinsMap.put("changeMoney", changeMoneyList);
				return coinsMap;
			}else  {
				paidMoneyList.add(coin);				
			}				
		}
		throw new InsufficeintBalanceException();
	}
}
