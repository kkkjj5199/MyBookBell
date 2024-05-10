package com.project.bookbell.service;

import com.project.bookbell.domain.Books;
import com.project.bookbell.domain.UserAccount;
import com.project.bookbell.dto.BooksDto;
import com.project.bookbell.dto.UserAccountDto;
import com.project.bookbell.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.PortMapperDsl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserAccountService extends DefaultOAuth2UserService {

    @Autowired
    private final UserRepository userRepository;




    public UserAccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Map<String, Object> attributes = super.loadUser(userRequest).getAttributes();
        log.info("ATTR INFO : {}",attributes.toString());

        OAuth2User user2 = super.loadUser(userRequest);

        String email = attributes.get("email").toString();
        String name = attributes.get("nickname").toString();

        if(userRepository.findByEmail(email) == null) {
            log.info("{}({}) NOT EXISTS. REGISTER" , email);
            UserAccount regist = new UserAccount();
            regist.registUser(email,name);



        }

        return super.loadUser(userRequest);

    }

    public Long join(UserAccount user){
        userRepository.save(user);
        return user.getId();
    }


}
