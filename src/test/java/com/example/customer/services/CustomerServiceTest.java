package com.example.customer.services;

import com.example.customer.domains.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.customer.common.CustomerServiceTestUtils.createTestCustomer;
import static com.example.customer.common.CustomerServiceTestUtils.findInList;
import static com.example.customer.common.CustomerServiceTestUtils.changeTestCustomerData;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void testAddGet() {
        Customer testCustomer1 = createTestCustomer();  // Create new test customer
        customerService.add(testCustomer1);  // Add to database

        // Verify get all
        List<Customer> customers = customerService.getAll();// Get all records from database
        Assert.assertNotNull("should return customers from db", customers);
        // Verify add
        Customer foundTestCustomer1 = findInList(customers, testCustomer1.getFirstName(), testCustomer1.getLastName(),
                testCustomer1.getPhone(), testCustomer1.getEmail());
        Assert.assertNotNull("should find added customer in customers returned from db", foundTestCustomer1);

        int id = foundTestCustomer1.getId();
        foundTestCustomer1 = null; // Reset
        // Verify get by id
        foundTestCustomer1 = customerService.getById(id);
        Assert.assertNotNull("should return customer from db", foundTestCustomer1);
        Assert.assertEquals("first name should match", testCustomer1.getFirstName(),
                foundTestCustomer1.getFirstName());
        Assert.assertEquals("last name should match", testCustomer1.getLastName(),
                foundTestCustomer1.getLastName());
        Assert.assertEquals("phone should match", testCustomer1.getPhone(), foundTestCustomer1.getPhone());
        Assert.assertEquals("email name should match", testCustomer1.getEmail(), foundTestCustomer1.getEmail());
    }

    @Test
    public void testUpdate() {
        Customer testCustomer1 = createTestCustomer(); // Create new test customer
        customerService.add(testCustomer1); // Add to db
        List<Customer> customers = customerService.getAll(); // Get all customers from db
        Assert.assertNotNull("should return customers from db", customers);
        Customer foundTestCustomer1 = findInList(customers, testCustomer1.getFirstName(), testCustomer1.getLastName(),
                testCustomer1.getPhone(), testCustomer1.getEmail());
        Assert.assertNotNull("should find added customer in customers returned from db", foundTestCustomer1);

        Customer updatedTestCustomer1 = changeTestCustomerData(foundTestCustomer1); // Update original customer
        customerService.update(updatedTestCustomer1); // Make updates in db
        customers = customerService.getAll(); // Get all customers from db
        Customer foundUpdatedTestCustomer1 = findInList(customers, updatedTestCustomer1.getFirstName(),
                updatedTestCustomer1.getLastName(), updatedTestCustomer1.getPhone(), updatedTestCustomer1.getEmail());
        Assert.assertNotNull("should find updated customer in customers returned from db",
                foundUpdatedTestCustomer1);
        Assert.assertEquals("returned customer should be updated customer", updatedTestCustomer1.getId(),
                foundUpdatedTestCustomer1.getId());
        Assert.assertNotEquals("returned customer should have different first name than before update",
                testCustomer1.getFirstName(), foundUpdatedTestCustomer1.getFirstName());
        Assert.assertNotEquals("returned customer should have different last name than before update",
                testCustomer1.getLastName(), foundUpdatedTestCustomer1.getLastName());
        Assert.assertNotEquals("returned customer should have different phone than before update",
                testCustomer1.getPhone(), foundUpdatedTestCustomer1.getPhone());
        Assert.assertNotEquals("returned customer should have different email than before update",
                testCustomer1.getEmail(), foundUpdatedTestCustomer1.getEmail());
    }

    @Test
    public void testDelete() {
        Customer testCustomer1 = createTestCustomer(); // Create new test customer
        customerService.add(testCustomer1); // Add to db
        List<Customer> customers = customerService.getAll(); // Get all customers from db
        Assert.assertNotNull("should return customers from db", customers);
        Customer foundTestCustomer1 = findInList(customers, testCustomer1.getFirstName(), testCustomer1.getLastName(),
                testCustomer1.getPhone(), testCustomer1.getEmail());
        Assert.assertNotNull("should find added customer in customers returned from db", foundTestCustomer1);

        customerService.delete(foundTestCustomer1.getId()); // Delete customer from db
        foundTestCustomer1 = null; // Reset
        customers = customerService.getAll(); // Get all customers from db again
        foundTestCustomer1 = findInList(customers, testCustomer1.getFirstName(), testCustomer1.getLastName(),
                testCustomer1.getPhone(), testCustomer1.getEmail());
        Assert.assertNull("should not find customer in customers returned from db", foundTestCustomer1);
    }
}
