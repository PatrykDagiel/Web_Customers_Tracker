package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // need to inject customer service
    @Autowired
    private CustomerService customerService;


//    // need to inject the customer DAO
//    @Autowired
//    private CustomerDAO customerDAO;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {

        // get customers from the dao
        List<Customer> theCustomers = customerService.getCustomers();

        // add customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create Model attribute
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
        
        // save customer using our service
        customerService.saveCustomer(theCustomer);
        
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

        // get the customer from the service
        Customer theCustomer = customerService.getCustomer(theId);
        // set customer as a model attribute to pre-populate the form
        theModel.addAttribute("customer", theCustomer);
        // send over to out form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {

        //deleteCustomer
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }

    @PostMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel) {
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }
}
