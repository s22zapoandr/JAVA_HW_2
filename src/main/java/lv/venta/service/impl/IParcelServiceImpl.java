package lv.venta.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Parcel;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.City;
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
		ArrayList<Parcel> result1 = parcelRepo.findParcelsByCustomerAsCompanyIdC(idC);
		ArrayList<Parcel> result2 = parcelRepo.findParcelsByCustomerAsPersonIdC(idC);
		if(idC < 0) throw new Exception("ID cannot be negative");
		if(result1.isEmpty()) {
			if(result1.isEmpty()) {
				throw new Exception("No customers with this ID found");
			}
			else {
				return result2;
			}
			
		}
		else{
			return result1;
		}
	} 
    
    public ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(Long idP) throws Exception{
    	Optional<Driver> driver = driverRepo.findById(idP);
    	if (!driver.isPresent()) {
    		throw new Exception("Driver with id (" + idP + ") does not exist");
    	}
    	else {
    		ArrayList<Parcel> result = parcelRepo.findByDriverIdP(idP);
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
        ArrayList<Parcel> personParcels = parcelRepo.findParcelsByCustomerAsPersonIdC(idC);
        ArrayList<Parcel> companyParcels = parcelRepo.findParcelsByCustomerAsCompanyIdC(idC);

        if (personParcels.isEmpty() && companyParcels.isEmpty()) {
            throw new Exception("No customers with this ID found");
        }

        double totalIncome = personParcels.stream().mapToDouble(Parcel::getPrice).sum() +
                             companyParcels.stream().mapToDouble(Parcel::getPrice).sum();
        return totalIncome;
    }

    	
    @Override
    public int calculateHowManyParcelsNeedToDeliverToday() throws Exception {
        int counter = 0;
        ArrayList<Parcel> parcels = (ArrayList<Parcel>) parcelRepo.findAll();
        if(parcels.isEmpty()) throw new Exception("There are no parcels in the system");
        for(Parcel tempP : parcels) {
        	if(tempP.getPlannedDelivery().equals(LocalDate.now())) {
        		counter++;
        	}
        }
        return counter;
    }
    	
    @Override
    public ArrayList<Parcel> selectAllParcelsPriceLessThan(float price) throws Exception	{
    	ArrayList<Parcel> result = parcelRepo.findByPriceLessThan(price);
    	if(result.isEmpty()) throw new Exception("No parcel for price less then ("+price+") found is system");
    	return result;
    }
    
    @Override
    	
    public ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception{
    	ArrayList<Parcel> result1 = parcelRepo.findParcelsByCustomerAsCompanyAddressCity(city);
    	ArrayList<Parcel> result2 = parcelRepo.findParcelsByCustomerAsPersonAddressCity(city);
    	if(result1.isEmpty()) {
    		if(result2.isEmpty()) {
    			throw new Exception("No parcels to be delivered to this city");
    		}
    		else {
    			return result2;
    		}
    	}
    	else {
    		return result1;
    	}
    }
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    
 
}
