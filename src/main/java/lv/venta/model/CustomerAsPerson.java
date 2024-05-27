package lv.venta.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "Customer_as_person")
@Entity



public class CustomerAsPerson {
	
	@NotNull
	@Column(name = "IDC2")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idC;
	
	
	@NotNull
	@Column(name = "Phone_number")
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	private String phoneNo;
	

	
	@NotNull
	@Column(name = "Customer_code")
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	@Size(min = 4, max = 20)
	protected String customerCode;
	

	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z]+")
	@Size(min = 2, max = 20)
	@Column(name = "Person_Code")
	private String personCode;
	
	
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z]+")
	@Size(min = 2, max = 10)
	@Column(name = "Name")
	private String name;
	

	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z]+")
	@Size(min = 2, max = 20)
	@Column(name = "Surname")
	private String surname;
	 
	//Linkage 
	
	@ManyToOne
	@NotNull
	@Column(name = "Address")
	private Address address;
	
	
	@OneToMany
	private ArrayList<Parcel> parcels = new ArrayList<Parcel>();
	
	
	
	//Override

	public String setCustomerCode() {
		return "0_person_"+personCode;
	}
	

	
	public CustomerAsPerson(String name, String surnname, String personCode, Address address, String phoneNo) {
		setName(name);
		setSurname(surname);
		setPersonCode(personCode);
		setCustomerCode();
		setPhoneNo(phoneNo);
		setAddress(address);
		
		
	}

}
