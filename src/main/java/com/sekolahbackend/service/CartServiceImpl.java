package com.sekolahbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekolahbackend.model.CartModel;
import com.sekolahbackend.repository.CartDetailRepository;
import com.sekolahbackend.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartDetailRepository cartDetailRepository;
	
	@Override
	public CartModel saveOrUpdate(CartModel entity) {
		return null;
	}

	@Override
	public CartModel delete(CartModel entity) {
		return null;
	}

	@Override
	public CartModel deleteById(Integer id) {
		return null;
	}

	@Override
	public CartModel findById(Integer id) {
		return null;
	}

	@Override
	public List<CartModel> findAll() {
		return null;
	}

	@Override
	public Long countAll() {
		return null;
	}

}