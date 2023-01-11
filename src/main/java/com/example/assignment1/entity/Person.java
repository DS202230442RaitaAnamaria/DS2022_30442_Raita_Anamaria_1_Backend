package com.example.assignment1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "devicesList"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpeople;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "person",fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Devices> devicesList;


    public Person() {
    }

    public Person(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Person(String username, String password, String role, List<Devices> devicesList) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.devicesList = devicesList;
    }

    public int getIdpeople() {
        return idpeople;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Devices> getDevicesList() {
        return devicesList;
    }

    public String getRole() {
        return role;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDevicesList(List<Devices> devicesList) {
        this.devicesList = devicesList;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
