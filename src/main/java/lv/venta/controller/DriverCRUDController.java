package lv.venta.controller;

import lv.venta.model.Driver;
import lv.venta.service.IDriverCRUDService;
import lv.venta.service.IDriverCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriverCRUDController {

    @Autowired
    private IDriverCRUDService driverService;


    @GetMapping("/show/all")
    public String getAllDrivers(Model model) {
        try {
            List<Driver> drivers = driverService.selectAllDriver();
            model.addAttribute("drivers", drivers);
            return "driver-all-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/show/all/{idP}")
    public String getDriverById(@PathVariable("idP") Long idP, Model model) {
        try {
            Driver driver = driverService.selectDriverById(idP);
            model.addAttribute("driver", driver);
            return "driver-one-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/remove/{idP}")
    public String removeDriverById(@PathVariable("idP") Long idP, Model model) {
        try {
            driverService.deleteDriverById(idP);
            return "redirect:/driver/show/all";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/add")
    public String getDriverForm(Model model) {
        model.addAttribute("driver", new Driver());
        return "driver-add-page";
    }

    @PostMapping("/add")
    public String addDriver(@ModelAttribute String name, String surname, String personCode, String licenseNo, float experienceInYears, Model model) {
        try {
            driverService.insertNewDriver(name, surname, personCode, licenseNo, experienceInYears);
            return "redirect:/driver/show/all";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

   
    @GetMapping("/update/{idP}")
    public String getUpdateDriverForm(@PathVariable("idP") Long idP, Model model) {
        try {
            Driver driver = driverService.selectDriverById(idP);
            model.addAttribute("driver", driver);
            return "driver-update-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/update/{idP}")
    public String updateDriverById(@PathVariable("idP") Long idP, String name, String surname, String personCode, String licenseNo, float experienceInYears, Model model) {
        try {
            driverService.updateDriverById(idP, name,  surname,  personCode,  licenseNo,  experienceInYears);
            return "redirect:/driver/show/all";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }
}
