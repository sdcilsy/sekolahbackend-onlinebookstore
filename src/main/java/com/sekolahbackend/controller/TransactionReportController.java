package com.sekolahbackend.controller;

import static com.sekolahbackend.util.EndpointConstant.PAGE;
import static com.sekolahbackend.util.EndpointConstant.PER_PAGE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sekolahbackend.model.TransactionModel;
import com.sekolahbackend.service.TransactionService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/rest/report/transaction")
public class TransactionReportController {

	@Autowired
	private TransactionService transactionService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/load")
	public Page<TransactionModel> load(
			@RequestParam(value = PAGE, required = false) Integer page,
			@RequestParam(value = PER_PAGE, required = false) Integer perPage,
			@RequestParam(value = "fullName", required = false) String fullName,
			@RequestParam(value = "invoiceNumber", required = false) String invoiceNumber) {
		return transactionService.findByUserOrInvoice(fullName, invoiceNumber, page, perPage);
	}
}
