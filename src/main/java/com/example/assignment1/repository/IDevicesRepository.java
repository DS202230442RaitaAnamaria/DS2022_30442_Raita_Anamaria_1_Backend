package com.example.assignment1.repository;

import com.example.assignment1.entity.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDevicesRepository extends JpaRepository<Devices,Integer> {

}
