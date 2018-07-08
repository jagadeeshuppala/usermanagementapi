package com.spamcollector.repo;

import com.spamcollector.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class UserRepoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepo userRepo;

    @Test
    public void testCreateUser(){
        User newUser = new User("Jagadeesh", "Uppala", "uppala.jagadeesh@gmail.com");
        User savedUser = userRepo.save(newUser);

        assertNotNull("User should have been saved", savedUser);
    }


    @Test
    public void getAllUsers(){
        entityManager.persist(new User("Jagadeesh", "Uppala", "uppala.jagadeesh@gmail.com"));
        entityManager.persist(new User("Scott", "Richard", "scott.richard@gmail.com"));
        entityManager.persist(new User("philip", "lewis", "philip.lewis@gmail.com"));
        entityManager.flush();

        List<User> users = userRepo.findAll();
        Assert.assertEquals("The size should be 3", 3, users.size());
    }






}
