package com.avasthi.praakhya.learning.spring.controllers;

import com.avasthi.praakhya.learning.spring.entities.UserEntity;
import com.avasthi.praakhya.learning.spring.pojo.User;
import com.avasthi.praakhya.learning.spring.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserEndpoint {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> getUser(@PathVariable("id") Long id) {
        UserEntity ue = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return Optional.of(User.builder()
                        .id(ue.getId())
                        .email(ue.getEmail())
                        .fullname(ue.getFullname())
                        .password(ue.getPassword())
                        .username(ue.getUsername())
                .build()
        );
    }
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> getUser(@RequestBody User user) {
        UserEntity ue = new UserEntity();
        ue.setEmail(user.getEmail());
        ue.setUsername(user.getUsername());
        ue.setFullname(user.getFullname());
        ue.setPassword(user.getPassword());
        ue = userRepository.save(ue); //write entity to database and return new one
        return Optional.of(User.builder()
                .id(ue.getId())
                .email(ue.getEmail())
                .fullname(ue.getFullname())
                .password(ue.getPassword())
                .username(ue.getUsername())
                .build()
        );
    }
    /*
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> deleteUser(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        userRepository.deleteById(id);
        return optionalUser;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> patchUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User storedUser = optionalUser.get();
            if (user.getEmail() != null) {
                storedUser.setEmail(user.getEmail());
            }
            if (user.getFullname() != null) {
                storedUser.setFullname(user.getFullname());
            }
            if (user.getPassword() != null) {
                storedUser.setPassword(user.getPassword());
            }
            return Optional.of(userRepository.save(storedUser));
        }
        throw new EntityNotFoundException(String.format(String.format("User with id %d is not found", id)));
    }
     */
}
