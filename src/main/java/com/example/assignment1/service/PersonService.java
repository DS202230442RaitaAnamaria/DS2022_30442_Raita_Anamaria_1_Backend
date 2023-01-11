package com.example.assignment1.service;

import com.example.assignment1.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.assignment1.repository.IPersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    IPersonRepository iPersonRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public List<Person> getAll() {
        return (List<Person>) iPersonRepository.findAll();
    }
    public List<Person> getAllUsers() {
        return (List<Person>) iPersonRepository.findAll().stream().filter(o-> o.getRole().equals("client")).collect(Collectors.toList());
    }

    public Person getById(Integer id) {
        Optional<Person> user = iPersonRepository.findById(id);
        return user.orElse(null);
    }

    public Person savePerson(Person person){
        person.setPassword(bcryptEncoder.encode(person.getPassword()));
        return iPersonRepository.save(person);
    }

    public Person editPerson(Person person){
        Person p=this.getById(person.getIdpeople());
        p.setUsername(person.getUsername());
        if(!person.getPassword().equals(p.getPassword()))
        {
            p.setPassword(bcryptEncoder.encode(person.getPassword()));
        }
        return iPersonRepository.save(p);
    }
    public Person findByUsername(String username) {
        Optional<Person> user = iPersonRepository.findAll().stream().filter(o -> o.getUsername()
                .equals(username)).findFirst();
        return user.orElse(null);
    }
    public void deletePerson(Integer id){
        iPersonRepository.deleteById(id);
    }
}
