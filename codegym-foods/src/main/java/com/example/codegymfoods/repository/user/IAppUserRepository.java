package com.example.codegymfoods.repository.user;

import com.example.codegymfoods.model.login.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByUserName(String username);
}
