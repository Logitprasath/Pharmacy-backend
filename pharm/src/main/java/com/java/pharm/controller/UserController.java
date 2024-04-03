
package com.java.pharm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.pharm.model.User;
import com.java.pharm.service.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService us;

    @PostMapping("/post")
    public ResponseEntity<User> add(@RequestBody User u) {
        User newuser = us.create(u);
        return new ResponseEntity<>(newuser, HttpStatus.CREATED);
    }

    @GetMapping("/getdetail")
    public ResponseEntity<List<User>> show() {
        List<User> obj = us.getAlldetails();
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping("/api/user/{userId}")
    public ResponseEntity<User> putMethodName(@PathVariable("userId") int id, @RequestBody User employee) {
        if (us.updateDetails(id, employee) == true) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

   @DeleteMapping("/api/user/{userId}")
    public ResponseEntity<Boolean> delete(@PathVariable("userId") int id)
    {
        if(us.deleteUser(id) == true)
        {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }



}
