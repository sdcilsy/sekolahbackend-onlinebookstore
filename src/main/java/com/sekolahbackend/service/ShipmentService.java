package com.sekolahbackend.service;

import java.util.List;

import com.sekolahbackend.model.ShipmentCreateRequestModel;
import com.sekolahbackend.model.ShipmentModel;
import com.sekolahbackend.model.ShipmentUpdateRequestModel;

public interface ShipmentService extends PersistenceService<ShipmentModel, Integer> {

	ShipmentModel create(ShipmentCreateRequestModel request);
	
	ShipmentModel update(ShipmentUpdateRequestModel request);
	
	List<ShipmentModel> findByUserId(Integer userId);
}
