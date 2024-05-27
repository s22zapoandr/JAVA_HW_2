package lv.venta.repo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsCompany;
import lv.venta.model.Parcel;

public interface ICustomerAsCompanyRepo extends CrudRepository<CustomerAsCompany,  Long> {

	ArrayList<Parcel> findParcelByIdC(Long idC);

	Optional<CustomerAsCompany> findByCustomerCode(String customerCode);

	Optional<CustomerAsCompany> findByCompanyRegNo(String companyRegNo);

}
