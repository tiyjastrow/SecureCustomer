package com.example.customer.services;

import com.example.customer.domains.Customer;
import com.example.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public Customer add(Customer customer) {
         return customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getById(int id) {
        return customerRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(int id) {
        customerRepository.delete(id);
    }
}
