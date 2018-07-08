package com.spamcollector.service;

import com.spamcollector.model.User;
import com.spamcollector.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserService();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @Test
    public void testUserCreation(){
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(new User(1L,"Jagadeesh", "Uppala", "uppala.jagadeesh@gmail.com", "someimageurl"));

        User user = new User("Jagadeesh", "Uppala", "uppala.jagadeesh@gmail.com");
        User createdUser = userService.createOrUpdateUser(user);
        assertEquals("First name should be Jagadeesh", "Jagadeesh", createdUser.getFirstName());
        assertNotNull("Create date should not be null", createdUser.getDate());


    }

    @Test
    public void testGetUsers(){
       List<User> userList =  Arrays.asList(new User("Jagadeesh", "Uppala", "uppala.jagadeesh@gmail.com"),
                new User("Scott", "Richard", "scott.richard@gmail.com"),
                new User("philip", "lewis", "philip.lewis@gmail.com"));
        Mockito.when(userRepo.findAll()).thenReturn(userList);

        List<User> usersFromDB = userService.getAllUsers();
        assertEquals("The list should return 3 items", 3, usersFromDB.size());
    }



}
