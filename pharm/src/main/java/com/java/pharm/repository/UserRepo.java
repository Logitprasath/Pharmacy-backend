package com.java.pharm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.pharm.model.User;

@Repository

public interface UserRepo extends JpaRepository<User,Integer>{
    
}