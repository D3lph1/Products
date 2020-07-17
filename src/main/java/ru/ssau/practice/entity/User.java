package ru.ssau.practice.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User extends Identifiable implements Serializable
{
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public User()
    {
    }

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }
}
