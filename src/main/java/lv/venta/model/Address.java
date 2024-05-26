package lv.venta.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Address")
@Entity

public class Address {
	
	@NotNull
	@Column(name = "IDA")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idA;
	
	@NotNull
	@Column(name = "City")
	private City city;
	
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z]+")
	@Size(min = 2, max = 20)
	@Column(name = "Street/house title")
	private String streetOrHouseTitle;
	
	@Column(name = "Experience in years")
	@NotNull
	@Min(1)
	@Max(200)
	private int houseNo; 
	
	public Address(City city, String streetOrHouseTitle, int houseNo) {
		setCity(city);
		setStreetOrHouseTitle(streetOrHouseTitle);
		setHouseNo(houseNo);
	}
}
