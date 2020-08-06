package com.sbs.vc.scan.maintenance;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sbs.vc.config.util.CommonUtils;
import com.sbs.vc.config.util.ResponseMessageDTO;
import com.sbs.vc.datapro.exceptions.ProcessFailedException;
import com.sbs.vc.scan.domain.Category;
import com.sbs.vc.scan.domain.ScanDetailMaster;
import com.sbs.vc.scan.domain.ScanImageDomain;
import com.sbs.vc.scan.domain.ScanMaster;
import com.sbs.vc.scan.dto.MappingObject;
import com.sbs.vc.scan.dto.MappingRequest;
import com.sbs.vc.scan.dto.SearchFilter;
import com.sbs.vc.scan.repository.CategoryRepository;
import com.sbs.vc.scan.repository.CountryRepository;
import com.sbs.vc.scan.repository.ScanDetailsRepository;
import com.sbs.vc.scan.repository.ScanImageRepository;
import com.sbs.vc.scan.repository.ScanMasterRepository;
import com.sbs.vc.scan.utils.ExtractVisitorDetails;

@Service
public class CardMaintenance {
	
	@Autowired
	ScanImageRepository scanImageRepository;
	
	@Autowired
	ScanMasterRepository scanMasterRepository;
	
	@Autowired
	ScanDetailsRepository scanDetailsRepository;
	
	@Autowired
	ScanReaderMaintenance scanReaderMaintenance;
	
	
	@Autowired
	ExtractVisitorDetails extractVisitorDetails;
	
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	
	public List<Category> getCategoties() {
		return categoryRepository.findAll();
	}
	
	public List<String> getCountry() {
		return countryRepository.getCountry();
	}
	
	
	public List<String> getState(String country) {
		return countryRepository.getStateList(country);
	}
	
	public List<String> getCity(String country,String state) {
		return countryRepository.getCityList(country,state);
	}
	
	public boolean saveScanRecords(List<MappingObject> mappingList) {
		List<MappingObject>  list=new ArrayList<MappingObject>();
		list.add(new MappingObject("f1","NAME","Bipin Kumar"));
		list.add(new MappingObject("f2","DESIGNATION","Software"));
		list.add(new MappingObject("f3","MOBILE_NO","+91-9922299999"));
		list.add(new MappingObject("f4","EMAIL_ID","bipin@gmail.com"));
		list.add(new MappingObject("f5","COUNTRY","India"));
		list.add(new MappingObject("f6","ADDRESS","Az "));
		list.add(new MappingObject("f7","ADDRESS"," 34/35"));
		list.add(new MappingObject("f8","ADDRESS"," New Delhi"));
		list.add(new MappingObject("f9","ADDRESS","India"));
		list.add(new MappingObject("f10","COMMENTS","Dummy records"));
		list.add(new MappingObject("cipharText","cipharText","Dummy records"));
		
		mapperObjectAndSave(list);
		return true;
	}
	
