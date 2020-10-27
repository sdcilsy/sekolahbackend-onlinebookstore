package com.sekolahbackend.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sekolahbackend.entity.Persistence.Status;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistenceModel {

	@NotNull
	private Integer id;
	
	private String createdBy;
	private String updatedBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
	private Date createdTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
	private Date updatedTime;
	
	private Status status;
}
