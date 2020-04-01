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

import com.sekolahbackend.model.ShipmentCreateRequestModel;
import com.sekolahbackend.model.ShipmentModel;
import com.sekolahbackend.model.ShipmentUpdateRequestModel;
import com.sekolahbackend.service.ShipmentService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/rest/shipment")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ShipmentController {

	@Autowired
	private ShipmentService shipmentService;

	@PostMapping("/create")
	public ShipmentModel create(@RequestBody @Valid ShipmentCreateRequestModel request, BindingResult result,
			HttpServletResponse response) throws IOException {
		ShipmentModel shipmentModel = new ShipmentModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return shipmentModel;
		} else 
			return shipmentService.create(request);
	}
	
	@PostMapping("/update")
	public ShipmentModel update(@RequestBody @Valid ShipmentUpdateRequestModel request, BindingResult result,
			HttpServletResponse response) throws IOException {
		ShipmentModel shipmentModel = new ShipmentModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return shipmentModel;
		} else 
			return shipmentService.update(request);
	}
	
	@GetMapping("/findAll")
	public List<ShipmentModel> findAll() {
		return shipmentService.findAll();
	}

	@GetMapping("/findById/{id}")
	public ShipmentModel findById(@PathVariable("id") final Integer id) {
		return shipmentService.findById(id);
	}
	
	@GetMapping("/findByUserId/{userId}")
	public List<ShipmentModel> findByUserId(@PathVariable("userId") final Integer userId) {
		return shipmentService.findByUserId(userId);
	}
}

