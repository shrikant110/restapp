package com.cardreader.controller.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("test")
public class TestController {
	
	


	/*@CrossOrigin
	@RequestMapping(value = "/ccy", method = RequestMethod.GET)
	public ResponseEntity<?> getccy(){
		//stockDetailsRepository.findCurrencyCodes();
		currencyRepository.deleteAll();
		List<Object[]> list=stockDetailsRepository.findCurrencyCodes();
		
	    for(Object obj:list) {
	    	CurrencyMaster currencyMaster= new CurrencyMaster();
	    	currencyMaster.setCurrency(obj.toString());
	    	currencyRepository.save(currencyMaster);
	    }
		return new ResponseEntity<>(stockDetailsRepository.findCurrencyCodes(), HttpStatus.OK);
		
	}
*/

}
