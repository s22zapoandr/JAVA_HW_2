package lv.venta.repo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.Parcel;

public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson,  Long> {

	Optional<CustomerAsPerson> findByCustomerCode(String customerCode);

	Optional<CustomerAsPerson> findByPersonCode(String personCode);



}
