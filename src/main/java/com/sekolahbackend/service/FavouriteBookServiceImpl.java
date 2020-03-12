package com.sekolahbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.sekolahbackend.entity.Book;
import com.sekolahbackend.entity.FavouriteBook;
import com.sekolahbackend.entity.FavouriteBookDetail;
import com.sekolahbackend.entity.Persistence.Status;
import com.sekolahbackend.entity.User;
import com.sekolahbackend.model.BookModel;
import com.sekolahbackend.model.FavouriteBookModel;
import com.sekolahbackend.model.FavouriteBookModel.DetailModel;
import com.sekolahbackend.model.UserModel;
import com.sekolahbackend.repository.BookRepository;
import com.sekolahbackend.repository.FavouriteBookDetailRepository;
import com.sekolahbackend.repository.FavouriteBookRepository;
import com.sekolahbackend.repository.UserRepository;

@Service
public class FavouriteBookServiceImpl implements FavouriteBookService {

	@Autowired
	private FavouriteBookRepository favouriteBookRepository;
	
	@Autowired
	private FavouriteBookDetailRepository favouriteBookDetailRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FavouriteBookModel saveOrUpdate(FavouriteBookModel entity) {
		FavouriteBook favouriteBook;
		User user;
		
		List<FavouriteBookModel.DetailModel> favouriteBookDetails = new ArrayList<>();
		if (entity.getId() != null) {
			favouriteBook = favouriteBookRepository.findById(entity.getId()).orElse(null);
			if (favouriteBook == null) 
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Favourite with id: " + entity.getId() + " not found");
			user = favouriteBook.getUser();
			
			// favorite book details
			for (DetailModel detail : entity.getDetails()) {
				FavouriteBookDetail favouriteBookDetail = favouriteBookDetailRepository.findById(detail.getId()).orElse(null);
				if (favouriteBookDetail == null)
					throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Favourite Detail with id: " + detail.getId() + " not found");
				Book book;
				if (!favouriteBookDetail.getBook().getId().equals(detail.getBookModel().getId())) {
					book = bookRepository.findById(detail.getBookModel().getId()).orElse(null);
					favouriteBookDetail.setBook(book);
					favouriteBookDetail = favouriteBookDetailRepository.save(favouriteBookDetail);
				}
				// construct details
				FavouriteBookModel.DetailModel favouriteBookModelDetail = new FavouriteBookModel.DetailModel();
				BookModel bookModel = new BookModel();
				book = favouriteBookDetail.getBook();
				BeanUtils.copyProperties(book, bookModel);
				favouriteBookModelDetail.setBookModel(bookModel);
				favouriteBookDetails.add(favouriteBookModelDetail);
			}
		} else {
			user = userRepository.findById(entity.getUserModel().getId()).orElse(null);
			if (user == null) 
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User with id: " + entity.getUserModel().getId() + " not found");
			
			favouriteBook = new FavouriteBook();
			favouriteBook.setUser(user);
			favouriteBook = favouriteBookRepository.save(favouriteBook);
			
			// favorite book details
			for (DetailModel detail : entity.getDetails()) {
				Book book = bookRepository.findById(detail.getBookModel().getId()).orElse(null);
				if (book == null)
					throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book with id: " + detail.getBookModel().getId() + " not found");
				FavouriteBookDetail favouriteBookDetail = new FavouriteBookDetail();
				favouriteBookDetail.setBook(book);
				favouriteBookDetail.setFavouriteBook(favouriteBook);
				favouriteBookDetail = favouriteBookDetailRepository.save(favouriteBookDetail);
				
				// construct details
				FavouriteBookModel.DetailModel favouriteBookModelDetail = new FavouriteBookModel.DetailModel();
				BookModel bookModel = new BookModel();
				BeanUtils.copyProperties(book, bookModel);
				
				favouriteBookModelDetail.setBookModel(bookModel);
				BeanUtils.copyProperties(favouriteBookDetail, favouriteBookModelDetail);
				favouriteBookDetails.add(favouriteBookModelDetail);
			}
		}
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(favouriteBook, entity);
		BeanUtils.copyProperties(user, userModel);
		entity.setUserModel(userModel);
		entity.setDetails(favouriteBookDetails);
		return entity;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FavouriteBookModel delete(FavouriteBookModel entity) {
		if (entity.getId() != null) {
			FavouriteBook favouriteBook = favouriteBookRepository.findById(entity.getId()).orElse(null);
			if (favouriteBook == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "FavouriteBook with id: " + entity.getId() + " not found");
			favouriteBook.setStatus(Status.NOT_ACTIVE);
			favouriteBook = favouriteBookRepository.save(favouriteBook);
			BeanUtils.copyProperties(favouriteBook, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FavouriteBookModel deleteById(Integer id) {
		if (id != null) {
			FavouriteBookModel entity = new FavouriteBookModel();
			FavouriteBook favouriteBook = favouriteBookRepository.findById(id).orElse(null);
			if (favouriteBook == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "FavouriteBook with id: " + entity.getId() + " not found");
			favouriteBook.setStatus(Status.NOT_ACTIVE);
			favouriteBook = favouriteBookRepository.save(favouriteBook);
			BeanUtils.copyProperties(favouriteBook, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public FavouriteBookModel findById(Integer id) {
		if (id != null) {
			FavouriteBookModel entity = new FavouriteBookModel();
			FavouriteBook favouriteBook = favouriteBookRepository.findById(id).orElse(null);
			if (favouriteBook == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "FavouriteBook with id: " + entity.getId() + " not found");
			
			FavouriteBookModel favouriteBookModel = new FavouriteBookModel();
			UserModel userModel = new UserModel();
			List<FavouriteBookModel.DetailModel> favouriteBookDetails = new ArrayList<>();
			
			favouriteBook.getFavouriteBookDetails().forEach(detail -> {
				FavouriteBookModel.DetailModel favouriteBookModelDetail = new FavouriteBookModel.DetailModel();
				BeanUtils.copyProperties(detail, favouriteBookModelDetail);
				favouriteBookDetails.add(favouriteBookModelDetail);
			});
			BeanUtils.copyProperties(favouriteBook.getUser(), userModel);
			favouriteBookModel.setDetails(favouriteBookDetails);
			favouriteBookModel.setUserModel(userModel);
			return favouriteBookModel;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<FavouriteBookModel> findAll() {
		List<FavouriteBookModel> entities = new ArrayList<>();
		favouriteBookRepository.findAll().forEach(data -> {
			FavouriteBookModel entity = new FavouriteBookModel();
			UserModel userModel = new UserModel();
			List<FavouriteBookModel.DetailModel> favouriteBookDetails = new ArrayList<>();
			
			data.getFavouriteBookDetails().forEach(detail -> {
				FavouriteBookModel.DetailModel favouriteBookModelDetail = new FavouriteBookModel.DetailModel();
				BeanUtils.copyProperties(detail, favouriteBookModelDetail);
				favouriteBookDetails.add(favouriteBookModelDetail);
			});
			BeanUtils.copyProperties(data.getUser(), userModel);
			entity.setDetails(favouriteBookDetails);
			entity.setUserModel(userModel);
			
			entities.add(entity);
		});
		return entities;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return favouriteBookRepository.count();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<FavouriteBookModel> findByUserId(Integer userId) {
		List<FavouriteBookModel> entities = new ArrayList<>();
		favouriteBookRepository.findByUserId(userId).forEach(data -> {
			FavouriteBookModel entity = new FavouriteBookModel();
			UserModel userModel = new UserModel();
			List<FavouriteBookModel.DetailModel> favouriteBookDetails = new ArrayList<>();
			
			data.getFavouriteBookDetails().forEach(detail -> {
				FavouriteBookModel.DetailModel favouriteBookModelDetail = new FavouriteBookModel.DetailModel();
				BeanUtils.copyProperties(detail, favouriteBookModelDetail);
				favouriteBookDetails.add(favouriteBookModelDetail);
			});
			BeanUtils.copyProperties(data.getUser(), userModel);
			entity.setDetails(favouriteBookDetails);
			entity.setUserModel(userModel);
			
			entities.add(entity);
		});
		return entities;
	}
	
}