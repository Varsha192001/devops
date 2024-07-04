package com.gcrm.devopsreports.services;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gcrm.devopsreports.hobjects.ItemInfo;
import com.gcrm.devopsreports.repositories.ItemInfoRepository;
import com.gcrm.devopsreports.util.DateUtil;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
public class ItemInfoService {

	@Autowired
	private final ItemInfoRepository itemInfoRepo;
	
	/**
	 * Method to create Devops item records in the system. Using these items daily work status, sprint reports are generated
	 * @param mpf
	 * @param iteration
	 * @param project
	 * @return
	 * @throws IOException
	 */
	public boolean createDataFromExcel(MultipartFile mpf, String iteration, String project) throws IOException{
		boolean isDataUploaded = false;
		InputStream is = mpf.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet sheet = workbook.getSheetAt(0);
		List<ItemInfo> items = itemInfoRepo.findByIterationAndProject(iteration, project);
		DataFormatter dataFormatter = new DataFormatter();
		
		for(int rowIdx=1; rowIdx < sheet.getPhysicalNumberOfRows(); rowIdx++) {
			System.out.println("*********************************");
			System.out.println("rowIdx: " + rowIdx);
			ItemInfo item = new ItemInfo();
			XSSFRow row = sheet.getRow(rowIdx);
			
			String itemId = dataFormatter.formatCellValue(row.getCell(0));
			System.out.println("itemId: " + itemId);
			item.setItemId(itemId);
			item.setType(dataFormatter.formatCellValue(row.getCell(1)));
			item.setPriority(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(2))));
			item.setTitle(dataFormatter.formatCellValue(row.getCell(3)));
			item.setState(dataFormatter.formatCellValue(row.getCell(4)));
			item.setDeveloper(dataFormatter.formatCellValue(row.getCell(5)));
			item.setOrigEstimate(Float.parseFloat(dataFormatter.formatCellValue(row.getCell(6))));
			item.setCompWork(Float.parseFloat(dataFormatter.formatCellValue(row.getCell(7))));
			item.setRemWork(Float.parseFloat(dataFormatter.formatCellValue(row.getCell(8))));
			String createDateString = dataFormatter.formatCellValue(row.getCell(9));
			
			//System.out.println("String value of Create Date: " + createDateString);
			//DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate convertedDate = DateUtil.covertExcelStringToDate(createDateString);
			item.setCreateDate(convertedDate);
			//System.out.println("Create Date: " + createDate);
			String changeDateString = dataFormatter.formatCellValue(row.getCell(10));
			//System.out.println("String value of Change Date: " + changeDateString);
			
			LocalDate changeDate = DateUtil.covertExcelStringToDate(changeDateString);;
			item.setChangeDate(changeDate);
			//String iterationid = dataFormatter.formatCellValue(row.getCell(10));
			item.setIteration(iteration);
			item.setProject(project);
			ItemInfo dbItem = itemInfoRepo.findByItemIdAndIteration(itemId, iteration);
		
			if(dbItem == null) {
				itemInfoRepo.save(item);
			}else {
				item.setId(dbItem.getId());
				itemInfoRepo.save(item);
			}
			
		}
		
		
		return isDataUploaded;
	}
}
