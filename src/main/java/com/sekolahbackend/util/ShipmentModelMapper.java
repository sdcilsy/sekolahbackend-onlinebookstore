package com.sekolahbackend.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.sekolahbackend.entity.Shipment;
import com.sekolahbackend.model.ShipmentModel;
import com.sekolahbackend.model.UserModel;

public class ShipmentModelMapper {

	public static ShipmentModel constructModel(Shipment shipment) {
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(shipment.getUser(), userModel);
		
		ShipmentModel model = new ShipmentModel();
		model.setUserModel(userModel);
		model.setTransactionModel(TransactionModelMapper.constructModel(shipment.getTransaction()));
		
		BeanUtils.copyProperties(shipment, model);
		return model;
	}
	
	public static List<ShipmentModel> constructModel(List<Shipment> shipments) {
		List<ShipmentModel> models = new ArrayList<>();
		shipments.forEach(shipment -> {
			ShipmentModel model = constructModel(shipment);
			models.add(model);
		});
		return models;
	}
}