	public ScanDetailMaster saveScanRecords(String[] contents) {
		List<MappingObject>  list=new ArrayList<MappingObject>();
		int index=0;
		for(String content:contents) {
			list.add(new MappingObject("f"+(++index),"","content"));
		}
		ScanDetailMaster scanDetailMaster=mapperObjectAndSave(list);
		return scanDetailMaster;
	}
	
	
	public ScanDetailMaster mapperObjectAndSave(List<MappingObject>  list) {
		ScanMaster scanMaster=new ScanMaster();
		ScanDetailMaster scanDetailMaster=new ScanDetailMaster();
		ScanImageDomain scanImageDomain=new ScanImageDomain();
		for(MappingObject mappingObject:list) {
			switch(mappingObject.getStageField()) {
				case "f1":
					scanMaster.setF1(mappingObject.getValue());
					break;
				case "f2":
					scanMaster.setF2(mappingObject.getValue());
					break;
				case "f3":
					scanMaster.setF3(mappingObject.getValue());
					break;
				case "f4":
					scanMaster.setF4(mappingObject.getValue());
					break;
				case "f5":
					scanMaster.setF5(mappingObject.getValue());
					break;
				case "f6":
					scanMaster.setF6(mappingObject.getValue());
					break;
				case "f7":
					scanMaster.setF7(mappingObject.getValue());
					break;
				case "f8":
					scanMaster.setF8(mappingObject.getValue());
					break;
				case "f9":
					scanMaster.setF9(mappingObject.getValue());
					break;
				case "f10":
					scanMaster.setF10(mappingObject.getValue());
					break;
				case "cipharText":
					scanMaster.setCipharText(mappingObject.getValue());
					break;
			}	
			
			switch(mappingObject.getTargetField()) {
				case "NAME":
					scanDetailMaster.setName(mappingObject.getValue());
					break;
				case "DESIGNATION":
					scanDetailMaster.setDesignation(mappingObject.getValue());
					break;
				case "MOBILE_NO":
					scanDetailMaster.setMobileNO(mappingObject.getValue());
					break;
				case "EMAIL_ID":
					scanDetailMaster.setEmailId(mappingObject.getValue());
					break;
				case "COUNTRY":
					scanDetailMaster.setCountry(mappingObject.getValue());
					break;
				case "ADDRESS":
					scanDetailMaster.setAddress(scanDetailMaster.getAddress()==null?mappingObject.getValue():scanDetailMaster.getAddress()+ ","+mappingObject.getValue());
					break;
				case "COMMENTS":
					scanDetailMaster.setComments(mappingObject.getValue());
					break;
				case "WEBSITE":
					scanDetailMaster.setOther1(mappingObject.getValue());
					break;
				case "OTH_2":
					scanDetailMaster.setOther2(mappingObject.getValue());
					break;
				case "OTH_3":
					scanDetailMaster.setOther3(mappingObject.getValue());
					break;
				case "OTH_4":
					scanDetailMaster.setOther4(mappingObject.getValue());
					break;
					
				case "STATE":
					scanDetailMaster.setState(mappingObject.getValue());
					break;
					
				case "CITY":
					scanDetailMaster.setCity(mappingObject.getValue());
					break;
					
				case "cipharText":
					scanDetailMaster.setCipharText(mappingObject.getValue());
					break;
			}		
		}
		
		
		//Save scanMaster
		
		//Save Image Master
		scanImageDomain.setName("image_logo.png");
		scanImageDomain.setPic(readBytesFromFile("D:\\a.png"));
		scanImageRepository.save(scanImageDomain);
		scanMaster.setScanImage(scanImageDomain);
		scanMasterRepository.save(scanMaster);
		scanDetailMaster.setScanMaster(scanMaster);
		scanDetailsRepository.save(scanDetailMaster);
		return scanDetailMaster;
	}
	
