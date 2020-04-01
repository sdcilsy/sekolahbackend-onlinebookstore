package com.sekolahbackend.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sekolahbackend.entity.Shipment.ShipmentStatus;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentUpdateRequestModel {

	@NotNull
	private Integer shipmentId;
	
	@NotNull
	private ShipmentStatus shipmentStatus;
	
	private String trackingNumber;
}
