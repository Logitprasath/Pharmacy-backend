
package com.java.pharm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.pharm.model.Product;
import com.java.pharm.model.User;
import com.java.pharm.repository.ProductRepo;
import com.java.pharm.service.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService us;
    @Autowired
    private ProductRepo productRepo;

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

    @GetMapping("/user/by-username")
    public ResponseEntity<Page<User>> findByUsername(@RequestParam String username, Pageable pageable) {
        Page<User> users = us.findByUsername(username, pageable);
        return ResponseEntity.ok(users);
    }
    @PostMapping("/user/{userId}/product")
    public ResponseEntity<Product> addProductToIvtmg(@PathVariable Integer userId, @RequestBody Product product) {
        User user = us.getUserById(userId);
        
        if (user != null) {
            product.setId(userId);
            Product addedProduct = productRepo.save(product); // Save product directly
            
            if (addedProduct != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
            } 
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
