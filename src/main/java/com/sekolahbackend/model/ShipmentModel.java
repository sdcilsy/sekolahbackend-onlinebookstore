package com.sekolahbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sekolahbackend.entity.Shipment.Courier;
import com.sekolahbackend.entity.Shipment.ShipmentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentModel extends PersistenceModel {

	private String trackingNumber;
	private String address;
	
	private UserModel userModel;
	private TransactionModel transactionModel;
	
	private ShipmentStatus shipmentStatus;
	private Courier courier;
}
