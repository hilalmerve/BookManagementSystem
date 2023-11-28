package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="users", uniqueConstraints = { 
          @UniqueConstraint(columnNames = "name")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @NotBlank
    @Column(name="name")
    private String name;

    @Column(name = "email")
    private String email;

    // private String address;

    @NotBlank
    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user")
    private Borrowed borrowed;

    
}

