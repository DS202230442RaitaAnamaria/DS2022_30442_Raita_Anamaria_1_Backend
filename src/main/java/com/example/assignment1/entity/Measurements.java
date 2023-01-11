package com.example.assignment1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "measurements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "devices"})
public class Measurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmeasurements;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timest;

    @Column(nullable = false)
    private Double encons;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deviceid")
    private Devices devices;

    public Measurements(Date timest, Double encons, Devices devices) {
        this.timest = timest;
        this.encons = encons;
        this.devices = devices;
    }

    public Measurements(Date timest, Double encons) {
        this.timest = timest;
        this.encons = encons;
    }
}
