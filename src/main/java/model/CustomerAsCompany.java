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
@Table(name = "Customer as company")
@Entity
public class CustomerAsCompany extends AbstractCustomer {
	@NotNull
	@Column(name = "Title")
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	private String title;
	
	@NotNull
	@Column(name = "Company regestration company")
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	private String companyRegNo;
	
	public CustomerAsCompany(Address address, String phoneNumber,String title, String companyRegNo) {
		setAddress(address);
		setPhoneNo(phoneNumber);
		setCID(getCID());
		setCustomerCode(getCustomerCode());
		setParcels(getParcels());
		setCompanyRegNo(companyRegNo);
		setTitle(title);
	}
}
