package com.sekolahbackend.service;

import java.util.List;

import com.sekolahbackend.model.TransactionCreateRequestModel;
import com.sekolahbackend.model.TransactionModel;
import com.sekolahbackend.model.TransactionUpdateRequestModel;

public interface TransactionService extends PersistenceService<TransactionModel, Integer> {

	TransactionModel save(TransactionCreateRequestModel request);
	
	TransactionModel update(TransactionUpdateRequestModel request);

	List<TransactionModel> findByUserId(Integer userId);
}
