package lv.venta.service;

import lv.venta.model.Address;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;

public interface ICustomerService {

	void insertNewCustomerAsPerson(CustomerAsPerson customer) throws Exception;

	void insertNewCustomerAsCompany(CustomerAsCompany customer) throws Exception;

	void addAddressToCustomerByCustomerId(Long cID, Address address) throws Exception;

}
