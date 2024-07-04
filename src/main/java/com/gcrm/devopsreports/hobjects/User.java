package com.gcrm.devopsreports.hobjects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "User")
@Data
public class User {

	@Id
	private String userId;
	private String userName;

	private String password;

}
