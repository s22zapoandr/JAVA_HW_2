package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.City;
import lv.venta.model.Parcel;

public interface IParcelRepo extends CrudRepository<Parcel, Long>{

	ArrayList<Parcel> findParcelsByCustomerAsPersonIdC(Long idC);

	ArrayList<Parcel> findParcelsByCustomerAsCompanyIdC(Long idC);

	ArrayList<Parcel> findByPriceLessThan(float price);

	ArrayList<Parcel> findParcelsByCustomerAsCompanyAddressCity(City city);

	ArrayList<Parcel> findParcelsByCustomerAsPersonAddressCity(City city);

	ArrayList<Parcel> findByDriverIdP(Long idP);


}
