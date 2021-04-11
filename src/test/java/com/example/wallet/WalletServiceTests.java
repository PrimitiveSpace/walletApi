package com.example.wallet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
/*import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;*/
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.wallet.dao.WalletDao;
import com.example.wallet.model.Wallet;
import com.example.wallet.service.WalletService;


@ExtendWith(MockitoExtension.class)
public class WalletServiceTests {
	
	/*
	 * @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	 */
	@InjectMocks
	WalletService mockWallet;
	
	@Mock
	WalletDao walletDao;
	
	/*
	 * @BeforeEach public void init() { MockitoAnnotations.initMocks(this); }
	 */
	
	  @Test 
	  public String testUpdateWallet(){
		  
	  Wallet wal = new Wallet();
	  wal.setWalletId(1L);
	  wal.setCoins(Arrays.asList(new Integer[] {1,2,3,4,5}));
		  
	  when(walletDao.save(Mockito.any(Wallet.class))).thenReturn(wal);
	  assertEquals(wal, mockWallet.updateWallet(Arrays.asList(new Integer[] {1,2,3,4,5})));
	  return "";
	  }
	 
	

}
