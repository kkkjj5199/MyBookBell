package com.project.bookbell.config;

import com.project.bookbell.domain.UserAccount;
import com.project.bookbell.repository.UserRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


@Import(SecurityConfig.class)
public class TestSecurityConfig {
    @MockBean private UserRepository userRepository;

//    @BeforeTestMethod
//    public void securitySetUp() {
//        given(userRepository.findById(anyString())).willReturn(Optional.of(UserAccount.user(
//                "김유진",
//                "1111",
//                "010-4906-7420",
//                "안양시",
//                "kkkjj5100@naver.com"
//                        )
//
//        ));
//    }
}
