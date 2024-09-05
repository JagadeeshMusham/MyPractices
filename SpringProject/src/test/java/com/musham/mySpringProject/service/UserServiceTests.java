package com.musham.mySpringProject.service;

import com.musham.mySpringProject.entity.User;
import com.musham.mySpringProject.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeAll
    static void beforeAllMethods() {
        System.out.println("This is initialization method before running any test in that file, and it is one time executor");
    }

    @BeforeEach
    void beforeEachMethod() {
        System.out.println("This method will execute before running each test");
    }

    @AfterEach
    void afterEachMethod() {
        System.out.println("This method will execute after running each test");
    }

    @AfterAll
    static void afterAllMethods() {
        System.out.println("This is kind of destructor method which will run after executing all tests");
    }

//    @Disabled
    @Test
    public void testFindByUserName() {
        User user = userRepository.findByUserName("test");
        assertTrue(!user.getUserName().isEmpty());
    }

//    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 10, 12",
            "3, 3, 9"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

//    @Disabled
    @ParameterizedTest
    @CsvSource({
            "test",
            "test11",
            "test10"
    })
    public void testFindByUserNameWithCSVParameters(String userName) {
        assertNotNull(userRepository.findByUserName(userName),
                "Failed for user name: " + userName);
    }

//    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "test",
            "test11",
            "test10"
    })
    public void testFindByUserNameWithValueParameters(String userName) {
        assertNotNull(userRepository.findByUserName(userName),
                "Failed for user name: " + userName);
    }

//    @Disabled
    @ParameterizedTest
    @EnumSource(UserEnum.class)
    public void testFindByUserNameWithValueParameters(UserEnum userEnum) {
        assertNotNull(userRepository.findByUserName(userEnum.name()),
                "Failed for user name: " + userEnum.name());
    }


    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testFindByUserNameWithArguementSourceParameters(User user) {

        assertTrue((userService.saveEntry(user) != null),
                "Failed for user name: " + user.getUserName());
    }
}
