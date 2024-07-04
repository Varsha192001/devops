package com.gcrm.devopsreports.util;

import java.time.LocalDate;
import java.util.Date;

public class DateUtil {

	/**
	 * The data for date strings are returned in different formats while reading from excel. Logic is developed to read date 
	 * data and parse it as yyyy-MM-dd format. The date can be MM/dd/yyyy HH:mm:ss or dd/MM/yy HH:mm or anything
	 * @param excelDate
	 */
	public static LocalDate covertExcelStringToDate(String excelDate) {
		//System.out.println("Date value coming from the calling method: " + excelDate);
		boolean isDateSet = false;
		int intMonth = 0;
		int intDay = 0;
		int intYear = 0;
		String dateSubStr = excelDate.substring(0, 10);
		//System.out.println("Date from substring: " + dateSubStr);
		//This if condition handles date string that are of 1/1/23 HH:mm
		if(dateSubStr.charAt(6) == ' ') {
			String yearSubStr = dateSubStr.substring(4, 6);
			System.out.println("Year substring: " + yearSubStr);
			intDay = Integer.parseInt(dateSubStr.substring(0, 1));
			intMonth = Integer.parseInt(dateSubStr.substring(2, 3));
			if(yearSubStr.equals("24")) {
				intYear = 2024;
			}
			if(yearSubStr.equals("23")) {
				intYear = 2023;
			}
			isDateSet = true;
		}
		//This if condition handles dates string that are date length of 7 i.e either d/MM/yy or dd/M/yy
		if(dateSubStr.charAt(7) == ' ') {
			String yearSubStr = dateSubStr.substring(5, 7);
			System.out.println("Year substring: " + yearSubStr);
			
			
			if(dateSubStr.charAt(2) == '/') {
				intDay = Integer.parseInt(dateSubStr.substring(0, 2));
				if(dateSubStr.charAt(4) == '/') {
					intMonth = Integer.parseInt(dateSubStr.substring(3, 4));
				}else {
					intMonth = Integer.parseInt(dateSubStr.substring(3, 5));
				}
				
			}else {
				intDay = Integer.parseInt(dateSubStr.substring(0, 1));
				if(dateSubStr.charAt(3) == '/') {
					intMonth = Integer.parseInt(dateSubStr.substring(2, 3));
				}else {
					intMonth = Integer.parseInt(dateSubStr.substring(2, 4));
				}
			}
			
			if(yearSubStr.equals("24")) {
				intYear = 2024;
			}
			if(yearSubStr.equals("23")) {
				intYear = 2023;
			}
			isDateSet = true;
		}
		//This if condition handles date string that are of lenght 8
		if(dateSubStr.charAt(8) == ' ') {
			String yearSubStr8 = dateSubStr.substring(6, 8);
			intDay = Integer.parseInt(dateSubStr.substring(0, 2));
			intMonth = Integer.parseInt(dateSubStr.substring(3, 5));
			if(yearSubStr8.equals("24")) {
				intYear = 2024;
			}
			if(yearSubStr8.equals("23")) {
				intYear = 2023;
			}
			isDateSet=true;
		}
		//System.out.println("Final dateSubStr: " + dateSubStr);
		//if(dateSubStr.charAt(9) == ' ') {
		//This if condition handles date string that are of 9 or above lenght that come as MM/dd/yyyy 12/14/2024
		if(!isDateSet) {
			if(dateSubStr.charAt(9) == ' ') {
				intMonth = Integer.parseInt(dateSubStr.substring(0, 1));
				intDay = Integer.parseInt(dateSubStr.substring(2, 4));
				intYear = Integer.parseInt(dateSubStr.substring(5, 9));
			}else {
				intMonth = Integer.parseInt(dateSubStr.substring(0, 2));
				intDay = Integer.parseInt(dateSubStr.substring(3, 5));
				intYear = Integer.parseInt(dateSubStr.substring(6, 9));
			}
		}
		LocalDate localDate = LocalDate.of(intYear, intMonth, intDay);
		//System.out.println("Local Date after parsing: " + localDate);
		//String formatedDate = intYear+"-"+intMonth+"-"+intDay;
		
		return localDate;
	}
}
