package com.example.AopExample.Service;

import com.example.AopExample.entity.User;
import com.example.AopExample.repo.UserRepo;
import com.example.AopExample.service.UserService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AopServiceTest {
    @Mock
    private UserRepo repo;

    @InjectMocks
    private UserService service;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Anu");
    }

    @Test
    public void testSaveUser() {
        when(repo.save(any(User.class))).thenReturn(user);

        User savedUser = service.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("Anu", savedUser.getName());
        verify(repo, times(1)).save(user);
    }

    @Test
    public void testGetUser() {
        User user2 = new User();
        user2.setName("Jan");

        List<User> userList = Arrays.asList(user, user2);
        when(repo.findAll()).thenReturn(userList);

        List<User> result = service.getUser();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repo, times(1)).findAll();
    }

}
