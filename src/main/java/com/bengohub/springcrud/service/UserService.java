package com.bengohub.springcrud.service;

import com.bengohub.springcrud.dto.UserDto;
import com.bengohub.springcrud.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void adduser(User user);

    List<User> getUsers();

    User getuser(Integer id);

    void updateUser(Integer id, User user);

    void delUser(Integer id);

    List<User> findByFilters(String name, Integer age, String address, Boolean deleted);

    List<User> findUserByMinAgeAddress(Integer minage, String address);

    void updatName(Integer id, UserDto userDto);
}
