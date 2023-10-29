package com.example.guitar.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class User {
    @Id
    public Long id;
    public String username;
    public String email;
    public String password;
}
