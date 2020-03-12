package com.sekolahbackend.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavouriteBookRequestUpdateModel {

	@NotNull
	private Integer id;

	@NotNull
	private List<DetailModelUpdate> details;

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class DetailModelUpdate {

		@NotNull
		private Integer detailId;
		
		@NotNull
		private Integer bookId;
		
	}
}
