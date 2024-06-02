package lv.venta.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "Person")
@Entity

public class Person {
	@NotNull
	@Column(name = "IDP")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idP;
	
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
	
	public Person(String personCode, String name, String surname) {
		setName(name);
		setSurname(surname);
		setPersonCode(personCode);
	}
	
	
}
