
package com.danila.spring_boot_crud.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data//ломбок аннотация: генерирует геттеры, сеттеры, икуалс, хеш код методы
@NoArgsConstructor//ломбок аннотация: конструктор без аргуметов
public class Role  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String role;


    public Role(String role) {
        this.role = role;
    }
}

