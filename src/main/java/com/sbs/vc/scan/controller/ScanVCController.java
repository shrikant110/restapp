package com.sbs.vc.scan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.vc.config.util.CommonUtils;
import com.sbs.vc.config.util.ResponseMessageDTO;
import com.sbs.vc.datapro.exceptions.ProcessFailedException;
import com.sbs.vc.scan.domain.ScanDetailMaster;
import com.sbs.vc.scan.dto.MappingObject;
import com.sbs.vc.scan.maintenance.ScanImageMaintenance;
@RestController
@RequestMapping("/vc")
public class ScanVCController {
	@Autowired
	ScanImageMaintenance scanImageMaintenance;
	
	@GetMapping("/scan/save")
	public ResponseMessageDTO saveData() throws ProcessFailedException {
		List<MappingObject>  list=new ArrayList<MappingObject>();
		scanImageMaintenance.saveScanRecords(list);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Save Successfully", "");
	}
	
	
	@GetMapping("/scan/get/{recordId}")
	public ResponseMessageDTO getScanDetailsData(@PathVariable long recordId) throws ProcessFailedException {
		ScanDetailMaster scanDetailMaster=scanImageMaintenance.getScanDetailsData(recordId);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Registration Visitor", scanDetailMaster);
	}

}
