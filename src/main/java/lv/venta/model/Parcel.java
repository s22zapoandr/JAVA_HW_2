package lv.venta.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Parcel")
@Entity
public class Parcel {
	
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idpa")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPa;
	
	
	@NotNull
	@Column(name = "Date_of_creation")
	private LocalDateTime orderCreated;
	
	@NotNull
	@Column(name = "Date_of_planned_delivery")
	private LocalDateTime plannedDelivery;
	
	@NotNull
	@Column(name = "Size")
	private ParcelSize size;
	
	@NotNull
	@Column(name = "Is_fragile")
	private boolean isFragile;
	
	@NotNull
	@Column(name = "Price")
	@Min(1)
	@Max(1000000)
	private float price;
	
	//Linkage
	
	@ManyToOne
	@JoinColumn(name = "IDC1") 
	private CustomerAsCompany customerAsCompany;
	
	@ManyToOne
	@JoinColumn(name = "IDC2")
	private CustomerAsPerson customerAsPerson;
	
	@ManyToOne
	@JoinColumn(name = "IDP")
	private Driver driver;

	//Override
	
	public void setOrderCreated() {
		this.orderCreated = LocalDateTime.now();
	}
	
	public void setPlannedDelivery(LocalDateTime plannedDelivery) {
		if(plannedDelivery.isAfter(orderCreated)) {
			this.plannedDelivery = plannedDelivery;
		} else {
			this.plannedDelivery = LocalDateTime.of(2025, 04, 24, 14, 33, 48, 123456789);
		}
	}
	
	public void setPrice() {
		float counter = 0;
		switch (getSize()) {
        case X:
            counter = 1.99f;
            break;
        case S:
        	counter = 1.99f*2;
            break;
        case M:
        	counter = 1.99f*3;
            break;
        case L:
        	counter = 1.99f*4;
            break;
        case XL:
        	counter = 1.99f*5;
            break;
        default:
        	counter = 0;
            break;
    }
		if(isFragile ) {
			this.price = counter + 2.99f;
		}
		else {
			this.price = counter;
		}
	}
	
	public Parcel(LocalDateTime orderCreated, LocalDateTime plannedDelivery, ParcelSize size, boolean isFragile, Driver driver, CustomerAsCompany customerAsCompany) {
		setOrderCreated();
		setPlannedDelivery(plannedDelivery);
		setSize(size);
		setFragile(isFragile);
		setDriver(driver);
		setPrice();
		setCustomerAsCompany(customerAsCompany);
	}
	public Parcel(LocalDateTime orderCreated, LocalDateTime plannedDelivery, ParcelSize size, boolean isFragile, Driver driver, CustomerAsPerson customerAsPerson) {
		setOrderCreated();
		setPlannedDelivery(plannedDelivery);
		setSize(size);
		setFragile(isFragile);
		setDriver(driver);
		setPrice();
		setCustomerAsPerson(customerAsPerson);
	}
	
	

}
