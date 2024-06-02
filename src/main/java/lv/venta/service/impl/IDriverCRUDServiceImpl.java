package lv.venta.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Driver;
import lv.venta.repo.IDriverRepo;
import lv.venta.service.IDriverCRUDService;


@Service

public class IDriverCRUDServiceImpl implements IDriverCRUDService {
	
	@Autowired
	private IDriverRepo driverRepo;
	
	@Override
	
	public ArrayList<Driver> selectAllDriver() throws Exception{
		ArrayList<Driver> result = (ArrayList<Driver>) driverRepo.findAll();
		
		if(result.isEmpty()) throw new Exception("There are no drivers");
		
		return result;
	}
	
	@Override
	
	public Driver selectDriverById(Long idP) throws Exception{
		
		if(idP < 1) throw new Exception("Id should be positive");
		
		if(!driverRepo.existsById(idP)) throw new Exception("Driver with id ("+idP+") does not exist");
		
		return driverRepo.findById(idP).get();
		
	}
	
	@Override
	
	public void deleteDriverById(Long idP) throws Exception{
		
		if(idP < 1) throw new Exception("Id should be positive");
		
		if(!driverRepo.existsById(idP)) throw new Exception("Driver with id ("+idP+") does not exist");
		
		 driverRepo.deleteById(idP);
	}
	
	@Override
	
	public void updateDriverById(Long idP, String name, String surname, String personCode, String licenseNo, float experienceInYears) throws Exception{
		
		if(idP < 1) throw new Exception("Id should be positive");
		
		if(!driverRepo.existsById(idP)) throw new Exception("Driver with id ("+idP+") does not exist");
		
		Optional<Driver> updateDriver = driverRepo.findById(idP);
		
		updateDriver.get().setName(surname);
		updateDriver.get().setSurname(surname);	
		updateDriver.get().setPersonCode(personCode);
		updateDriver.get().setLicenseNo(licenseNo);
		updateDriver.get().setExperienceInYears(experienceInYears);
		
		driverRepo.save(updateDriver.get());
	}
	
	@Override
	
	public void insertNewDriver(String name, String surname, String personCode, String licenseNo, float experienceInYears)throws Exception {
		if (name == null || surname == null || personCode == null || licenseNo == null|| experienceInYears < 0)
			throw new Exception("Problems with input params");


		Driver newDriver = new Driver(name, surname, personCode, licenseNo, experienceInYears);
		driverRepo.save(newDriver);

	}
	
	
	
	
	
	
	
	
}
