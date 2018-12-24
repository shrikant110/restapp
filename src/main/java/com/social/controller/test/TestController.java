package com.social.controller.test;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.net.ntp.TimeStamp;
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
import com.social.inbound.stocts.entities.AuroraStockDetails;
import com.social.inbound.stocts.repository.StockDetailsRepository;
import com.social.inbound.stocts.repository.StockGroupRepository;
import com.social.inbound.stocts.repository.StockItemsRepository;
import com.social.inbound.stocts.repository.StockSectorRepository;
import com.social.inbound.stocts.repository.StockTextRepository;


@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	StockGroupRepository stockRepository;
	
	@Autowired
	StockSectorRepository stockSectorRepository;
	
	
	@Autowired
	StockItemsRepository stockItemsRepository;
	
	
	@Autowired
	StockTextRepository stockTextsRepository;
	
	
	@Autowired
	FundAlternativeRepository fundAlternativeRepository;
	
	
	@Autowired
	FundAreasRepository fundAreasRepository;
	
	@Autowired
	FundItemsRepository fundItemsRepository;
	
	@Autowired
	FundsAssetClassesRepository fundsAssetClassesRepository;
	
	@Autowired
	FundsProfileRepository fundsProfileRepository;
	
	
	@Autowired
	FundsPromotersRepository fundsPromotersRepository;
	
	
	@Autowired
	FundsRepository fundsRepository ;
	
	@Autowired
	FundTextsRepository fundTextsRepository ;
	
	@Autowired
	StockDetailsRepository stockDetailsRepository;
	
	@CrossOrigin
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<?> loadData() {
		try{
			String fileName = "Aurora_StocksCheckList.xml";
	        ClassLoader classLoader = new TestController().getClass().getClassLoader();
	        File file = new File(classLoader.getResource(fileName).getFile());
		    //creating the JAXB context
		    JAXBContext jContext = JAXBContext.newInstance(Checklist.class);
		    //creating the unmarshall object
		    Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
		    //calling the unmarshall method
		    Checklist checklist=(Checklist) unmarshallerObj.unmarshal(file);
		    //  System.out.println("checklist.funds >> "+checklist.getTexts());
		    
		    /* 
		    
		    PropertyMap<Checklist.Groups.Group, AurGroupMaster> groupMap = new PropertyMap<Checklist.Groups.Group, AurGroupMaster>() {
		    	  protected void configure() {
		    	    map().setGroupId(source.getId());
		    	    String x=source.getLang().value();
		    	    map().setLanguageType(x);
		    	  }
		    };
		    
		    ModelMapper modelMapper = new ModelMapper();
		    modelMapper.addMappings(groupMap); 
		   	
		   	*/ 
		    
		    System.out.println("------------------------------------------Group------------------------");
		   
		    /*List<AurGroupMaster> list=new ArrayList<>();
		    System.out.println("checklist.getGroups >> "+checklist.getGroups().getGroup().size());
		    		   System.out.println("checklist.getGroups >> "+checklist.getGroups().getGroup());
		    		   for(Checklist.Groups.Group x:checklist.getGroups().getGroup() ) {
		    			   System.out.println(x.getId());
		    			   System.out.println(x.getValue());
		    			   System.out.println(x.getLang());
		    			   System.out.println("next>>");
		    			   
		    			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
		    			   
		    			   AurGroupMaster dto=new AurGroupMaster();
		    			   dto.setGroupId(x.getId());
		    			   dto.setGroupName(x.getValue());
		    			   dto.setLanguageType(x.getLang().value());
		    			   System.out.println("Model:::>>"+dto);
		    			   list.add(dto);
		    			 
		    		   }
		    		   stockRepository.save(list);
		    		    System.out.println("------------------------------------------ completed");
		    		    
		    		    */
		    
		    /*
		    System.out.println("------------------------------------------Sector------------------------");
			   
		    List<AurSectorMaster> list=new ArrayList<>();
		    System.out.println("checklist.getGroups >> "+checklist.getSectors().getSector().size());
		    		   System.out.println("checklist.getGroups >> "+checklist.getSectors().getSector());
		    		   for(Checklist.Sectors.Sector x:checklist.getSectors().getSector() ) {
		    			   System.out.println(x.getId());
		    			   System.out.println(x.getValue());
		    			   System.out.println(x.getLang());
		    			   System.out.println("next>>");
		    			   
		    			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
		    			   
		    			   AurSectorMaster dto=new AurSectorMaster();
		    			   dto.setSectorId(x.getId());
		    			   dto.setSectorName(x.getValue());
		    			   dto.setLanguageType(x.getLang().value());
		    			   System.out.println("Model:::>>"+dto);
		    			   list.add(dto);
		    			 
		    		   }
		    stockSectorRepository.save(list);
		    System.out.println("------------------------------------------ completed");
		    */		    
		    
		    
		  /*  List<AurTextIdMaster> list=new ArrayList<>();
		    System.out.println("checklist.getGroups >> "+checklist.getTexts().getText().size());
		    		   System.out.println("checklist.getGroups >> "+checklist.getTexts().getText());
		    		   for(Checklist.Texts.Text x:checklist.getTexts().getText() ) {
		    			  System.out.println("next>>");
		    			   
		    			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
		    			   
		    			   AurTextIdMaster dto=new AurTextIdMaster();
		    			   dto.setTextId(x.getId());
		    			   dto.setTextShortDesc(x.getShort().getContent().get(0).toString()); // Code not completed
		    			   dto.setTextLongDesc(x.getLong()!=null?x.getLong().getContent().get(0).toString():""); // // Code not completed
		    			   dto.setLanguageType(x.getLang().value());
		    			   System.out.println("Model:::>>"+dto);
		    			   list.add(dto);
		    		 }
		    stockTextsRepository.save(list);
		    System.out.println("------------------------------------------ completed");*/
		    
		    
		   /* System.out.println("checklist.getGroups >> "+checklist.getStocks().getStock().size());
		    
		    List<AurItemsMaster> list=new ArrayList<>();
		    System.out.println("checklist.getGroups >> "+checklist.getItems().getItem().size());
		    		   for(Checklist.Items.Item x:checklist.getItems().getItem()) {
		    			  System.out.println("next>>"+x);
		    			   
		    			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
		    			   
		    			   AurItemsMaster dto=new AurItemsMaster();
		    			   dto.setTag(x.getTag());
		    			  
		    			   dto.setLanguageType(x.getLang().value()); // Code not completed
		    			   dto.setItemName(x.getValue()); // // Code not completed
		    			   System.out.println("Model:::>>"+dto);
		    			   list.add(dto);
		    		 }*/
		    //stockItemsRepository.save(list);
		    System.out.println("------------------------------------------ completed");
		    
		    int i=0; 
		    List<AuroraStockDetails> stocklist=new ArrayList<>();
		    System.out.println("checklist.getGroups >> "+checklist.getStocks().getStock().size());
		    		   for(Checklist.Stocks.Stock stockInfo:checklist.getStocks().getStock()) {
		    			  System.out.println("next>>"+stockInfo);
		    			   
		    			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
		    			   
		    			   AuroraStockDetails stockDomain=new AuroraStockDetails();
		    			   stockDomain.setIsin(stockInfo.getIsin());
		    			   stockDomain.setBeta(stockInfo.getBeta().getValue());
		    			   stockDomain.setBadNewRisk(stockInfo.getBnfrisk());
		    			  
		    			  
		    			   stockDomain.setCompanyName(stockInfo.getName());
		    			   
		    			   stockDomain.setBetaTxtid(stockInfo.getBeta().getText());
		    			  // dto.setAnalysisDate(x.get);
		    			   stockDomain.setBadNewFactor(stockInfo.getBadnews().getValue());
		    			   stockDomain.setBadNewFactorTxtid(stockInfo.getBadnews().getText());
		    			   stockDomain.setBadNewRisk(stockInfo.getBnfrisk());
		    			   stockDomain.setBearMktFactor(stockInfo.getBear().getValue());
		    			   stockDomain.setBearMktFactorTxtid(stockInfo.getBear().getText());
		    			   stockDomain.setBearMktRisk(stockInfo.getBmfrisk());
		    			   stockDomain.setCorrelation(stockInfo.getCorr2().getValue());
		    			   stockDomain.setCorrelationTxtid(stockInfo.getCorr2().getText());
		    			   stockDomain.setCurrencycode(stockInfo.getCcy());
		    			   stockDomain.setCountrycode(stockInfo.getCountry());
		    			   stockDomain.setCurrencycode(stockInfo.getCcy());
		    			   stockDomain.setDivend(stockInfo.getDividend().getValue());
		    			   stockDomain.setDivend_Txtid(stockInfo.getDividend().getText());
		    			   stockDomain.setEarnings(stockInfo.getErev());
		    			   stockDomain.setEarRevTrend(stockInfo.getErt().getValue());
		    			   stockDomain.setEarRevTrendStar(stockInfo.getErt().getStar());
		    			   stockDomain.setEarRevTrendTxtid(stockInfo.getErt().getText());
		    			   stockDomain.setErtPriceHist(stockInfo.getErtprice());
		    			   stockDomain.setErtTrenHistDate(new Timestamp(stockInfo.getErtdate().getMillisecond()));
		    			   stockDomain.setFirstAnlyDate(new Timestamp(stockInfo.getFirstdate().getValue().getMillisecond()));
		    			   stockDomain.setFirstAnlyDateTxtid(stockInfo.getFirstdate().getText());
		    			   stockDomain.setGlbEvaluation(stockInfo.getGleval().getValue());
		    			   stockDomain.setGlbEvaluationTxtid(stockInfo.getGleval().getText());
		    			   stockDomain.setGlbHistDate(new Timestamp(stockInfo.getGldate().getMillisecond()));
		    			   stockDomain.setGroups(stockInfo.getGroup().toString());
		    			   stockDomain.setInterest(stockInfo.getIrst().getValue());
		    			   stockDomain.setInterestTxtid(stockInfo.getIrst().getText());
		    			   stockDomain.setIntHistDate(new Timestamp(stockInfo.getIrstdate().getMillisecond()));
		    			   stockDomain.setIsPDFReport(stockInfo.getPdf());
		    			   stockDomain.setLTG(stockInfo.getLtg().getValue());
		    			   stockDomain.setLTGTxtid(stockInfo.getLtg().getText());
		    			   stockDomain.setLTPE(stockInfo.getLtpe().getValue());
		    			   stockDomain.setLTPETxtid(stockInfo.getLtpe().getText());
		    			   stockDomain.setMarketCapBn(stockInfo.getMc().getValue());
		    			   stockDomain.setMarketCapBnTxtid(stockInfo.getMc().getText());
		    			   stockDomain.setMedTermTechTrend(stockInfo.getTt().getValue());
		    			   stockDomain.setMedTermTechTrendStar(stockInfo.getTt().getStar());
		    			   stockDomain.setMedTermTechTrendTxtid(stockInfo.getTt().getText());
		    			   stockDomain.setNumberOfAnalyst(stockInfo.getNbranl().getValue());
		    			   stockDomain.setNumberOfAnalystTxtid(stockInfo.getNbranl().getText());
		    			   stockDomain.setPayout(stockInfo.getPayout());
		    			   stockDomain.setPERatio(stockInfo.getGpe().getValue());
		    			   stockDomain.setPERatioTxtid(stockInfo.getGleval().getText());
		    			   stockDomain.setPremium(stockInfo.getPremium());
		    			   stockDomain.setRefIndexName(stockInfo.getIdx());
		    			   stockDomain.setRelPer4week(stockInfo.getRp().getValue());
		    			   stockDomain.setRelPer4weekStar(stockInfo.getRp().getStar());
		    			   stockDomain.setRelPer4weekTxtid(stockInfo.getRp().getText());
		    			   stockDomain.setRiskHistDate(new Timestamp(stockInfo.getRiskdate().getMillisecond()));
		    			   stockDomain.setRiskPercentage(stockInfo.getVarpct());
		    			   stockDomain.setRiskZone(stockInfo.getRiskzone().getValue());
		    			   stockDomain.setRiskZoneTxtid(stockInfo.getRiskzone().getText());
		    			   stockDomain.setSector(stockInfo.getSector());
		    			   stockDomain.setStockPrice(stockInfo.getPrice());
		    			   stockDomain.setTechHistDate(new Timestamp(stockInfo.getTtdate().getMillisecond()));
		    			   stockDomain.setTechRevMax(stockInfo.getTrMax());
		    			   stockDomain.setTechRevMin(stockInfo.getTrMin());
		    			   stockDomain.setTicker(stockInfo.getTicker());
		    			   stockDomain.setValRating(stockInfo.getVr().getValue());
		    			   stockDomain.setValRatingStar(stockInfo.getVr().getStar());
		    			   stockDomain.setValRatingTxtid(stockInfo.getVr().getText());
		    			   stockDomain.setValueRisk(stockInfo.getVarval().getValue());
		    			   stockDomain.setValueRiskTxtid(stockInfo.getVarval().getText());
		    			   stockDomain.setVol1month(stockInfo.getVol1M());
		    			   stockDomain.setVol21month(stockInfo.getVol12M());
		    			   stockDomain.setYearofanalysis(stockInfo.getLtgyear());
		    			  
			    		   System.out.println(i++);
			    		   stocklist.add(stockDomain);
							if(i%50==0) {
								
								stockDetailsRepository.save(stocklist);
								stocklist=new ArrayList<>();
							}
					 	
				 	}
			 stockDetailsRepository.save(stocklist);
		    System.out.println("------------------------------------------ completed");
		    
		    
		    		    
		   // System.out.println("checklist.getItems >> "+checklist.getItems());
		    System.out.println("------------------------------------------");
		    
		    
		    /*
		     * try{
			System.out.println("checklist.funds "); 
		    String fileName = "fundsChecklistSample.xml";
	        ClassLoader classLoader = new Main().getClass().getClassLoader();
	 
	        File file = new File(classLoader.getResource(fileName).getFile());
		    //creating the JAXB context
		    JAXBContext jContext = JAXBContext.newInstance(Checklist.class);
		    //creating the unmarshall object
		    Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
		    //calling the unmarshall method
		    Checklist checklist=(Checklist) unmarshallerObj.unmarshal(file);
		    System.out.println("checklist.funds >> "+checklist);
		}catch(Exception e){
		    e.printStackTrace();
		}
		*/
		    
		}catch(Exception e){
		    e.printStackTrace();
		}
		return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/funds", method = RequestMethod.GET)
	public ResponseEntity<?> funddataload() {
		try{
			String fileName = "Aurora_FundsCheckList.xml";
	        ClassLoader classLoader = new TestController().getClass().getClassLoader();
	        File file = new File(classLoader.getResource(fileName).getFile());
	        JAXBContext jContext = JAXBContext.newInstance(com.social.data.parse.funds.Checklist.class);
		    //creating the unmarshall object
		    Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
		    //calling the unmarshall method
		    com.social.data.parse.funds.Checklist checklist=(com.social.data.parse.funds.Checklist) unmarshallerObj.unmarshal(file);
		    System.out.println("checklist.funds >> "+checklist.getFunds().getFund().size());
		    System.out.println("checklist.getProfiles >> "+checklist.getProfiles());
		    System.out.println("checklist.getPromoters >> "+checklist.getPromoters());
		    System.out.println("checklist.getItems >> "+checklist.getItems());
		    
		  
		   List<FundsProfileCheckListMaster> list=new ArrayList<>();
		    System.out.println("checklist.getGroups >> "+checklist.getProfiles().getProfile().size());
 		    System.out.println("checklist.getGroups >> "+checklist.getProfiles().getProfile());
 		   for(com.social.data.parse.funds.Checklist.Profiles.Profile x:checklist.getProfiles().getProfile() ) {
 			   System.out.println(x.getId());
 			   System.out.println(x.getValue());
 			   System.out.println(x.getLang());
 			   System.out.println("next>>");
 			   
 			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
 			   
 			   FundsProfileCheckListMaster dto=new FundsProfileCheckListMaster();
 			   dto.setProfileId(x.getId());
 			   dto.setDescription(x.getValue());
 			   dto.setLanguage(x.getLang().value());
 			   System.out.println("Model:::>>"+dto);
 			   list.add(dto);
 			 
 		   }
 		  fundsProfileRepository.save(list);
 		   System.out.println("------------------------------------------ completed");
 		    
		    
		    
		   List<FundsPromotersCheckListMaster> fundPromotorlist=new ArrayList<>();
		    System.out.println("checklist.getGroups >> "+checklist.getPromoters().getPromoter().size());
 		    System.out.println("checklist.getGroups >> "+checklist.getPromoters().getPromoter());
 		   for(com.social.data.parse.funds.Checklist.Promoters.Promoter x:checklist.getPromoters().getPromoter() ) {
 			   System.out.println(x.getId());
 			   System.out.println(x.getValue());
 			   System.out.println("next>>");
 			   
 			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
 			   
 			  FundsPromotersCheckListMaster dto=new FundsPromotersCheckListMaster();
 			   dto.setPromoterId(x.getId());
 			   dto.setDescription(x.getValue());
 			   //dto.setLanguage(x.ge.value());
 			    			   System.out.println("Model:::>>"+dto);
 			    			  fundPromotorlist.add(dto);
 			 
 		   }
 		  fundsPromotersRepository.save(fundPromotorlist);
 		   System.out.println("------------------------------------------ completed");
		    
		    List<FundItemsCheckListMaster> fundItemlist=new ArrayList<>();
		    System.out.println("checklist.getGroups >> "+checklist.getItems().getItem().size());
 		    System.out.println("checklist.getGroups >> "+checklist.getItems().getItem());
 		   for(com.social.data.parse.funds.Checklist.Items.Item x:checklist.getItems().getItem()) {
 			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
 			   
 			  FundItemsCheckListMaster dto=new FundItemsCheckListMaster();
 			   dto.setItemTag(x.getTag());
 			   dto.setDescription(x.getValue());
 			   dto.setLanguage(x.getLang()!=null ?x.getLang().value():"");
 			    			   System.out.println("Model:::>>"+dto);
 			    			  fundItemlist.add(dto);
 			 
 		   }
 		  fundItemsRepository.save(fundItemlist);
 		   System.out.println("------------------------------------------ completed");
 		   
 		 List<FundAreasCheckListMaster> fundArealist=new ArrayList<>();
		   System.out.println("checklist.getGroups >> "+checklist.getAreas().getArea().size());
 		   System.out.println("checklist.getGroups >> "+checklist.getAreas().getArea());
 		   for(com.social.data.parse.funds.Checklist.Areas.Area x:checklist.getAreas().getArea()) {
 			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
 			   
 			  FundAreasCheckListMaster dto=new FundAreasCheckListMaster();
 			   dto.setAreaId(x.getId());
 			   dto.setDescription(x.getValue());
 			   dto.setLanguage(x.getLang()!=null ?x.getLang().value():"");
 			    			   System.out.println("Model:::>>"+dto);
 			    			  fundArealist.add(dto);
 			 
 		   }
 		   fundAreasRepository.save(fundArealist);
 		   System.out.println("------------------------------------------ completed");
		    
		   List<FundsAssetClassesCheckListMaster> fundAssestlist=new ArrayList<>();
			 //  System.out.println("checklist.getGroups >> "+checklist.getAreas().getArea().size());
	 		  // System.out.println("checklist.getGroups >> "+checklist.getAreas().getArea());
	 		   for(com.social.data.parse.funds.Checklist.Assetclasses.Assetclass x:checklist.getAssetclasses().getAssetclass()) {
	 			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
	 			   
	 			  FundsAssetClassesCheckListMaster dto=new FundsAssetClassesCheckListMaster();
	 			   dto.setAssetclassId(x.getId());
	 			   dto.setDescription(x.getValue());
	 			   dto.setLanguage(x.getLang()!=null ?x.getLang().value():"");
	 			    			   System.out.println("Model:::>>"+dto);
	 			    			  fundAssestlist.add(dto);
	 			 
	 		   }
	 		   fundsAssetClassesRepository.save(fundAssestlist);
	 		   System.out.println("------------------------------------------ completed");
		   
		    
		    
		    List<FundTextsCheckListMaster> fundTextList=new ArrayList<>();
			 //  System.out.println("checklist.getGroups >> "+checklist.getAreas().getArea().size());
	 		  // System.out.println("checklist.getGroups >> "+checklist.getAreas().getArea());
	 		   for(com.social.data.parse.funds.Checklist.Texts.Text x:checklist.getTexts().getText()) {
	 			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
	 			   
	 			  FundTextsCheckListMaster dto=new FundTextsCheckListMaster();
	 			   dto.setTextId(x.getId());
	 			   dto.setFundType(x.getShort()!=null?x.getShort().getContent().get(0).toString():"");
	 			   dto.setDescription(x.getLong()!=null ?x.getLong().getContent().get(0).toString():"");
	 			   dto.setLanguage(x.getLang()!=null ?x.getLang().value():"");
	 			    			   System.out.println("Model:::>>"+dto);
	 			    fundTextList.add(dto);
	 			 
	 		   }
	 		   fundTextsRepository.save(fundTextList);
	 		   System.out.println("------------------------------------------ completed");
		    
		   
 		   
		     	//System.out.println("checklist.getGroups >> "+checklist.getFunds().getPublishdate());
			 	//System.out.println("checklist.getGroups >> "+checklist.getFunds().getAnalysisdate());
			 	//System.out.println("checklist.getGroups >> "+checklist.getFunds().getFund());
			 	List<Funds> fundsList=new ArrayList<>();
			 	int i=0;
			 	List<com.social.data.parse.funds.Checklist.Funds.Fund>  f_1=checklist.getFunds().getFund();
			 	for (com.social.data.parse.funds.Checklist.Funds.Fund f:f_1) {
				 	Funds funds=new Funds();
				 	funds.setCcy(f.getCcy());
				 	funds.setPublishDate(new Timestamp(checklist.getFunds().getPublishdate().getMillisecond()));
				 	funds.setAnalysisDate(new Timestamp(checklist.getFunds().getAnalysisdate().getMillisecond()));
				 	funds.setAuthCountries(f.getAuthCountries());
				 	funds.setPea(f.getPea());
				 	funds.setUcits(f.getUcits());
				 	funds.setEtf(f.getEtf());
				 	funds.setPdf(f.getPdf());
				 	funds.setPricingFrequency(f.getPricingFrequency());
				 	funds.setIsin(f.getIsin());
				 	funds.setAssetClass(f.getAssetclass());
				 	funds.setProfile(f.getProfile());
				 	funds.setPromotor(f.getPromoter());
				 	funds.setAlpha(f.getAlpha().getValue());
				 	funds.setAlphaTextId(f.getAlpha().getText());
				    funds.setAlphaDate(f.getAlphadate()!=null ?new Timestamp(f.getAlphadate().getMillisecond()):null);
				 	funds.setArea(f.getArea().longValue()); // short &Text
				 	funds.setAsset(f.getAsset().getValue());
				 	funds.setAssetTextId(f.getAsset().getText());
				 	funds.setAssetDate(f.getAssetdate()!=null ? new Timestamp(f.getAssetdate().getMillisecond()):null);
				 	funds.setBear(f.getBear().getValue());
				 	funds.setBearTextId(f.getBear().getText());
				 	funds.setBednew(f.getBadnews().getValue());
				 	funds.setBednewTextId(f.getBadnews().getText());
				 	funds.setBeta(f.getBeta().getValue());
				 	funds.setBetaTextId(f.getBeta().getText());
				 	funds.setBmfRisk(f.getBmfrisk());
				 	funds.setBnfRisk(f.getBnfrisk());
				 	funds.setCorr2(f.getCorr2().getValue());
				 	funds.setCorr2TextId(f.getCorr2().getText());
				 	funds.setFirstDate(f.getFirstdate()!=null? new Timestamp(f.getFirstdate().getValue().getMillisecond()):null);
				 	funds.setFirstDateTextId(f.getFirstdate()!=null?f.getFirstdate().getText():null);
				 	funds.setGlDate(f.getGldate()!=null ?new Timestamp(f.getGldate().getMillisecond()):null);
				 	funds.setGleval(f.getGleval().getValue());
				 	funds.setGlevalTextId(f.getGleval().getText());
				 	funds.setInceptionDate(new Timestamp(f.getInceptiondate().getMillisecond()));
				 	funds.setInforatio(f.getInforatio().getValue());
				 	funds.setInforatioStar(f.getInforatio().getStar());
				 	funds.setInforatioTextId(f.getInforatio().getText());
				 	funds.setInformationDate(f.getInforatiodate()!=null ?new Timestamp(f.getInforatiodate().getMillisecond()):null);
				 	funds.setIrst(f.getIrst().getValue());
				 	funds.setIrstTextId(f.getIrst().getText());
				 	funds.setIrstDate(f.getIrstdate()!=null ? new Timestamp(f.getIrstdate().getMillisecond()):null);
				 	funds.setMgtfees(f.getMgtfees());
				 	funds.setName(f.getName());
				 	funds.setNav(f.getNav());
				 	funds.setNavDate(f.getNavdate() !=null?new Timestamp(f.getNavdate().getMillisecond()):null);
				 	funds.setPerf1y(f.getPerf1Y());
				 	funds.setPerf3y(f.getPerf3Y());
				 	funds.setPerf5y(f.getPerf5Y());
				 	funds.setPerftd(f.getPerfytd());
				 	funds.setRedemptionFees(f.getRedemptionfees());
				 	funds.setRefAssetClass(f.getRefAssetclass().longValue());
				 	funds.setRefCountry(f.getRefCountry());
				 	funds.setRefProfile(f.getRefProfile());
				 	funds.setRiskDate(new Timestamp(f.getRiskdate().getMillisecond()));
				 	funds.setRiskZone(f.getRiskzone().getValue());
				 	funds.setRiskzoneTextId(f.getRiskzone().getText());
				 	funds.setRp6m(f.getRp6M().getValue());
				 	funds.setRp6mStar(f.getRp6M().getStar());
				 	funds.setRp6mTextId(f.getRp6M().getText());
				 	funds.setSharpDate(f.getSharpedate()!=null? new Timestamp(f.getSharpedate().getMillisecond()):null);
				 	funds.setSharpe(f.getSharpe().getValue());
				 	funds.setSharpeStar(f.getSharpe().getStar());
				 	funds.setSharpeTextId(f.getSharpe().getText());
				 	funds.setTrMax(f.getTrMax());
				 	funds.setTrMin(f.getTrMin());
				 	funds.setTracking(f.getTracking().getValue());
				 	funds.setTrackingTextId(f.getTracking().getText());
				 	funds.setTt(f.getTt().getValue());
				 	funds.setTtStar(f.getTt().getStar());
				 	funds.setTtTextId(f.getTt().getText());
					funds.setTtDate(f.getTtdate()!=null?new Timestamp(f.getTtdate().getMillisecond()):null);
					funds.setVarpct(f.getVarpct());
					funds.setVarval(f.getVarval().getValue());
					funds.setVarvalTextId(f.getVarval().getText());
					funds.setVol12M(f.getVol12M());
					funds.setVol1m(f.getVol1M());
					System.out.println(i++);
					fundsList.add(funds);
					if(i%50==0) {
						
						fundsRepository.save(fundsList);
						fundsList=new ArrayList<>();
					}
				 	
			 	}
			   fundsRepository.save(fundsList);
	 		  // System.out.println("checklist.getGroups >> "+checklist.getAreas().getArea());
	 		 /*  for(com.social.data.parse.funds.Checklist.Funds.Fund x:checklist.getFunds()) {
	 			   //AurGroupMaster dto = modelMapper.map(x, AurGroupMaster.class);
	 			   
	 			  Funds dto=new Funds();
	 			   dto.setTextId(x.getId());
	 			   dto.setFundType(x.getShort()!=null?x.getShort().getContent().get(0).toString():"");
	 			   dto.setDescription(x.getLong()!=null ?x.getLong().getContent().get(0).toString():"");
	 			   dto.setLanguage(x.getLang()!=null ?x.getLang().value():"");
	 			    			   System.out.println("Model:::>>"+dto);
	 			   list.add(dto);
	 			 
	 		   }*/
	 		  // fundTextsRepository.save(list);
	 		   System.out.println("------------------------------------------ completed");
 		   
 		   
 	

		    
		}catch(Exception e){
		    e.printStackTrace();
		}
		return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
	}

}
