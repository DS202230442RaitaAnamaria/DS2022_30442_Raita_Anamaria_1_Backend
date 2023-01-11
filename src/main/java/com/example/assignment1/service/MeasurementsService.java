package com.example.assignment1.service;

import com.example.assignment1.entity.Devices;
import com.example.assignment1.entity.Measurements;
import com.example.assignment1.entity.Person;
import com.example.assignment1.repository.IMeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeasurementsService {

    @Autowired
    DeviceService deviceService;

    @Autowired
    IMeasurementsRepository iMeasurementsRepository;

    public Measurements saveMeasurements(Measurements measurement, Integer id){
        Devices devices1=deviceService.getById(id);
        iMeasurementsRepository.save(measurement);
        if(devices1.getMeasurementsList()==null){
            List<Measurements> newMesList = new ArrayList<>();
            newMesList.add(measurement);
            devices1.setMeasurementsList(newMesList);
            measurement.setDevices(devices1);
        }
        else{
            List<Measurements> oldMesList=devices1.getMeasurementsList();
            oldMesList.add(measurement);
            devices1.setMeasurementsList(oldMesList);
            measurement.setDevices(devices1);
        }
        return iMeasurementsRepository.save(measurement);
    }
}
