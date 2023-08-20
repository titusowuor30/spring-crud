package com.bengohub.springcrud.controller;

import com.bengohub.springcrud.entity.User;
import com.bengohub.springcrud.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String AddUser(@RequestBody User user){
        userService.adduser(user);
        return "Success, user added successfully!";
    }

    @GetMapping("/list")
    public List<User> getUsers(){
        return  userService.getUsers();
    }

    @GetMapping("get/")
    public User getUser(@RequestParam Integer Id){
        return  userService.getuser(Id);
    }

    //filter methods
    @GetMapping("/by-minage-address")
    public List<User> getUserByminAgeAddress( @RequestParam(required = false) Integer minage,@RequestParam(required = false) String address){
        return userService.findUserByMinAgeAddress(minage,address);
    }

    @GetMapping("/filter")
    public List<User> filterUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Boolean deleted
    ) {
        return userService.findByFilters(name, age, address, deleted);
    }
    //end filters

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Integer id,@RequestBody User user){
        userService.updateUser(id,user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/del/{id}")
    public String deleteUser(@PathVariable Integer id){
        userService.delUser(id);
        return "User with id "+id+" deleted successfully!";
    }
}
