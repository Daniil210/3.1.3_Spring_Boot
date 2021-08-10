package com.danila.spring_boot_crud.Dao;

import com.danila.spring_boot_crud.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
