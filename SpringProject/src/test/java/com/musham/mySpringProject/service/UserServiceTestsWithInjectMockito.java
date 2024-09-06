package com.musham.mySpringProject.service;

import com.musham.mySpringProject.entity.User;
import com.musham.mySpringProject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UserServiceTestsWithInjectMockito {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testFindByUserName() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(
                User.builder().userName("test").password("pwd").build());

        User user = userService.findByUserName("test");
        assertNotNull(user);
    }
}
