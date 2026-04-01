package com.businessmodel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

	@Id
	@Column(name = "orderNumber")
	private Integer orderNumber;

	@Column(name = "orderDate")
	private LocalDate orderDate;

	@Column(name = "requiredDate")
	private LocalDate requiredDate;

	@Column(name = "shippedDate")
	private LocalDate shippedDate;

	@Column(name = "status")
	private String status;

	@Column(name = "comments")
	private String comments;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerNumber", referencedColumnName = "customerNumber")
	private Customer customer;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails;

}