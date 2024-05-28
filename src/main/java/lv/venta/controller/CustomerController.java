package lv.venta.controller;

import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.Address;
import lv.venta.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;


    @GetMapping("/create/person")
    public String getCustomerAsPersonForm(Model model) {
        model.addAttribute("customer", new CustomerAsPerson());
        return "customer-person-create-page";
    }

    @PostMapping("/create/person")
    public String createCustomerAsPerson(@ModelAttribute CustomerAsPerson customer, Model model) {
        try {
            customerService.insertNewCustomerAsPerson(customer);
            return "redirect:/customer/create/person";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/create/company")
    public String getCustomerAsCompanyForm(Model model) {
        model.addAttribute("customer", new CustomerAsCompany());
        return "customer-company-create-page";
    }

    @PostMapping("/create/company")
    public String createCustomerAsCompany(@ModelAttribute CustomerAsCompany customer, Model model) {
        try {
            customerService.insertNewCustomerAsCompany(customer);
            return "redirect:/customer/create/company";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/add/address/{cID}")
    public String getAddressForm(@PathVariable Long cID, Model model) {
        model.addAttribute("address", new Address());
        model.addAttribute("customerid", cID);
        return "customer-add-address-page";
    }

    @PostMapping("/add/address/{cID}")
    public String addAddressToCustomer(@PathVariable Long cID, @ModelAttribute Address address, Model model) {
        try {
            customerService.addAddressToCustomerByCustomerId(cID, address);
            return "redirect:/customer/add/address/" + cID;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }
}