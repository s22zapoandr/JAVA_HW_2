package lv.venta;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Address;
import lv.venta.model.City;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.Driver;
import lv.venta.model.Parcel;
import lv.venta.model.ParcelSize;
import lv.venta.model.Person;
import lv.venta.repo.IAddressRepo;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.IParcelRepo;
import lv.venta.repo.IPersonRepo;

@SpringBootApplication
public class MyEparcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEparcelApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner testDB(IAddressRepo addressRepo, ICustomerAsCompanyRepo cacRepo, ICustomerAsPersonRepo capRepo, IDriverRepo driverRepo, IParcelRepo parcelRepo, IPersonRepo personRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				Address a1 = new Address(City.Daugavpils, "Saules", 54);
				Address a2 = new Address(City.Ventspils, "Inzenieru", 101);
				addressRepo.save(a1);
				addressRepo.save(a2);
				
				
				CustomerAsCompany cac1 = new CustomerAsCompany(a1, "37124321", "Hanzamatrix", "LV41021245");
				CustomerAsCompany cac2 = new CustomerAsCompany(a1, "37247120", "Dianasveces", "LT12401233");
				cacRepo.save(cac1);
				cacRepo.save(cac2);
				
				CustomerAsPerson cap1 = new CustomerAsPerson("Andrii", "Zaporozhets","4214141", a2, "421432131");
				CustomerAsPerson cap2 = new CustomerAsPerson("Anton", "Volkov", "213123", a2, "21421342");
				capRepo.save(cap1);
				capRepo.save(cap2);
				
				Driver d1 = new Driver("Igors", "Goncarovs", "1241231", "LT23541", 5.7f);
				Driver d2 = new Driver("Peter", "Steele", "1241241", "US12431", 10.0f);
				driverRepo.save(d1);
				driverRepo.save(d2);
				
				//Parcel par1 = new Parcel();
				Parcel par2 = new Parcel(LocalDateTime.of(2024, 04, 24, 14, 33, 48, 123456789), LocalDateTime.of(2024, 04, 30, 14, 33, 48, 123456789), ParcelSize.M, false , d1, cac1);
				//parcelRepo.save(par1);
				parcelRepo.save(par2);
				
				Person per1 = new Person("1242532", "John", "Smith");
				Person per2 = new Person("1240432", "Armen", "Smith");
				personRepo.save(per1);
				personRepo.save(per2);
				
				
				
			}
		};
	}
	

}
