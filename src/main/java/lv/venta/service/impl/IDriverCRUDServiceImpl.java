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
		ArrayList<Driver> result = driverRepo.findAll();
		
		if(result.isEmpty()) throw new Exception("There are no drivers");
		
		return result;
	}
	
	@Override
	
	public Driver selectDriverById(Long idP) throws Exception{
		
		if(idP < 1) throw new Exception("Id should be positive");
		
		if(!driverRepo.existsById(idP)) throw new Exception("Driver with id ("+idP+") does not exist");
		
		return driverRepo.findById(idP).get();
		
	}
	
	public void deleteDriverById(Long idP) throws Exception{
		
		if(idP < 1) throw new Exception("Id should be positive");
		
		if(!driverRepo.existsById(idP)) throw new Exception("Driver with id ("+idP+") does not exist");
		
		 driverRepo.deleteById(idP);
	}
	
	
	public void updateDriverById(Long idP, String name, String surname, String personCode, String licenseNo, float experienceInYears) throws Exception{
		
		if(idP < 1) throw new Exception("Id should be positive");
		
		if(!driverRepo.existsById(idP)) throw new Exception("Driver with id ("+idP+") does not exist");
		
		driverRepo.updateById(idP);
	

	}
	public void insertNewDriver(Driver newDriver) {

		driverRepo.save(newDriver);
		
	}
	

	
	
	
	
}
