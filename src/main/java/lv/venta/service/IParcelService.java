package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.City;
import lv.venta.model.Parcel;

public interface IParcelService {
	
    ArrayList<Parcel> selectAllParcelsByCustomerId(Long idC) throws Exception;

    ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(Long idP) throws Exception;
    
    void insertNewParcelByCustomerCodeAndDriverId(String customerCode, Long driverId, Parcel newParcel)
			throws Exception;

	void changeParcelDriverByParcelIdAndDriverId(Long idPa, Long idP) throws Exception;

	double calculateIncomeOfParcelsByCustomerId(Long customerId) throws Exception;

	int calculateHowManyParcelsNeedToDeliverToday() throws Exception;

	ArrayList<Parcel> selectAllParcelsPriceLessThan(float price) throws Exception;

	ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception;
	
	


	
   
 
}
