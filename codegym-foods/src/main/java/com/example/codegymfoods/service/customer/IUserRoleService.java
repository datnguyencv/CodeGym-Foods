package com.example.codegymfoods.service.customer;

import com.example.codegymfoods.model.login.AppUser;
import com.example.codegymfoods.model.login.UserRole;

import java.util.List;

public interface IUserRoleService {
    List<UserRole> findAllUserRole();
    void saveUserRole(UserRole userRole);
    void findById(Integer id);
    void deleteUserRole(UserRole userRole);
    UserRole findUserRoleByAppUser(AppUser appUser);
}
