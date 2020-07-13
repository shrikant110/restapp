package com.sbs.vc.dashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.vc.config.util.CommonUtils;
import com.sbs.vc.config.util.ResponseMessageDTO;
import com.sbs.vc.dashboard.dto.DashboardDTO;
import com.sbs.vc.datapro.email.ClientEmails;

@RestController
@RequestMapping("dashboard")
//@PreAuthorize("hasRole('USER')")
public class DashBoardController {

	@Autowired
	ClientEmails clientEmails;

	@CrossOrigin
	@RequestMapping(value = "/getassetclassdata", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public ResponseMessageDTO getAssetClassData() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		System.out.println("inside -->getassetclassdata" + username);
		DashboardDTO dashboardDTO = new DashboardDTO();
		return CommonUtils.getSuccessMessage("DashBoard data fetch successfully", dashboardDTO);
	}

	

}
