package com.spamcollector.service;

import com.spamcollector.exception.UserNotFoundException;
import com.spamcollector.model.User;
import com.spamcollector.repo.UserRepo;
import org.hibernate.internal.ExceptionMapperStandardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    //create or update User
    public User createOrUpdateUser(User user){
        return userRepo.save(user);
    }


    // get User
    public User getUser(Long id){
        Optional<User> offer = userRepo.findById(id);
        if(offer.isPresent()){
            return userRepo.findById(id).get();
        }else{
            throw new UserNotFoundException(" User with "+ id + " does not exist");
        }

    }
    // Get all Users
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

}
