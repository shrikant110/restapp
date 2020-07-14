package com.sbs.vc.scan.maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
