package com.sekolahbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookCategoryRequestUpdateModel extends BookCategoryRequestCreateModel {

	private Integer id;
	
}
