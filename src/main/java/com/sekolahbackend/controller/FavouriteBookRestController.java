package com.sekolahbackend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sekolahbackend.model.FavouriteBookModel;
import com.sekolahbackend.model.FavouriteBookRequestCreateModel;
import com.sekolahbackend.model.FavouriteBookRequestUpdateModel;
import com.sekolahbackend.service.FavouriteBookService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/rest/favourite-book")
public class FavouriteBookRestController {
	
	@Autowired
	private FavouriteBookService favouriteBookService;

	@PostMapping("/save")
	public FavouriteBookModel save(@RequestBody @Valid FavouriteBookRequestCreateModel request, BindingResult result,
			HttpServletResponse response) throws IOException {
		FavouriteBookModel bookModel = new FavouriteBookModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return bookModel;
		} else {
			BeanUtils.copyProperties(request, bookModel);
			return favouriteBookService.saveOrUpdate(bookModel);
		}
	}

	@PostMapping("/update")
	public FavouriteBookModel update(@RequestBody @Valid FavouriteBookRequestUpdateModel request, BindingResult result,
			HttpServletResponse response) throws IOException {
		FavouriteBookModel bookModel = new FavouriteBookModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return bookModel;
		} else {
			BeanUtils.copyProperties(request, bookModel);
			return favouriteBookService.saveOrUpdate(bookModel);
		}
	}

	@DeleteMapping("/deleteById/{id}")
	public FavouriteBookModel delete(@PathVariable("id") final Integer id) {
		return favouriteBookService.deleteById(id);
	}

	@GetMapping("/findAll")
	public List<FavouriteBookModel> findAll() {
		return favouriteBookService.findAll();
	}

	@GetMapping("/findById/{id}")
	public FavouriteBookModel findById(@PathVariable("id") final Integer id) {
		return favouriteBookService.findById(id);
	}
	
	@GetMapping("/findByUserId/{userId}")
	public List<FavouriteBookModel> findByUserId(@PathVariable("userId") final Integer userId) {
		return favouriteBookService.findByUserId(userId);
	}
}
