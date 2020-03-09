package com.sekolahbackend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookCategoryModel {
	
	private Integer id;

	private String name;
	private String code;
	private String createdBy;
	private String updatedBy;

	private Date createdTime;
	private Date updatedTime;
	
}
