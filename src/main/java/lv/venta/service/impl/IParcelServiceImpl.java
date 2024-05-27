package lv.venta.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Parcel;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.Driver;
import lv.venta.repo.IParcelRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.IDriverRepo;
import lv.venta.service.IParcelService;

@Service
public class IParcelServiceImpl implements IParcelService {

    @Autowired
    private IParcelRepo parcelRepo;

    @Autowired
    private ICustomerAsPersonRepo capRepo;

    @Autowired
    private ICustomerAsCompanyRepo cacRepo;

    @Autowired
    private IDriverRepo driverRepo;

    @Override
    public ArrayList<Parcel> selectAllParcelsByCustomerId(Long idC) throws Exception{
		ArrayList<Parcel> result = cacRepo.findParcelByIdC(idC);
		ArrayList<Parcel> result1 = capRepo.findParcelByIdC(idC);
		if(idC < 0) throw new Exception("ID cannot be negative");
		if(result.isEmpty()) {
			if(result1.isEmpty()) {
				throw new Exception("No customers with this ID found");
			}
			else {
				return result1;
			}
			
		}
		else{
			return result;
		}
	} 
    
    public ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(Long idP) throws Exception{
    	Optional<Driver> driver = driverRepo.findById(idP);
    	if (!driver.isPresent()) {
    		throw new Exception("Driver with id (" + idP + ") does not exist");
    	}
    	else {
    		ArrayList<Parcel> result = parcelRepo.findAllById(idP);
    		return result;
    	}
    }
    
    @Override
    public void insertNewParcelByCustomerCodeAndDriverId(String customerCode, Long dId, Parcel newParcel) throws Exception {
        Optional<CustomerAsPerson> person = capRepo.findByCustomerCode(customerCode);
        Optional<CustomerAsCompany> company = cacRepo.findByCustomerCode(customerCode);
        Optional<Driver> driver = driverRepo.findById(dId);

        if (!person.isPresent() && !company.isPresent()) {
            throw new Exception("Customer with code (" + customerCode + ") does not exist");
        }
        if (!driver.isPresent()) {
            throw new Exception("Driver with id (" + dId + ") does not exist");
        }

        if (person.isPresent()) {
            newParcel.setCustomerAsPerson(person.get());
        } else {
            newParcel.setCustomerAsCompany(company.get());
        }
        newParcel.setDriver(driver.get());
        
        parcelRepo.save(newParcel);
    }
    
    @Override
    public void changeParcelDriverByParcelIdAndDriverId(Long idPa, Long idP) throws Exception {
        Optional<Parcel> parcel = parcelRepo.findById(idP);
        Optional<Driver> driver = driverRepo.findById(idP);

        if (!parcel.isPresent()) {
            throw new Exception("Parcel with id (" + idPa + ") does not exist");
        }
        if (!driver.isPresent()) {
            throw new Exception("Driver with id (" + idPa + ") does not exist");
        }

        Parcel existingParcel = parcel.get();
        existingParcel.setDriver(driver.get());
        parcelRepo.save(existingParcel);
    }
    	

    @Override
    public double calculateIncomeOfParcelsByCustomerId(Long idC) throws Exception {
        ArrayList<Parcel> personParcels = parcelRepo.findAllParcelsByCustomerAsPersonidC(idC);
        ArrayList<Parcel> companyParcels = parcelRepo.findAllParcelsByCustomerAsCompanyidC(idC);

        if (personParcels.isEmpty() && companyParcels.isEmpty()) {
            throw new Exception("No customers with this ID found");
        }

        double totalIncome = personParcels.stream().mapToDouble(Parcel::getPrice).sum() +
                             companyParcels.stream().mapToDouble(Parcel::getPrice).sum();
        return totalIncome;
    }

    	
    @Override
    public int calculateHowManyParcelsNeedToDeliverToday() throws Exception {
        return parcelRepo.countParcelsToDeliverToday();
    }
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    
 
}
