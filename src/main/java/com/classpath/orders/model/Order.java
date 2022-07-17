package com.classpath.orders.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import com.classpath.orders.validator.Password;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor

//JPA Annotations
@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty(message="customer name cannot be blank")
	private String customerName;
	
	@NotBlank(message = "customer email cannot be blank")
	@Email(message = "customer email is not in correct format")
	private String customerEmail;
	
	private String password;
	
	@Min(value = 1000, message = "min order price should be 1000")
	@Max(value=20000, message = "max order price cannot be more than 20000")
	private double orderPrice;
	
	@PastOrPresent(message="order date cannot be in future")
	private LocalDate orderDate;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<LineItem> lineItems;
	
	public void addLineItem(LineItem lineItem) {
		if (Objects.isNull(lineItems)) {
			this.lineItems = new HashSet<>();
		}
		this.lineItems.add(lineItem);
		lineItem.setOrder(this);
	}
}
