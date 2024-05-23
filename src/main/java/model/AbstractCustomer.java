package model;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Abstract Customer")
@Entity
public class AbstractCustomer {
	@NotNull
	@Column(name = "CID")
	@Min(1)
	@Max(200)
	private long cID;
	
	@NotNull
	@Column(name = "Address")
	private Address address;
	
	@NotNull
	@Column(name = "Phone number")
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	private String phoneNo;
	
	
	private ArrayList<Parcel> parcels = new ArrayList<Parcel>();
	
	@NotNull
	@Column(name = "Customer code")
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	@Size(min = 4, max = 20)
	protected String customerCode;
	private long counter = 0; 
	
	public AbstractCustomer(Address address, String phoneNo) {
		setAddress(address);
		setPhoneNo(phoneNo);
		setCID(cID);
		setParcels(parcels);
	}
	

}
