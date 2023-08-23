package com.example.codegymfoods.repository.employee;

import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    Page<Employee> findAllByNameContainingAndPositionId(String name, Integer id, Pageable pageable);
    
    Page<Employee> findAllByNameContaining(String name, Pageable pageable);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByAppUser_UserName(String userName);

    Employee findCustomerByAppUser_UserName(String account);
    @Query(value = "select * from employee join position  on employee.position_id = position.id join app_user on employee.app_user_id = app_user.user_id where app_user.user_name =?" ,nativeQuery = true)
    Employee findByUsername(String username);
}

