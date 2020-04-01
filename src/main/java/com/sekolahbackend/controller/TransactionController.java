package com.sekolahbackend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sekolahbackend.model.TransactionCreateRequestModel;
import com.sekolahbackend.model.TransactionModel;
import com.sekolahbackend.model.TransactionUpdateRequestModel;
import com.sekolahbackend.service.TransactionService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/rest/transaction")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/checkout")
	public TransactionModel checkout(@RequestBody @Valid TransactionCreateRequestModel request, BindingResult result,
			HttpServletResponse response) throws IOException {
		TransactionModel transactionModel = new TransactionModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return transactionModel;
		} else 
			return transactionService.save(request);
	}
	
	// FIXME: separate into confirm payment and settlement
	@PostMapping("/payment")
	public TransactionModel payment(@RequestBody @Valid TransactionUpdateRequestModel request, BindingResult result,
			HttpServletResponse response) throws IOException {
		TransactionModel transactionModel = new TransactionModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return transactionModel;
		} else 
			return transactionService.update(request);
	}
	
	@GetMapping("/findAll")
	public List<TransactionModel> findAll() {
		return transactionService.findAll();
	}

	@GetMapping("/findById/{id}")
	public TransactionModel findById(@PathVariable("id") final Integer id) {
		return transactionService.findById(id);
	}
	
	@GetMapping("/findByUserId/{userId}")
	public List<TransactionModel> findByUserId(@PathVariable("userId") final Integer userId) {
		return transactionService.findByUserId(userId);
	}
}
