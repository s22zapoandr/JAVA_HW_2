package lv.venta.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "Customer as company")
@Entity
public class CustomerAsCompany {
	
	@NotNull
	@Column(name = "IDC")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idC;
	
	@NotNull
	@Column(name = "Address")
	private Address address;
	
	@NotNull
	@Column(name = "Phone_number")
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	private String phoneNo;
	
	
	private ArrayList<Parcel> parcels = new ArrayList<Parcel>();
	
	@NotNull
	@Column(name = "Customer_code")
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	@Size(min = 4, max = 20)
	protected String customerCode;
	
	@NotNull
	@Column(name = "Title")
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	private String title;
	
	@NotNull
	@Column(name = "Company_regestration_number")
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	private String companyRegNo;
	
	
	//Linkage
	
	@OneToOne(mappedBy = "IDP")
	@ToString.Exclude
	private Driver driver;
	
	//Override

	public String setCustomerCode() {
		return "0_company_"+companyRegNo;
	}
	
	public CustomerAsCompany(Address address, String phoneNumber,String title, String companyRegNo) {
		setAddress(address);
		setPhoneNo(phoneNumber);
		setCompanyRegNo(companyRegNo);
		setCustomerCode();
		setParcels(getParcels());
		setTitle(title);
		setDriver(driver);
	}
}
