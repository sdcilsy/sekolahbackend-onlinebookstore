package com.sekolahbackend.model;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionCreateRequestModel {

	@NotNull
	private Integer userId;
	
	@NotNull
	private Set<Integer> cartDetailIds;
}
