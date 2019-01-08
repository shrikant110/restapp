package com.social.controller.test;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.social.data.parse.stock.Checklist;
import com.social.inbound.funds.entities.FundAreasCheckListMaster;
import com.social.inbound.funds.entities.FundItemsCheckListMaster;
import com.social.inbound.funds.entities.FundTextsCheckListMaster;
import com.social.inbound.funds.entities.Funds;
import com.social.inbound.funds.entities.FundsAssetClassesCheckListMaster;
import com.social.inbound.funds.entities.FundsProfileCheckListMaster;
import com.social.inbound.funds.entities.FundsPromotersCheckListMaster;
import com.social.inbound.funds.repository.FundAlternativeRepository;
import com.social.inbound.funds.repository.FundAreasRepository;
import com.social.inbound.funds.repository.FundItemsRepository;
import com.social.inbound.funds.repository.FundTextsRepository;
import com.social.inbound.funds.repository.FundsAssetClassesRepository;
import com.social.inbound.funds.repository.FundsProfileRepository;
import com.social.inbound.funds.repository.FundsPromotersRepository;
import com.social.inbound.funds.repository.FundsRepository;
import com.social.inbound.funds.service.LoadFundDataMaintenenceService;
import com.social.inbound.funds.service.maintenence.LoadStockDataMaintenenceService;
import com.social.inbound.stocts.entities.AurGroupMaster;
import com.social.inbound.stocts.entities.AurItemsMaster;
import com.social.inbound.stocts.entities.AurSectorMaster;
import com.social.inbound.stocts.entities.AurTextIdMaster;
import com.social.inbound.stocts.entities.AuroraStockDetails;
import com.social.inbound.stocts.entities.CurrencyMaster;
import com.social.inbound.stocts.repository.CurrencyCodeRepository;
import com.social.inbound.stocts.repository.StockDetailsRepository;
import com.social.inbound.stocts.repository.StockGroupRepository;
import com.social.inbound.stocts.repository.StockItemsRepository;
import com.social.inbound.stocts.repository.StockSectorRepository;
import com.social.inbound.stocts.repository.StockTextRepository;
import com.social.services.FTPDownloader;
import com.social.services.ZipUnZipDirectory;


@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	LoadFundDataMaintenenceService load;
	
	@Autowired
	LoadStockDataMaintenenceService sload;
	
	@CrossOrigin
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<?> loadData() {
		load.downloadandupdateFunddata();
		return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/funds", method = RequestMethod.GET)
	public ResponseEntity<?> funddataload() {
		sload.downloadandupdateStockdata();
		return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
	}
	

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
