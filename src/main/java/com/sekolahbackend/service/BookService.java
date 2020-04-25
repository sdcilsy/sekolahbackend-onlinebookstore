package com.sekolahbackend.service;

import org.springframework.web.multipart.MultipartFile;

import com.sekolahbackend.model.BookModel;

public interface BookService extends PersistenceService<BookModel, Integer> {

	BookModel uploadImage(Integer id, MultipartFile file);
	
}
