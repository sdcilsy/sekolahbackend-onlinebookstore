package com.sekolahbackend.service;

import static com.sekolahbackend.util.ShipmentModelMapper.constructModel;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.sekolahbackend.entity.Persistence.Status;
import com.sekolahbackend.entity.Shipment;
import com.sekolahbackend.entity.Shipment.ShipmentStatus;
import com.sekolahbackend.entity.Transaction;
import com.sekolahbackend.entity.Transaction.TransactionStatus;
import com.sekolahbackend.entity.User;
import com.sekolahbackend.model.ShipmentCreateRequestModel;
import com.sekolahbackend.model.ShipmentModel;
import com.sekolahbackend.model.ShipmentUpdateRequestModel;
import com.sekolahbackend.repository.ShipmentRepository;
import com.sekolahbackend.repository.TransactionRepository;
import com.sekolahbackend.repository.UserRepository;

@Service
public class ShipmentServiceImpl implements ShipmentService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;

	@Override
	public ShipmentModel saveOrUpdate(ShipmentModel entity) {
		return null;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShipmentModel create(ShipmentCreateRequestModel request) {
		// validate user
		User user = userRepository.findById(request.getUserId()).orElse(null);
		if (user == null)
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User with id: " + request.getUserId() + " not found");
		
		// validate transaction
		Transaction transaction = transactionRepository.findById(request.getTransactionId()).orElse(null);
		if (transaction == null)
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Transaction with id: " + request.getUserId() + " not found");
		if (!transaction.getTransactionStatus().equals(TransactionStatus.SETTLED))
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Transaction with id: " + request.getUserId() + " is not SETTLED");
		
		Shipment shipment = new Shipment();
		shipment.setAddress(user.getAddress());
		shipment.setCourier(request.getCourier());
		shipment.setShipmentStatus(ShipmentStatus.ORDERED);
		shipment.setStatus(Status.ACTIVE);
		shipment.setTransaction(transaction);
		shipment.setUser(user);
		
		shipment = shipmentRepository.save(shipment);
		return constructModel(shipment);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShipmentModel update(ShipmentUpdateRequestModel request) {
		Shipment shipment = shipmentRepository.findById(request.getShipmentId()).orElse(null);
		if (shipment == null)
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Shipment with id: " + request.getShipmentId() + " not found");		
		
		if (request.getShipmentStatus().equals(ShipmentStatus.ORDERED)) 
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Shipment with id: " + request.getShipmentId() + " is already ORDERED");
		
		if (StringUtils.isNotBlank(request.getTrackingNumber()))
			shipment.setTrackingNumber(request.getTrackingNumber());
		shipment.setShipmentStatus(request.getShipmentStatus());
		
		shipment = shipmentRepository.save(shipment);
		return constructModel(shipment);
	}

	@Override
	public ShipmentModel delete(ShipmentModel entity) {
		return null;
	}

	@Override
	public ShipmentModel deleteById(Integer id) {
		return null;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ShipmentModel findById(Integer id) {
		return constructModel(shipmentRepository.findById(id).orElse(null));
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<ShipmentModel> findAll() {
		return constructModel(shipmentRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return shipmentRepository.count();
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<ShipmentModel> findByUserId(Integer userId) {
		return constructModel(shipmentRepository.findByUserId(userId));
	}

}
