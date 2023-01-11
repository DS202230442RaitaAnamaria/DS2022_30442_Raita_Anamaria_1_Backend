package com.example.assignment1.controller;

import com.example.assignment1.entity.Person;
import com.example.assignment1.security.JwtRequest;
import com.example.assignment1.security.JwtResponse;
import com.example.assignment1.security.JwtTokenUtil;
import com.example.assignment1.security.JwtUserDetailsService;
import com.example.assignment1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    @RequestMapping(value = "/getAuthority", method = RequestMethod.POST)
    public ResponseEntity<?>  getAuthority(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        return ResponseEntity.ok(userDetails.getAuthorities());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('admin')")
    public List<Person> getAll() {
        return personService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/alluser")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('admin')")
    public List<Person> getAllUser() {
        return personService.getAllUsers();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/id")
    @ResponseBody
    public Person getById(@RequestParam(name = "id") Integer id) {
        return personService.getById(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('admin')")
    public void savePerson(@RequestBody Person person) throws IOException {
        personService.savePerson(person);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('admin')")
    public void editPerson(@RequestBody Person person) throws IOException {
        personService.editPerson(person);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('admin')")
    public void deletePerson(@RequestParam (name="id") Integer id){
          personService.deletePerson(id);
    }

}
