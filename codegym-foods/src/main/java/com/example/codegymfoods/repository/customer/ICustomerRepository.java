package com.example.codegymfoods.repository.customer;

import com.example.codegymfoods.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
    @Query(value = "select * from customer where name_customer like concat('%',:name_customer,'%')", nativeQuery = true)
    Page<Customer> findByNameCustomer(@Param("name_customer") String name, PageRequest pageRequest);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByAppUser_UserName(String userName);
    Customer findCustomerByAppUser_UserName(String account);
    Page<Customer>findAllByNameContaining(String name , Pageable pageable);

    @Query(value = "select * from customer join app_user on customer.app_user_id = app_user.user_id where app_user.user_name = ? ",nativeQuery = true)
    Customer findByUsername(String username);
}
