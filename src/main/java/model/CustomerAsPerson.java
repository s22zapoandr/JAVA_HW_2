package model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Customer as person")
@Entity
public class CustomerAsPerson extends AbstractCustomer{
	public CustomerAsPerson(String name, String surnname, String personCode, Address addres, String phoneNo) {
	}

}
