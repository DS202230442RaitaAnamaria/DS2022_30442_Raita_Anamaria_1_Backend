package com.example.assignment1.controller;

import com.example.assignment1.entity.Devices;
import com.example.assignment1.entity.Measurements;
import com.example.assignment1.entity.Person;
import com.example.assignment1.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/devices")
public class DevicesController {

    @Autowired
    DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('admin')")
    public List<Devices> getAll() {
        return deviceService.getAll();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/id")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('admin')")
    public Devices getById(@RequestParam(name = "id") Integer id) {
        return deviceService.getById(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('admin')")
    public void saveDevice(@RequestBody Devices device,@RequestParam(name = "id") Integer id) throws IOException {
        deviceService.saveDevices(device,id);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('admin')")
    public void editDevices(@RequestBody Devices device){
        deviceService.editDevices(device);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/username")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user')")
    public List<Devices> getAllByUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return deviceService.getByUser(principal.getUsername());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chart")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user')")
    public List<Measurements> getForChart(@RequestParam(name = "id") Integer id,@RequestParam(name = "data") Date data) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        data.setYear(data.getYear()+1900);
        return deviceService.getForChart(principal.getUsername(),id,data);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('admin')")
    public void deleteDevices(@RequestParam (name="id") Integer id){
        deviceService.deleteDevice(id);
    }

}
