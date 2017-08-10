package com.example.customer.repositories;

import com.example.customer.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}
