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
@Document(collection="releases")
public class ReleaseItem {
	@Id
	private String id;
	private String itemId;
	private String type;
	private int priority;
	private String title;
	private String developer;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate createDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate releaseDate;
	private String iteration;
	private String project;
	private String client;
	private String releaseNo;
}
