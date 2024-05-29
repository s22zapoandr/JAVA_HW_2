package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "Customer as company")
@Entity
public class CustomerAsCompany {
	
	@NotNull
	@Column(name = "IDC1")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idC;

	
	@NotNull
	@Column(name = "Phone_number")
	@Size(min = 4, max = 20)
	//@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	private String phoneNo;
	
	
	//@NotNull
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
	//@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	private String companyRegNo;
	
	
	//Linkage
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "IDA")
	private Address address;
	
	@OneToMany(mappedBy = "customerAsCompany")
	private Collection<Parcel> parcels = new ArrayList<Parcel>();
	
	
	
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
	}
}
