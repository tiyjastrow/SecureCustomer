package com.example.customer.controllers;

import com.example.customer.domains.Customer;
import com.example.customer.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    private ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/customers")
    String getCustomers(Model model) {
        List<Customer> customerList = customerService.getAll();
        model.addAttribute("customers", customerList);
        return "view_customers";
    }

    @GetMapping("/customer")
    String newCustomer() {
        return "new_customer";
    }

    @PostMapping("/customer")
    String addCustomer(@ModelAttribute("newCustomer") Customer newCustomer) {
        customerService.add(newCustomer);
        return "redirect:customers";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/administration")
    String admins() {
        return "administration";
    }
}
