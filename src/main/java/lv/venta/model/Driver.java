package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Driver")
@Entity
public class Driver{
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "IdP")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idP;
	
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
	

	@NotNull
	//@Pattern(regexp = "[A-Z]{1}[a-z]+")
	@Size(min = 2, max = 20)
	@Column(name = "Person Code")
	private String personCode;
	
	
	
	@Column(name = "Experience in years")
	@NotNull
	@Min(1)
	@Max(50)
	private float experienceInYears;
	
	
	@NotNull
	//@Pattern(regexp = "[A-Z]{1}[a-z]+")
	@Size(min = 2, max = 20)
	@Column(name = "License_number")
	private String licenseNo;
	

	
	
		
	public Driver(String name, String surname, String personCode, String licenseNo, float experienceInYears) {
		setPersonCode(personCode);
		setName(name);
		setSurname(surname);
		setExperienceInYears(experienceInYears);
		setLicenseNo(licenseNo);
	}

}
