package lv.venta.controller;

import lv.venta.model.City;
import lv.venta.model.Parcel;
import lv.venta.service.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/parcel")
public class ParcelController {

    @Autowired
    private IParcelService parcelService;


    @GetMapping("/show/customer/{idC}")
    public String getAllParcelsByCustomerIdc(@PathVariable("idC") Long idC, Model model) {
        try {
        	ArrayList<Parcel> parcels = parcelService.selectAllParcelsByCustomerId(idC);
            model.addAttribute("parcels", parcels);
            return "parcel-all-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/show/driver/{idP}")
    public String getAllParcelsDeliveredByDriverIdp(@PathVariable("idP") Long idP, Model model) {
        try {
            ArrayList<Parcel> parcels = parcelService.selectAllParcelsDeliveredByDriverId(idP);
            model.addAttribute("parcels", parcels);
            return "parcel-all-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

 
    @GetMapping("/show/price/{threshold}")
    public String getAllParcelsPriceLessThan(@PathVariable("threshold") float threshold, Model model) {
        try {
            ArrayList<Parcel> parcels = parcelService.selectAllParcelsPriceLessThan(threshold);
            model.addAttribute("parcels", parcels);
            return "parcel-all-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/show/city/{cityparam}")
    public String getAllParcelsDeliveredToCity(@PathVariable("cityparam") City cityparam, Model model) {
        try {
            ArrayList<Parcel> parcels = parcelService.selectAllParcelsDeliveredToCity(cityparam);
            model.addAttribute("parcels", parcels);
            return "parcel-all-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/add/{customerCode}/{idP}")
    public String getParcelForm(@PathVariable String customerCode, @PathVariable Long idP, Model model) {
        model.addAttribute("parcel", new Parcel());
        return "parcel-add-page";
    }

    @PostMapping("/add/{customerCode}/{idP}")
    public String addNewParcel(@PathVariable("customerCode") String customerCode, @PathVariable("idP") Long idP, @ModelAttribute Parcel parcel, Model model) {
        try {
            parcelService.insertNewParcelByCustomerCodeAndDriverId(customerCode, idP, parcel);
            if(parcel.getCustomerAsCompany().equals(null)) {
            	return "redirect:/parcel/show/customer/" + parcel.getCustomerAsPerson().getIdC();
            }
            else {
            	return "redirect:/parcel/show/customer/" + parcel.getCustomerAsCompany().getIdC();
            }
            } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/change/{idPa}/{idP}")
    public String changeParcelDriver(@PathVariable("idPa") Long idPa, @PathVariable("idP") Long idP, Model model) {
        try {
            parcelService.changeParcelDriverByParcelIdAndDriverId(idPa, idP);
            return "redirect:/parcel/show/driver/" + idP;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/calculate/income/{cId}")
    public String calculateIncomeOfParcelsByCustomerIdc(@PathVariable("cId") Long cId, Model model) {
        try {
            double income = parcelService.calculateIncomeOfParcelsByCustomerId(cId);
            model.addAttribute("income", income);
            return "parcel-calculate-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/calculate/count/today")
    public String calculateHowManyParcelsNeedToDeliverToday(Model model) {
        try {
            int count = parcelService.calculateHowManyParcelsNeedToDeliverToday();
            model.addAttribute("count", count);
            return "parcel-calculate-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }
}
