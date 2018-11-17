package com.social.dashboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.social.dashboard.dao.DashBoardRepository;
import com.social.dashboard.entities.Module;

@Service
public class DashBoardService {
	@Autowired
	DashBoardRepository dashBoardRepository;
	
	public List<Module> getModules() {
		return dashBoardRepository.findAll();
	}
	
	public Module getModule(Long id) {
		return dashBoardRepository.findOne(id);
	}
}


