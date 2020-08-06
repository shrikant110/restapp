package com.sbs.vc.scan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbs.vc.config.util.CommonUtils;
import com.sbs.vc.config.util.ResponseMessageDTO;
import com.sbs.vc.datapro.auth.security.CurrentUser;
import com.sbs.vc.datapro.auth.security.UserPrincipal;
import com.sbs.vc.datapro.exceptions.FileNotSelectedException;
import com.sbs.vc.datapro.exceptions.ProcessFailedException;
import com.sbs.vc.scan.domain.ScanDetailMaster;
import com.sbs.vc.scan.dto.MappingObject;
import com.sbs.vc.scan.dto.MappingRequest;
import com.sbs.vc.scan.dto.SearchFilter;
import com.sbs.vc.scan.maintenance.CardMaintenance;
import com.sbs.vc.scan.maintenance.ScanReaderMaintenance;
@RestController
@RequestMapping("/vc")
public class ScanVCController {
	@Autowired
	CardMaintenance cardMainterance;
	
	@Autowired
	ScanReaderMaintenance scanReaderMaintenance;
	
	
	@GetMapping("/scan/save")
	public ResponseMessageDTO saveData() throws ProcessFailedException {
		List<MappingObject>  list=new ArrayList<MappingObject>();
		cardMainterance.saveScanRecords(list);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Save Successfully", "");
	}
	
	
	
	
	
	
	
	@GetMapping("/scan/autosuggest/{searchString}")
	public ResponseMessageDTO autosuggest(@PathVariable String searchString) throws ProcessFailedException {
		List<Object[]> result=cardMainterance.autoSuggest(searchString);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Registration Visitor", result);
	}
	
	@GetMapping("/scan/records/{searchString}")
	public ResponseMessageDTO getResult(@PathVariable String searchString) throws ProcessFailedException {
		List<ScanDetailMaster> result=cardMainterance.getResult(searchString);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Registration Visitor", result);
	}
	
	@PostMapping("/scan/search")
	public ResponseMessageDTO getResult(@RequestBody SearchFilter searchFilter) throws ProcessFailedException {
		List<ScanDetailMaster> result=cardMainterance.getSearchResult(searchFilter);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Registration Visitor", result);
	}
	
	
	@GetMapping("/scan/copy/image")
	public ResponseMessageDTO scanCopy() throws ProcessFailedException {
		ScanDetailMaster scanDetailMaster=scanReaderMaintenance.vcCardProcess();
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(),"Image Contents", scanDetailMaster);
	}
	
	@PostMapping(value="/file/upload")
	public ResponseMessageDTO uploadFile(@RequestParam("image") MultipartFile uploadfile,@CurrentUser UserPrincipal currentUser)
			throws FileNotSelectedException {
		if (uploadfile.isEmpty()) {
			throw new FileNotSelectedException("Upload proper file");
		}
		ScanDetailMaster scanDetailMaster;
		 try { 
			 String userName=currentUser.getUsername(); 
			 System.out.println("userName: "+userName);
			 scanDetailMaster=cardMainterance.saveUploadedFiles(userName,Arrays.asList(uploadfile));
		 }
		 catch (IOException e) {
			return CommonUtils.getSuccessMessage(HttpStatus.BAD_REQUEST.value(), "Error on file", null);
		}
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Image uploaded successfully", scanDetailMaster);
	}
	
	@PostMapping("/set/mapping")
	public ResponseMessageDTO setObjectMapping(@RequestBody MappingRequest mappingRequest) throws ProcessFailedException {
		ScanDetailMaster scanDetailMaster=cardMainterance.setScanDetailsFromMapping(mappingRequest);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(),"Image Contents", scanDetailMaster);
	}
	
	@GetMapping("/scan/get/{recordId}")
	public ResponseMessageDTO getScanDetailsData(@PathVariable long recordId) throws ProcessFailedException {
		ScanDetailMaster scanDetailMaster=cardMainterance.getScanDetailsData(recordId);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Registration Visitor", scanDetailMaster);
	}
	
	@PostMapping("/edit")
	public ResponseMessageDTO setObjectMapping(@RequestBody ScanDetailMaster editObj,@CurrentUser UserPrincipal currentUser) throws ProcessFailedException, IOException {
		editObj=cardMainterance.updateRecords(editObj,currentUser.getEmail());
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(),"Details is updated success", editObj);
	}
	
	@GetMapping("/getCategories")
	public ResponseMessageDTO getCategories() throws ProcessFailedException, IOException {
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(),"Category List Fetch Successfully", cardMainterance.getCategoties());
	}
	
	@GetMapping("/list/country")
	public ResponseMessageDTO getCountry() throws ProcessFailedException, IOException {
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(),"Country List Fetch Successfully", cardMainterance.getCountry());
	}
	@GetMapping("/list/state/{country}")
	public ResponseMessageDTO getCountry(@PathVariable String country) throws ProcessFailedException {
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(),"State List Fetch Successfully", cardMainterance.getState(country));
	}
	
	
	@GetMapping("/list/city/{country}/{state}")
	public ResponseMessageDTO getCountry(@PathVariable String country,@PathVariable String state) throws ProcessFailedException {
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(),"City List Fetch Successfully", cardMainterance.getCity(country, state));
	}
	
	@GetMapping("/scan/delete/{recordId}")
	public ResponseMessageDTO deleteScanCopy(@PathVariable Long recordId,@CurrentUser UserPrincipal currentUser) throws ProcessFailedException {
		cardMainterance.deleteScanId(recordId,currentUser.getEmail());
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(),"Delete Successfully",null );
	}

}
