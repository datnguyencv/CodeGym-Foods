package com.example.codegymfoods.service.customer.impl;

import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.login.AppUser;
import com.example.codegymfoods.repository.customer.ICustomerRepository;
import com.example.codegymfoods.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public Page<Customer> findAllByName(String name, PageRequest pageRequest) {
        return customerRepository.findAllByNameContaining(name,pageRequest);
    }

    @Override
    public Page<Customer> findAllCustomer(String name, Pageable pageable) {
        return null;
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    @Override
    public void deleteCustomer(Integer idCustomer) {
        customerRepository.deleteById(idCustomer);
    }

    @Override
    public Customer findByIdCustomer(Integer idCustomer) {
        return customerRepository.findById(idCustomer).get();
    }

    @Override
    public Page<Customer> findByNameCustomer(String nameCustomer, Pageable pageable) {
        return customerRepository.findAllByNameContaining(nameCustomer, pageable);
    }


    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return customerRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsByAppUser_UserName(String userName) {
        return customerRepository.existsByAppUser_UserName(userName);
    }

    @Override
    public Customer findByNameAccount(String nameAccount) {
        return customerRepository.findCustomerByAppUser_UserName(nameAccount);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
}
