package com.sekolahbackend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favourite_book_detail")
@Where(clause = "status = 'ACTIVE'")
public class FavouriteBookDetail extends Persistence {

	@JoinColumn(name = "book_id")
	@ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
	private Book book;
	
	@JoinColumn(name = "favourite_book_id")
	@ManyToOne(targetEntity = FavouriteBook.class, fetch = FetchType.LAZY)
	private FavouriteBook favouriteBook;
}
