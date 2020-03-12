package com.sekolahbackend.service;

import java.util.List;

import com.sekolahbackend.model.FavouriteBookModel;

public interface FavouriteBookService extends PersistenceService<FavouriteBookModel, Integer> {

	List<FavouriteBookModel> findByUserId(Integer userId);
	
}
