package com.sekolahbackend.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavouriteBookRequestCreateModel {

	@NotNull
	private Integer userId;
	
	@NotNull
	private List<Integer> bookIds;
	
}
