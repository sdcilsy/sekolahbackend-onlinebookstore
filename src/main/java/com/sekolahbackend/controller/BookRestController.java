package com.sekolahbackend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sekolahbackend.model.BookModel;
import com.sekolahbackend.model.BookRequestCreateModel;
import com.sekolahbackend.model.BookRequestUpdateModel;
import com.sekolahbackend.service.BookService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/rest/book")
public class BookRestController {
	
	@Autowired
	private BookService bookService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/save")
	public BookModel save(@RequestBody @Valid BookRequestCreateModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		BookModel bookModel = new BookModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return bookModel;
		} else {
			BeanUtils.copyProperties(request, bookModel);
			return bookService.saveOrUpdate(bookModel);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/update")
	public BookModel update(@RequestBody @Valid BookRequestUpdateModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		BookModel bookModel = new BookModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return bookModel;
		} else {
			BeanUtils.copyProperties(request, bookModel);
			return bookService.saveOrUpdate(bookModel);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/deleteById/{id}")
	public BookModel delete(@PathVariable("id") final Integer id) {
		return bookService.deleteById(id);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
	@GetMapping("/findAll")
	public List<BookModel> findAll() {
		return bookService.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
	@GetMapping("/findById/{id}")
	public BookModel findById(@PathVariable("id") final Integer id) {
		return bookService.findById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/uploadImage/{id}")
	public BookModel uploadImage(@PathVariable("id") final Integer id, @RequestParam("file") MultipartFile file) {
		return bookService.uploadImage(id, file);
	}
}
