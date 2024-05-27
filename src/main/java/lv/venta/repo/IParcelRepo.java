package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Parcel;

public interface IParcelRepo extends CrudRepository<Parcel, Long>{

	ArrayList<Parcel> findAllById(Long idP);

	ArrayList<Parcel> findAllParcelsByCustomerAsPersonidC(Long idC);

	ArrayList<Parcel> findAllParcelsByCustomerAsCompanyidC(Long idC);

	int countParcelsToDeliverToday();

}
