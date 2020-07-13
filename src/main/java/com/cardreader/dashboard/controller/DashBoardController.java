package com.cardreader.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cardreader.dashboard.entities.Module;
import com.cardreader.dashboard.services.DashBoardService;

@RestController
@RequestMapping("dashboard")
public class DashBoardController {
	
	@Autowired
	DashBoardService dashBoardService;
	
	@CrossOrigin
	@RequestMapping(value="/getmodules", method = RequestMethod.GET)
	public  ResponseEntity<?> getModules(){
		System.out.println("inside -->getmodules");
		return new ResponseEntity<List<Module>>(dashBoardService.getModules(),HttpStatus.CREATED);
	}
	
	
	
	

}
