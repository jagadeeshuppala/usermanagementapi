package com.spamcollector.controller;

import com.spamcollector.model.User;
import com.spamcollector.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testCreateUser() throws Exception{

        Mockito.when(userService.createOrUpdateUser(Mockito.any())).thenReturn(new User(1L,"Jagadeesh", "Uppala", "uppala.jagadeesh@gmail.com", "someimageurl"));
        mockMvc.perform(MockMvcRequestBuilders.post("/users").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"Jagadeesh\",\"lastName\":\"uppala\",\"email\":\"uppala.jagadeesh@gmail.com\"}"))
               // .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testCreateUserWithoutRequiredFields() throws Exception{

        Mockito.when(userService.createOrUpdateUser(Mockito.any())).thenReturn(new User(1L,"Jagadeesh", "Uppala", "uppala.jagadeesh@gmail.com", "someimageurl"));
        mockMvc.perform(MockMvcRequestBuilders.post("/users").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{}"))
                // .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }



    @Test
    public void testGetUsers() throws Exception{
        List<User> userList =  Arrays.asList(new User("Jagadeesh", "Uppala", "uppala.jagadeesh@gmail.com"),
                new User("Scott", "Richard", "scott.richard@gmail.com"),
                new User("philip", "lewis", "philip.lewis@gmail.com"));

        Mockito.when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users").
                contentType(MediaType.APPLICATION_JSON_VALUE))
                // .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
