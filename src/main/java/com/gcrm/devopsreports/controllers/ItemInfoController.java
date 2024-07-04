package com.gcrm.devopsreports.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gcrm.devopsreports.services.ItemInfoService;

@RestController
@RequestMapping("item")
public class ItemInfoController {

	@Autowired
	ItemInfoService itemInfoSvc;
	
	@PostMapping("upload")
	public ResponseEntity<Boolean> uploadSprintData(@RequestParam("file") MultipartFile file, 
												@RequestParam("iteration") String iteration, 
												@RequestParam("project") String project) throws IOException{
		
			System.out.println("Inside uploadSprintData method...");
			return new ResponseEntity<Boolean>(itemInfoSvc.createDataFromExcel(file,iteration, project),HttpStatus.OK);
	
	}
}
