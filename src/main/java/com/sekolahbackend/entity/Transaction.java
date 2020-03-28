package com.sekolahbackend.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transaction")
@Where(clause = "status = 'ACTIVE'")
public class Transaction extends Persistence {
	private static final long serialVersionUID = 9220882551539786922L;

	public enum TransactionStatus {
		PENDING, SETTLED, EXPIRED
	}
	
	public enum PaymentMethod {
		BANK_TRANSFER, CASH_ON_DELIVERY, VIRTUAL_ACCOUNT
	}
	
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(unique = true)
	private String invoiceNumber;
	
	@Column(length = 255)
	private String receiptImageUrl;
	
	@JoinColumn(name = "user_id")
	@OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	private User user;
	
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private TransactionStatus transactionStatus;
	
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Where(clause = "status = 'ACTIVE'")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction", fetch = FetchType.LAZY)
	private Set<TransactionDetail> transactionDetails;
}
