package lv.venta.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.CustomerAsPerson;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.Address;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.service.ICustomerService;

@Service
public class ICustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerAsPersonRepo capRepo;

    @Autowired
    private ICustomerAsCompanyRepo cacRepo;


    @Override
    public void insertNewCustomerAsPerson(CustomerAsPerson customer) throws Exception {
        Optional<CustomerAsPerson> existingCustomer = capRepo.findByPersonCode(customer.getPersonCode());
        if (existingCustomer.isPresent()) {
            throw new Exception("Customer with code (" + customer.getPersonCode() + ") already exists");
        }
       capRepo.save(customer);
    }

    @Override
    public void insertNewCustomerAsCompany(CustomerAsCompany customer) throws Exception {
        Optional<CustomerAsCompany> existingCustomer = cacRepo.findByCompanyRegNo(customer.getCompanyRegNo());
        if (existingCustomer.isPresent()) {
            throw new Exception("Company with code (" + customer.getCompanyRegNo() + ") already exists");
        }
        cacRepo.save(customer);
    }

    @Override
    public void addAddressToCustomerByCustomerId(String customerCode, Address address) throws Exception {
        Optional<CustomerAsPerson> customer1 = capRepo.findByCustomerCode(customerCode);
        Optional<CustomerAsCompany> customer2 = cacRepo.findByCustomerCode(customerCode);
        if (!customer1.isPresent()) {
        	if(!customer2.isPresent()) {
        		throw new Exception("Customer with code (" + customerCode + ") does not exist");
        	}
        	else {
        		CustomerAsCompany existingCustomer = customer2.get();
        		existingCustomer.setAddress(address);
        		cacRepo.save(existingCustomer);
        	}
        }
        else {
        	CustomerAsPerson existingCustomer = customer1.get();
        	existingCustomer.setAddress(address);
        	capRepo.save(existingCustomer);
        }
    }
}
