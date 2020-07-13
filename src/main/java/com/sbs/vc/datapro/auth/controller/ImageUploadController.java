package com.sbs.vc.datapro.auth.controller;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbs.vc.config.util.CommonUtils;
import com.sbs.vc.config.util.ResponseMessageDTO;
import com.sbs.vc.datapro.auth.security.CurrentUser;
import com.sbs.vc.datapro.auth.security.UserPrincipal;
import com.sbs.vc.datapro.auth.service.UserMaintenenceService;
import com.sbs.vc.datapro.exceptions.FileNotSelectedException;
import com.sbs.vc.datapro.exceptions.ImageNotAvailable;

@RestController
public class ImageUploadController {

	@Autowired
	UserMaintenenceService UserMaintenenceService;

	@PostMapping(value="/account/api/image/upload")
	public ResponseMessageDTO uploadFile(@RequestParam("image") MultipartFile uploadfile,@CurrentUser UserPrincipal currentUser)
			throws FileNotSelectedException {
		if (uploadfile.isEmpty()) {
			throw new FileNotSelectedException("Select Not Selected");
		}
		try {
			String userName=currentUser.getUsername();
			System.out.println();
			UserMaintenenceService.saveUploadedFiles(userName,Arrays.asList(uploadfile));
		} catch (IOException e) {
			return CommonUtils.getSuccessMessage(HttpStatus.BAD_REQUEST.value(), "Error on file", null);
		}
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Image uploaded successfully", null);
	}

	@GetMapping(value = "/account/api/image/load", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@CurrentUser UserPrincipal currentUser) throws ImageNotAvailable {
		 String userName=currentUser.getUsername();
		return UserMaintenenceService.getImage(userName).getPic();
	}
	
	@GetMapping(value = "/account/api/image/data")
	public ResponseMessageDTO getUserImage(@CurrentUser UserPrincipal currentUser) throws ImageNotAvailable {
		 String userName=currentUser.getUsername();
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Image Fetch successfully", UserMaintenenceService.getImage(userName));
	}

}
