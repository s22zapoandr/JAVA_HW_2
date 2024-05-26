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
	private long idpa;
	
	
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
	
	//Linkage
	
	@ManyToOne
	@JoinColumn(name = "IdC1") 
	private CustomerAsPerson customerAsPerson;
	
	@ManyToOne
	@JoinColumn(name = "IdC2")
	private CustomerAsCompany customerAsCompany;
	
	@ManyToOne
	@JoinColumn(name = "IdP")
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
	
	public Parcel(LocalDateTime orderCreated, LocalDateTime plannedDelivery, ParcelSize size, boolean isFragile, Driver driver) {
		setOrderCreated();
		setPlannedDelivery(plannedDelivery);
		setSize(size);
		setFragile(isFragile);
		setDriver(driver);
	}
	
	

}
