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
    @Size(min = 1, max = 8)
    @Pattern(regexp = "\\d{1,8}")
    private String phoneNo;

    @Column(name = "Customer_code")
    private String customerCode;

	@NotNull
	@Pattern(regexp = "[0-9]{6}-[0-9]{5}")
	@Size(min = 0, max = 12)
	@Column(name = "Person_Code")
	private String personCode;

	@NotNull
	@Pattern(regexp = "[A-Za-z ]{2,10}")
	@Size(min = 2, max = 10)
	@Column(name = "Name")
	private String name;

	@NotNull
	@Pattern(regexp = "[A-Za-z ]{2,20}")
	@Size(min = 2, max = 20)
	@Column(name = "Surname")
	private String surname;
	 
	//Linkage 
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "IDA")
	private Address address;
	
	
	@OneToMany(mappedBy = "customerAsPerson")
	private Collection<Parcel> parcels = new ArrayList<Parcel>();
	
	
	
	//Override

	public void setCustomerCode() {
		this.customerCode = idC+"person"+personCode;
	}
	

	
	public CustomerAsPerson(String name, String surname, String personCode, Address address, String phoneNo) {
		setName(name);
		setSurname(surname);
		setPersonCode(personCode);
		setCustomerCode();
		setPhoneNo(phoneNo);
		setAddress(address);
		
		
	}

}
