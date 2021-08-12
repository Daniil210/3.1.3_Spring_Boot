package com.danila.spring_boot_crud.dto;

import com.danila.spring_boot_crud.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private Long id;

    private String name;

    private String surname;

    private Integer age;

    private String email;

    private String password;

    private Set<Role> roles = new HashSet<>();

    public boolean checkAdmin() {
        return this.roles.toString().contains("ADMIN");
    }

    public boolean checkUser() {
        return this.roles.toString().contains("USER");
    }
}
