package com.example.assignment1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Devices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddevices;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double mhec;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "peopleid")
    private Person person;

    @OneToMany(mappedBy = "devices",fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Measurements> measurementsList;

    public Person getPerson() {
        return person;
    }
}
