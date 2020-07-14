package com.sbs.vc.scan.maintenance;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.vc.scan.domain.ScanDetailMaster;
import com.sbs.vc.scan.domain.ScanImageDomain;
import com.sbs.vc.scan.domain.ScanMaster;
import com.sbs.vc.scan.dto.MappingObject;
import com.sbs.vc.scan.repository.ScanDetailsRepository;
import com.sbs.vc.scan.repository.ScanImageRepository;
import com.sbs.vc.scan.repository.ScanMasterRepository;

@Service
public class ScanImageMaintenance {
	
	@Autowired
	ScanImageRepository scanImageRepository;
	
	@Autowired
	ScanMasterRepository scanMasterRepository;
	
	@Autowired
	ScanDetailsRepository scanDetailsRepository;
	
	
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
		
		mapperObjectAndSave(list);
		return true;
	}
	
	
	public boolean mapperObjectAndSave(List<MappingObject>  list) {
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
				case "OTH_1":
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
		return true;
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

	
	
	
	

}
