package com.gcrm.devopsreports.hobjects;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="item_info")
public class ItemInfo {

	@Id
	private String id;
	private String itemId;
	private String type;
	private int priority;
	private String title;
	private String state;
	private String developer;
	private float origEstimate;
	private float compWork;
	private float remWork;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate createDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate changeDate;
	private String iteration;
	private String project;
	//@JsonFormat(pattern="yyyy-MM-dd")
	//private LocalDate releaseDate;
}
