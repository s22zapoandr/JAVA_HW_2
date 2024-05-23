package model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	
	@NotNull
	@Column(name = "Date of creation")
	private LocalDateTime orderCreated;
	
	@NotNull
	@Column(name = "Date of planned delivery")
	private LocalDateTime plannedDelivery;
	
	@NotNull
	@Column(name = "Size")
	private Parcel_Size size;
	
	@NotNull
	@Column(name = "Is fragile?")
	private boolean isFragile;
	
	@Column(name = "Driver")
	@NotNull
	private Driver driver;
	
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
	
	public Parcel(LocalDateTime orderCreated, LocalDateTime plannedDelivery, Parcel_Size size, boolean isFragile, Driver driver) {
		setOrderCreated();
		setPlannedDelivery(plannedDelivery);
		setSize(size);
		setFragile(isFragile);
		setDriver(driver);
	}
	
	

}
