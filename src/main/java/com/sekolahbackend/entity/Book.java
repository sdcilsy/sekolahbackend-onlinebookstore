package com.sekolahbackend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "book")
@Where(clause = "status = 'ACTIVE'")
public class Book extends Persistence {

	@Column(length = 255)
	private String title;
	
	@Column(length = 100)
	private String isbn;
	
	@Column(length = 255)
	private String authorName;
	
	@Column(columnDefinition = "text")
	private String synopsis;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date publicationDate;
	
	@Column(columnDefinition = "double precision default '0'")
	private Double price;
	
	@JoinColumn(name = "book_categori_id")
	@ManyToOne(targetEntity = BookCategory.class, fetch = FetchType.LAZY)
	private BookCategory bookCategory;
}
