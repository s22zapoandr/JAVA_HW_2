package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Driver;
public interface IDriverRepo extends CrudRepository<Driver, Long>{

	void findById(long idP);
	
	void deleteById(long idP);
	
	void updateById(long idP);
	
	ArrayList<Driver> findAll();
	
	
	
	
}
