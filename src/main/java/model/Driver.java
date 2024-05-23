package model;

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
public class Driver extends Person{
	

	@Column(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z]{1,20}")
	@Size(min = 2, max = 20)
	@Column(name = "Surname")
	private String licenseNo;
	
	
	@Column(name = "Experience in years")
	@NotNull
	@Min(1)
	@Max(50)
	private float experienceInYears;
	
	private long counter = 0; 
	

	public void setId() {
		this.id = counter;
		counter ++;
		
	}
		
	public Driver(String name, String surname, String personCode, String licenseNo, float experienceInYears) {
		setId();
		setPersonCode(personCode);
		setName(name);
		setSurname(surname);
		setExperienceInYears(experienceInYears);
		setLicenseNo(licenseNo);
	}

}
