package com.example.assignment1.service;


import com.example.assignment1.entity.Devices;
import com.example.assignment1.entity.Measurements;
import com.example.assignment1.entity.Person;
import com.example.assignment1.repository.IDevicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Autowired
    IDevicesRepository iDevicesRepository;

    @Autowired
    PersonService personService;

    public List<Devices> getAll() {
        return (List<Devices>) iDevicesRepository.findAll();
    }

    public Devices getById(Integer id) {
        Optional<Devices> devices = iDevicesRepository.findById(id);
        return devices.orElse(null);
    }

    public Devices saveDevices(Devices devices,Integer id){
        Person person=personService.getById(id);
        if(person.getDevicesList()==null){
            List<Devices> newDevlist = new ArrayList<>();
            newDevlist.add(devices);
            person.setDevicesList(newDevlist);
            devices.setPerson(person);
        }
        else{
            List<Devices> oldDevList=person.getDevicesList();
            oldDevList.add(devices);
            person.setDevicesList(oldDevList);
            devices.setPerson(person);
        }
        return iDevicesRepository.save(devices);
    }
    public Devices editDevices(Devices devices){
        Devices d=this.getById(devices.getIddevices());
        d.setDescription(devices.getDescription());
        d.setAddress(devices.getAddress());
        d.setMhec(devices.getMhec());
        return iDevicesRepository.save(d);
    }
    public void deleteDevice(Integer id){
        iDevicesRepository.deleteById(id);
    }

    public List<Devices> getByUser(String username){
       return (List<Devices>) iDevicesRepository.findAll().stream().filter(o->o.getPerson().getUsername().equals(username)).collect(Collectors.toList());
    }

    public List<Measurements> getForChart(String username, Integer deviceid, Date date){
        Devices devices=this.getById(deviceid);

        List<Measurements> measurementsList=new ArrayList<>();
        List<Measurements> old=devices.getMeasurementsList();
        if(devices!=null && devices.getPerson().getUsername().equals(username)){
            for (Measurements measurement:
                 old) {
                if(date.getYear()==measurement.getTimest().getYear()+1900 && date.getMonth()==measurement.getTimest().getMonth()
                && date.getDate()==measurement.getTimest().getDate())
                {
                    measurementsList.add(measurement);
                }
            }
        return measurementsList;
        }
        else
            return null;
    }

}
