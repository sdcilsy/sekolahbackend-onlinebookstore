package com.sekolahbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shipment")
@Where(clause = "status = 'ACTIVE'")
public class Shipment extends Persistence {
	private static final long serialVersionUID = -1289639546810599917L;

	public enum ShipmentStatus {
		ORDERED, PACKING, DELIVERY, RECEIVED
	}
	
	public enum Courier {
		JNE, JNT, POS, NINJAEXPRESS, TIKI, OTHER
	}
	
	@Column
	private String trackingNumber;
	
	@NotNull
	@JoinColumn(name = "user_id")
	@OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	private User user;
	
	@NotNull
	@Column(columnDefinition = "text")
	private String address;
	
	@NotNull
	@JoinColumn(name = "transaction_id")
	@ManyToOne(targetEntity = Transaction.class, fetch = FetchType.LAZY)
	private Transaction transaction;
	
	@NotNull
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private ShipmentStatus shipmentStatus;
	
	@NotNull
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private Courier courier;
}