	 private static byte[] readBytesFromFile(String filePath) {

	        FileInputStream fileInputStream = null;
	        byte[] bytesArray = null;

	        try {

	            File file = new File(filePath);
	            bytesArray = new byte[(int) file.length()];

	            //read file into bytes[]
	            fileInputStream = new FileInputStream(file);
	            fileInputStream.read(bytesArray);

	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (fileInputStream != null) {
	                try {
	                    fileInputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }

	        }

	        return bytesArray;

	    }


	public ScanDetailMaster getScanDetailsData(long recordId) {
		return scanDetailsRepository.findById(recordId).get();
		
		
	}
	
	public List<Object[]> autoSuggest(String searchString) {
		List<Object[]> result=scanDetailsRepository.getAutosuggestion(searchString,searchString,searchString);
		return result;
	}
	
	public List<ScanDetailMaster>getResult(String searchString) {
		List<ScanDetailMaster> result=scanDetailsRepository.findByCipharTextContainingIgnoreCaseAndStatus(searchString, "Y");
		if(result==null || result.size()==0)
		result=scanDetailsRepository.findByCityContainingIgnoreCaseAndStatus(searchString, "Y");
		if(result==null || result.size()==0)
		result=scanDetailsRepository.findByStateContainingIgnoreCaseAndStatus(searchString, "Y");
		if(result==null || result.size()==0)
		result=scanDetailsRepository.findByCountryContainingIgnoreCaseAndStatus(searchString, "Y");
		return result;
	}
	
	/*
	 * public ScanDetailMaster autoSuggest(String searchOn,String searchString) {
	 * return scanDetailsRepository.findById(searchString).get(); }
	 * 
	 * public ScanDetailMaster searchResult(String searchString) { return
	 * scanDetailsRepository.findById(recordId).get();
	 * 
	 * 
	 * }
	 * 
	 * public ScanDetailMaster searchResult(String searchOn,String searchString) {
	 * return scanDetailsRepository.findById(searchString).get();
	 * 
	 * 
	 * }
	 */
	
	
	public ScanDetailMaster saveUploadedFiles(String username,List<MultipartFile> files) throws IOException {
		ScanMaster scanMaster=new ScanMaster();
		ScanDetailMaster scanDetailMaster=new ScanDetailMaster();
		ScanImageDomain scanImageDomain=new ScanImageDomain();
		for (MultipartFile file : files) {
        	scanImageDomain.setName(file.getOriginalFilename());
    		scanImageDomain.setPic(file.getBytes());
    		scanImageRepository.save(scanImageDomain);
    		writeBytesToFileNio(file.getBytes(),"D:\\app\\images\\"+scanImageDomain.getScanImageId()+"_"+scanImageDomain.getName());
    		List<MappingObject> list=scanReaderMaintenance.runScript("D:\\app\\images\\"+scanImageDomain.getScanImageId()+"_"+scanImageDomain.getName());
    		scanMaster=mapperObjectforScanMaster(list);
    		scanMaster.setScanImage(scanImageDomain);
    		scanMasterRepository.save(scanMaster);
    		scanDetailMaster.setEmailId(ExtractVisitorDetails.getEmailId(scanMaster.getCipharText()));
    		scanDetailMaster.setMobileNO(ExtractVisitorDetails.getMobileNumber(scanMaster.getCipharText()));
    		scanDetailMaster.setWebsite(ExtractVisitorDetails.getWebsites(scanMaster.getCipharText()));
    		scanDetailMaster.setCipharText(scanMaster.getCipharText());
    		//
    		extractVisitorDetails.setAddress(scanDetailMaster);
    		scanDetailMaster.setScanMaster(scanMaster);
    		scanDetailMaster.setCategory("");
    		scanDetailMaster.setComments("");
    		scanDetailMaster.setStatus("N");
    		scanDetailMaster.setCreatedBy(username);
    		//String ciphar = new String(file.getBytes(), StandardCharsets.UTF_8);
    		//setAnalyzedata(scanDetailMaster, ciphar);
    		scanDetailsRepository.save(scanDetailMaster);
    	}
		return scanDetailMaster;
    }
	
	 //Since JDK 7, NIO
    private static void writeBytesToFileNio(byte[] bFile, String fileDest) {

        try {
            Path path = Paths.get(fileDest);
            Files.write(path, bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    
    public ScanMaster mapperObjectforScanMaster(List<MappingObject>  list) {
		ScanMaster scanMaster=new ScanMaster();
		for(MappingObject mappingObject:list) {
			switch(mappingObject.getStageField()) {
				case "f1":
					scanMaster.setF1(mappingObject.getValue());
					break;
				case "f2":
					scanMaster.setF2(mappingObject.getValue());
					break;
				case "f3":
					scanMaster.setF3(mappingObject.getValue());
					break;
				case "f4":
					scanMaster.setF4(mappingObject.getValue());
					break;
				case "f5":
					scanMaster.setF5(mappingObject.getValue());
					break;
				case "f6":
					scanMaster.setF6(mappingObject.getValue());
					break;
				case "f7":
					scanMaster.setF7(mappingObject.getValue());
					break;
				case "f8":
					scanMaster.setF8(mappingObject.getValue());
					break;
				case "f9":
					scanMaster.setF9(mappingObject.getValue()); 
					break;
				case "f10":
					scanMaster.setF10(mappingObject.getValue());
					break;
				case "cipharText":
					scanMaster.setCipharText(mappingObject.getValue());
					break;
			}
		}
		return scanMaster;
	}
    public void setAnalyzedata(ScanDetailMaster scanDetailMaster, String ciphar) {
    	String result=null;
    	Pattern pattern = Pattern.compile("\\d{3}\\d{3}\\d{4}");
    	Matcher matcher = pattern.matcher(ciphar);
    	
    	if (matcher.find()) {
    		if(result==null) {
    			result=matcher.group(0);
    		}else {
    			result=result+","+matcher.group(0);
    		}
    	}
    	scanDetailMaster.setMobileNO(result);
    	scanDetailMaster.setOther4(ciphar);
    }
    /**
     * This Method used to set the  ScanDetailMaster object from the Mapping Object
     * @param scanDetailMaster
     * @param list
     * @return
     */
	public ScanDetailMaster setScanDetailsFromMapping(MappingRequest mappingRequest) {
		Long scanId=mappingRequest.getScanId();
		ScanDetailMaster scanDetailMaster=scanDetailsRepository.findById(scanId).get();
		for(MappingObject mappingObject:mappingRequest.getMapperObject()) {
			switch(mappingObject.getTargetField()) {
				case "NAME":
					scanDetailMaster.setName(mappingObject.getValue());
					break;
				case "DESIGNATION":
					scanDetailMaster.setDesignation(mappingObject.getValue());
					break;
				case "MOBILE_NO":
					scanDetailMaster.setMobileNO(mappingObject.getValue());
					break;
				case "EMAIL_ID":
					scanDetailMaster.setEmailId(mappingObject.getValue());
					break;
				
				case "ADDRESS":
					scanDetailMaster.setAddress(scanDetailMaster.getAddress()==null?mappingObject.getValue():scanDetailMaster.getAddress()+ ","+mappingObject.getValue());
					break;
				case "COMMENTS":
					scanDetailMaster.setComments(mappingObject.getValue());
					break;
				case "WEBSITE":
					scanDetailMaster.setOther1(mappingObject.getValue());
					break;
				case "OTH_2":
					scanDetailMaster.setOther2(mappingObject.getValue());
					break;
				case "OTH_3":
					scanDetailMaster.setOther3(mappingObject.getValue());
					break;
				case "OTH_4":
					scanDetailMaster.setOther4(mappingObject.getValue());
					break;
				case "COUNTRY":
					scanDetailMaster.setCountry(mappingObject.getValue());
					break;
					
				case "STATE":
					scanDetailMaster.setState(mappingObject.getValue());
					break;
					
				case "CITY":
					scanDetailMaster.setCity(mappingObject.getValue());
					break;
				
			}	
		}
		scanDetailMaster.setCipharText(scanDetailMaster.toString());
		scanDetailMaster.setStatus("Y");
		scanDetailsRepository.save(scanDetailMaster);
		return scanDetailMaster;
	}
	
	public ScanDetailMaster updateRecords(ScanDetailMaster editObj,String userId) throws IOException {
		ScanDetailMaster domObj=scanDetailsRepository.findById(editObj.getId()).get();
		domObj.setAddress(editObj.getAddress());
		domObj.setMobileNO(editObj.getMobileNO());
		domObj.setCity(editObj.getCity());
		domObj.setComments(editObj.getComments());
		domObj.setCountry(editObj.getCountry());
		domObj.setState(editObj.getState());
		domObj.setName(editObj.getName());
		domObj.setDesignation(editObj.getDesignation());
		domObj.setOther1(editObj.getOther1());
		domObj.setOther2(editObj.getOther2());
		domObj.setOther3(editObj.getOther3());
		domObj.setOther4(editObj.getOther4());
		domObj.setCategory(editObj.getCategory());
		domObj.setStatus("Y");
		domObj.setUpdatedBy(userId);
		scanDetailsRepository.save(domObj);
		return domObj;
    }
	
	
    /**
     * Search Records from the 
     */
	public void deleteScanId(long recordId,String userId) {
		ScanDetailMaster scanDetailMaster= scanDetailsRepository.findById(recordId).get();
		scanDetailMaster.setStatus("N");
		scanDetailMaster.setUpdatedBy(userId);;
		scanDetailsRepository.save(scanDetailMaster);
	}

	public List<ScanDetailMaster> getSearchResult(SearchFilter searchFilter) {
		List<ScanDetailMaster> result=null;
		if(searchFilter.getSearchString()==null) {searchFilter.setSearchString("");}
		
		if(searchFilter.getCategory().equalsIgnoreCase("All") && searchFilter.getSearchBy().equals("Country")) {
			result=scanDetailsRepository.findByCountryContainingIgnoreCaseAndStatus(searchFilter.getSearchString(), "Y");
		}else if(searchFilter.getSearchBy().equals("Country")) {
			result=scanDetailsRepository.findByCategoryAndCountryContainingIgnoreCaseAndStatus(searchFilter.getCategory(),searchFilter.getSearchString(), "Y");
		}
		
		if(searchFilter.getCategory().equalsIgnoreCase("All") && searchFilter.getSearchBy().equals("Name")) {
			result=scanDetailsRepository.findByNameContainingIgnoreCaseAndStatus(searchFilter.getSearchString(), "Y");
		}if(searchFilter.getSearchBy().equals("Name")) {
        	result=scanDetailsRepository.findByCategoryAndNameContainingIgnoreCaseAndStatus(searchFilter.getCategory(),searchFilter.getSearchString(), "Y");
		}
        
        if(searchFilter.getCategory().equalsIgnoreCase("All") && searchFilter.getSearchBy().equals("Address")) {
        	result=scanDetailsRepository.findByAddressContainingIgnoreCaseAndStatus(searchFilter.getSearchString(), "Y");
		}else if (searchFilter.getSearchBy().equals("Address")) {
			result=scanDetailsRepository.findByCategoryAndAddressContainingIgnoreCaseAndStatus(searchFilter.getCategory(),searchFilter.getSearchString(), "Y");
		}
        
        if(searchFilter.getCategory().equalsIgnoreCase("All") && searchFilter.getSearchBy().equalsIgnoreCase("All")){
         	result=scanDetailsRepository.findByCipharTextContainingIgnoreCaseAndStatus(searchFilter.getSearchString(), "Y");
    	}else if(searchFilter.getSearchBy().equalsIgnoreCase("All")) {
        	result=scanDetailsRepository.findByCategoryAndCipharTextContainingIgnoreCaseAndStatus(searchFilter.getCategory(),searchFilter.getSearchString(), "Y");
		}
        
        if(searchFilter.getSearchBy().equalsIgnoreCase("All") && searchFilter.getCategory().equalsIgnoreCase("All")) {
        	result=scanDetailsRepository.findByCipharTextContainingIgnoreCaseAndStatus(searchFilter.getSearchString(), "Y");
		}
        return result;
	}
	

}
