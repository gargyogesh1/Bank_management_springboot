package com.example.customer_service.service;

import com.example.customer_service.entity.Customer;
import com.example.customer_service.dto.LoginRequest;
import com.example.customer_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer getCustomerById(Long id);
    Customer getCustomerBySsnId(String ssnId);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Long id, Customer updatedCustomer);
    void deleteCustomer(Long id);
    Customer loginCustomer(LoginRequest loginRequest);
}

@Service
class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private String generateSSN() {
        Random random = new Random();
        int number = 1000000 + random.nextInt(9000000); // 7-digit random
        return String.valueOf(number);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setSsnId(generateSSN());
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public Customer getCustomerBySsnId(String ssnId) {
        return customerRepository.findBySsnId(ssnId)
                .orElseThrow(() -> new RuntimeException("Customer not found with SSN: " + ssnId));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existing = getCustomerById(id);
        existing.setFirstName(updatedCustomer.getFirstName());
        existing.setLastName(updatedCustomer.getLastName());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setPassword(updatedCustomer.getPassword());
        existing.setAddress(updatedCustomer.getAddress());
        existing.setContactNumber(updatedCustomer.getContactNumber());
        existing.setAccountNumber(updatedCustomer.getAccountNumber());
        existing.setIfscCode(updatedCustomer.getIfscCode());
        existing.setAccountBalance(updatedCustomer.getAccountBalance());
        existing.setAdharNo(updatedCustomer.getAdharNo());
        existing.setPanCard(updatedCustomer.getPanCard());
        existing.setDateOfBirth(updatedCustomer.getDateOfBirth());
        existing.setGender(updatedCustomer.getGender());
        existing.setMaritalStatus(updatedCustomer.getMaritalStatus());
        return customerRepository.save(existing);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    
    public Customer loginCustomer(LoginRequest loginRequest) {
        Optional<Customer> customerOpt = customerRepository.findBySsnId(loginRequest.getSsnId());

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (customer.getPassword().equals(loginRequest.getPassword())) {
                return customer; // ✅ Password matches, return full data
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("Customer not found with SSN ID: " + loginRequest.getSsnId());
        }
    }
}
