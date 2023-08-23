package com.example.codegymfoods.repository.user;

import com.example.codegymfoods.model.login.AppUser;
import com.example.codegymfoods.model.login.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface IUserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findByAppUser(AppUser appUser);
    UserRole findUserRoleByAppUser(AppUser appUser);

}
