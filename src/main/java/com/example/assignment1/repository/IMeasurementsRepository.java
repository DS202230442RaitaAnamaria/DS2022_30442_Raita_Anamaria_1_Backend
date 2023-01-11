package com.example.assignment1.repository;

import com.example.assignment1.entity.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMeasurementsRepository extends JpaRepository<Measurements,Integer> {
}
