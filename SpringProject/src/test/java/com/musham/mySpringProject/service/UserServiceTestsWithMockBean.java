package com.musham.mySpringProject.service;

import com.musham.mySpringProject.entity.User;
import com.musham.mySpringProject.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("dev")
@SpringBootTest
public class UserServiceTestsWithMockBean {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testFindByUserName() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(
                User.builder().userName("test").password("pwd").build());
        User user = userRepository.findByUserName("test");
        assertNotNull(user);
    }
}
