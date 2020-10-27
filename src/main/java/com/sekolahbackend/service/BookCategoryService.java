package com.sekolahbackend.service;

import java.util.List;

import com.sekolahbackend.entity.BookCategory;
import com.sekolahbackend.model.BookCategoryModel;

public interface BookCategoryService extends PersistenceService<BookCategoryModel, Integer> {

	List<BookCategory> findByStatusNotActive();
}
