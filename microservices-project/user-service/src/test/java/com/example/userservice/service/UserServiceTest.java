package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        userService.findAll();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setName("Test Name");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        verify(userRepository, times(1)).save(user);
        assertEquals("Test Name", savedUser.getName());
    }

    @Test
    public void testDelete() {
        Long userId = 1L;
        userService.delete(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
