package com.sekolahbackend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.sekolahbackend.model.FavouriteBookModel;
import com.sekolahbackend.model.FavouriteBookRequestModel;
import com.sekolahbackend.service.FavouriteBookService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/rest/favourite-book")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
public class FavouriteBookRestController {
	
	@Autowired
	private FavouriteBookService favouriteBookService;

	@PostMapping("/saveOrUpdate")
	public FavouriteBookModel saveOrUpdate(@RequestBody @Valid FavouriteBookRequestModel request, BindingResult result,
			HttpServletResponse response) throws IOException {
		FavouriteBookModel favouriteBookModel = new FavouriteBookModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return favouriteBookModel;
		} else 
			return favouriteBookService.saveOrUpdate(request);
	}

	@DeleteMapping("/deleteByFavouriteBookDetailId/{detailId}")
	public FavouriteBookModel delete(@PathVariable("detailId") final Integer detailId) {
		return favouriteBookService.deleteByFavouriteBookDetailId(detailId);
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
	public FavouriteBookModel findByUserId(@PathVariable("userId") final Integer userId) {
		return favouriteBookService.findByUserId(userId);
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@GetMapping("/findByUsername/{username}")
	public FavouriteBookModel findByUsername(@PathVariable("username") final String username) {
		return favouriteBookService.findByUsername(username);
	}
}
