package com.danila.spring_boot_crud.service;

import com.danila.spring_boot_crud.dto.RoleDto;
import com.danila.spring_boot_crud.dto.UsersDto;
import com.danila.spring_boot_crud.model.Role;
import com.danila.spring_boot_crud.model.User;
import org.springframework.stereotype.Component;

@Component
public class UsersConverter {

    public User fromUserDtoToUser(UsersDto usersDto) {
        User users = new User();
        users.setId(usersDto.getId());
        users.setName(usersDto.getName());
        users.setSurname(usersDto.getSurname());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        users.setAge(usersDto.getAge());


        users.setRoles(usersDto.getRoles());

        return users;
    }

    public UsersDto fromUserToUserDto(User users) {
        return UsersDto.builder()
                .id(users.getId())
                .name(users.getName())
                .surname(users.getSurname())
                .email(users.getEmail())
                .age(users.getAge())
                .password(users.getPassword())
                .roles(users.getRoles())
                .build();
    }

    /*public Role formRoleDtoToRole(RoleDto roleDto) {
        Role role =new Role();
        role.setId(roleDto.getId());
        role.setRole(roleDto.getRole());
        return role;
    }

    public RoleDto formRoleToRoleDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .role(role.getRole())
                .build();
    }*/

    /*public User fromUserDtoToUser(UsersDto usersDto) {
        User users = new User();
        users.setId(usersDto.getId());
        users.setName(usersDto.getName());
        users.setSurname(usersDto.getSurname());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        users.setAge(usersDto.getAge());


        users.setRoles(usersDto.getRoles()
                .stream()
                .map(new UsersConverter()::formRoleDtoToRole)
                .collect(Collectors.toSet()));

        return users;
    }

    public UsersDto fromUserToUserDto(User users) {
        return UsersDto.builder()
                .id(users.getId())
                .name(users.getName())
                .surname(users.getSurname())
                .email(users.getEmail())
                .age(users.getAge())
                .password(users.getPassword())
                .roles(users.getRoles().stream()
                        .map(new UsersConverter()::formRoleToRoleDto)
                        .collect(Collectors.toSet()))
                .build();
    }*/

}
