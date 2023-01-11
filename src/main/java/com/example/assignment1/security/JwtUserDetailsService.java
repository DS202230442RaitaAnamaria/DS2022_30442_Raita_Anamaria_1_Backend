package com.example.assignment1.security;

import com.example.assignment1.entity.Person;
import com.example.assignment1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person person=personService.findByUsername(username);
        Set<GrantedAuthority> authorities =new HashSet<>();
        if (person == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else if (person.getRole().equals("client")) {
            authorities.add(new SimpleGrantedAuthority("user"));
            User user = new User(person.getUsername(), person.getPassword(),
                    authorities);
            return user;
        } else {
            authorities.add(new SimpleGrantedAuthority("admin"));
            User user = new User(person.getUsername(), person.getPassword(),
                    authorities);
            return user;
        }
    }
}