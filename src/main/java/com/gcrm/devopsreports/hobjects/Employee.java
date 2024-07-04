package com.gcrm.devopsreports.hobjects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="releases")
public class Employee {

	@Id
	private String id;
	private String empId;
	private String name;
	
}
