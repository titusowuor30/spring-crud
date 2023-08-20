package com.bengohub.springcrud.service.impl;
import com.bengohub.springcrud.entity.User;
import com.bengohub.springcrud.repository.UserRepository;
import com.bengohub.springcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void adduser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getuser(Integer id) {
        User user=userRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid User id "+id));
        return  user;
    }
    @Override
    public void updateUser(Integer id, User user) {
        //check if user exists or not
        userRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid User id "+id));
        //update user if exits
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void delUser(Integer id) {
        //check if user exists or not
        userRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid User id "+id));
        userRepository.deleteById(id);
    }

    /**
     * @param name
     * @param age
     * @param address
     * @param deleted
     * @return
     */
    @Override
    public List<User> findByFilters(String name, Integer age, String address, Boolean deleted) {
        return userRepository.findByFilters(name,age,address,deleted);
    }

    @Override
    public List<User> findUserByMinAgeAddress(Integer minage, String address) {
        return userRepository.findUsersByAgeAndAddress(minage,address);
    }

}
