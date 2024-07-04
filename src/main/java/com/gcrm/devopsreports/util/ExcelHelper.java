package com.gcrm.devopsreports.util;

import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERS = { "Id", "Work Item Type", "Priority","Title","Assigned To","State","Developer","Original Estimate","Completed Work","Remaining Work","Created Date","Changed Date","Iteration Path"  };
	static String SHEET = "Tutorials";
	
	 public static boolean hasExcelFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	}
}
