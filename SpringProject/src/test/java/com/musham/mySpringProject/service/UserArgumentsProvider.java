package com.musham.mySpringProject.service;

import com.musham.mySpringProject.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("ammu").password("ammu").build()),
                Arguments.of(User.builder().userName("lucky").password("lucky").build()),
                Arguments.of(User.builder().userName("krish").password("krish").build()),
                Arguments.of(User.builder().userName("testEMptyPwd").password("").build())
        );
    }
}
